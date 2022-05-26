package xyz.apex.forge.fantasyfurniture.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

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
			String name = VenthyrTableSmallBlock.FANCY.getName();

			if(stackTag.contains(name, Constants.NBT.TAG_BYTE) && stackTag.getBoolean(name))
				return id + ".fancy";
		}

		return id;
	}
}
