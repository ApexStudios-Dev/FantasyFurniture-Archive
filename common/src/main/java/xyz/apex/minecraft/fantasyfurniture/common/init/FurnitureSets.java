package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.hooks.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.hooks.PointOfInterestHooks;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockBuilder;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface FurnitureSets
{
    static BlockBuilder<Block> wool(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/wool".formatted(furnitureSet), Block::new)
                .flammability(30, 60)
                .copyProperties(() -> Blocks.WHITE_WOOL)
        ;
    }

    static BlockBuilder<CarpetBlock> carpet(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/carpet".formatted(furnitureSet), CarpetBlock::new)
                .flammability(60, 20)
                .copyProperties(() -> Blocks.WHITE_CARPET)
        ;
    }

    static void creativeModeTab(String ownerId, String furnitureSet, UnaryOperator<CreativeModeTab.Builder> builderFunc)
    {
        CreativeModeTabHooks.getInstance().register(new ResourceLocation(ownerId, furnitureSet), builderFunc);
    }

    static BlockBuilder<WallLightBlock> wallLight(String ownerId, String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilder
                .builder(ownerId, "%s/wall_light".formatted(furnitureSet), properties -> new WallLightBlock(flameParticle, properties))
                .copyProperties(() -> Blocks.WALL_TORCH)
        ;
    }

    static BlockBuilder<FloorLightBlock> floorLight(String ownerId, String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilder
                .builder(ownerId, "%s/floor_light".formatted(furnitureSet), properties -> new FloorLightBlock(flameParticle, properties))
                .copyProperties(() -> Blocks.TORCH)
        ;
    }

    static BlockBuilder<CeilingLightBlock> ceilingLight(String ownerId, String furnitureSet, Supplier<ParticleOptions> flameParticle)
    {
        return BlockBuilder
                .builder(ownerId, "%s/ceiling_light".formatted(furnitureSet), properties -> new CeilingLightBlock(flameParticle, properties))
                .copyProperties(() -> Blocks.TORCH)
        ;
    }

    private static <T extends SimpleComponentBlock> BlockBuilder<T> table(String ownerId, String furnitureSet, String tableType, BlockBuilder.BlockFactory<T> blockFactory)
    {
        return BlockBuilder
                .builder(ownerId, "%s/table_%s".formatted(furnitureSet, tableType), blockFactory)
                .flammability(5, 20)
                .copyProperties(() -> Blocks.OAK_PLANKS)
                .noOcclusion() // TODO: is this needed?
        ;
    }

    static BlockBuilder<TableSmallBlock> tableSmall(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "small", TableSmallBlock::new);
    }

    static BlockBuilder<TableSmallBlock> tableSmallFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "small_fancy", TableSmallBlock::new);
    }

    static BlockBuilder<TableWideBlock> tableWide(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "wide", TableWideBlock::new);
    }

    static BlockBuilder<TableWideBlock> tableWideFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "wide_fancy", TableWideBlock::new);
    }

    static BlockBuilder<TableLargeBlock> tableLarge(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "large", TableLargeBlock::new);
    }

    static BlockBuilder<TableLargeBlock> tableLargeFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "large_fancy", TableLargeBlock::new);
    }

    private static <T extends Block & ComponentBlock> BlockBuilder<T> seat(String ownerId, String furnitureSet, String seatType, BlockBuilder.BlockFactory<T> factory)
    {
        return BlockBuilder
                .builder(ownerId, "%s/%s".formatted(furnitureSet, seatType), factory)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<BenchBlock> bench(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "bench", BenchBlock::new);
    }

    static BlockBuilder<ChairBlock> chair(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "chair", ChairBlock::new);
    }

    static BlockBuilder<CushionBlock> cushion(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "cushion", CushionBlock::new);
    }

    static BlockBuilder<StoolBlock> stool(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "stool", StoolBlock::new);
    }

    static BlockBuilder<ChestBlock> chest(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/chest".formatted(furnitureSet), ChestBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<BookshelfBlock> bookshelf(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/bookshelf".formatted(furnitureSet), BookshelfBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    private static BlockBuilder<DeskBlock> desk(String ownerId, String furnitureSet, String type)
    {
        return BlockBuilder
                .builder(ownerId, "%s/desk_%s".formatted(furnitureSet, type), DeskBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<DeskBlock> deskLeft(String ownerId, String furnitureSet)
    {
        return desk(ownerId, furnitureSet, "left");
    }

    static BlockBuilder<DeskBlock> deskRight(String ownerId, String furnitureSet)
    {
        return desk(ownerId, furnitureSet, "right");
    }

    static BlockBuilder<DrawerBlock> drawer(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/drawer".formatted(furnitureSet), DrawerBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<DresserBlock> dresser(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/dresser".formatted(furnitureSet), DresserBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<LockboxBlock> lockbox(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/lockbox".formatted(furnitureSet), LockboxBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<WardrobeTopBlock> wardrobeTop(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/wardrobe_top".formatted(furnitureSet), WardrobeTopBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
                .noOcclusion() // TODO: is this need?
        ;
    }

    static BlockBuilder<WardrobeBottomBlock> wardrobeBottom(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/wardrobe_bottom".formatted(furnitureSet), WardrobeBottomBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<PaintingSmallBlock> paintingSmall(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/painting_small".formatted(furnitureSet), PaintingSmallBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<PaintingWideBlock> paintingWide(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/painting_wide".formatted(furnitureSet), PaintingWideBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    private static <T extends OvenBlock> BlockBuilder<T> oven(String ownerId, String furnitureSet, BlockBuilder.BlockFactory<T> factory)
    {
        return BlockBuilder
                .builder(ownerId, "%s/oven".formatted(furnitureSet), factory)
                .initialProperties(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel(blockState -> blockState.getOptionalValue(OvenBlock.LIT).orElse(false) ? 13 : 0)
        ;
    }

    static BlockBuilder<OvenBlock> oven(String ownerId, String furnitureSet)
    {
        return oven(ownerId, furnitureSet, OvenBlock::new);
    }

    static BlockBuilder<OvenBlock.WithMultiBlock> ovenMultiBlock(String ownerId, String furnitureSet)
    {
        return oven(ownerId, furnitureSet, OvenBlock.WithMultiBlock::new);
    }

    private static BlockBuilder<DoorBlock> door(String ownerId, String furnitureSet, String doorType)
    {
        return BlockBuilder
                .builder(ownerId, "%s/door_%s".formatted(furnitureSet, doorType), DoorBlock::new)
                .copyProperties(() -> Blocks.OAK_DOOR)
        ;
    }

    static BlockBuilder<DoorBlock> doorSingle(String ownerId, String furnitureSet)
    {
        return door(ownerId, furnitureSet, "single");
    }

    static BlockBuilder<DoorBlock> doorDouble(String ownerId, String furnitureSet)
    {
        return door(ownerId, furnitureSet, "double");
    }

    private static <T extends Block & ComponentBlock> BlockBuilder<T> bed(String ownerId, String furnitureSet, String bedType, BlockBuilder.BlockFactory<T> factory)
    {
        return BlockBuilder
                .builder(ownerId, "%s/bed_%s".formatted(furnitureSet, bedType), factory)
                .initialProperties(Material.WOOL, MaterialColor.WOOL)
                .sound(SoundType.WOOD)
                .strength(.2F)
                .noOcclusion()
                .onRegister(block -> RegistryManager.get(ownerId).getRegistry(Registries.POINT_OF_INTEREST_TYPE).registerCallback(() -> PointOfInterestHooks.registerHomePoint(() -> block)))
        ;
    }

    static BlockBuilder<BedSingleBlock> bedSingle(String ownerId, String furnitureSet)
    {
        return bed(ownerId, furnitureSet, "single", BedSingleBlock::new);
    }

    static BlockBuilder<BedDoubleBlock> bedDouble(String ownerId, String furnitureSet)
    {
        return bed(ownerId, furnitureSet, "double", BedDoubleBlock::new);
    }

    static BlockBuilder<ShelfBlock> shelf(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/shelf".formatted(furnitureSet), ShelfBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<SofaBlock> sofa(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/sofa".formatted(furnitureSet), SofaBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static BlockBuilder<CounterBlock> counter(String ownerId, String furnitureSet)
    {
        return BlockBuilder
                .builder(ownerId, "%s/counter".formatted(furnitureSet), CounterBlock::new)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }
}
