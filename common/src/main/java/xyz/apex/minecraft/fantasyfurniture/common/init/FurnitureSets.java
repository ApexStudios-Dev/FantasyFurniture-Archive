package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolderFactory;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.hooks.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.hooks.PointOfInterestHooks;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockComponentBuilder;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.CarpetBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockEntityComponent;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface FurnitureSets
{
    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> block(String ownerId, String furnitureSet, BlockComponentHolderFactory<T> blockFactory)
    {
        return BlockComponentBuilder
                .builder(ownerId, "%s".formatted(furnitureSet), blockFactory)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> wool(String ownerId, String furnitureSet, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, "%s/wool".formatted(furnitureSet), blockFactory)
                .flammability(30, 60)
                .copyProperties(() -> Blocks.WHITE_WOOL)
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> carpet(String ownerId, String furnitureSet, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, "%s/carpet".formatted(furnitureSet), blockFactory)
                .registerComponent(CarpetBlockComponent.COMPONENT_TYPE)
                .flammability(60, 20)
                .copyProperties(() -> Blocks.WHITE_CARPET)
        ;
    }

    static void creativeModeTab(String ownerId, String furnitureSet, UnaryOperator<CreativeModeTab.Builder> builderFunc)
    {
        CreativeModeTabHooks.getInstance().register(new ResourceLocation(ownerId, furnitureSet), builderFunc);
    }

    static Supplier<BlockColor> blockColor()
    {
        return () -> (blockState, level, pos, tintIndex) -> DyeableBlockEntityComponent.buildBlockColor(blockState, level, pos, tintIndex).orElse(-1);
    }

    static Supplier<ItemColor> itemColor()
    {
        return () -> (stack, tintIndex) -> DyeableBlockEntityComponent.buildItemColor(stack, tintIndex).orElse(-1);
    }

    static BlockComponentBuilder<WallLightBlock> wallLight(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/wall_light".formatted(furnitureSet), WallLightBlock::new)
                .copyProperties(() -> Blocks.WALL_TORCH)
        ;
    }

    static BlockComponentBuilder<FloorLightBlock> floorLight(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/floor_light".formatted(furnitureSet), FloorLightBlock::new)
                .copyProperties(() -> Blocks.TORCH)
        ;
    }

    static BlockComponentBuilder<CeilingLightBlock> ceilingLight(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/ceiling_light".formatted(furnitureSet), CeilingLightBlock::new)
                .copyProperties(() -> Blocks.TORCH)
        ;
    }

    private static <T extends BaseBlockComponentHolder> BlockComponentBuilder<T> table(String ownerId, String furnitureSet, String tableType, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, "%s/table_%s".formatted(furnitureSet, tableType), blockFactory).flammability(5, 20);
    }

    static BlockComponentBuilder<TableSmallBlock> tableSmall(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "small", TableSmallBlock::new);
    }

    static BlockComponentBuilder<TableSmallBlock> tableSmallFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "small_fancy", TableSmallBlock::new);
    }

    static BlockComponentBuilder<TableWideBlock> tableWide(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "wide", TableWideBlock::new);
    }

    static BlockComponentBuilder<TableWideBlock> tableWideFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "wide_fancy", TableWideBlock::new);
    }

    static BlockComponentBuilder<TableLargeBlock> tableLarge(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "large", TableLargeBlock::new);
    }

    static BlockComponentBuilder<TableLargeBlock> tableLargeFancy(String ownerId, String furnitureSet)
    {
        return table(ownerId, furnitureSet, "large_fancy", TableLargeBlock::new);
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> seat(String ownerId, String furnitureSet, String seatType, BlockComponentHolderFactory<T> factory)
    {
        return block(ownerId, "%s/%s".formatted(furnitureSet, seatType), factory);
    }

    static BlockComponentBuilder<BenchBlock> bench(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "bench", BenchBlock::new);
    }

    static BlockComponentBuilder<ChairBlock> chair(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "chair", ChairBlock::new);
    }

    static BlockComponentBuilder<CushionBlock> cushion(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "cushion", CushionBlock::new);
    }

    static BlockComponentBuilder<StoolBlock> stool(String ownerId, String furnitureSet)
    {
        return seat(ownerId, furnitureSet, "stool", StoolBlock::new);
    }

    static BlockComponentBuilder<ChestBlock> chest(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/chest".formatted(furnitureSet), ChestBlock::new);
    }

    static BlockComponentBuilder<BookshelfBlock> bookshelf(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/bookshelf".formatted(furnitureSet), BookshelfBlock::new);
    }

    private static BlockComponentBuilder<DeskBlock> desk(String ownerId, String furnitureSet, String type)
    {
        return block(ownerId, "%s/desk_%s".formatted(furnitureSet, type), DeskBlock::new);
    }

    static BlockComponentBuilder<DeskBlock> deskLeft(String ownerId, String furnitureSet)
    {
        return desk(ownerId, furnitureSet, "left");
    }

    static BlockComponentBuilder<DeskBlock> deskRight(String ownerId, String furnitureSet)
    {
        return desk(ownerId, furnitureSet, "right");
    }

    static BlockComponentBuilder<DrawerBlock> drawer(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/drawer".formatted(furnitureSet), DrawerBlock::new);
    }

    static BlockComponentBuilder<DresserBlock> dresser(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/dresser".formatted(furnitureSet), DresserBlock::new);
    }

    static BlockComponentBuilder<LockboxBlock> lockbox(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/lockbox".formatted(furnitureSet), LockboxBlock::new);
    }

    static BlockComponentBuilder<WardrobeTopBlock> wardrobeTop(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/wardrobe_top".formatted(furnitureSet), WardrobeTopBlock::new);
    }

    static BlockComponentBuilder<WardrobeBottomBlock> wardrobeBottom(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/wardrobe_bottom".formatted(furnitureSet), WardrobeBottomBlock::new);
    }

    static BlockComponentBuilder<PaintingSmallBlock> paintingSmall(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/painting_small".formatted(furnitureSet), PaintingSmallBlock::new);
    }

    static BlockComponentBuilder<PaintingWideBlock> paintingWide(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/painting_wide".formatted(furnitureSet), PaintingWideBlock::new);
    }

    private static <T extends OvenBlock> BlockComponentBuilder<T> oven(String ownerId, String furnitureSet, BlockComponentHolderFactory<T> factory)
    {
        return block(ownerId, "%s/oven".formatted(furnitureSet), factory)
                .initialProperties(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel(blockState -> blockState.getOptionalValue(OvenBlock.LIT).orElse(false) ? 13 : 0)
        ;
    }

    static BlockComponentBuilder<OvenBlock> oven(String ownerId, String furnitureSet)
    {
        return oven(ownerId, furnitureSet, OvenBlock::new);
    }

    static BlockComponentBuilder<OvenBlock> ovenMultiBlock(String ownerId, String furnitureSet)
    {
        return oven(ownerId, furnitureSet, OvenBlock::new).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    private static BlockComponentBuilder<DoorBlock> door(String ownerId, String furnitureSet, String doorType)
    {
        return block(ownerId, "%s/door_%s".formatted(furnitureSet, doorType), DoorBlock::new)
                .copyProperties(() -> Blocks.OAK_DOOR)
        ;
    }

    static BlockComponentBuilder<DoorBlock> doorSingle(String ownerId, String furnitureSet)
    {
        return door(ownerId, furnitureSet, "single");
    }

    static BlockComponentBuilder<DoorBlock> doorDouble(String ownerId, String furnitureSet)
    {
        return door(ownerId, furnitureSet, "double");
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bed(String ownerId, String furnitureSet, String bedType, BlockComponentHolderFactory<T> factory)
    {
        return block(ownerId, "%s/bed_%s".formatted(furnitureSet, bedType), factory)
                .initialProperties(Material.WOOL, MaterialColor.WOOL)
                .sound(SoundType.WOOD)
                .strength(.2F)
                .noOcclusion()
                .onRegister(block -> RegistryManager.get(ownerId).getRegistry(Registries.POINT_OF_INTEREST_TYPE).registerCallback(() -> PointOfInterestHooks.registerHomePoint(() -> block)))
        ;
    }

    static BlockComponentBuilder<BedSingleBlock> bedSingle(String ownerId, String furnitureSet)
    {
        return bed(ownerId, furnitureSet, "single", BedSingleBlock::new);
    }

    static BlockComponentBuilder<BedDoubleBlock> bedDouble(String ownerId, String furnitureSet)
    {
        return bed(ownerId, furnitureSet, "double", BedDoubleBlock::new);
    }

    static BlockComponentBuilder<ShelfBlock> shelf(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/shelf".formatted(furnitureSet), ShelfBlock::new);
    }

    static BlockComponentBuilder<SofaBlock> sofa(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/sofa".formatted(furnitureSet), SofaBlock::new);
    }

    static BlockComponentBuilder<CounterBlock> counter(String ownerId, String furnitureSet)
    {
        return block(ownerId, "%s/counter".formatted(furnitureSet), CounterBlock::new);
    }
}
