package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.java.utility.nullness.NonnullConsumer;

import javax.annotation.Nullable;

public class SetWardrobeTopperBlock extends BaseMultiBlock
{
	public SetWardrobeTopperBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(NonnullConsumer<Property<?>> consumer)
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

		if(belowBlock instanceof SetWardrobeBlock wardrobeBlock)
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

		if(belowBlock instanceof SetWardrobeBlock wardrobeBlock)
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
		return FFPatterns.PATTERN_1x2x1;
	}
}
