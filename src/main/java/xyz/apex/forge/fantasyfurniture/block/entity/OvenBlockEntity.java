package xyz.apex.forge.fantasyfurniture.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

import java.util.List;

public final class OvenBlockEntity extends InventoryBlockEntity implements RecipeHolder, StackedContentsCompatible, ContainerData
{
	public static final Lazy<RecipeType<? extends AbstractCookingRecipe>> RECIPE_TYPE = Lazy.of(() -> RecipeType.SMOKING);
	public static final Lazy<RecipeBookType> RECIPE_BOOK_TYPE = Lazy.of(() -> RecipeBookType.SMOKER);
	public static final int DEFAULT_COOK_TIME = AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;
	public static final String NBT_LIT_TIME = "BurnTime";
	public static final String NBT_COOKING_PROGRESS = "CookTime";
	public static final String NBT_COOKING_TIME_TOTAL = "CookTimeTotal";
	public static final String NBT_RECIPES_USED = "RecipesUsed";

	private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;
	private final Lazy<RecipeWrapperContainer> containerWrapper;
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	private int litTime;
	private int litDuration;
	private int cookingProgress;
	private int cookingTotalTime;

	public OvenBlockEntity(BlockEntityType<? extends OvenBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, OvenMenu.SLOT_COUNT);

		quickCheck = RecipeManager.createCheck(RECIPE_TYPE.get());
		containerWrapper = Lazy.of(() -> new RecipeWrapperContainer(this));
	}

	@Override
	public void setRecipeUsed(@Nullable Recipe<?> recipe)
	{
		if(recipe != null)
			recipesUsed.addTo(recipe.getId(), 1);
	}

	@Nullable
	@Override
	public Recipe<?> getRecipeUsed()
	{
		return null;
	}

	@Override
	public void fillStackedContents(StackedContents contents)
	{
		for(var i = 0; i < itemHandler.getSlots(); i++)
		{
			contents.accountStack(itemHandler.getStackInSlot(i));
		}
	}

	@Override
	public void awardUsedRecipes(Player player)
	{
	}

	@Override
	public CompoundTag serializeData()
	{
		var tagCompound = super.serializeData();

		tagCompound.putInt(NBT_LIT_TIME, litTime);
		tagCompound.putInt(NBT_COOKING_PROGRESS, cookingProgress);
		tagCompound.putInt(NBT_COOKING_TIME_TOTAL, cookingTotalTime);

		var recipesUsedTag = new CompoundTag();
		recipesUsed.forEach((recipeId, count) -> recipesUsedTag.putInt(recipeId.toString(), count));
		tagCompound.put(NBT_RECIPES_USED, recipesUsedTag);

		return tagCompound;
	}

	@Override
	public void deserializeData(CompoundTag tagCompound)
	{
		super.deserializeData(tagCompound);

		litTime = tagCompound.getInt(NBT_LIT_TIME);
		cookingProgress = tagCompound.getInt(NBT_COOKING_PROGRESS);
		cookingTotalTime = tagCompound.getInt(NBT_COOKING_TIME_TOTAL);
		litDuration = getBurnDuration(itemHandler.getStackInSlot(OvenMenu.SLOT_FUEL));

		var recipesUsedTag = tagCompound.getCompound(NBT_RECIPES_USED);
		recipesUsedTag.getAllKeys().forEach(key -> recipesUsed.put(new ResourceLocation(key), recipesUsedTag.getInt(key)));
	}

	@Override
	public int get(int index)
	{
		return switch(index) {
			case OvenMenu.DATA_SLOT_LIT_TIME -> litTime;
			case OvenMenu.DATA_SLOT_LIT_DURATION -> litDuration;
			case OvenMenu.DATA_SLOT_LIT_COOKING_PROGRESS -> cookingProgress;
			case OvenMenu.DATA_SLOT_LIT_COOKING_TOTAL_TIME -> cookingTotalTime;
			default -> 0;
		};
	}

	@Override
	public void set(int index, int value)
	{
		switch(index)
		{
			case OvenMenu.DATA_SLOT_LIT_TIME -> litTime = value;
			case OvenMenu.DATA_SLOT_LIT_DURATION -> litDuration = value;
			case OvenMenu.DATA_SLOT_LIT_COOKING_PROGRESS -> cookingProgress = value;
			case OvenMenu.DATA_SLOT_LIT_COOKING_TOTAL_TIME -> cookingTotalTime = value;
		}
	}

	@Override
	public int getCount()
	{
		return OvenMenu.DATA_SLOT_COUNT;
	}

	public Container getRecipeContainerWrapper()
	{
		return containerWrapper.get();
	}

	private void awardUsedRecipesAndPopExperience(ServerPlayer player)
	{
		var list = getRecipesToAwardAndPopExperience(player.getLevel(), player.position());
		player.awardRecipes(list);
		recipesUsed.clear();
	}

	public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 position)
	{
		var result = Lists.<Recipe<?>>newArrayList();

		for(var entry : recipesUsed.object2IntEntrySet())
		{
			level.getRecipeManager().byKey(entry.getKey()).map(AbstractCookingRecipe.class::cast).ifPresent(recipe -> {
				result.add(recipe);
				createExperience(level, position, entry.getIntValue(), recipe.getExperience());
			});
		}

		return result;
	}

	private boolean isLit()
	{
		return litTime > 0;
	}

	private boolean canBurn(@Nullable AbstractCookingRecipe recipe, int amount)
	{
		if(!itemHandler.getStackInSlot(OvenMenu.SLOT_INPUT).isEmpty() && recipe != null)
		{
			var result = recipe.assemble(containerWrapper.get());

			if(result.isEmpty())
				return false;
			else
			{
				var output = itemHandler.getStackInSlot(OvenMenu.SLOT_OUTPUT);

				if(output.isEmpty())
					return true;
				else if(!output.sameItem(result))
					return false;
				// Forge fix: make furnace respect stack sizes in furnace recipes
				else if(output.getCount() + result.getCount() <= amount && output.getCount() + result.getCount() <= output.getMaxStackSize())
					return true;
				else
					return output.getCount() + result.getCount() <= result.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
			}
		}
		else
			return false;
	}

	private boolean burn(@Nullable AbstractCookingRecipe recipe, int amount)
	{
		if(recipe != null && canBurn(recipe, amount))
		{
			var input = itemHandler.getStackInSlot(OvenMenu.SLOT_INPUT);
			var result = recipe.assemble(containerWrapper.get());
			var output = itemHandler.getStackInSlot(OvenMenu.SLOT_OUTPUT);

			if(output.isEmpty())
				itemHandler.setStackInSlot(OvenMenu.SLOT_OUTPUT, result.copy());
			else if(output.is(result.getItem()))
				output.grow(result.getCount());

			input.shrink(1);
			return true;
		}
		else
			return false;
	}

	public static void tick(Level level, BlockPos pos, BlockState blockState, OvenBlockEntity blockEntity)
	{
		boolean flag = blockEntity.isLit();
		boolean flag1 = false;

		if(blockEntity.isLit())
			--blockEntity.litTime;

		ItemStack itemstack = blockEntity.itemHandler.getStackInSlot(OvenMenu.SLOT_FUEL);
		boolean flag2 = !blockEntity.itemHandler.getStackInSlot(OvenMenu.SLOT_INPUT).isEmpty();
		boolean flag3 = !itemstack.isEmpty();

		if(blockEntity.isLit() || flag3 && flag2)
		{
			AbstractCookingRecipe recipe;

			if(flag2)
				recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.containerWrapper.get(), level).orElse(null);
			else
				recipe = null;

			//int i = p_155017_.getMaxStackSize();
			int i = 64;

			if(!blockEntity.isLit() && blockEntity.canBurn(recipe, i))
			{
				blockEntity.litTime = blockEntity.getBurnDuration(itemstack);
				blockEntity.litDuration = blockEntity.litTime;

				if(blockEntity.isLit())
				{
					flag1 = true;

					if(itemstack.hasCraftingRemainingItem())
						blockEntity.itemHandler.setStackInSlot(OvenMenu.SLOT_FUEL, itemstack.getCraftingRemainingItem());
					else if(flag3)
					{
						itemstack.shrink(1);
						if(itemstack.isEmpty())
							blockEntity.itemHandler.setStackInSlot(OvenMenu.SLOT_FUEL, itemstack.getCraftingRemainingItem());
					}
				}
			}

			if(blockEntity.isLit() && blockEntity.canBurn(recipe, i))
			{
				++blockEntity.cookingProgress;

				if(blockEntity.cookingProgress == blockEntity.cookingTotalTime)
				{
					blockEntity.cookingProgress = 0;
					blockEntity.cookingTotalTime = getTotalCookTime(level, blockEntity);

					if(blockEntity.burn(recipe, i))
						blockEntity.setRecipeUsed(recipe);

					flag1 = true;
				}
			}
			else
				blockEntity.cookingProgress = 0;
		}
		else if(!blockEntity.isLit() && blockEntity.cookingProgress > 0)
			blockEntity.cookingProgress = Mth.clamp(blockEntity.cookingProgress - 2, 0, blockEntity.cookingTotalTime);

		if(flag != blockEntity.isLit())
		{
			flag1 = true;
			blockState = blockState.setValue(AbstractFurnaceBlock.LIT, blockEntity.isLit());
			// p_155014_.setBlock(p_155015_, p_155016_, 3);
		}

		if(flag1)
			setChanged(level, pos, blockState);
	}

	public static boolean isValidFuel(ItemStack stack)
	{
		return getBurnDuration(stack) > 0;
	}

	public static int getBurnDuration(ItemStack stack)
	{
		return stack.is(ItemTags.Vanilla.NON_FLAMMABLE_WOOD) ? 0 : ForgeHooks.getBurnTime(stack, RECIPE_TYPE.get());
	}

	public static int getTotalCookTime(Level level, OvenBlockEntity blockEntity)
	{
		return blockEntity.quickCheck.getRecipeFor(blockEntity.containerWrapper.get(), level).map(AbstractCookingRecipe::getCookingTime).orElse(DEFAULT_COOK_TIME);
	}

	public static void createExperience(ServerLevel level, Vec3 pos, int useCount, float experience)
	{
		var i = Mth.floor((float) useCount * experience);
		var f = Mth.frac((float) useCount * experience);

		if(f != 0F && Math.random() < (double) f)
			i++;

		ExperienceOrb.award(level, pos, i);
	}

	public static final class RecipeWrapperContainer extends RecipeWrapper
	{
		private final OvenBlockEntity blockEntity;

		public RecipeWrapperContainer(OvenBlockEntity blockEntity)
		{
			super((IItemHandlerModifiable) blockEntity.getItemHandler());

			this.blockEntity = blockEntity;
		}

		public void awardUsedRecipesAndPopExperience(ServerPlayer player)
		{
			blockEntity.awardUsedRecipesAndPopExperience(player);
		}
	}
}