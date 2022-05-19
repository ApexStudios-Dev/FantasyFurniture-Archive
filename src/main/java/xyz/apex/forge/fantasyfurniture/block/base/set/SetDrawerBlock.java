package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.tileentity.TileEntityType;

import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetDrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public class SetDrawerBlock extends SimpleFourWayWaterLoggedContainerBlock<SetDrawerBlockEntity, SetDrawerContainer>
{
	public SetDrawerBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected TileEntityType<SetDrawerBlockEntity> getBlockEntityType()
	{
		return FFElements.DRAWER_BLOCK_ENTITY.asBlockEntityType();
	}
}
