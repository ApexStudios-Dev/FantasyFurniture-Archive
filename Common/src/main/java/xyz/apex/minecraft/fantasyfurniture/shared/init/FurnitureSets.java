package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockTypes;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.registry.builders.BlockBuilder;
import xyz.apex.minecraft.apexcore.shared.registry.builders.BlockBuilders;
import xyz.apex.minecraft.apexcore.shared.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.SeatBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.SimpleSeatBlock;
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
    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet, MultiBlockType multiBlockType, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/floor_light".formatted(furnitureSet), multiBlockType, (multiBlockType1, properties) -> new FloorLightBlock(multiBlockType1, properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape, AllVoxelShapes::getFloorLightShape)
        ;
    }

    static BlockBuilder<FloorLightBlock> floorLight(String furnitureSet, MultiBlockType multiBlockType, Supplier<VoxelShape> baseShape)
    {
        return floorLight(furnitureSet, multiBlockType, () -> ParticleTypes.FLAME, baseShape);
    }
    // endregion
    // endregion

    // region: Table
    MultiBlockType MB_TABLE_WIDE = MultiBlockTypes.MB_1x1x2.clone().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_TABLE_LARGE = MultiBlockTypes.MB_2x1x2.clone().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();

    private static <T extends Block> BlockBuilder<T> applyTableProperties(BlockBuilder<T> builder)
    {
        return builder
                .flammability(5, 20)
                .initialProperties(Properties.BLOCK_PLANKS)
                .noOcclusion()
        ;
    }

    static BlockBuilder<Block> tableSmall(String furnitureSet)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/table_small".formatted(furnitureSet))
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    private static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing> table(String furnitureSet, String type, MultiBlockType multiBlockType)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/table_%s".formatted(furnitureSet, type), multiBlockType, SimpleMultiBlock.WithHorizontalFacing::new)
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing> tableWide(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "wide", MB_TABLE_WIDE)
                .hitbox(baseShape, AllVoxelShapes::getTableWideShape)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing> tableLarge(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large", MB_TABLE_LARGE)
                .hitbox(baseShape, AllVoxelShapes::getTableLargeShape)
        ;
    }
    // endregion

    // region: Seat
    private static <T extends Block & SeatBlock> BlockBuilder<T> applySeatProperties(BlockBuilder<T> blockBuilder)
    {
        return blockBuilder.initialProperties(Properties.BLOCK_PLANKS);
    }

    private static BlockBuilder<SimpleSeatBlock> seat(String furnitureSet, String seatType)
    {
        return BlockBuilders
                .builder(FantasyFurniture.ID, "%s/%s".formatted(furnitureSet, seatType), SimpleSeatBlock::new)
                .transform(FurnitureSets::applySeatProperties)
        ;
    }

    private static BlockBuilder<SimpleSeatBlock.WithMultiBlock> seat(String furnitureSet, String seatType, MultiBlockType multiBlockType)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/%s".formatted(furnitureSet, seatType), multiBlockType, SimpleSeatBlock.WithMultiBlock::new)
                .transform(FurnitureSets::applySeatProperties)
        ;
    }

    static BlockBuilder<SimpleSeatBlock.WithMultiBlock> bench(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "bench", MB_TABLE_WIDE).hitbox(baseShape, AllVoxelShapes::getBenchShape);
    }
    // endregion
}
