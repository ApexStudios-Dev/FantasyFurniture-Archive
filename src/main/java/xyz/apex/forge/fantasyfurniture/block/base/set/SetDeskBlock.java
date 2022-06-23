package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.SetDeskBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDeskContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;

import java.util.function.Consumer;

public class SetDeskBlock extends BaseMultiBlock.WithContainer<SetDeskBlockEntity, SetDeskContainer>
{
	public SetDeskBlock(Properties properties)
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

	@Override
	protected MenuType<SetDeskContainer> getContainerType()
	{
		return FFElements.DESK_CONTAINER.get();
	}

	@Override
	protected BlockEntityType<SetDeskBlockEntity> getBlockEntityType()
	{
		return FFElements.DESK_BLOCK_ENTITY.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x1;
	}
}
