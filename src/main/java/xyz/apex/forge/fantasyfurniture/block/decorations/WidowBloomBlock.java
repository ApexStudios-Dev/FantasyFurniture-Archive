package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.function.Consumer;

public final class WidowBloomBlock extends BaseBlock.WithBlockEntity<WidowBloomBlockEntity>
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6D, 0D, 6D, 10D, 3D, 10D),
			box(5.5D, 3D, 5.5D, 10.5D, 6D, 10.5D),
			box(5D, 6D, 5D, 11D, 8D, 11D),
			box(4.5D, 8D, 4.5D, 11.5D, 10D, 11.5D),
			box(5D, 10D, 5D, 11D, 11D, 11D)
	);

	public WidowBloomBlock(Properties properties)
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
	protected BlockEntityType<WidowBloomBlockEntity> getBlockEntityType()
	{
		return ModElements.VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY.get();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}
}