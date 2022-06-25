package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetLockboxBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetLockboxContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

import java.util.function.Consumer;

public class SetLockboxBlock extends BaseBlock.WithContainer<SetLockboxBlockEntity, SetLockboxContainer>
{
	public SetLockboxBlock(Properties properties)
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
	protected MenuType<SetLockboxContainer> getContainerType()
	{
		return FFElements.LOCKBOX_CONTAINER.get();
	}

	@Override
	protected BlockEntityType<SetLockboxBlockEntity> getBlockEntityType()
	{
		return FFElements.LOCKBOX_BLOCK_ENTITY.get();
	}
}
