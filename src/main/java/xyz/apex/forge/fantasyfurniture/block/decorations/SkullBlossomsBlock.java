package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SkullBlossomsBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.function.Consumer;

public final class SkullBlossomsBlock extends BaseBlock.WithBlockEntity<SkullBlossomsBlockEntity>
{
	public static final VoxelShape SHAPE = box(4, 0, 4, 12, 8, 12);

	public SkullBlossomsBlock(Properties properties)
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
	protected BlockEntityType<SkullBlossomsBlockEntity> getBlockEntityType()
	{
		return ModElements.BONE_SKULL_BLOSSOMS_BLOCK_ENTITY.get();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}
}