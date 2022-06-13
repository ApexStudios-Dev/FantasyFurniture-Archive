package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.fantasyfurniture.block.base.core.CushionBlock;
import xyz.apex.java.utility.nullness.NonnullConsumer;

public class SetCushionBlock extends CushionBlock
{
	public SetCushionBlock(Properties properties)
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
}
