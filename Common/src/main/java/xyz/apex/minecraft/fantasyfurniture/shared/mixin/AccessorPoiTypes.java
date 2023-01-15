package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

@Mixin(PoiTypes.class)
public interface AccessorPoiTypes
{
    @Accessor("BEDS")
    static Set<BlockState> FantasyFurniture$getBeds()
    {
        throw new AssertionError("Internal Mixin Error!");
    }
}
