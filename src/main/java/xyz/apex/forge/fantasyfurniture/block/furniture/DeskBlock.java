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
import xyz.apex.forge.fantasyfurniture.block.entity.DeskBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;
import xyz.apex.forge.fantasyfurniture.menu.SmallInventoryMenu;

import java.util.function.Consumer;

public class DeskBlock extends BaseMultiBlock.WithContainer<DeskBlockEntity, SmallInventoryMenu>
{
	public DeskBlock(Properties properties)
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
	protected MenuType<SmallInventoryMenu> getContainerType()
	{
		return ModElements.SMALL_INVENTORY_MENU.get();
	}

	@Override
	protected BlockEntityType<DeskBlockEntity> getBlockEntityType()
	{
		return ModElements.DESK_BLOCK_ENTITY.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_DESK_LEFT.isIn(blockState))
			return HitBoxes.NORDIC.deskLeft(this, blockState);
		else if(ModBlocks.NORDIC_DESK_RIGHT.isIn(blockState))
			return HitBoxes.NORDIC.deskRight(this, blockState);
		else if(ModBlocks.DUNMER_DESK_LEFT.isIn(blockState))
			return HitBoxes.DUNMER.deskLeft(this, blockState);
		else if(ModBlocks.DUNMER_DESK_RIGHT.isIn(blockState))
			return HitBoxes.DUNMER.deskRight(this, blockState);
		else if(ModBlocks.VENTHYR_DESK_LEFT.isIn(blockState))
			return HitBoxes.VENTHYR.deskLeft(this, blockState);
		else if(ModBlocks.VENTHYR_DESK_RIGHT.isIn(blockState))
			return HitBoxes.VENTHYR.deskRight(this, blockState);
		else if(ModBlocks.BONE_SKELETON_DESK_LEFT.isIn(blockState) || ModBlocks.BONE_WITHER_DESK_LEFT.isIn(blockState))
			return HitBoxes.BONE.deskLeft(this, blockState);
		else if(ModBlocks.BONE_SKELETON_DESK_RIGHT.isIn(blockState) || ModBlocks.BONE_WITHER_DESK_RIGHT.isIn(blockState))
			return HitBoxes.BONE.deskRight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}