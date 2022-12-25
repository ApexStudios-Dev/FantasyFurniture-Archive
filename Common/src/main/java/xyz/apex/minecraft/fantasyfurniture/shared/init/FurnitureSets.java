package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockTypes;
import xyz.apex.minecraft.apexcore.shared.registry.builders.BlockBuilder;
import xyz.apex.minecraft.apexcore.shared.registry.builders.BlockBuilders;
import xyz.apex.minecraft.apexcore.shared.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.SimpleBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.TableMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

import java.util.function.Supplier;

public interface FurnitureSets
{
    static BlockBuilder<Block> wool(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/wool".formatted(furnitureSet))
                .flammability(30, 60)
                .initialProperties(Properties.BLOCK_WOOL)
        ;
    }

    static BlockBuilder<CarpetBlock> carpet(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/carpet".formatted(furnitureSet), CarpetBlock::new)
                .flammability(60, 20)
                .initialProperties(Properties.BLOCK_CARPET)
        ;
    }

    // region: Light
    // region: Wall
    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<WallLightBlock> wallLight(String furnitureSet)
    {
        return wallLight(furnitureSet, () -> ParticleTypes.FLAME);
    }
    // endregion

    // region: Floor
    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet, MultiBlockType multiBlockType, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/floor_light".formatted(furnitureSet), multiBlockType, (multiBlockType1, properties) -> new FloorLightBlock(multiBlockType1, properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
        ;
    }

    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet, MultiBlockType multiBlockType)
    {
        return floorLight(furnitureSet, multiBlockType, () -> ParticleTypes.FLAME);
    }
    // endregion
    // endregion

    // region: Table
    MultiBlockType MB_TABLE_WIDE = MultiBlockTypes.MB_1x1x2.clone().rotateLocalSpaceForFacing(TableMultiBlock.FACING).build();
    MultiBlockType MB_TABLE_LARGE = MultiBlockTypes.MB_2x1x2.clone().rotateLocalSpaceForFacing(TableMultiBlock.FACING).build();

    private static <T extends Block> BlockBuilder<T> applyTableProperties(BlockBuilder<T> builder)
    {
        return builder
                .flammability(5, 20)
                .initialProperties(Properties.BLOCK_PLANKS)
                .noOcclusion()
        ;
    }

    static BlockBuilder<SimpleBlock> tableSmall(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/table_small".formatted(furnitureSet), SimpleBlock::new)
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    private static BlockBuilder<TableMultiBlock> table(String furnitureSet, String type, MultiBlockType multiBlockType)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/table_%s".formatted(furnitureSet, type), multiBlockType, TableMultiBlock::new)
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    static BlockBuilder<TableMultiBlock> tableWide(String furnitureSet)
    {
        return table(furnitureSet, "wide", MB_TABLE_WIDE);
    }

    static BlockBuilder<TableMultiBlock> tableLarge(String furnitureSet)
    {
        return table(furnitureSet, "large", MB_TABLE_LARGE);
    }
    // endregion
}
