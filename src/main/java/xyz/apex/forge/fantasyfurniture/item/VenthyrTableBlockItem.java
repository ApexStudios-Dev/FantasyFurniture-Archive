package xyz.apex.forge.fantasyfurniture.item;

import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

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
		var id = super.getDescriptionId(stack);

		var stackTag = stack.getTag();

		if(stackTag != null)
		{
			if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Tag.TAG_COMPOUND))
			{
				var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
				var name = VenthyrTableSmallBlock.FANCY.getName();

				if(blockStateTag.contains(name, Tag.TAG_STRING))
				{
					var strFancy = blockStateTag.getString(name);

					if(VenthyrTableLargeBlock.FANCY.getValue(strFancy).orElse(false))
						return id + ".fancy";
				}
			}
		}

		return id;
	}
}
