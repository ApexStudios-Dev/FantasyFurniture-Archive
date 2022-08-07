package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public class WardrobeTopBlock extends BaseMultiBlock
{
	public WardrobeTopBlock(Properties properties)
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
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		// move to origin of topper block
		var startPos = getMultiBlockOriginPos(blockState, pos);

		// validate block below topper origin is a wardrobe block
		var belowPos = startPos.below();
		var belowBlockState = level.getBlockState(belowPos);
		var belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof WardrobeBottomBlock wardrobeBlock)
		{
			// move to wardrobe origin block
			var origin = wardrobeBlock.getMultiBlockOriginPos(belowBlockState, belowPos);

			// build new raytrace result
			var newResult = result.withPosition(origin).withDirection(player.getDirection());

			// try again from wardrobe origin pov
			var originBlockState = level.getBlockState(origin);
			return originBlockState.use(level, player, hand, newResult);
		}

		// default logic, no wardrobe below topper
		return super.use(blockState, level, pos, player, hand, result);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos)
	{
		// move to origin of topper block
		var startPos = getMultiBlockOriginPos(blockState, pos);

		// validate block below topper origin is a wardrobe block
		var belowPos = startPos.below();
		var belowBlockState = level.getBlockState(belowPos);
		var belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof WardrobeBottomBlock wardrobeBlock)
		{
			var origin = wardrobeBlock.getMultiBlockOriginPos(blockState, belowPos);

			// try again from wardrobe origin pov
			var originBlockState = level.getBlockState(origin);
			return originBlockState.getMenuProvider(level, origin);
		}

		// default logic, no wardrobe below topper
		return super.getMenuProvider(blockState, level, pos);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_WARDROBE_TOP.isIn(blockState))
			return HitBoxes.NORDIC.wardrobeTop(this, blockState);
		else if(ModBlocks.DUNMER_WARDROBE_TOP.isIn(blockState))
			return HitBoxes.DUNMER.wardrobeTop(this, blockState);
		else if(ModBlocks.VENTHYR_WARDROBE_TOP.isIn(blockState))
			return HitBoxes.VENTHYR.wardrobeTop(this, blockState);
		else if(ModBlocks.BONE_SKELETON_WARDROBE_TOP.isIn(blockState) || ModBlocks.BONE_WITHER_WARDROBE_TOP.isIn(blockState))
			return HitBoxes.BONE.wardrobeTop(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}