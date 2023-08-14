package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.AllBlockEntities;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllItems;
import xyz.apex.forge.fantasyfurniture.common.block.entity.SkullBlossomsBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.block.entity.WidowBloomBlockEntity;

@SideOnly(SideOnly.Side.CLIENT)
public final class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer
{
	private static final Lazy<BlockEntityWithoutLevelRenderer> INSTANCE = Lazy.of(() -> new ModBlockEntityWithoutLevelRenderer(Minecraft.getInstance()));

	private final WidowBloomBlockEntityRenderer widowBloomBlockEntityRenderer;
	private final SkullBlossomsBlockEntityRenderer skullBlossomsBlockEntityRenderer;

	private final Lazy<WidowBloomBlockEntity> widowBloomBlockEntity = Lazy.of(() -> {
		var blockState = AllBlocks.VENTHYR_WIDOW_BLOOM.defaultBlockState();
		return AllBlockEntities.VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY.create(BlockPos.ZERO, blockState);
	});

	private final Lazy<SkullBlossomsBlockEntity> skeletonSkullBlossomsBlockEntityLazy = Lazy.of(() -> {
		var blockState = AllBlocks.BONE_SKELETON_SKULL_BLOSSOMS.defaultBlockState();
		return AllBlockEntities.BONE_SKULL_BLOSSOMS_BLOCK_ENTITY.create(BlockPos.ZERO, blockState);
	});

	private final Lazy<SkullBlossomsBlockEntity> witherSkullBlossomsBlockEntityLazy = Lazy.of(() -> {
		var blockState = AllBlocks.BONE_WITHER_SKULL_BLOSSOMS.defaultBlockState();
		return AllBlockEntities.BONE_SKULL_BLOSSOMS_BLOCK_ENTITY.create(BlockPos.ZERO, blockState);
	});

	private ModBlockEntityWithoutLevelRenderer(Minecraft mc)
	{
		super(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());

		var ctx = new BlockEntityRendererProvider.Context(mc.getBlockEntityRenderDispatcher(), mc.getBlockRenderer(), mc.getItemRenderer(), mc.getEntityRenderDispatcher(), mc.getEntityModels(), mc.font);
		widowBloomBlockEntityRenderer = new WidowBloomBlockEntityRenderer(ctx);
		skullBlossomsBlockEntityRenderer = new SkullBlossomsBlockEntityRenderer(ctx);
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack pose, MultiBufferSource buffer, int light, int overlay)
	{
		var partialTick = Minecraft.getInstance().getDeltaFrameTime();

		if(AllItems.VENTHYR_WIDOW_BLOOM.isIn(stack))
		{
			var blockEntity = widowBloomBlockEntity.get();
			widowBloomBlockEntityRenderer.render(blockEntity, partialTick, pose, buffer, light, overlay);
		}
		else if(AllItems.BONE_SKELETON_SKULL_BLOSSOMS.isIn(stack))
		{
			var blockEntity = skeletonSkullBlossomsBlockEntityLazy.get();
			skullBlossomsBlockEntityRenderer.render(blockEntity, partialTick, pose, buffer, light, overlay);
		}
		else if(AllItems.BONE_WITHER_SKULL_BLOSSOMS.isIn(stack))
		{
			var blockEntity = witherSkullBlossomsBlockEntityLazy.get();
			skullBlossomsBlockEntityRenderer.render(blockEntity, partialTick, pose, buffer, light, overlay);
		}
		else
			super.renderByItem(stack, displayContext, pose, buffer, light, overlay);
	}

	public static BlockEntityWithoutLevelRenderer getInstance()
	{
		return INSTANCE.get();
	}
}
