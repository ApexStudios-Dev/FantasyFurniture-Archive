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
import xyz.apex.forge.fantasyfurniture.block.entity.SetDrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SetDrawerBlock extends BaseBlock.WithContainer<SetDrawerBlockEntity, SetDrawerContainer> implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetDrawerBlock(ModBlocks furnitureSet, Properties properties)
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
	protected MenuType<SetDrawerContainer> getContainerType()
	{
		return FFElements.DRAWER_CONTAINER.get();
	}

	@Override
	protected BlockEntityType<SetDrawerBlockEntity> getBlockEntityType()
	{
		return FFElements.DRAWER_BLOCK_ENTITY.get();
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.drawer(this, blockState);
	}
}
