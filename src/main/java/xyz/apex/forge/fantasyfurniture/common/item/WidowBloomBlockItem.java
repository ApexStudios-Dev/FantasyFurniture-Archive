package xyz.apex.forge.fantasyfurniture.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;

import xyz.apex.forge.apexcore.lib.item.WearableBlockItem;
import xyz.apex.forge.fantasyfurniture.client.renderer.ModBlockEntityWithoutLevelRenderer;

import java.util.function.Consumer;

public final class WidowBloomBlockItem extends WearableBlockItem
{
	public WidowBloomBlockItem(Block block, Properties properties)
	{
		super(block, properties, EquipmentSlot.HEAD);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer)
	{
		consumer.accept(new IItemRenderProperties() {
			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer()
			{
				return ModBlockEntityWithoutLevelRenderer.getInstance();
			}
		});
	}
}
