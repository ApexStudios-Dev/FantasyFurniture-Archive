package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SetWallLightBlock extends WallLightBlock implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetWallLightBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(properties);

		this.furnitureSet = furnitureSet;
	}

	@Override
	public final ModBlocks getFurnitureSet()
	{
		return furnitureSet;
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(FACING_4_WAY);
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.wallLight(this, blockState);
	}
}
