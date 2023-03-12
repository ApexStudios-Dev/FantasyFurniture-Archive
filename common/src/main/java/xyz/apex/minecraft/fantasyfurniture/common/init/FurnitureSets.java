package xyz.apex.minecraft.fantasyfurniture.common.init;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.ChestBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.DoorBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.OvenComponent;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
                .block("%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(flameParticle, properties))
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
    static BlockBuilder<FloorLightBlock, Registrar, Registrar> floorLight(String furnitureSet, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/floor_light".formatted(furnitureSet), properties -> new FloorLightBlock(flameParticle, properties))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape, AllVoxelShapes::getFloorLightShape)
        ;
    }

    static BlockBuilder<FloorLightBlock, Registrar, Registrar> floorLight(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return floorLight(furnitureSet, () -> ParticleTypes.FLAME, baseShape);
    }
    // endregion

    // region: Ceiling
    static BlockBuilder<CeilingLightBlock, Registrar, Registrar> ceilingLight(String furnitureSet, Supplier<ParticleOptions> flameParticle, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/ceiling_light".formatted(furnitureSet), properties -> new CeilingLightBlock(flameParticle, properties))
                .initialProperties(Properties.BLOCK_TORCH)
                .hitbox(baseShape)
        ;
    }

    static BlockBuilder<CeilingLightBlock, Registrar, Registrar> ceilingLight(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return ceilingLight(furnitureSet, () -> ParticleTypes.FLAME, baseShape);
    }
    // endregion
    // endregion

    // region: Table
    private static <T extends SimpleComponentBlock> BlockBuilder<T, Registrar, Registrar> table(String furnitureSet, String tableType, BlockBuilder.Factory<T> blockFactory)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/table_%s".formatted(furnitureSet, tableType), blockFactory)
                .flammability(5, 20)
                .initialProperties(Properties.BLOCK_PLANKS)
                .noOcclusion()
        ;
    }

    static BlockBuilder<TableSmallBlock, Registrar, Registrar> tableSmall(String furnitureSet)
    {
        return table(furnitureSet, "small", TableSmallBlock::new);
    }

    static BlockBuilder<TableSmallBlock, Registrar, Registrar> tableSmallFancy(String furnitureSet)
    {
        return table(furnitureSet, "small_fancy", TableSmallBlock::new);
    }

    static BlockBuilder<TableWideBlock, Registrar, Registrar> tableWide(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "wide", TableWideBlock::new).hitbox(baseShape, AllVoxelShapes::getTableWideShape);
    }

    static BlockBuilder<TableWideBlock, Registrar, Registrar> tableWideFancy(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "wide_fancy", TableWideBlock::new).hitbox(baseShape, AllVoxelShapes::getTableWideShape);
    }

    static BlockBuilder<TableLargeBlock, Registrar, Registrar> tableLarge(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large", TableLargeBlock::new).hitbox(baseShape, AllVoxelShapes::getTableLargeShape);
    }

    static BlockBuilder<TableLargeBlock, Registrar, Registrar> tableLargeFancy(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large_fancy", TableLargeBlock::new).hitbox(baseShape, AllVoxelShapes::getTableLargeShape);
    }
    // endregion

    // region: Seat
    private static <T extends Block & ComponentBlock> BlockBuilder<T, Registrar, Registrar> seat(String furnitureSet, String seatType, BlockBuilder.Factory<T> factory)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/%s".formatted(furnitureSet, seatType), factory)
                .initialProperties(Properties.BLOCK_PLANKS)
        ;
    }

    static BlockBuilder<BenchBlock, Registrar, Registrar> bench(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "bench", BenchBlock::new).hitbox(baseShape, AllVoxelShapes::getBenchShape);
    }

    static BlockBuilder<ChairBlock, Registrar, Registrar> chair(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "chair", ChairBlock::new).hitbox(baseShape, AllVoxelShapes::getChairShape);
    }

    static BlockBuilder<CushionBlock, Registrar, Registrar> cushion(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "cushion", CushionBlock::new).hitbox(baseShape, AllVoxelShapes::getCushionShape);
    }

    static BlockBuilder<StoolBlock, Registrar, Registrar> stool(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "stool", StoolBlock::new).hitbox(baseShape, AllVoxelShapes::getStoolShape);
    }
    // endregion

    static BlockBuilder<ChestBlock, Registrar, Registrar> chest(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/chest".formatted(furnitureSet), ChestBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getChestShape)
        ;
    }

    static BlockBuilder<BookshelfBlock, Registrar, Registrar> bookshelf(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/bookshelf".formatted(furnitureSet), BookshelfBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getBookshelfShape)
        ;
    }

    // region: Desk
    private static BlockBuilder<DeskBlock, Registrar, Registrar> desk(String furnitureSet, String type, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/desk_%s".formatted(furnitureSet, type), DeskBlock::new)
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
                .block("%s/dresser".formatted(furnitureSet), DresserBlock::new)
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
    static BlockBuilder<WardrobeTopBlock, Registrar, Registrar> wardrobeTop(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/wardrobe_top".formatted(furnitureSet), WardrobeTopBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getWardrobeTopShape)
                .noOcclusion()
        ;
    }

    static BlockBuilder<WardrobeBottomBlock, Registrar, Registrar> wardrobeBottom(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/wardrobe_bottom".formatted(furnitureSet), WardrobeBottomBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getWardrobeBottomShape)
        ;
    }
    // endregion

    static BlockBuilder<PaintingSmallBlock, Registrar, Registrar> paintingSmall(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/painting_small".formatted(furnitureSet), PaintingSmallBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getPaintingSmallShape)
        ;
    }

    static BlockBuilder<PaintingWideBlock, Registrar, Registrar> paintingWide(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/painting_wide".formatted(furnitureSet), PaintingWideBlock::new)
                .initialProperties(Properties.BLOCK_PLANKS)
                .hitbox(baseShape, AllVoxelShapes::getPaintingWideShape)
        ;
    }

    // region: Oven
    private static <T extends OvenBlock> BlockBuilder<T, Registrar, Registrar> oven(String furnitureSet, BlockBuilder.Factory<T> factory, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/oven".formatted(furnitureSet), factory)
                .initialProperties(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel(blockState -> blockState.getOptionalValue(OvenComponent.LIT).orElse(false) ? 13 : 0)
                .hitbox(baseShape, AllVoxelShapes::getOvenShape)
        ;
    }

    static BlockBuilder<OvenBlock, Registrar, Registrar> oven(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return oven(furnitureSet, OvenBlock::new, baseShape);
    }

    static BlockBuilder<OvenBlock.WithMultiBlock, Registrar, Registrar> ovenMultiBlock(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return oven(furnitureSet, OvenBlock.WithMultiBlock::new, baseShape);
    }
    // endregion

    // region: Doors
    private static BlockBuilder<DoorBlock, Registrar, Registrar> door(String furnitureSet, String doorType, Supplier<VoxelShape> baseShape)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/door_%s".formatted(furnitureSet, doorType), DoorBlock::new)
                .copyFrom(() -> Blocks.OAK_DOOR)
                .hitbox(baseShape, AllVoxelShapes::getDoorShape)
        ;
    }

    static BlockBuilder<DoorBlock, Registrar, Registrar> doorSingle(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return door(furnitureSet, "single", baseShape);
    }

    static BlockBuilder<DoorBlock, Registrar, Registrar> doorDouble(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return door(furnitureSet, "double", baseShape);
    }
    // endregion

    // region: Beds
    private static <T extends Block & ComponentBlock> BlockBuilder<T, Registrar, Registrar> bed(String furnitureSet, String bedType, BlockBuilder.Factory<T> factory)
    {
        return FantasyFurniture.REGISTRAR
                .block("%s/bed_%s".formatted(furnitureSet, bedType), factory)
                // .copyFrom(() -> Blocks.WHITE_BED)
                .initialProperties(Material.WOOL, MaterialColor.WOOL)
                .sound(SoundType.WOOD)
                .strength(.2F)
                .noOcclusion()
                .onRegisterAfter(Registries.POINT_OF_INTEREST_TYPE, FurnitureSets::registerCustomHomePoi)
        ;
    }

    static BlockBuilder<BedSingleBlock, Registrar, Registrar> bedSingle(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return bed(furnitureSet, "single", BedSingleBlock::new).hitbox(baseShape, AllVoxelShapes::getBedSingleShape);
    }

    static BlockBuilder<BedDoubleBlock, Registrar, Registrar> bedDouble(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return bed(furnitureSet, "double", BedDoubleBlock::new).hitbox(baseShape, AllVoxelShapes::getBedDoubleShape);
    }

    private static void registerCustomHomePoi(Block... blocks)
    {
        // collect all possible block states into set
        // block states must have the BedPart property
        // collect only block states that are for the `HEAD` part
        // same logic as vanilla `HOME` poi
        var blockStates = Arrays
                .stream(blocks)
                .map(Block::getStateDefinition)
                .map(StateDefinition::getPossibleStates)
                .flatMap(Collection::stream)
                .filter(s -> s.hasProperty(BedBlock.PART))
                .filter(s -> s.getValue(BedBlock.PART) == BedPart.HEAD)
                .collect(Collectors.toSet());

        if(PoiTypes.BEDS instanceof ImmutableSet) PoiTypes.BEDS = Sets.newHashSet(PoiTypes.BEDS);
        PoiTypes.BEDS.addAll(blockStates);
        var holder = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(PoiTypes.HOME);
        PoiTypes.registerBlockStates(holder, blockStates);
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
