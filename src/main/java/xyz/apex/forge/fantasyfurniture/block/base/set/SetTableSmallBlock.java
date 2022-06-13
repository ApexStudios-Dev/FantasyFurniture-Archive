package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.java.utility.nullness.NonnullConsumer;

public class SetTableSmallBlock extends BaseBlock
{
	public SetTableSmallBlock(Properties properties)
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
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}
