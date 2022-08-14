package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.OvenBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

import java.util.function.Consumer;

public class OvenMultiBlock extends BaseMultiBlock.WithContainer<OvenBlockEntity, OvenMenu>
{
	public OvenMultiBlock(Properties properties)
	{
		super(properties);
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
		if(ModBlocks.DUNMER_OVEN.isIn(blockState))
			return HitBoxes.DUNMER.oven(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean isMoving)
	{
		if(!blockState.is(newBlockState.getBlock()) && level instanceof ServerLevel serverLevel)
		{
			var blockEntity = getBlockEntity(blockState, level, pos);

			if(blockEntity != null)
				blockEntity.awardRecipesAndExperience(serverLevel, null, Vec3.atCenterOf(pos));
		}

		super.onRemove(blockState, level, pos, newBlockState, isMoving);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x1;
	}

	@Override
	protected @Nullable BlockEntityTicker<OvenBlockEntity> getBlockEntityTicker(boolean clientSide)
	{
		return clientSide ? null : (level, pos, blockState, blockEntity) -> blockEntity.serverTick(level, pos, blockState);
	}
}