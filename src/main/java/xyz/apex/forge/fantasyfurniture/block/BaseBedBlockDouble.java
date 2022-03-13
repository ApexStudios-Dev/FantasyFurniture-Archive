package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

// TODO: Figure out why survival doesnt break partner side
public class BaseBedBlockDouble extends BaseBedBlock
{
	public static final EnumProperty<BedSide> BED_SIDE = EnumProperty.create("bed_side", BedSide.class);

	public BaseBedBlockDouble(Properties properties)
	{
		super(properties);
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide && player.isCreative())
		{
			Direction facing = blockState.getValue(FACING);

			BedPart bedPart = blockState.getValue(PART);
			BedPart bedPartOther = bedPart == BedPart.FOOT ? BedPart.HEAD : BedPart.FOOT;
			BedPart bedPartMain = bedPartOther == BedPart.FOOT ? BedPart.HEAD : BedPart.FOOT;

			if(bedPart == bedPartMain)
			{
				BlockPos blockPos = pos.relative(getNeighbourDirection(bedPart, facing));
				BlockState state = level.getBlockState(blockPos);

				if(state.getBlock() == this && state.getValue(PART) == bedPartOther)
				{
					level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 35);
					level.levelEvent(player, 2001, blockPos, Block.getId(state));
				}
			}

			BedSide bedSide = blockState.getValue(BED_SIDE);
			BedSide bedSidePartner = bedSide == BedSide.MAIN ? BedSide.PARTNER : BedSide.MAIN;
			BedSide bedSideMain = bedSidePartner == BedSide.MAIN ? BedSide.PARTNER : BedSide.MAIN;

			if(bedSide == bedSideMain)
			{
				BlockPos blockPos = pos.relative(getConnectedBedSideDirection(bedSide, facing));
				BlockState state = level.getBlockState(blockPos);

				if(state.getBlock() == this && state.getValue(BED_SIDE) == bedSidePartner)
				{
					level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 35);
					level.levelEvent(player, 2001, blockPos, Block.getId(state));
				}
			}
		}

		// No super call, to ignore BaseBedBlock
		// from Block, Needed as BaseBedBlock calls super (and we still want this logic)
		level.levelEvent(player, 2001, pos, Block.getId(blockState));

		if(is(BlockTags.GUARDED_BY_PIGLINS))
			PiglinTasks.angerNearbyPiglins(player, false);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		return blockState.getValue(BED_SIDE) == BedSide.MAIN ? super.getRenderShape(blockState) : BlockRenderType.INVISIBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BED_SIDE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.setPlacedBy(level, pos, blockState, placer, stack);

		if(!level.isClientSide)
		{
			BlockPos relativePos = pos.relative(getConnectedBedSideDirection(blockState));
			level.setBlock(relativePos, blockState.setValue(BED_SIDE, BedSide.PARTNER), 3);

			relativePos = relativePos.relative(blockState.getValue(FACING));
			level.setBlock(relativePos, blockState.setValue(BED_SIDE, BedSide.PARTNER).setValue(PART, BedPart.HEAD), 3);
			level.blockUpdated(pos, Blocks.AIR);
			blockState.updateNeighbourShapes(level, pos, 3);
		}
	}

	@Override
	public long getSeed(BlockState blockState, BlockPos pos)
	{
		Direction facing = blockState.getValue(FACING);
		Direction sideFacing = getConnectedBedSideDirection(blockState);
		BlockPos relativePos = pos.relative(facing, blockState.getValue(PART) == BedPart.HEAD ? 0 : 1).relative(sideFacing);
		return MathHelper.getSeed(relativePos);
	}

	public static Direction getConnectedBedSideDirection(BlockState blockState)
	{
		Direction facing = blockState.getValue(FACING);
		BedSide bedSide = blockState.getValue(BED_SIDE);
		return getConnectedBedSideDirection(bedSide, facing);
	}

	public static Direction getConnectedBedSideDirection(BedSide bedSide, Direction facing)
	{
		return bedSide == BedSide.MAIN ? facing.getClockWise() : facing.getCounterClockWise();
	}

	public enum BedSide implements IStringSerializable
	{
		MAIN("main"),
		PARTNER("partner");

		private final String serializedName;

		BedSide(String serializedName)
		{
			this.serializedName = serializedName;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}
