package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.registry.BlockBuilder;
import xyz.apex.minecraft.apexcore.shared.registry.BlockBuilders;
import xyz.apex.minecraft.apexcore.shared.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

import java.util.function.Supplier;

public interface FurnitureSets
{
    static BlockBuilder<Block> wool(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/wool".formatted(furnitureSet))
                // .flammability(30, 60) // TODO
                .initialProperties(Properties.BLOCK_WOOL)
        ;
    }

    static BlockBuilder<CarpetBlock> carpet(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/carpet".formatted(furnitureSet), CarpetBlock::new)
                // .flammability(60, 20) // TODO
                .initialProperties(Properties.BLOCK_CARPET)
        ;
    }

    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
        ;
    }

    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet)
    {
        return wallLight(furnitureSet, () -> ParticleTypes.FLAME);
    }

    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/floor_light".formatted(furnitureSet), properties -> new FloorLightBlock(properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
        ;
    }

    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet)
    {
        return floorLight(furnitureSet, () -> ParticleTypes.FLAME);
    }
}
