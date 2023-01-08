package xyz.apex.minecraft.fantasyfurniture.shared.menu;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Runnables;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.FurnitureStationRecipe;

import java.util.Collections;
import java.util.List;

public final class FurnitureStationMenu extends AbstractContainerMenu
{
    public static final int SLOT_INGREDIENT_LEFT = 0;
    public static final int SLOT_INGREDIENT_RIGHT = 1;
    public static final int SLOT_BINDING_AGENT = 2;
    public static final int SLOT_INGREDIENT_COUNT = 3;
    public static final int SLOT_INGREDIENT_X = 17;
    public static final int SLOT_INGREDIENT_Y = 15;

    public static final int SLOT_RESULT = 0; // in a separate container from ingredients, can share index
    public static final int SLOT_RESULT_X = 138;
    public static final int SLOT_RESULT_Y = 16;

    public static final int PLAYER_INV_X = 8;
    public static final int PLAYER_INV_Y = 140;

    private final BlockPos pos;
    private final Player player;
    private final SimpleContainer inputsContainer;
    private final ResultContainer resultContainer;
    private final List<FurnitureStationRecipe> recipes = Lists.newArrayList();
    private final List<FurnitureStationRecipe> recipesView = Collections.unmodifiableList(recipes);
    private final DataSlot selectedResultIndex = DataSlot.standalone();
    private Runnable screenListener = Runnables.doNothing();

    public FurnitureStationMenu(MenuType<? extends FurnitureStationMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, containerId);

        this.player = player;

        pos = data.readBlockPos();

        inputsContainer = new SimpleContainer(SLOT_INGREDIENT_COUNT);
        resultContainer = new ResultContainer();

        inputsContainer.addListener(this::slotsChanged);
        // resultContainer.addListener(this::slotsChanged);

        addDataSlot(selectedResultIndex);

        // ingredients
        for(var i = 0; i < SLOT_INGREDIENT_COUNT; i++)
        {
            addSlot(new Slot(inputsContainer, i, SLOT_INGREDIENT_X + i * 18, SLOT_INGREDIENT_Y));
        }

        // result
        addSlot(new ResultSlot());

        // player inventory
        var playerInventory = player.getInventory();

        for(var i = 0; i < 3; i++)
        {
            for(var j = 0; j < 9; j++)
            {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, PLAYER_INV_X + j * 18, PLAYER_INV_Y + i * 18));
            }
        }

        for(var i = 0; i < 9; i++)
        {
            addSlot(new Slot(playerInventory, i, PLAYER_INV_X + i * 18, PLAYER_INV_Y + 58));
        }

        inputsContainer.startOpen(player);
        resultContainer.startOpen(player);
    }

    public void setScreenListener(Runnable screenListener)
    {
        this.screenListener = screenListener;
    }

    public ItemStack getResult()
    {
        return resultContainer.getItem(SLOT_RESULT);
    }

    public ItemStack getInputLeft()
    {
        return inputsContainer.getItem(SLOT_INGREDIENT_LEFT);
    }

    public ItemStack getInputRight()
    {
        return inputsContainer.getItem(SLOT_INGREDIENT_RIGHT);
    }

    public ItemStack getBindingAgent()
    {
        return inputsContainer.getItem(SLOT_BINDING_AGENT);
    }

    public List<FurnitureStationRecipe> getRecipes()
    {
        return recipesView;
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {
        return setupResult(id);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex)
    {
        var stack = ItemStack.EMPTY;
        var slot = slots.get(slotIndex);

        if(slot.hasItem())
        {
            var stack1 = slot.getItem();
            stack = stack1.copy();

            var ingredientStart = 0;
            var ingredientEnd = SLOT_INGREDIENT_COUNT - 1;

            var resultSlot = ingredientEnd + 1;

            var playerStart = resultSlot + 1;
            var playerEnd = playerStart + (9 * 3) - 1;

            var hotStart = playerEnd + 1;
            var hotEnd = hotStart + 8;

            // clicked ingredient
            if(slotIndex <= ingredientEnd && slotIndex >= ingredientStart)
            {
                // try move to player inventory
                // playerStart -> hotEnd, includes inventory & hot bar
                if(!moveItemStackTo(stack1, playerStart, hotEnd + 1, false)) return ItemStack.EMPTY;
            }
            // clicked result
            else if(slotIndex == resultSlot)
            {
                stack1.getItem().onCraftedBy(stack1, player.level, player);

                // try move to player inventory
                // playerStart -> hotEnd, includes inventory & hot bar
                if(!moveItemStackTo(stack1, playerStart, hotEnd + 1, false)) return ItemStack.EMPTY;

                slot.onQuickCraft(stack1, stack);
            }
            // clicked player inventory
            else if(slotIndex <= playerEnd && slotIndex >= playerStart)
            {
                // try move to ingredient slot
                if(!moveItemStackTo(stack1, ingredientStart, ingredientEnd + 1, false)) return ItemStack.EMPTY;
                // try move to hot bar
                else if(!moveItemStackTo(stack1, hotStart, hotEnd + 1, false)) return ItemStack.EMPTY;
            }
            // clicked hot bar
            else if(slotIndex <= hotEnd && slotIndex >= hotStart)
            {
                // try move to ingredient slot
                if(!moveItemStackTo(stack1, ingredientStart, ingredientEnd + 1, false)) return ItemStack.EMPTY;
                // try move to player inventory
                else if(!moveItemStackTo(stack1, playerStart, playerEnd + 1, false)) return ItemStack.EMPTY;
            }

            if(stack1.isEmpty()) slot.set(ItemStack.EMPTY);
            slot.setChanged();
            if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack1);
            broadcastChanges();
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return FantasyFurniture.FURNITURE_STATION_BLOCK.hasBlockState(this.player.level.getBlockState(pos)) && player.distanceToSqr(this.player) <= 64D;
    }

    @Override
    public void slotsChanged(Container container)
    {
        super.slotsChanged(container);
        updateRecipes();
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);

        inputsContainer.stopOpen(player);
        resultContainer.stopOpen(player);

        resultContainer.clearContent();

        clearContainer(player, inputsContainer);

        inputsContainer.clearContent();
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
    {
        return slot.container != resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    private void updateRecipes()
    {
        recipes.clear();
        recipes.addAll(player.level.getRecipeManager().getRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE.asRecipeType(), inputsContainer, player.level));

        var result = getResult();

        if(recipes.stream().map(FurnitureStationRecipe::getResultItem).noneMatch(result::sameItem))
        {
            resultContainer.setItem(SLOT_RESULT, ItemStack.EMPTY);
            selectedResultIndex.set(-1);
        }

        broadcastChanges();
        screenListener.run();
    }

    private boolean setupResult(int recipeIndex)
    {
        updateRecipes();

        if(!recipes.isEmpty() && recipeIndex < recipes.size() && recipeIndex >= 0)
        {
            var recipe = recipes.get(recipeIndex);

            if(recipe.matches(inputsContainer, player.level))
            {
                var stack = recipe.assemble(inputsContainer);

                if(stack.isItemEnabled(player.level.enabledFeatures()))
                {
                    resultContainer.setRecipeUsed(recipe);
                    resultContainer.setItem(SLOT_RESULT, stack);
                    selectedResultIndex.set(recipeIndex);
                    broadcastChanges();
                    return true;
                }
            }
        }

        resultContainer.setRecipeUsed(null);
        resultContainer.setItem(SLOT_RESULT, ItemStack.EMPTY);
        selectedResultIndex.set(-1);
        broadcastChanges();
        return false;
    }

    private void decrementInputs()
    {
        var left = getInputLeft();
        var right = getInputRight();
        var bindingAgent = getBindingAgent();

        left.shrink(1);
        right.shrink(1);
        bindingAgent.shrink(1);
    }

    private final class ResultSlot extends Slot
    {
        private long lastSoundTime = -1L;

        private ResultSlot()
        {
            super(resultContainer, SLOT_RESULT, SLOT_RESULT_X, SLOT_RESULT_Y);
        }

        @Override
        public boolean mayPlace(ItemStack stack)
        {
            return false;
        }

        @Override
        public void onTake(Player player, ItemStack stack)
        {
            stack.onCraftedBy(player.level, player, stack.getCount());
            decrementInputs();
            setupResult(selectedResultIndex.get());
            resultContainer.awardUsedRecipes(player);

            var gameTime = player.level.getGameTime();

            if(lastSoundTime != gameTime)
            {
                player.level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1F, 1F);
                lastSoundTime = gameTime;
            }

            super.onTake(player, stack);
        }
    }
}
