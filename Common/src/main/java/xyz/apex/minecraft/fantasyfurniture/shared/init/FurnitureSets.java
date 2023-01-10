package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockFactory;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.registry.Registrar;
import xyz.apex.minecraft.apexcore.shared.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.shared.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.*;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface FurnitureSets
{
    static BlockBuilder<Block, Registrar, Registrar> wool(String furnitureSet)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/wool".formatted(furnitureSet))
                .flammability(30, 60)
                .initialProperties(Properties.BLOCK_WOOL)
        ;
    }

    static BlockBuilder<CarpetBlock, Registrar, Registrar> carpet(String furnitureSet)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/carpet".formatted(furnitureSet), CarpetBlock::new)
                .flammability(60, 20)
                .initialProperties(Properties.BLOCK_CARPET)
        ;
    }

    static void creativeModeTab(String furnitureSet, UnaryOperator<CreativeModeTab.Builder> builderFunc)
    {
        FantasyFurniture.REGISTRAR.creativeModeTab(furnitureSet, builderFunc::apply);
    }

    // region: Light
    // region: Wall
    static BlockBuilder<WallLightBlock, Registrar, Registrar> wallLight(String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<WallLightBlock, Registrar, Registrar> wallLight(String furnitureSet)
    {
        return wallLight(furnitureSet, () -> ParticleTypes.FLAME);
    }
    // endregion

    // region: Floor
    static BlockBuilder<FloorLightBlock, Registrar, Registrar> floorLight(String furnitureSet, MultiBlockType multiBlockType, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/floor_light".formatted(furnitureSet), multiBlockType, (multiBlockType1, properties) -> new FloorLightBlock(multiBlockType1, properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape, AllVoxelShapes::getFloorLightShape)
        ;
    }

    static BlockBuilder<FloorLightBlock, Registrar, Registrar> floorLight(String furnitureSet, MultiBlockType multiBlockType, Supplier<VoxelShape> baseShape)
    {
        return floorLight(furnitureSet, multiBlockType, () -> ParticleTypes.FLAME, baseShape);
    }
    // endregion

    // region: Chandelier
    static BlockBuilder<LightBlock, Registrar, Registrar> chandelier(String furnitureSet, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/chandelier".formatted(furnitureSet), properties -> new LightBlock(flameParticle, properties))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape)
        ;
    }

    static BlockBuilder<LightBlock, Registrar, Registrar> chandelier(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return chandelier(furnitureSet, () -> ParticleTypes.FLAME, baseShape);
    }
    // endregion
    // endregion

    // region: Table
    private static <T extends Block> BlockBuilder<T, Registrar, Registrar> applyTableProperties(BlockBuilder<T, Registrar, Registrar> builder)
    {
        return builder
                .flammability(5, 20)
                .initialProperties(Properties.BLOCK_PLANKS)
                .noOcclusion()
        ;
    }

    static BlockBuilder<Block, Registrar, Registrar> tableSmall(String furnitureSet)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/table_small".formatted(furnitureSet))
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    private static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> table(String furnitureSet, String type, MultiBlockType multiBlockType)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/table_%s".formatted(furnitureSet, type), multiBlockType, SimpleMultiBlock.WithHorizontalFacing::new)
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> tableWide(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "wide", AllMultiBlockTypes.MB_1x1x2_FACING)
                .hitbox(baseShape, AllVoxelShapes::getTableWideShape)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> tableLarge(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large", AllMultiBlockTypes.MB_2x1x2_FACING)
                .hitbox(baseShape, AllVoxelShapes::getTableLargeShape)
        ;
    }
    // endregion

    // region: Seat
    private static <T extends Block & SeatBlock> BlockBuilder<T, Registrar, Registrar> applySeatProperties(BlockBuilder<T, Registrar, Registrar> blockBuilder)
    {
        return blockBuilder.initialProperties(Properties.BLOCK_PLANKS);
    }

    private static BlockBuilder<SimpleSeatBlock, Registrar, Registrar> seat(String furnitureSet, String seatType)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/%s".formatted(furnitureSet, seatType), SimpleSeatBlock::new)
                .transform(FurnitureSets::applySeatProperties)
        ;
    }

    private static <T extends Block & SeatBlock.MultiBlock> BlockBuilder<T, Registrar, Registrar> seat(String furnitureSet, String seatType, MultiBlockType multiBlockType, MultiBlockFactory<T> factory)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/%s".formatted(furnitureSet, seatType), multiBlockType, factory)
                .transform(FurnitureSets::applySeatProperties)
        ;
    }

    private static BlockBuilder<SimpleSeatBlock.WithMultiBlock, Registrar, Registrar> seat(String furnitureSet, String seatType, MultiBlockType multiBlockType)
    {
        return seat(furnitureSet, seatType, multiBlockType, SimpleSeatBlock.WithMultiBlock::new);
    }

    static BlockBuilder<SimpleSeatBlock.WithMultiBlock, Registrar, Registrar> bench(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "bench", AllMultiBlockTypes.MB_1x1x2_FACING).hitbox(baseShape, AllVoxelShapes::getBenchShape);
    }

    static BlockBuilder<SimpleSeatBlock.WithMultiBlock.AtOriginOnly, Registrar, Registrar> chair(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "chair", AllMultiBlockTypes.MB_1x2x1_FACING, SimpleSeatBlock.WithMultiBlock.AtOriginOnly::new)
                .hitbox(baseShape, AllVoxelShapes::getChairShape)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<SimpleSeatBlock, Registrar, Registrar> cushion(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "cushion")
                .hitbox(baseShape, AllVoxelShapes::getCushionShape)
        ;
    }

    static BlockBuilder<SimpleSeatBlock, Registrar, Registrar> stool(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "stool")
                .hitbox(baseShape, AllVoxelShapes::getStoolShape)
        ;
    }
    // endregion

    static BlockBuilder<ChestBlock, Registrar, Registrar> chest(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/chest".formatted(furnitureSet), AllMultiBlockTypes.MB_1x1x2_FACING, ChestBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getChestShape)
        ;
    }

    static BlockBuilder<BookshelfBlock, Registrar, Registrar> bookshelf(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/bookshelf".formatted(furnitureSet), AllMultiBlockTypes.MB_1x2x2_FACING, BookshelfBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getBookshelfShape)
        ;
    }

    // region: Desk
    private static BlockBuilder<DeskBlock, Registrar, Registrar> desk(String furnitureSet, String type, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/desk_%s".formatted(furnitureSet, type), AllMultiBlockTypes.MB_1x1x2_FACING, DeskBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getDeskShape)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<DeskBlock, Registrar, Registrar> deskLeft(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return desk(furnitureSet, "left", baseShape);
    }

    static BlockBuilder<DeskBlock, Registrar, Registrar> deskRight(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return desk(furnitureSet, "right", baseShape);
    }
    // endregion

    static BlockBuilder<DrawerBlock, Registrar, Registrar> drawer(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/drawer".formatted(furnitureSet), DrawerBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getDrawerShape)
        ;
    }

    static BlockBuilder<DresserBlock, Registrar, Registrar> dresser(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/dresser".formatted(furnitureSet), AllMultiBlockTypes.MB_1x1x2_FACING, DresserBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getDresserShape)
        ;
    }

    static BlockBuilder<LockboxBlock, Registrar, Registrar> lockbox(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/lockbox".formatted(furnitureSet), LockboxBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getLockboxShape)
        ;
    }

    // region: Wardrobe
    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> wardrobeTop(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/wardrobe_top".formatted(furnitureSet), AllMultiBlockTypes.MB_1x1x2_FACING, SimpleMultiBlock.WithHorizontalFacing::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getWardrobeTopShape)
                .noOcclusion()
        ;
    }

    static BlockBuilder<WardrobeBlock, Registrar, Registrar> wardrobeBottom(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/wardrobe_bottom".formatted(furnitureSet), AllMultiBlockTypes.MB_1x2x2_FACING, WardrobeBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getWardrobeBottomShape)
        ;
    }
    // endregion

    static BlockBuilder<SimpleHorizontalFacingBlock, Registrar, Registrar> paintingSmall(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/painting_small".formatted(furnitureSet), SimpleHorizontalFacingBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getPaintingSmallShape)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> paintingWide(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/painting_wide".formatted(furnitureSet), AllMultiBlockTypes.MB_1x1x2_FACING, SimpleMultiBlock.WithHorizontalFacing::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getPaintingWideShape)
        ;
    }
}
