package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import xyz.apex.forge.apexcore.lib.util.ContainerHelper;
import xyz.apex.forge.fantasyfurniture.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.InventoryContainer;

import javax.annotation.Nullable;

public abstract class SimpleFourWayContainerBlock<BLOCK_ENTITY extends InventoryBlockEntity<MENU>, MENU extends InventoryContainer> extends SimpleFourWayBlockEntityBlock<BLOCK_ENTITY>
{
	public SimpleFourWayContainerBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult)
	{
		var stack = player.getItemInHand(hand);

		if(player instanceof ServerPlayer serverPlayer)
		{
			if(!openContainerScreen(blockState, level, pos, serverPlayer, hand, stack, stack.getHoverName()))
				return InteractionResult.PASS;
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.setPlacedBy(level, pos, blockState, placer, stack);

		var blockEntity = getBlockEntity(level, pos);

		if(blockEntity != null && stack.hasCustomHoverName())
		{
			var customName = stack.getHoverName();
			blockEntity.setCustomName(customName);
		}
	}

	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean isMoving)
	{
		var blockEntity = getBlockEntity(level, pos);

		if(blockEntity != null)
		{
			var itemHandler = blockEntity.getItemHandler();

			for(var i = 0; i < itemHandler.getSlots(); i++)
			{
				var stack = itemHandler.getStackInSlot(i);
				Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
			}
		}

		super.onRemove(blockState, level, pos, newBlockState, isMoving);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState blockState)
	{
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos)
	{
		return ContainerHelper.getRedstoneSignalFromContainer(level, pos);
	}

	protected boolean openContainerScreen(BlockState blockState, Level level, BlockPos pos, ServerPlayer player, InteractionHand hand, ItemStack stack, Component titleComponent)
	{
		NetworkHooks.openGui(player, getMenuProvider(blockState, level, pos));
		return true;
	}
}
