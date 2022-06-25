package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.SetDresserBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SetDresserBlock extends BaseMultiBlock.WithContainer<SetDresserBlockEntity, SetDresserContainer> implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetDresserBlock(ModBlocks furnitureSet, Properties properties)
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
	protected BlockEntityType<SetDresserBlockEntity> getBlockEntityType()
	{
		return FFElements.DRESSER_BLOCK_ENTITY.get();
	}

	@Override
	protected MenuType<SetDresserContainer> getContainerType()
	{
		return FFElements.DRESSER_CONTAINER.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x1;
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.dresser(this, blockState);
	}
}
