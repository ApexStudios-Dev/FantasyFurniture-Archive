package xyz.apex.forge.fantasyfurniture.common.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
import xyz.apex.forge.fantasyfurniture.AllBlockEntities;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllMenus;
import xyz.apex.forge.fantasyfurniture.AllPatterns;
import xyz.apex.forge.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.BookshelfMenu;
import xyz.apex.forge.fantasyfurniture.core.HitBoxes;

import java.util.List;
import java.util.function.Consumer;

public class BookshelfBlock extends BaseMultiBlock.WithContainer<BookshelfBlockEntity, BookshelfMenu>
{
	public BookshelfBlock(Properties properties)
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
	protected MenuType<BookshelfMenu> getContainerType()
	{
		return AllMenus.BOOKSHELF_MENU.get();
	}

	@Override
	protected BlockEntityType<BookshelfBlockEntity> getBlockEntityType()
	{
		return AllBlockEntities.BOOKSHELF_BLOCK_ENTITY.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return AllPatterns.PATTERN_1x2x2;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(AllBlocks.NORDIC_BOOKSHELF.isIn(blockState))
			return HitBoxes.NORDIC.bookshelf(this, blockState);
		else if(AllBlocks.DUNMER_BOOKSHELF.isIn(blockState))
			return HitBoxes.DUNMER.bookshelf(this, blockState);
		else if(AllBlocks.VENTHYR_BOOKSHELF.isIn(blockState))
			return HitBoxes.VENTHYR.bookshelf(this, blockState);
		else if(AllBlocks.BONE_SKELETON_BOOKSHELF.isIn(blockState) || AllBlocks.BONE_WITHER_BOOKSHELF.isIn(blockState))
			return HitBoxes.BONE.bookshelf(this, blockState);
		else if(AllBlocks.ROYAL_BOOKSHELF.isIn(blockState))
			return HitBoxes.ROYAL.bookshelf(this, blockState);
		else if(AllBlocks.NECROLORD_BOOKSHELF.isIn(blockState))
			return HitBoxes.NECROLORD.bookshelf(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends BookshelfBlock implements IDyeable
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(IDyeable.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return IDyeable.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			IDyeable.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
			return IDyeable.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = IDyeable.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return IDyeable.getCloneItemStack(blockState, level, pos, stack);
		}

		@Override
		public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
		{
			super.appendHoverText(stack, level, tooltip, flag);
			IDyeable.appendHoverText(this, tooltip);
		}
	}
}
