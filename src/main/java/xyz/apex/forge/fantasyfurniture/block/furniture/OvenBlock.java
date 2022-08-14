package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.OvenBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

import java.util.function.Consumer;

public class OvenBlock extends BaseBlock.WithContainer<OvenBlockEntity, OvenMenu>
{
	public OvenBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.LIT, false));
	}

	@Override
	protected MenuType<OvenMenu> getContainerType()
	{
		return ModElements.OVEN_MENU.get();
	}

	@Override
	protected BlockEntityType<OvenBlockEntity> getBlockEntityType()
	{
		return ModElements.OVEN_BLOCK_ENTITY.get();
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(BlockStateProperties.LIT);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_OVEN.isIn(blockState))
			return HitBoxes.NORDIC.oven(this, blockState);
		else if(ModBlocks.VENTHYR_OVEN.isIn(blockState))
			return HitBoxes.VENTHYR.oven(this, blockState);
		else if(ModBlocks.BONE_SKELETON_OVEN.isIn(blockState) || ModBlocks.BONE_WITHER_PAINTING_WIDE.isIn(blockState))
			return HitBoxes.BONE.oven(this, blockState);
		else if(ModBlocks.ROYAL_OVEN.isIn(blockState))
			return HitBoxes.ROYAL.oven(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	@Override
	protected @Nullable BlockEntityTicker<OvenBlockEntity> getBlockEntityTicker(boolean clientSide)
	{
		return clientSide ? null : (level, pos, blockState, blockEntity) -> blockEntity.serverTick(level, pos, blockState);
	}
}