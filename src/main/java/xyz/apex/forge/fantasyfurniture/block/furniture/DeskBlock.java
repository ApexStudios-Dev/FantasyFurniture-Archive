package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
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
		else if(ModBlocks.ROYAL_DESK_LEFT.isIn(blockState))
			return HitBoxes.ROYAL.deskLeft(this, blockState);
		else if(ModBlocks.ROYAL_DESK_RIGHT.isIn(blockState))
			return HitBoxes.ROYAL.deskRight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends DeskBlock
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(DyeableBlock.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return DyeableBlock.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			DyeableBlock.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
			return DyeableBlock.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = DyeableBlock.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return DyeableBlock.getCloneItemStack(blockState, level, pos, stack);
		}
	}
}