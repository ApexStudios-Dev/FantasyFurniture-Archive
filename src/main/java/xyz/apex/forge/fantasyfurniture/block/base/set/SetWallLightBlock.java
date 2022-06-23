package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.WallLightBlock;

import java.util.function.Consumer;

public class SetWallLightBlock extends WallLightBlock
{
	public SetWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(FACING_4_WAY);
	}
}
