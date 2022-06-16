package xyz.apex.forge.fantasyfurniture.tests;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import xyz.apex.forge.apexcore.revamp.util.GameTestUtils;
import xyz.apex.forge.commonality.init.Mods;
import xyz.apex.forge.fantasyfurniture.block.base.set.StackedBlock;
import xyz.apex.forge.fantasyfurniture.init.Decorations;

@GameTestHolder(Mods.FANTASY_FURNITURE)
@PrefixGameTestTemplate(false)
public final class StackedBlockTests
{
	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void bookStack(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.BOOK_STACK_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void tankardEmpty(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.TANKARD_EMPTY_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void tankardHoneymead(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.TANKARD_HONEYMEAD_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void tankardMilk(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.TANKARD_MILK_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void tankardSweetberry(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.TANKARD_SWEETBERRY_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void boildCremeTreats(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.BOILED_CREME_TREATS_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void sweetrolls(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.SWEETROLLS_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void meadBottles(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.MEAD_BOTTLES_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void platter(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.VENTHYR_PLATTER_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void tomes(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.VENTHYR_TOMES_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	@GameTest(template = "bedrock_5x5x5", batch = "stacked_blocks")
	public static void chalices(GameTestHelper test)
	{
		testStackingBlock(test, Decorations.VENTHYR_CHALICES_BLOCK.asBlock(), new BlockPos(3, 2, 3));
	}

	private static <BLOCK extends StackedBlock> void testStackingBlock(GameTestHelper test, BLOCK block, BlockPos pos)
	{
		var blockName = block.getName().getString();
		var posStr = pos.toShortString();

		test.setBlock(pos, block);
		test.assertBlock(pos, blk -> blk == block, () -> "Failed to set Block['%s'] at Pos['%s']".formatted(blockName, posStr));

		var stackSizeProperty = block.getStackSizeProperty();

		var minValue = StackedBlock.getMinValue(stackSizeProperty);
		var maxValue = StackedBlock.getMaxValue(stackSizeProperty);

		GameTestUtils.assertBlockStateHasProperty(test, pos, test.getBlockState(pos), stackSizeProperty);
		test.assertBlockProperty(pos, stackSizeProperty, minValue);

		var sequence = test.startSequence();

		var absPos = test.absolutePos(pos);
		var player = test.makeMockPlayer();
		player.setItemInHand(InteractionHand.MAIN_HAND, block.asItem().getDefaultInstance());

		for(var i = minValue + 1; i < maxValue; i++)
		{
			var val = i;

			sequence
					.thenExecute(() -> {
						var blockState = test.getBlockState(pos);
						blockState.use(test.getLevel(), player, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atCenterOf(absPos), Direction.NORTH, absPos, true));
					})
					.thenExecute(() -> test.assertBlockProperty(pos, stackSizeProperty, val))
			;
		}

		sequence.thenSucceed();
	}
}
