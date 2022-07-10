package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.DresserBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;
import xyz.apex.forge.fantasyfurniture.menu.MediumInventoryMenu;

import java.util.function.Consumer;

public class DresserBlock extends BaseMultiBlock.WithContainer<DresserBlockEntity, MediumInventoryMenu>
{
	public DresserBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	protected BlockEntityType<DresserBlockEntity> getBlockEntityType()
	{
		return ModElements.DRESSER_BLOCK_ENTITY.get();
	}

	@Override
	protected MenuType<MediumInventoryMenu> getContainerType()
	{
		return ModElements.MEDIUM_INVENTORY_MENU.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_DRESSER.has(blockState))
			return HitBoxes.NORDIC.dresser(this, blockState);
		else if(ModBlocks.DUNMER_DRESSER.has(blockState))
			return HitBoxes.DUNMER.dresser(this, blockState);
		else if(ModBlocks.VENTHYR_DRESSER.has(blockState))
			return HitBoxes.VENTHYR.dresser(this, blockState);
		else if(ModBlocks.BONE_SKELETON_DRESSER.has(blockState) || ModBlocks.BONE_WITHER_DRESSER.has(blockState))
			return HitBoxes.BONE.dresser(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}