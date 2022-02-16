package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import xyz.apex.forge.apexcore.lib.util.ModEventBusHelper;
import xyz.apex.forge.fantasyfurniture.init.FFBlockEntities;
import xyz.apex.forge.fantasyfurniture.init.FFItems;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

import java.util.Map;

@SuppressWarnings({ "ConstantConditions", "unchecked" })
@OnlyIn(Dist.CLIENT)
public final class CustomItemStackTileEntityRenderer extends ItemStackTileEntityRenderer
{
	public static final CustomItemStackTileEntityRenderer INSTANCE = new CustomItemStackTileEntityRenderer();
	private final Map<Item, TileEntityType<?>> blockEntityTypeMap = Maps.newHashMap();
	private final Map<TileEntityType<?>, TileEntity> blockEntityMap = Maps.newHashMap();

	private CustomItemStackTileEntityRenderer()
	{
		ModEventBusHelper.addEnqueuedListener(FMLLoadCompleteEvent.class, event -> {
			registerBlockEntityRenderer(FFItems.NORDIC_BED, FFBlockEntities.NORDIC_BED);
		});
	}

	private <ITEM extends BlockItem, BLOCK_ENTITY extends TileEntity> void registerBlockEntityRenderer(ItemEntry<ITEM> item, BlockEntityEntry<BLOCK_ENTITY> blockEntityType)
	{
		registerBlockEntityRenderer(item.asItem(), blockEntityType.asBlockEntityType());
	}

	private <ITEM extends BlockItem, BLOCK_ENTITY extends TileEntity> void registerBlockEntityRenderer(ITEM item, TileEntityType<BLOCK_ENTITY> blockEntityType)
	{
		blockEntityTypeMap.put(item, blockEntityType);
	}

	private <BLOCK_ENTITY extends TileEntity> BLOCK_ENTITY getOrCreateBlockEntity(TileEntityType<BLOCK_ENTITY> tileEntityType)
	{
		return (BLOCK_ENTITY) blockEntityMap.computeIfAbsent(tileEntityType, TileEntityType::create);
	}

	@Override
	public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack pose, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		Item item = stack.getItem();
		TileEntityType<?> tileEntityType = blockEntityTypeMap.get(item);

		if(tileEntityType != null)
		{
			TileEntity tileEntity = getOrCreateBlockEntity(tileEntityType);
			TileEntityRendererDispatcher.instance.renderItem(tileEntity, pose, buffer, combinedLight, combinedOverlay);
		}
	}
}
