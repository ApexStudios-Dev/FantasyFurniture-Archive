package xyz.apex.forge.fantasyfurniture.block.furniture;

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
import xyz.apex.forge.fantasyfurniture.block.entity.WardrobeBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;
import xyz.apex.forge.fantasyfurniture.menu.LargeInventoryMenu;

import java.util.function.Consumer;

public class WardrobeBottomBlock extends BaseMultiBlock.WithContainer<WardrobeBlockEntity, LargeInventoryMenu>
{
	public WardrobeBottomBlock(Properties properties)
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
	protected MenuType<LargeInventoryMenu> getContainerType()
	{
		return ModElements.LARGE_INVENTORY_MENU.get();
	}

	@Override
	protected BlockEntityType<WardrobeBlockEntity> getBlockEntityType()
	{
		return ModElements.WARDROBE_BLOCK_ENTITY.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x2;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_WARDROBE_BOTTOM.has(blockState))
			return HitBoxes.NORDIC.wardrobeBottom(this, blockState);
		else if(ModBlocks.DUNMER_WARDROBE_BOTTOM.has(blockState))
			return HitBoxes.DUNMER.wardrobeBottom(this, blockState);
		else if(ModBlocks.VENTHYR_WARDROBE_BOTTOM.has(blockState))
			return HitBoxes.VENTHYR.wardrobeBottom(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}