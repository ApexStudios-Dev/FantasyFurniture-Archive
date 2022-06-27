package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModItems;

public final class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer
{
	private static final Lazy<BlockEntityWithoutLevelRenderer> INSTANCE = Lazy.of(() -> new ModBlockEntityWithoutLevelRenderer(Minecraft.getInstance()));

	private final WidowBloomBlockEntityRenderer widowBloomBlockEntityRenderer;

	private final Lazy<WidowBloomBlockEntity> widowBloomBlockEntity = Lazy.of(() -> {
		var blockState = ModBlocks.VENTHYR_WIDOW_BLOOM.getDefaultState();
		return ModElements.VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY.create(BlockPos.ZERO, blockState);
	});

	private ModBlockEntityWithoutLevelRenderer(Minecraft mc)
	{
		super(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());

		var ctx = new BlockEntityRendererProvider.Context(mc.getBlockEntityRenderDispatcher(), mc.getBlockRenderer(), mc.getEntityModels(), mc.font);
		widowBloomBlockEntityRenderer = new WidowBloomBlockEntityRenderer(ctx);
	}

	@Override
	public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack pose, MultiBufferSource buffer, int light, int overlay)
	{
		var partialTick = Minecraft.getInstance().getDeltaFrameTime();

		if(ModItems.VENTHYR_WIDOW_BLOOM.isIn(stack))
		{
			var blockEntity = widowBloomBlockEntity.get();
			widowBloomBlockEntityRenderer.render(blockEntity, partialTick, pose, buffer, light, overlay);
		}
		else
			super.renderByItem(stack, transformType, pose, buffer, light, overlay);
	}

	public static BlockEntityWithoutLevelRenderer getInstance()
	{
		return INSTANCE.get();
	}
}