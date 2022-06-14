package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.entity.SetBookshelfBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetBookshelfContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.java.utility.nullness.NonnullConsumer;

public class SetBookshelfBlock extends BaseMultiBlock.WithContainer<SetBookshelfBlockEntity, SetBookshelfContainer>
{
	public SetBookshelfBlock(Properties properties)
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
	protected MenuType<SetBookshelfContainer> getContainerType()
	{
		return FFElements.BOOKSHELF_CONTAINER.asMenuType();
	}

	@Override
	protected BlockEntityType<SetBookshelfBlockEntity> getBlockEntityType()
	{
		return FFElements.BOOKSHELF_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x2;
	}
}
