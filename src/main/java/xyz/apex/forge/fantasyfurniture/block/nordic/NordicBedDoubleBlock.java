package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.state.properties.BedPart;

import xyz.apex.forge.fantasyfurniture.block.BaseBedBlockDouble;

public final class NordicBedDoubleBlock extends BaseBedBlockDouble
{
	public NordicBedDoubleBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(BED_SIDE, BedSide.MAIN));
	}
}
