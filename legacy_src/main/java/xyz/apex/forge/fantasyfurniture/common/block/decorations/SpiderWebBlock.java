package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

import java.util.function.Consumer;

public final class SpiderWebBlock extends BaseBlock implements IForgeShearable
{
	public static final VoxelShape SHAPE = Block.box(0, 0, 14, 16, 16, 16);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public SpiderWebBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(FACING_4_WAY);
	}

	@Override
	public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity)
	{
		entity.makeStuckInBlock(blockState, new Vec3(.25D, .05D, .25D));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = getFacing(blockState);
		return SHAPER.get(facing);
	}
}
