package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import xyz.apex.minecraft.apexcore.common.inventory.InventoryBlockEntity;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class OvenBlockEntity extends AbstractFurnaceBlockEntity
{
    public OvenBlockEntity(BlockEntityType<? extends OvenBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState, RecipeType.SMOKING);
    }

    @Override
    protected Component getDefaultName()
    {
        return getBlockState().getBlock().getName();
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
    {
        return new SmokerMenu(containerId, inventory, this, dataAccess);
    }

    public static final class Delegate extends OvenBlockEntity
    {
        public Delegate(BlockEntityType<? extends OvenBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
        {
            super(blockEntityType, pos, blockState);
        }

        @Override protected void saveAdditional(CompoundTag tag) {}
        @Override public void load(CompoundTag tag) {}

        @Override
        public void saveToItem(ItemStack stack)
        {
            lookupOrigin().ifPresent(blockEntity -> blockEntity.saveToItem(stack));
        }

        @Override
        public void setChanged()
        {
            lookupOrigin().ifPresent(BlockEntity::setChanged);
            super.setChanged();
        }

        @Nullable
        @Override
        public Packet<ClientGamePacketListener> getUpdatePacket()
        {
            return lookupOrigin().map(BlockEntity::getUpdatePacket).orElse(null);
        }

        @Override
        public CompoundTag getUpdateTag()
        {
            return lookupOrigin().map(BlockEntity::getUpdateTag).orElseGet(super::getUpdateTag);
        }

        @Override
        public boolean isRemoved()
        {
            return lookupOrigin().map(BlockEntity::isRemoved).orElseGet(super::isRemoved);
        }

        @Override
        public void setRemoved()
        {
            lookupOrigin().ifPresent(BlockEntity::setRemoved);
            super.setRemoved();
        }

        @Override
        public void clearRemoved()
        {
            lookupOrigin().ifPresent(BlockEntity::clearRemoved);
            super.clearRemoved();
        }

        @Override
        public boolean triggerEvent(int id, int type)
        {
            return lookupOrigin().map(blockEntity -> blockEntity.triggerEvent(id, type)).orElse(false);
        }

        @Override
        public boolean onlyOpCanSetNbt()
        {
            return lookupOrigin().map(BlockEntity::onlyOpCanSetNbt).orElse(false);
        }

        private Optional<BlockEntity> lookupOrigin()
        {
            return lookupOrigin(level, worldPosition, getBlockState());
        }

        private <T> Optional<T> lookupOriginAs(Class<T> type)
        {
            return lookupOrigin().filter(type::isInstance).map(type::cast);
        }

        @Override
        public Component getName()
        {
            return lookupOriginAs(Nameable.class).map(Nameable::getName).orElseGet(() -> getBlockState().getBlock().getName());
        }

        @Override
        public Component getDisplayName()
        {
            return lookupOriginAs(Nameable.class).map(Nameable::getDisplayName).orElseGet(() -> getBlockState().getBlock().getName());
        }

        @Override
        public boolean hasCustomName()
        {
            return lookupOriginAs(Nameable.class).map(Nameable::hasCustomName).orElse(false);
        }

        @Nullable
        @Override
        public Component getCustomName()
        {
            return lookupOriginAs(Nameable.class).map(Nameable::getCustomName).orElse(null);
        }

        public void setCustomName(@Nullable Component customName)
        {
            lookupOriginAs(InventoryBlockEntity.class).ifPresent(blockEntity -> blockEntity.setCustomName(customName));
        }

        @Override
        protected Component getDefaultName()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(OvenBlockEntity::getDefaultName).orElseGet(super::getDefaultName);
        }

        @Override
        protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.createMenu(containerId, inventory)).orElse(null);
        }

        @Override
        protected int getBurnDuration(ItemStack fuel)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.getBurnDuration(fuel)).orElse(0);
        }

        @Override
        public int[] getSlotsForFace(Direction side)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.getSlotsForFace(side)).orElse(new int[0]);
        }

        @Override
        public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.canPlaceItemThroughFace(index, itemStack, direction)).orElse(false);
        }

        @Override
        public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.canTakeItemThroughFace(index, stack, direction)).orElse(false);
        }

        @Override
        public int getContainerSize()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(AbstractFurnaceBlockEntity::getContainerSize).orElse(0);
        }

        @Override
        public boolean isEmpty()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(AbstractFurnaceBlockEntity::isEmpty).orElse(true);
        }

        @Override
        public ItemStack getItem(int slot)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.getItem(slot)).orElse(ItemStack.EMPTY);
        }

        @Override
        public ItemStack removeItem(int slot, int amount)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.removeItem(slot, amount)).orElse(ItemStack.EMPTY);
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.removeItemNoUpdate(slot)).orElse(ItemStack.EMPTY);
        }

        @Override
        public void setItem(int slot, ItemStack stack)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.setItem(slot, stack));
        }

        @Override
        public boolean stillValid(Player player)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.stillValid(player)).orElse(false);
        }

        @Override
        public boolean canPlaceItem(int index, ItemStack stack)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.canPlaceItem(index, stack)).orElse(false);
        }

        @Override
        public void clearContent()
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(AbstractFurnaceBlockEntity::clearContent);
        }

        @Override
        public void setRecipeUsed(@Nullable Recipe<?> recipe)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.setRecipeUsed(recipe));
        }

        @Nullable
        @Override
        public Recipe<?> getRecipeUsed()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(AbstractFurnaceBlockEntity::getRecipeUsed).orElse(null);
        }

        @Override
        public void awardUsedRecipes(Player player)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.awardUsedRecipes(player));
        }

        @Override
        public void awardUsedRecipesAndPopExperience(ServerPlayer player)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.awardUsedRecipesAndPopExperience(player));
        }

        @Override
        public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.getRecipesToAwardAndPopExperience(level, popVec)).orElseGet(Collections::emptyList);
        }

        @Override
        public void fillStackedContents(StackedContents helper)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.fillStackedContents(helper));
        }

        @Override
        public boolean canOpen(Player player)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.canOpen(player)).orElse(false);
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.createMenu(i, inventory, player)).orElse(null);
        }

        @Override
        public int getMaxStackSize()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(Container::getMaxStackSize).orElse(0);
        }

        @Override
        public void startOpen(Player player)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.startOpen(player));
        }

        @Override
        public void stopOpen(Player player)
        {
            lookupOriginAs(OvenBlockEntity.class).ifPresent(oven -> oven.stopOpen(player));
        }

        @Override
        public int countItem(Item item)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.countItem(item)).orElse(0);
        }

        @Override
        public boolean hasAnyOf(Set<Item> set)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.hasAnyOf(set)).orElse(false);
        }

        @Override
        public boolean hasAnyMatching(Predicate<ItemStack> predicate)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.hasAnyMatching(predicate)).orElse(false);
        }

        @Override
        public boolean setRecipeUsed(Level level, ServerPlayer player, Recipe<?> recipe)
        {
            return lookupOriginAs(OvenBlockEntity.class).map(oven -> oven.setRecipeUsed(level, player, recipe)).orElse(false);
        }

        @Override
        protected boolean isLit()
        {
            return lookupOriginAs(OvenBlockEntity.class).map(OvenBlockEntity::isLit).orElse(false);
        }

        private static Optional<BlockEntity> lookupOrigin(@Nullable BlockGetter level, BlockPos pos, BlockState blockState)
        {
            if(level == null) return Optional.empty();

            if(blockState.getBlock() instanceof MultiBlock multiBlock)
            {
                if(!multiBlock.isSameBlockTypeForMultiBlock(blockState)) return Optional.empty();

                var multiBlockType = multiBlock.getMultiBlockType();

                if(!multiBlockType.isOrigin(blockState))
                {
                    var originPos = multiBlockType.getOriginPos(multiBlock, blockState, pos);
                    var originBlockState = level.getBlockState(originPos);
                    return lookupOrigin(level, originPos, originBlockState);
                }
            }

            var blockEntity = level.getBlockEntity(pos);
            return Optional.ofNullable(blockEntity);
        }
    }
}
