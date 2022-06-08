package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.util.ContainerHelper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayBlockEntityBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import javax.annotation.Nullable;

public final class FurnitureStationBlock extends SimpleFourWayBlockEntityBlock<FurnitureStationBlockEntity>
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 14D, 3D),
			box(1D, 0D, 13D, 3D, 14D, 15D),
			box(13D, 0D, 13D, 15D, 14D, 15D),
			box(13D, 0D, 1D, 15D, 14D, 3D),
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(2D, 2D, 2D, 14D, 14D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public FurnitureStationBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
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

	@Override
	protected BlockEntityType<FurnitureStationBlockEntity> getBlockEntityType()
	{
		return FurnitureStation.BLOCK_ENTITY.asBlockEntityType();
	}
}
