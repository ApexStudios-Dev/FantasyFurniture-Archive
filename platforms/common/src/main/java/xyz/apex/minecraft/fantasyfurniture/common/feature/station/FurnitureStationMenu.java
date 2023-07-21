package xyz.apex.minecraft.fantasyfurniture.common.feature.station;

import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.menu.EnhancedSlot;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;

import java.util.Collections;
import java.util.List;

public final class FurnitureStationMenu extends AbstractContainerMenu
{
    private final SimpleContainer inputsContainer = new SimpleContainer(FurnitureStation.INPUT_SLOTS);
    private final ResultContainer resultContainer = new ResultContainer();
    private final List<FurnitureStationRecipe> recipes = Lists.newArrayList();
    private final List<FurnitureStationRecipe> recipesView = Collections.unmodifiableList(recipes);
    private final DataSlot selectResultIndex = DataSlot.standalone();
    private final ContainerLevelAccess levelAccess;
    private long lastSoundTime = 0L;

    FurnitureStationMenu(int windowId, Inventory playerInventory, ContainerLevelAccess levelAccess)
    {
        super(FurnitureStation.MENU.get(), windowId);

        this.levelAccess = levelAccess;

        inputsContainer.addListener(this::slotsChanged);

        var slotX = 17;
        var slotY = 15;
        var slotSize = 18;

        addSlot(new OutputSlot());

        addSlot(new EnhancedSlot(inputsContainer, FurnitureStation.INPUT_SLOT_LEFT, slotX, slotY));
        addSlot(new EnhancedSlot(inputsContainer, FurnitureStation.INPUT_SLOT_RIGHT, slotX + slotSize, slotY));
        addSlot(new EnhancedSlot(inputsContainer, FurnitureStation.INPUT_SLOT_BINDING_AGENT, slotX + slotSize * 2, slotY));

        addDataSlot(selectResultIndex);

        SimpleContainerMenu.bindPlayerInventory(playerInventory, 8, 140, this::addSlot);

        inputsContainer.startOpen(playerInventory.player);
        resultContainer.startOpen(playerInventory.player);
    }

    public ItemStack getResult()
    {
        return resultContainer.getItem(FurnitureStation.OUTPUT_SLOT);
    }

    public ItemStack getInputLeft()
    {
        return inputsContainer.getItem(FurnitureStation.INPUT_SLOT_LEFT);
    }

    public ItemStack getInputRight()
    {
        return inputsContainer.getItem(FurnitureStation.INPUT_SLOT_RIGHT);
    }

    public ItemStack getBindingAgent()
    {
        return inputsContainer.getItem(FurnitureStation.INPUT_SLOT_BINDING_AGENT);
    }

    public List<FurnitureStationRecipe> getRecipes()
    {
        return recipesView;
    }

    private void setRecipe(Level level, @Nullable FurnitureStationRecipe recipe, int id, boolean broadcast)
    {
        resultContainer.setRecipeUsed(recipe);
        resultContainer.setItem(FurnitureStation.OUTPUT_SLOT, recipe == null ? ItemStack.EMPTY : recipe.assemble(inputsContainer, level.registryAccess()));
        selectResultIndex.set(recipe == null ? -1 : id);

        if(broadcast)
            broadcastChanges();
    }

    private void setupRecipes(Level level, boolean broadcast)
    {
        recipes.clear();
        recipes.addAll(level.getRecipeManager().getRecipesFor(FurnitureStation.RECIPE, inputsContainer, level));

        setRecipe(level, null, -1, broadcast);

        if(broadcast)
            broadcastChanges();
    }

    private boolean setupResult(Level level, int id, boolean broadcast)
    {
        setupRecipes(level, false);

        if(!recipes.isEmpty() && id < recipes.size() && id >= 0)
        {
            var recipe = recipes.get(id);
            setRecipe(level, recipe, id, broadcast);
            return true;
        }

        setRecipe(level, null, -1, broadcast);
        return false;
    }

    private void decrementInputs(int amount)
    {
        getInputLeft().shrink(amount);
        getInputRight().shrink(amount);
        getBindingAgent().shrink(amount);
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {
        return levelAccess.evaluate((level, pos) -> setupResult(level, id, true)).orElse(false);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex)
    {
        var stack = ItemStack.EMPTY;
        var slot = getSlot(slotIndex);

        if(slot.hasItem())
        {
            var stack1 = slot.getItem();
            stack = stack1.copy();

            var resultSlot = 0;
            var inputStart = resultSlot + 1;
            var inputEnd = inputStart + (FurnitureStation.INPUT_SLOTS - 1);
            var playerStart = inputEnd + 1;
            var playerEnd = playerStart + (9 * 3) - 1;
            var hotStart = playerEnd + 1;
            var hotEnd = hotStart + 8;

            if(slotIndex <= inputEnd && slotIndex >= inputStart)
            {
                if(!moveItemStackTo(stack1, playerStart, hotEnd + 1, false))
                    return ItemStack.EMPTY;
            }
            else if(slotIndex == resultSlot)
            {
                levelAccess.execute((level, pos) -> stack1.getItem().onCraftedBy(stack1, level, player));

                if(!moveItemStackTo(stack1, playerStart, hotEnd + 1, false))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(stack1, stack);
            }
            else if(slotIndex <= playerEnd && slotIndex >= playerStart)
            {
                if(!moveItemStackTo(stack1, inputStart, inputEnd + 1, false))
                    return ItemStack.EMPTY;
                if(!moveItemStackTo(stack1, hotStart, hotEnd + 1, false))
                    return ItemStack.EMPTY;
            }
            else if(slotIndex <= hotEnd && slotIndex >= hotStart)
            {
                if(!moveItemStackTo(stack1, inputStart, inputEnd + 1, false))
                    return ItemStack.EMPTY;
                if(!moveItemStackTo(stack1, playerStart, playerEnd + 1, false))
                    return ItemStack.EMPTY;
            }

            if(stack1.isEmpty())
                slot.set(ItemStack.EMPTY);

            slot.setChanged();

            if(stack1.getCount() == stack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack1);
            broadcastChanges();
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(levelAccess, player, FurnitureStation.BLOCK.get());
    }

    @Override
    public void slotsChanged(Container container)
    {
        levelAccess.execute((level, pos) -> setupRecipes(level, false));
        super.slotsChanged(container);
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

    static FurnitureStationMenu forServer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData)
    {
        var pos = extraData.readBlockPos();
        return new FurnitureStationMenu(windowId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), pos));
    }

    static FurnitureStationMenu forClient(int windowId, Inventory playerInventory)
    {
        return new FurnitureStationMenu(windowId, playerInventory, ContainerLevelAccess.NULL);
    }

    private final class OutputSlot extends EnhancedSlot
    {
        private OutputSlot()
        {
            super(FurnitureStationMenu.this.resultContainer, FurnitureStation.OUTPUT_SLOT, 138, 16);
        }

        @Override
        public boolean mayPlace(ItemStack stack)
        {
            return false;
        }

        @Override
        public void onTake(Player player, ItemStack stack)
        {
            levelAccess.execute((level, pos) -> {
                stack.onCraftedBy(level, player, stack.getCount());
                decrementInputs(1);
                resultContainer.awardUsedRecipes(player, List.of(getInputLeft(), getInputRight(), getBindingAgent()));
                setupResult(level, selectResultIndex.get(), true);

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
