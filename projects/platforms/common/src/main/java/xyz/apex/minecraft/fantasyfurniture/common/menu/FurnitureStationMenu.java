package xyz.apex.minecraft.fantasyfurniture.common.menu;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Runnables;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import xyz.apex.minecraft.apexcore.common.lib.menu.EnhancedSlot;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.List;

public final class FurnitureStationMenu extends AbstractContainerMenu
{
    public static final int SLOT_INGREDIENT_A = 0;
    public static final int SLOT_INGREDIENT_B = 1;
    public static final int SLOT_BINDING_AGENT = 2;
    public static final int SLOT_COUNT = 3;

    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final ContainerLevelAccess levelAccess;
    private final Level level;
    private final List<RecipeHolder<FurnitureStationRecipe>> recipes = Lists.newArrayList();
    private ItemStack inputA = ItemStack.EMPTY;
    private ItemStack inputB = ItemStack.EMPTY;
    private ItemStack bindingAgent = ItemStack.EMPTY;
    private long lastSoundTime = 0L;
    private final Slot inputSlotA;
    private final Slot inputSlotB;
    private final Slot bindingAgentSlot;
    private final Slot recipeSlot;
    private Runnable slotUpdateListener = Runnables.doNothing();
    private final InputContainer inputContainer = new InputContainer();
    private final ResultContainer resultContainer = new ResultContainer();

    public FurnitureStationMenu(MenuType<? extends FurnitureStationMenu> menuType, int syncId, Inventory inventory, ContainerLevelAccess levelAccess)
    {
        super(menuType, syncId);

        this.levelAccess = levelAccess;
        level = inventory.player.level();

        var slotX = 8;
        var slotY = 20;
        var slotSize = 18;

        bindingAgentSlot = addSlot(new EnhancedSlot(inputContainer, SLOT_BINDING_AGENT, slotX, slotY));
        inputSlotA = addSlot(new EnhancedSlot(inputContainer, SLOT_INGREDIENT_A, slotX + 5 + slotSize, slotY));
        inputSlotB = addSlot(new EnhancedSlot(inputContainer, SLOT_INGREDIENT_B, slotX + 5 + slotSize * 2, slotY));

        recipeSlot = addSlot(new ResultSlot());

        SimpleContainerMenu.bindPlayerInventory(inventory, 8, 140, this::addSlot);

        addDataSlot(selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex()
    {
        return selectedRecipeIndex.get();
    }

    public List<RecipeHolder<FurnitureStationRecipe>> getRecipes()
    {
        return recipes;
    }

    public int getNumRecipes()
    {
        return recipes.size();
    }

    public boolean hasInputItem()
    {
        return inputSlotA.hasItem() && inputSlotB.hasItem() && bindingAgentSlot.hasItem() && !recipes.isEmpty();
    }

    public void registerUpdateListener(Runnable slotUpdateListener)
    {
        this.slotUpdateListener = slotUpdateListener;
    }

    private boolean isValidRecipeIndex(int index)
    {
        return index >= 0 && index < recipes.size();
    }

    private void setupRecipeList(Container container)
    {
        var inputA = inputSlotA.getItem();
        var inputB = inputSlotB.getItem();
        var bindingAgent = bindingAgentSlot.getItem();

        if(ItemStack.isSameItem(inputA, this.inputA) && ItemStack.isSameItem(inputB, this.inputB) && ItemStack.isSameItem(bindingAgent, this.bindingAgent))
            return;

        recipes.clear();
        recipeSlot.set(ItemStack.EMPTY);
        recipes.addAll(level.getRecipeManager().getRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE, container, level));

        var index = selectedRecipeIndex.get();

        if(isValidRecipeIndex(index))
        {
            var recipeHolder = recipes.get(index);
            var recipe = recipeHolder.value();
            var result = recipe.assemble(inputContainer, level.registryAccess());

            if(result.isItemEnabled(level.enabledFeatures()))
            {
                resultContainer.setRecipeUsed(recipeHolder);
                recipeSlot.set(result);
            }
        }

        this.inputA = inputA.copy();
        this.inputB = inputB.copy();
        this.bindingAgent = bindingAgent.copy();
    }

    private void setupResultSlot()
    {
        var index = selectedRecipeIndex.get();

        if(!isValidRecipeIndex(index))
            return;

        recipeSlot.set(ItemStack.EMPTY);

        if(!recipes.isEmpty())
        {
            var recipeHolder = recipes.get(index);
            var recipe = recipeHolder.value();
            var result = recipe.assemble(inputContainer, level.registryAccess());

            if(result.isItemEnabled(level.enabledFeatures()))
            {
                resultContainer.setRecipeUsed(recipeHolder);
                recipeSlot.set(result);
            }
        }

        broadcastChanges();
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(levelAccess, player, FantasyFurniture.FURNITURE_STATION_BLOCK.value());
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {
        if(isValidRecipeIndex(id))
        {
            selectedRecipeIndex.set(id);
            setupResultSlot();
        }

        return true;
    }

    @Override
    public void slotsChanged(Container container)
    {
        setupRecipeList(container);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
    {
        return slot.container != resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        var stack = ItemStack.EMPTY;
        var slot = slots.get(index);

        if(slot.hasItem())
        {
            var stack2 = slot.getItem();
            var item = stack2.getItem();
            stack = stack2.copy();

            if(index == 3)
            {
                item.onCraftedBy(stack2, player.level(), player);

                if(!moveItemStackTo(stack2, 4, 38, true))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(stack2, stack);
            }
            else if(index >= 0 && index < 3)
            {
                if(!moveItemStackTo(stack2, 4, 38, false))
                    return ItemStack.EMPTY;
            }
            else if(index >= 4 && index < 31)
            {
                if(!moveItemStackTo(stack2, 0, 3, false))
                    return ItemStack.EMPTY;
                if(!moveItemStackTo(stack2, 31, 40, false))
                    return ItemStack.EMPTY;
            }
            else if(index >= 31 && index < 40)
            {
                if(!moveItemStackTo(stack2, 0, 3, false))
                    return ItemStack.EMPTY;
                if(!moveItemStackTo(stack2, 4, 31, false))
                    return ItemStack.EMPTY;
            }

            if(stack2.isEmpty())
                slot.setByPlayer(ItemStack.EMPTY);

            slot.setChanged();

            if(stack2.getCount() == stack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack2);
            broadcastChanges();
        }

        return stack;
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        resultContainer.removeItemNoUpdate(1);
        levelAccess.execute((level, pos) -> clearContainer(player, inputContainer));
    }

    public static FurnitureStationMenu create(int syncId, Inventory inventory, ContainerLevelAccess levelAccess)
    {
        return new FurnitureStationMenu(FantasyFurniture.FURNITURE_STATION_MENU.value(), syncId, inventory, levelAccess);
    }

    public static FurnitureStationMenu forNetwork(MenuType<? extends FurnitureStationMenu> menuType, int syncId, Inventory inventory, FriendlyByteBuf buffer)
    {
        return create(syncId, inventory, ContainerLevelAccess.NULL);
    }

    private final class InputContainer extends SimpleContainer
    {
        private InputContainer()
        {
            super(SLOT_COUNT);
        }

        @Override
        public void setChanged()
        {
            super.setChanged();
            slotsChanged(this);
            slotUpdateListener.run();
        }
    }

    private final class ResultSlot extends EnhancedSlot
    {
        private ResultSlot()
        {
            super(FurnitureStationMenu.this.resultContainer, 0, 150, 20);
        }

        @Override
        public boolean mayPlace(ItemStack stack)
        {
            return false;
        }

        @Override
        public void onTake(Player player, ItemStack stack)
        {
            stack.onCraftedBy(player.level(), player, stack.getCount());
            resultContainer.awardUsedRecipes(player, List.of(inputSlotA.getItem(), inputSlotB.getItem()));

            var inputA = inputSlotA.remove(1);
            var inputB = inputSlotB.remove(1);
            var bindingAgent = bindingAgentSlot.remove(1);

            if(!inputA.isEmpty() && !inputB.isEmpty() && !bindingAgent.isEmpty())
                setupResultSlot();

            levelAccess.execute((level, pos) -> {
                var gameTime = level.getGameTime();

                if(lastSoundTime != gameTime)
                {
                    level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1F, 1F);
                    lastSoundTime = gameTime;
                }
            });

            super.onTake(player, stack);
        }
    }
}
