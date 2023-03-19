package xyz.apex.minecraft.fantasyfurniture.common.client.renderer;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public interface MultiBlockRendererPlacementModifier
{
    BlockState getBlockStateForRenderPlacement(BlockPlaceContext ctx);
}
