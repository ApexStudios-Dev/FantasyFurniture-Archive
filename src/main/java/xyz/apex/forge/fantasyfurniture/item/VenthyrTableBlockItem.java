package xyz.apex.forge.fantasyfurniture.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.venthyr.VenthyrTableLargeBlock;
import xyz.apex.forge.fantasyfurniture.block.venthyr.VenthyrTableSmallBlock;

public final class VenthyrTableBlockItem extends BlockItem
{
	public VenthyrTableBlockItem(Block block, Properties properties)
	{
		super(block, properties);
	}

	@Override
	public String getDescriptionId(ItemStack stack)
	{
		String id = super.getDescriptionId(stack);

		CompoundNBT stackTag = stack.getTag();

		if(stackTag != null)
		{
			if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Constants.NBT.TAG_COMPOUND))
			{
				CompoundNBT blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
				String name = VenthyrTableSmallBlock.FANCY.getName();

				if(blockStateTag.contains(name, Constants.NBT.TAG_STRING))
				{
					String strFancy = blockStateTag.getString(name);

					if(VenthyrTableLargeBlock.FANCY.getValue(strFancy).orElse(false))
						return id + ".fancy";
				}
			}
		}

		return id;
	}
}
