package xyz.apex.minecraft.fantasyfurniture.common.block.entity.component;

import com.google.common.collect.Lists;
import com.google.errorprone.annotations.DoNotCall;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseContainerBlockEntityComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.FurnaceBlockComponent;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

public final class FurnaceBlockEntityComponent extends BaseContainerBlockEntityComponent<FurnaceBlockEntityComponent> implements RecipeCraftingHolder, StackedContentsCompatible
{
    public static final BlockEntityComponentType<FurnaceBlockEntityComponent> COMPONENT_TYPE = BlockEntityComponentType.register(FantasyFurniture.ID, "furnace/smelting", FurnaceBlockEntityComponent::new);

    public static final int SLOT_INPUT = AbstractFurnaceBlockEntity.SLOT_INPUT;
    public static final int SLOT_FUEL = AbstractFurnaceBlockEntity.SLOT_FUEL;
    public static final int SLOT_RESULT = AbstractFurnaceBlockEntity.SLOT_RESULT;
    public static final int DATA_LIT_TIME = AbstractFurnaceBlockEntity.DATA_LIT_TIME;
    public static final int[] SLOTS_FOR_UP = AbstractFurnaceBlockEntity.SLOTS_FOR_UP;
    public static final int[] SLOTS_FOR_DOWN = AbstractFurnaceBlockEntity.SLOTS_FOR_DOWN;
    public static final int[] SLOTS_FOR_SIDES = AbstractFurnaceBlockEntity.SLOTS_FOR_SIDES;
    public static final int DATA_LIT_DURATION = AbstractFurnaceBlockEntity.DATA_LIT_DURATION;
    public static final int DATA_COOKING_PROGRESS = AbstractFurnaceBlockEntity.DATA_COOKING_PROGRESS;
    public static final int DATA_COOKING_TOTAL_TIME = AbstractFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME;
    public static final int NUM_DATA_VALUES = AbstractFurnaceBlockEntity.NUM_DATA_VALUES;
    public static final int BURN_TIME_STANDARD = AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;
    public static final int BURN_COOL_SPEED = AbstractFurnaceBlockEntity.BURN_COOL_SPEED;

    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;
    private ItemStack lastInput = ItemStack.EMPTY;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    @Nullable private RecipeBookType recipeBookType;
    @Nullable private RecipeType<? extends AbstractCookingRecipe> recipeType;
    @Nullable private RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;
    private ToIntBiFunction<ItemStack, Integer> burnDurationModifier = (stack, burnDuration) -> burnDuration;
    private Supplier<Supplier<AbstractFurnaceRecipeBookComponent>> recipeBookComponent = () -> SmeltingRecipeBookComponent::new;
    private ResourceLocation menuTexture = new ResourceLocation("textures/gui/container/furnace.png");
    private ResourceLocation litProgressSprite = new ResourceLocation("container/furnace/lit_progress");
    private ResourceLocation burnProgressSprite = new ResourceLocation("container/furnace/burn_progress");

    private FurnaceBlockEntityComponent(BlockEntityComponentHolder componentHolder)
    {
        super(componentHolder);

        super.withSlotCount(3);
    }

    public FurnaceBlockEntityComponent withRecipeType(RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookType recipeBookType)
    {
        Validate.isTrue(!isRegistered(), "Can only set RecipeType during registration");

        this.recipeType = recipeType;
        this.recipeBookType = recipeBookType;
        quickCheck = RecipeManager.createCheck(recipeType);

        return this;
    }

    public FurnaceBlockEntityComponent withBurnDurationModifier(ToIntBiFunction<ItemStack, Integer> burnDurationModifier)
    {
        Validate.isTrue(!isRegistered(), "Can only set burn duration modifier during registration");
        this.burnDurationModifier = burnDurationModifier;
        return this;
    }

    public FurnaceBlockEntityComponent withMenuProperties(ResourceLocation menuTexture, ResourceLocation litProgressSprite, ResourceLocation burnProgressSprite, Supplier<Supplier<AbstractFurnaceRecipeBookComponent>> recipeBookComponent)
    {
        Validate.isTrue(!isRegistered(), "Can only set menu properties during registration");

        this.menuTexture = menuTexture;
        this.litProgressSprite = litProgressSprite;
        this.burnProgressSprite = burnProgressSprite;
        this.recipeBookComponent = recipeBookComponent;

        return this;
    }

    public ResourceLocation getMenuTexture()
    {
        return menuTexture;
    }

    public ResourceLocation getLitProgressSprite()
    {
        return litProgressSprite;
    }

    public ResourceLocation getBurnProgressSprite()
    {
        return burnProgressSprite;
    }

    @SideOnly(PhysicalSide.CLIENT)
    public AbstractFurnaceRecipeBookComponent getRecipeBookComponent()
    {
        return PhysicalSide.CLIENT.callWhenOn(recipeBookComponent).orElseThrow();
    }

    public boolean isLit()
    {
        return litTime > 0;
    }

    public RecipeType<? extends AbstractCookingRecipe> getRecipeType()
    {
        return Objects.requireNonNull(recipeType);
    }

    public RecipeBookType getRecipeBookType()
    {
        return Objects.requireNonNull(recipeBookType);
    }

    private RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> getQuickCheck()
    {
        return Objects.requireNonNull(quickCheck);
    }

    public ItemStack getInput()
    {
        return getItem(SLOT_INPUT);
    }

    public ItemStack getFuel()
    {
        return getItem(SLOT_FUEL);
    }

    public ItemStack getResult()
    {
        return getItem(SLOT_RESULT);
    }

    public int getBurnDuration(ItemStack fuel)
    {
        if(fuel.isEmpty())
            return 0;

        var burnDuration = AbstractFurnaceBlockEntity.getFuel().getOrDefault(fuel.getItem(), 0);

        if(burnDuration == 0)
            return 0;

        return burnDurationModifier.applyAsInt(fuel, burnDuration);
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec)
    {
        var recipes = Lists.<RecipeHolder<?>>newArrayList();

        for(var entry : recipesUsed.object2IntEntrySet())
        {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                if(recipe.value() instanceof AbstractCookingRecipe cookingRecipe)
                {
                    recipes.add(recipe);
                    AbstractFurnaceBlockEntity.createExperience(level, popVec, entry.getIntValue(), cookingRecipe.getExperience());
                }
            });
        }

        return recipes;
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player)
    {
        var recipes = getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        player.awardRecipes(recipes);
        recipes.forEach(recipe -> player.triggerRecipeCrafted(recipe, getItems()));
        recipesUsed.clear();
    }

    @Override
    protected void onRemoved(Level level, BlockState newBlockState)
    {
        if(level instanceof ServerLevel sLevel)
            getRecipesToAwardAndPopExperience(sLevel, getGameObject().getBlockPos().getCenter());
    }

    @Override
    public void fillStackedContents(StackedContents contents)
    {
        forEach(contents::accountStack);
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> items)
    {
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed()
    {
        return null;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe)
    {
        if(recipe != null && recipe.value() instanceof AbstractCookingRecipe)
            recipesUsed.addTo(recipe.id(), 1);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack)
    {
        if(index == SLOT_RESULT)
            return false;
        else if(index != SLOT_FUEL)
            return true;
        else
        {
            var fuel = getFuel();
            return AbstractFurnaceBlockEntity.isFuel(stack) || stack.is(Items.BUCKET) && !fuel.is(Items.BUCKET);
        }
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side)
    {
        if(side == Direction.DOWN && slot == SLOT_FUEL)
            return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);

        return true;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side)
    {
        return canPlaceItem(slot, stack);
    }

    @Override
    public int[] getSlotsForFace(Direction side)
    {
        return switch(side) {
            // idk why but this wants up/down to be inverted?
            case UP -> SLOTS_FOR_DOWN;
            case DOWN -> SLOTS_FOR_UP;
            default -> SLOTS_FOR_SIDES;
        };
    }

    @Override
    public void set(int index, int value)
    {
        switch(index) {
            case DATA_LIT_TIME -> litTime = value;
            case DATA_LIT_DURATION -> litDuration = value;
            case DATA_COOKING_PROGRESS -> cookingProgress = value;
            case DATA_COOKING_TOTAL_TIME -> cookingTotalTime = value;
        }
    }

    @Override
    public int get(int index)
    {
        return switch(index) {
            case DATA_LIT_TIME -> litTime;
            case DATA_LIT_DURATION -> litDuration;
            case DATA_COOKING_PROGRESS -> cookingProgress;
            case DATA_COOKING_TOTAL_TIME -> cookingTotalTime;
            default -> 0;
        };
    }

    @Override
    public void deserializeFrom(CompoundTag tag, boolean fromNetwork)
    {
        super.deserializeFrom(tag, fromNetwork);

        litTime = tag.getShort("BurnTime");
        cookingProgress = tag.getShort("CookTime");
        cookingTotalTime = tag.getShort("CookTimeTotal");
        litDuration = getBurnDuration(getFuel());

        recipesUsed.clear();
        var recipesUsedTag = tag.getCompound("RecipesUsed");
        recipesUsedTag.getAllKeys().forEach(key -> recipesUsed.put(new ResourceLocation(key), recipesUsedTag.getInt(key)));
    }

    @Override
    public void serializeInto(CompoundTag tag, boolean forNetwork)
    {
        super.serializeInto(tag, forNetwork);

        tag.putShort("BurnTime", (short) litTime);
        tag.putShort("CookTime", (short) cookingProgress);
        tag.putShort("CookTimeTotal", (short) cookingTotalTime);

        var recipesUsedTag = new CompoundTag();
        recipesUsed.forEach((recipeId, count) -> recipesUsedTag.putInt(recipeId.toString(), count));
        tag.put("RecipesUsed", recipesUsedTag);
    }

    @Override
    public int getCount()
    {
        return NUM_DATA_VALUES;
    }

    @DoNotCall
    @Override
    public FurnaceBlockEntityComponent withSlotCount(int slotCount)
    {
        return this;
    }

    public static <T extends BlockEntity & BlockEntityComponentHolder> void serverTick(Level level, BlockPos pos, BlockState blockState, T blockEntity)
    {
        var furnace = blockEntity.getRequiredComponent(COMPONENT_TYPE);

        var currentInput = furnace.getInput();
        var changed = false;

        if(!ItemStack.isSameItemSameTags(furnace.lastInput, currentInput))
        {
            furnace.cookingTotalTime = getTotalCookTime(level, furnace);
            furnace.cookingProgress = 0;
            furnace.lastInput = currentInput.copy();
            changed = true;
        }

        var isLit = furnace.isLit();

        if(furnace.isLit())
            furnace.litTime--;

        var fuel = furnace.getFuel();
        var hasInput = !furnace.getInput().isEmpty();
        var hasFuel = !fuel.isEmpty();
        var items = furnace.getItems();

        if(furnace.isLit() || hasFuel && hasInput)
        {
            var recipe = hasInput ? furnace.getQuickCheck().getRecipeFor(furnace, level).orElse(null) : null;
            var maxStackSize = furnace.getMaxStackSize();

            if(!furnace.isLit() && FantasyFurniture.INSTANCE.canBurn(furnace, level.registryAccess(), recipe, items, maxStackSize))
            {
                furnace.litTime = furnace.getBurnDuration(fuel);
                furnace.litDuration = furnace.litTime;

                if(furnace.isLit())
                {
                    changed = true;

                    if(hasFuel)
                    {
                        var item = fuel.getItem();
                        fuel.shrink(1);

                        if(fuel.isEmpty())
                        {
                            var item2 = item.getCraftingRemainingItem();
                            furnace.setItem(SLOT_FUEL, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
                        }
                    }
                }
            }

            if(furnace.isLit() && FantasyFurniture.INSTANCE.canBurn(furnace, level.registryAccess(), recipe, items, maxStackSize))
            {
                furnace.cookingProgress++;

                if(furnace.cookingProgress == furnace.cookingTotalTime)
                {
                    furnace.cookingProgress = 0;
                    furnace.cookingTotalTime = getTotalCookTime(level, furnace);

                    if(FantasyFurniture.INSTANCE.burn(furnace, level.registryAccess(), recipe, items, maxStackSize))
                        furnace.setRecipeUsed(recipe);

                    changed = true;
                }
            }
            else
                furnace.cookingProgress = 0;
        }
        else if(!furnace.isLit() && furnace.cookingProgress > 0)
            furnace.cookingProgress = Mth.clamp(furnace.cookingProgress - 2, 0, furnace.cookingTotalTime);

        if(isLit != furnace.isLit())
        {
            changed = true;
            blockState = blockState.setValue(FurnaceBlockComponent.LIT, furnace.isLit());
            level.setBlock(pos, blockState, Block.UPDATE_ALL);
        }

        if(changed)
            furnace.setChanged();
    }

    private static int getTotalCookTime(Level level, FurnaceBlockEntityComponent component)
    {
        return component.getQuickCheck().getRecipeFor(component, level).map(RecipeHolder::value).map(AbstractCookingRecipe::getCookingTime).orElse(BURN_TIME_STANDARD);
    }
}
