package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockFactory;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.common.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;

import java.util.function.BiFunction;
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
    static BlockBuilder<WallLightBlock, Registrar, Registrar> wallLight(String furnitureSet, Supplier<VoxelShape> baseShape, Supplier<ParticleOptions> flameParticle)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape, AllVoxelShapes::getWallLightShape)
        ;
    }

    static BlockBuilder<WallLightBlock, Registrar, Registrar> wallLight(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return wallLight(furnitureSet, baseShape, () -> ParticleTypes.FLAME);
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

    static BlockBuilder<FloorLightBlock.WithHorizontalFacing, Registrar, Registrar> floorLightFacing(String furnitureSet, MultiBlockType multiBlockType, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/floor_light".formatted(furnitureSet), multiBlockType, (multiBlockType1, properties) -> new FloorLightBlock.WithHorizontalFacing(multiBlockType1, properties, flameParticle))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape, AllVoxelShapes::getFloorLightShape)
        ;
    }

    static BlockBuilder<FloorLightBlock.WithHorizontalFacing, Registrar, Registrar> floorLightFacing(String furnitureSet, MultiBlockType multiBlockType, Supplier<VoxelShape> baseShape)
    {
        return floorLightFacing(furnitureSet, multiBlockType, () -> ParticleTypes.FLAME, baseShape);
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

    static BlockBuilder<SimpleHorizontalFacingBlock, Registrar, Registrar> tableSmall(String furnitureSet)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/table_small".formatted(furnitureSet), SimpleHorizontalFacingBlock::new)
                .transform(FurnitureSets::applyTableProperties)
        ;
    }

    static BlockBuilder<SimpleHorizontalFacingBlock, Registrar, Registrar> tableSmallFancy(String furnitureSet)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/table_small_fancy".formatted(furnitureSet), SimpleHorizontalFacingBlock::new)
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
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> tableWideFancy(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "wide_fancy", AllMultiBlockTypes.MB_1x1x2_FACING)
                .hitbox(baseShape, AllVoxelShapes::getTableWideShape)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> tableLarge(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large", AllMultiBlockTypes.MB_2x1x2_FACING)
                .hitbox(baseShape, AllVoxelShapes::getTableLargeShape)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing, Registrar, Registrar> tableLargeFancy(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large_fancy", AllMultiBlockTypes.MB_2x1x2_FACING)
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

    // region: Oven
    static BlockBuilder<OvenBlock, Registrar, Registrar> oven(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/oven".formatted(furnitureSet), OvenBlock::new)
                .copyFrom(() -> Blocks.SMOKER)
                .hitbox(baseShape, AllVoxelShapes::getOvenShape)
        ;
    }

    static BlockBuilder<OvenBlock.AsMultiBlock, Registrar, Registrar> ovenMultiBlock(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/oven".formatted(furnitureSet), AllMultiBlockTypes.MB_1x1x2_FACING, OvenBlock.AsMultiBlock::new)
                .copyFrom(() -> Blocks.SMOKER)
                .hitbox(baseShape, AllVoxelShapes::getOvenShape)
        ;
    }
    // endregion

    // region: Doors
    private static BlockBuilder<DoorMultiBlock, Registrar, Registrar> door(String furnitureSet, String doorType, DoorMultiBlock.DoorSounds doorSounds, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/door_%s".formatted(furnitureSet, doorType), AllMultiBlockTypes.MB_1x2x1_FACING_DOOR, (multiBlockType, properties) -> new DoorMultiBlock(multiBlockType, doorSounds, properties))
                .copyFrom(() -> Blocks.OAK_DOOR)
                .hitbox(baseShape, AllVoxelShapes::getDoorShape)
        ;
    }

    static BlockBuilder<DoorMultiBlock, Registrar, Registrar> doorSingle(String furnitureSet, DoorMultiBlock.DoorSounds doorSounds, Supplier<VoxelShape> baseShape)
    {
        return door(furnitureSet, "single", doorSounds, baseShape);
    }

    static BlockBuilder<DoorMultiBlock, Registrar, Registrar> doorDouble(String furnitureSet, DoorMultiBlock.DoorSounds doorSounds, Supplier<VoxelShape> baseShape)
    {
        return door(furnitureSet, "double", doorSounds, baseShape);
    }
    // endregion

    // region: Beds
    private static BlockBuilder<BedMultiBlock, Registrar, Registrar> bed(String furnitureSet, String bedType, MultiBlockType multiBlockType)
    {
        return FantasyFurniture.REGISTRAR
                .multiBlock("%s/bed_%s".formatted(furnitureSet, bedType), multiBlockType, BedMultiBlock::new)
                .copyFrom(() -> Blocks.WHITE_BED)
                .onRegisterAfter(Registries.POINT_OF_INTEREST_TYPE, BedMultiBlock::registerCustomHomePoi)
        ;
    }

    static BlockBuilder<BedMultiBlock, Registrar, Registrar> bedSingle(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return bed(furnitureSet, "single", AllMultiBlockTypes.MB_2x1x1_FACING_BED)
                .hitbox(baseShape, AllVoxelShapes::getBedSingleShape)
        ;
    }

    static BlockBuilder<BedMultiBlock, Registrar, Registrar> bedDouble(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return bed(furnitureSet, "double", AllMultiBlockTypes.MB_2x1x2_FACING_BED)
                .hitbox(baseShape, AllVoxelShapes::getBedDoubleShape)
        ;
    }
    // endregion

    static BlockBuilder<ShelfBlock, Registrar, Registrar> shelf(String furnitureSet, Supplier<VoxelShape> baseShape, BiFunction<ShelfBlock, BlockState, VoxelShape> shapeGetter)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/shelf".formatted(furnitureSet), ShelfBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, (shape, block, blockState) -> shapeGetter.apply(block, blockState))
        ;
    }

    static BlockBuilder<SofaBlock, Registrar, Registrar> sofa(String furnitureSet, Supplier<VoxelShape> baseShape, BiFunction<SofaBlock, BlockState, VoxelShape> shapeGetter)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/sofa".formatted(furnitureSet), SofaBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, (shape, block, blockState) -> shapeGetter.apply(block, blockState))
        ;
    }

    static BlockBuilder<CounterBlock, Registrar, Registrar> counter(String furnitureSet, Supplier<VoxelShape> baseShape, BiFunction<CounterBlock, BlockState, VoxelShape> shapeGetter)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/counter".formatted(furnitureSet), CounterBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, (shape, block, blockState) -> shapeGetter.apply(block, blockState))
        ;
    }
}