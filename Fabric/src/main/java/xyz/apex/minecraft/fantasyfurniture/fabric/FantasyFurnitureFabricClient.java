package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

import net.minecraft.client.renderer.RenderType;

import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class FantasyFurnitureFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // NOTE: These must match the values from the block model files in the common module
        //  Forge loads these directly from the model files
        //  Fabric does not have the ability to do this, so we have to manually do it here
        BlockRenderLayerMap.INSTANCE.putBlock(NordicSet.WALL_LIGHT.get(), RenderType.cutout());
    }
}
