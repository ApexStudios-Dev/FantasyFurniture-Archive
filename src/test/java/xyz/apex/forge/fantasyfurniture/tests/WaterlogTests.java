package xyz.apex.forge.fantasyfurniture.tests;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertPosException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.IMultiBlock;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

import java.util.Objects;

@GameTestHolder(FantasyFurniture.ID)
@PrefixGameTestTemplate(false)
public final class WaterlogTests
{
	// NOTE: Increment timeoutTicks for every new block that can be water logged
	// 60 ticks per block (10 ticks between each step, 6 steps)
	// 60 * waterloggableBlockCount = ~timeoutTicks
	// 120 Blocks => 3600 timeoutTicks
	@GameTest(template = "bedrock_5x5x5", batch = "water_logging", timeoutTicks = 3600)
	public static void waterlogTest(GameTestHelper test)
	{
		var pos = new BlockPos(3, 2, 3);
		var fluid = Fluids.WATER;

		var fluidName = Objects.requireNonNull(fluid.getRegistryName()).toString();

		var sequence = test.startSequence();
		var blocks = FFRegistry.getInstance()
		                       .getAll(Block.class)
		                       .stream()
		                       .filter(RegistryEntry::isPresent)
		                       .map(BlockEntry::cast)
		                       .map(BlockEntry::asBlock)
		                       .toArray(Block[]::new);

		System.out.printf("BlockCount: %d\n", blocks.length);

		for(var block : blocks)
		{
			var defaultBlockState = block.defaultBlockState();

			if(!BaseBlock.supportsWaterLogging(defaultBlockState))
				continue;

			var waterloggedBlock = (SimpleWaterloggedBlock) block;

			// try and place the block
			// assert that block was placed
			// assert that block is not currently water logged
			sequence.thenExecute(() -> test.setBlock(pos, defaultBlockState));
			sequence.thenIdle(10);
			sequence.thenExecute(() -> assertWaterLogState(test, pos, block, false));
			sequence.thenIdle(1);

			// try and place the fluid in the block
			// assert that the fluid can be placed
			// assert that fluid was placed (block is water logged)
			sequence.thenExecute(() -> placeLiquid(test, pos, block, waterloggedBlock, fluid, fluidName));
			sequence.thenIdle(1);
			sequence.thenExecute(() -> assertWaterLogState(test, pos, block, true));
			sequence.thenIdle(1);

			// try and pickup fluid from the block
			// assert that fluid was picked up
			// assert that bucket picked up is filled fluid container for the fluid
			sequence.thenExecute(() -> pickupLiquid(test, pos, block, waterloggedBlock, fluid));
			sequence.thenIdle(1);
			sequence.thenExecute(() -> assertWaterLogState(test, pos, block, false));
			sequence.thenIdle(1);
		}

		sequence.thenExecute(() -> test.setBlock(pos, Blocks.AIR)).thenSucceed();
	}

	private static void assertWaterLogState(GameTestHelper test, BlockPos pos, Block block, boolean waterlogged)
	{
		if(block instanceof IMultiBlock multiBlock)
		{
			var blockState = test.getBlockState(pos);
			var absPos = test.absolutePos(pos);
			var origin = absPos; //multiBlock.getMultiBlockOriginPos(blockState, absPos);

			for(var localSpace : multiBlock.getMultiBlockLocalPositions())
			{
				// var worldSpace = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localSpace);
				var worldSpace = origin.offset(localSpace.rotate(Rotation.CLOCKWISE_90));
				var relWorldSpace = test.relativePos(worldSpace);
				var subBlockState = test.getLevel().getBlockState(worldSpace);

				if(!subBlockState.is(block))
					throw new GameTestAssertPosException("Expected Block '%s' at Position '%s' but found Block '%s'".formatted(block.getRegistryName(), worldSpace.toShortString(), subBlockState.getBlock().getRegistryName()), worldSpace, relWorldSpace, test.getTick());

				// test.assertBlockPresent(block, worldSpace);
				// test.assertBlockProperty(relWorldSpace, BlockStateProperties.WATERLOGGED, waterlogged);
			}
		}
		else
		{
			test.assertBlockPresent(block, pos);
			// test.assertBlockProperty(pos, BlockStateProperties.WATERLOGGED, waterlogged);
		}
	}

	private static void placeLiquid(GameTestHelper test, BlockPos pos, Block block, SimpleWaterloggedBlock waterloggedBlock, Fluid fluid, String fluidName)
	{
		var level = test.getLevel();
		var blockState = test.getBlockState(pos);
		var absPos = test.absolutePos(pos);
		var fluidState = fluid instanceof FlowingFluid flowingFluid ? flowingFluid.getSource(false) : fluid.defaultFluidState();

		if(block instanceof IMultiBlock multiBlock)
		{
			// var origin = multiBlock.getMultiBlockOriginPos(blockState, absPos);
			var origin = absPos; //multiBlock.getMultiBlockOriginPos(blockState, absPos);

			for(var localSpace : multiBlock.getMultiBlockLocalPositions())
			{
				// var worldSpace = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localSpace);
				var worldSpace = origin.offset(localSpace.rotate(Rotation.CLOCKWISE_90));
				var relWorldSpace = test.relativePos(worldSpace);
				var subBlockState = test.getLevel().getBlockState(worldSpace);

				if(!subBlockState.is(block))
					throw new GameTestAssertPosException("Expected Block '%s' at Position '%s' but found Block '%s'".formatted(block.getRegistryName(), worldSpace.toShortString(), subBlockState.getBlock().getRegistryName()), worldSpace, relWorldSpace, test.getTick());

				if(!waterloggedBlock.canPlaceLiquid(level, worldSpace, subBlockState, fluid))
					throw new GameTestAssertPosException("Fluid '%s' can not be placed within the Block".formatted(fluidName), worldSpace, relWorldSpace, test.getTick());
				if(!waterloggedBlock.placeLiquid(level, worldSpace, subBlockState, fluidState))
					throw new GameTestAssertPosException("Fluid '%s' failed to be placed with the Block".formatted(fluidName), worldSpace, relWorldSpace, test.getTick());
			}
		}
		else
		{
			test.assertBlockPresent(block, pos);

			if(!waterloggedBlock.canPlaceLiquid(level, absPos, blockState, fluid))
				throw new GameTestAssertPosException("Fluid '%s' can not be placed within the Block".formatted(fluidName), absPos, pos, test.getTick());
			if(!waterloggedBlock.placeLiquid(level, absPos, blockState, fluidState))
				throw new GameTestAssertPosException("Fluid '%s' failed to be placed with the Block".formatted(fluidName), absPos, pos, test.getTick());
		}
	}

	private static void pickupLiquid(GameTestHelper test, BlockPos pos, Block block, SimpleWaterloggedBlock waterloggedBlock, Fluid fluid)
	{
		var level = test.getLevel();
		var blockState = test.getBlockState(pos);
		var absPos = test.absolutePos(pos);
		var expectedBucket = fluid.getBucket();

		if(block instanceof IMultiBlock multiBlock)
		{
			// var origin = multiBlock.getMultiBlockOriginPos(blockState, absPos);
			var origin = absPos; //multiBlock.getMultiBlockOriginPos(blockState, absPos);

			for(var localSpace : multiBlock.getMultiBlockLocalPositions())
			{
				// var worldSpace = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localSpace);
				var worldSpace = origin.offset(localSpace.rotate(Rotation.CLOCKWISE_90));
				var relWorldSpace = test.relativePos(worldSpace);
				var subBlockState = test.getLevel().getBlockState(worldSpace);

				if(!subBlockState.is(block))
					throw new GameTestAssertPosException("Expected Block '%s' at Position '%s' but found Block '%s'".formatted(block.getRegistryName(), worldSpace.toShortString(), subBlockState.getBlock().getRegistryName()), worldSpace, relWorldSpace, test.getTick());

				var bucket = waterloggedBlock.pickupBlock(level, worldSpace, subBlockState);

				if(!bucket.is(expectedBucket))
					throw new GameTestAssertPosException("Picked up Fluid Container '%s' was not of expected type '%s'".formatted(bucket.getItem().getRegistryName(), expectedBucket.getRegistryName()), worldSpace, relWorldSpace, test.getTick());
			}
		}
		else
		{
			test.assertBlockPresent(block, pos);

			var bucket = waterloggedBlock.pickupBlock(level, pos, blockState);

			if(!bucket.is(expectedBucket))
				throw new GameTestAssertPosException("Picked up Fluid Container '%s' was not of expected type '%s'".formatted(bucket.getItem().getRegistryName(), expectedBucket.getRegistryName()), absPos, pos, test.getTick());
		}
	}
}
