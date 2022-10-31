package xyz.apex.forge.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllBlockEntities;
import xyz.apex.forge.fantasyfurniture.AllMenus;
import xyz.apex.forge.fantasyfurniture.common.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.FurnitureStationMenu;

import java.util.function.Consumer;

public final class FurnitureStationBlock extends BaseBlock.WithContainer<FurnitureStationBlockEntity, FurnitureStationMenu>
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
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		return SHAPER.get(facing);
	}

	@Override
	protected BlockEntityType<FurnitureStationBlockEntity> getBlockEntityType()
	{
		return AllBlockEntities.FURNITURE_STATION.get();
	}

	@Override
	protected MenuType<FurnitureStationMenu> getContainerType()
	{
		return AllMenus.FURNITURE_STATION.get();
	}
}
