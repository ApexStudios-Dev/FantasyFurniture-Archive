package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockBuilder;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

import java.util.function.Supplier;

public interface FurnitureSets
{
    static BlockBuilder<Block> wool(String furnitureSet)
    {
        return FantasyFurniture.Registries.BLOCKS
                .generic("%s/wool".formatted(furnitureSet))
                .flammability(30, 60)
                .initialProperties(FantasyFurniture.WOOL_PROPERTIES)
        ;
    }

    static BlockBuilder<CarpetBlock> carpet(String furnitureSet)
    {
        return FantasyFurniture.Registries.BLOCKS
                .builder("%s/carpet".formatted(furnitureSet), CarpetBlock::new)
                .flammability(60, 20)
                .initialProperties(FantasyFurniture.CARPET_PROPERTIES)
        ;
    }

    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return FantasyFurniture.Registries.BLOCKS
                .builder("%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(properties, flameParticle))
                .initialProperties(FantasyFurniture.TORCH_PROPERTIES)
        ;
    }

    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet)
    {
        return wallLight(furnitureSet, () -> ParticleTypes.FLAME);
    }
}
