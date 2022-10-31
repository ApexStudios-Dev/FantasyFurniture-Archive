package xyz.apex.forge.fantasyfurniture.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;

import xyz.apex.forge.fantasyfurniture.client.renderer.ModBlockEntityWithoutLevelRenderer;

import java.util.function.Consumer;

public final class SkullBlossomsBlockItem extends BlockItem
{
	public SkullBlossomsBlockItem(Block block, Properties properties)
	{
		super(block, properties);
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
