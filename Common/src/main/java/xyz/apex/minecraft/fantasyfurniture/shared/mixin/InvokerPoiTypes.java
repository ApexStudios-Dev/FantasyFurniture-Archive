package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

@Mixin(PoiTypes.class)
public interface InvokerPoiTypes
{
    @Invoker("registerBlockStates")
    static void FantasyFurniture$registerBlockStates(Holder<PoiType> poiType, Set<BlockState> blockStates)
    {
        throw new AssertionError("Internal Mixin Error!");
    }
}
