package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetDrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.java.utility.nullness.NonnullConsumer;

public class SetDrawerBlock extends BaseBlock.WithContainer<SetDrawerBlockEntity, SetDrawerContainer>
{
	public SetDrawerBlock(Properties properties)
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
	protected MenuType<SetDrawerContainer> getContainerType()
	{
		return FFElements.DRAWER_CONTAINER.asMenuType();
	}

	@Override
	protected BlockEntityType<SetDrawerBlockEntity> getBlockEntityType()
	{
		return FFElements.DRAWER_BLOCK_ENTITY.asBlockEntityType();
	}
}
