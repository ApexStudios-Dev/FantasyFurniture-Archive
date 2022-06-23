package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.SetWardrobeBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;

import java.util.function.Consumer;

public class SetWardrobeBlock extends BaseMultiBlock.WithContainer<SetWardrobeBlockEntity, SetWardrobeContainer>
{
	public SetWardrobeBlock(Properties properties)
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
	protected MenuType<SetWardrobeContainer> getContainerType()
	{
		return FFElements.WARDROBE_CONTAINER.get();
	}

	@Override
	protected BlockEntityType<SetWardrobeBlockEntity> getBlockEntityType()
	{
		return FFElements.WARDROBE_BLOCK_ENTITY.get();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x2;
	}
}
