package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.SimpleFourWayContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.DrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.DrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFBlockEntities;

public final class NordicDrawerBlock extends SimpleFourWayContainerBlock<DrawerBlockEntity, DrawerContainer>
{
	public static final VoxelShape SHAPE = BlockHelper.makeShape(
			box(1D, 0D, 1D, 15D, 13D, 15D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public NordicDrawerBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}

	@Override
	protected TileEntityType<DrawerBlockEntity> getBlockEntityType()
	{
		return FFBlockEntities.NORDIC_DRAWER.asBlockEntityType();
	}
}
