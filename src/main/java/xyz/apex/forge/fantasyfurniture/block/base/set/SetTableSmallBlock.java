package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;

import java.util.function.Consumer;

public class SetTableSmallBlock extends BaseBlock
{
	public SetTableSmallBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}
}
