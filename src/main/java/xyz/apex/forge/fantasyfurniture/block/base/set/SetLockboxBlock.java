package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetLockboxBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetLockboxContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SetLockboxBlock extends BaseBlock.WithContainer<SetLockboxBlockEntity, SetLockboxContainer> implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetLockboxBlock(ModBlocks furnitureSet, Properties properties)
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
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	protected MenuType<SetLockboxContainer> getContainerType()
	{
		return FFElements.LOCKBOX_CONTAINER.get();
	}

	@Override
	protected BlockEntityType<SetLockboxBlockEntity> getBlockEntityType()
	{
		return FFElements.LOCKBOX_BLOCK_ENTITY.get();
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.lockbox(this, blockState);
	}
}
