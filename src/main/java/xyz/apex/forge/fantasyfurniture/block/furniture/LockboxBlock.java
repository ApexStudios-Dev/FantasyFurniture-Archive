package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.LockboxBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.SmallInventoryMenu;

import java.util.function.Consumer;

public class LockboxBlock extends BaseBlock.WithContainer<LockboxBlockEntity, SmallInventoryMenu>
{
	public LockboxBlock(Properties properties)
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
	protected BlockEntityType<LockboxBlockEntity> getBlockEntityType()
	{
		return ModElements.LOCKBOX_BLOCK_ENTITY.get();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_LOCKBOX.isIn(blockState))
			return HitBoxes.NORDIC.lockbox(this, blockState);
		else if(ModBlocks.DUNMER_LOCKBOX.isIn(blockState))
			return HitBoxes.DUNMER.lockbox(this, blockState);
		else if(ModBlocks.VENTHYR_LOCKBOX.isIn(blockState))
			return HitBoxes.VENTHYR.lockbox(this, blockState);
		else if(ModBlocks.BONE_SKELETON_LOCKBOX.isIn(blockState) || ModBlocks.BONE_WITHER_LOCKBOX.isIn(blockState))
			return HitBoxes.BONE.lockbox(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}