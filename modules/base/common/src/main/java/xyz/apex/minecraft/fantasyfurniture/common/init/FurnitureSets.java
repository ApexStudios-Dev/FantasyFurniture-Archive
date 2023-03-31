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
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolderFactory;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.hooks.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.hooks.PointOfInterestHooks;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockComponentBuilder;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.*;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface FurnitureSets
{
    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> block(String ownerId, @Nullable String subsetType, String blockType, BlockComponentHolderFactory<T> blockFactory)
    {
        var blockTypeName = subsetType == null ? blockType : "%s/%s".formatted(subsetType, blockType);
        return BlockComponentBuilder
                .builder(ownerId, blockTypeName, blockFactory)
                .copyProperties(() -> Blocks.OAK_PLANKS)
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> facingBlock(String ownerId, @Nullable String subsetType, String blockType, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, subsetType, blockType, blockFactory).registerComponent(BlockComponentTypes.HORIZONTAL_FACING);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> wool(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, subsetType, "wool", blockFactory)
                .flammability(30, 60)
                .copyProperties(() -> Blocks.WHITE_WOOL)
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> carpet(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, subsetType, "carpet", blockFactory)
                .registerComponent(CarpetBlockComponent.COMPONENT_TYPE)
                .flammability(60, 20)
                .copyProperties(() -> Blocks.WHITE_CARPET)
        ;
    }

    static void creativeModeTab(String ownerId, @Nullable String subsetType, UnaryOperator<CreativeModeTab.Builder> builderFunc)
    {
        var creativeTabName = subsetType == null ? "blocks" : "%s.blocks".formatted(subsetType);
        CreativeModeTabHooks.getInstance().register(new ResourceLocation(ownerId, creativeTabName), builderFunc);
    }

    static Supplier<BlockColor> blockColor()
    {
        return () -> (blockState, level, pos, tintIndex) -> DyeableBlockEntityComponent.buildBlockColor(blockState, level, pos, tintIndex).orElse(-1);
    }

    static Supplier<ItemColor> itemColor()
    {
        return () -> (stack, tintIndex) -> DyeableBlockEntityComponent.buildItemColor(stack, tintIndex).orElse(-1);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> wallLight(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "wall_light", blockFactory)
                .copyProperties(() -> Blocks.WALL_TORCH)
                .registerComponent(LightBlockComponent.COMPONENT_TYPE, component -> component
                        .setPlaceOnWalls(true)
                        .setPlaceOnFloor(false)
                        .setPlaceOnCeilings(false)
                );
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> floorLight(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "floor_light", blockFactory)
                .copyProperties(() -> Blocks.TORCH)
                .registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x2x1))
                .registerComponent(LightBlockComponent.COMPONENT_TYPE, component -> component
                        .setPlaceOnWalls(false)
                        .setPlaceOnFloor(true)
                        .setPlaceOnCeilings(false)
                );
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> ceilingLight(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return block(ownerId, subsetType, "ceiling_light", blockFactory)
                .copyProperties(() -> Blocks.TORCH)
                .registerComponent(LightBlockComponent.COMPONENT_TYPE, component -> component
                        .setPlaceOnWalls(false)
                        .setPlaceOnFloor(false)
                        .setPlaceOnCeilings(true)
                );
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> table(String ownerId, @Nullable String subsetType, String tableType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "table_%s".formatted(tableType), blockFactory).flammability(5, 20);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableSmall(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "small", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableSmallFancy(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "small_fancy", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableWide(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "wide", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableWideFancy(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "wide_fancy", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableLarge(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "large", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_2x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> tableLargeFancy(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return table(ownerId, subsetType, "large_fancy", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_2x1x2_FACING));
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> seat(String ownerId, @Nullable String subsetType, String seatType, BlockComponentHolderFactory<T> factory)
    {
        return facingBlock(ownerId, subsetType, seatType, factory).registerComponent(SeatBlockComponent.COMPONENT_TYPE);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bench(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return seat(ownerId, subsetType, "bench", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> chair(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return seat(ownerId, subsetType, "chair", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x2x1_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> cushion(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return seat(ownerId, subsetType, "cushion", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> stool(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return seat(ownerId, subsetType, "stool", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> chest(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "chest", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bookshelf(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "bookshelf", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x2x2_FACING));
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> desk(String ownerId, @Nullable String subsetType, String type, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "desk_%s".formatted(type), blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> deskLeft(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return desk(ownerId, subsetType, "left", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> deskRight(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return desk(ownerId, subsetType, "right", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> drawer(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "drawer", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> dresser(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "dresser", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> lockbox(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "lockbox", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> wardrobeTop(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "wardrobe_top", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> wardrobeBottom(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "wardrobe_bottom", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x2x2_FACING));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> paintingSmall(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "painting_small", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> paintingWide(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "painting_wide", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    static <T extends OvenBlock<?>> BlockComponentBuilder<T> oven(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> factory)
    {
        return facingBlock(ownerId, subsetType, "oven", factory)
                .initialProperties(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel(blockState -> blockState.getOptionalValue(OvenBlock.LIT).orElse(false) ? 13 : 0)
        ;
    }

    static <T extends OvenBlock<?>> BlockComponentBuilder<T> ovenMultiBlock(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return oven(ownerId, subsetType, blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING));
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> door(String ownerId, @Nullable String subsetType, String doorType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "door_%s".formatted(doorType), blockFactory)
                .copyProperties(() -> Blocks.OAK_DOOR)
                .registerComponent(BlockComponentTypes.DOOR)
                .registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_1x2x1_FACING_DOOR))
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> doorSingle(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return door(ownerId, subsetType, "single", blockFactory);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> doorDouble(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return door(ownerId, subsetType, "double", blockFactory);
    }

    private static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bed(String ownerId, @Nullable String subsetType, String bedType, BlockComponentHolderFactory<T> factory)
    {
        return facingBlock(ownerId, subsetType, "bed_%s".formatted(bedType), factory)
                .initialProperties(Material.WOOL, MaterialColor.WOOL)
                .sound(SoundType.WOOD)
                .strength(.2F)
                .noOcclusion()
                .registerComponent(BlockComponentTypes.BED)
                .registerComponent(BlockComponentTypes.HORIZONTAL_FACING, component -> component.setGetFacingDirectionFunc(facing -> facing))
                .onRegister(block -> RegistryManager.get(ownerId).getRegistry(Registries.POINT_OF_INTEREST_TYPE).registerCallback(() -> PointOfInterestHooks.registerHomePoint(() -> block)))
        ;
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bedSingle(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return bed(ownerId, subsetType, "single", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_2x1x1_FACING_BED_SINGLE));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> bedDouble(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return bed(ownerId, subsetType, "double", blockFactory).registerComponent(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(AllMultiBlockTypes.MB_2x1x2_FACING_BED_DOUBLE));
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> shelf(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "shelf", blockFactory).registerComponent(ShelfBlockComponent.COMPONENT_TYPE);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> sofa(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return seat(ownerId, subsetType, "sofa", blockFactory).registerComponent(SofaBlockComponent.COMPONENT_TYPE);
    }

    static <T extends Block & BlockComponentHolder> BlockComponentBuilder<T> counter(String ownerId, @Nullable String subsetType, BlockComponentHolderFactory<T> blockFactory)
    {
        return facingBlock(ownerId, subsetType, "counter", blockFactory).registerComponent(CounterBlockComponent.COMPONENT_TYPE);
    }
}
