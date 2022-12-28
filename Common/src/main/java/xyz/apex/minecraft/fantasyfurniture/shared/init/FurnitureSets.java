package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.event.events.CreativeModeTabEvent;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockFactory;
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
import java.util.function.UnaryOperator;

public interface FurnitureSets
{
    // region: MultiBlock Types
    MultiBlockType MB_1x1x2_FACING = MultiBlockTypes.MB_1x1x2.clone().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_2x1x2_FACING = MultiBlockTypes.MB_2x1x2.clone().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_1x2x1_FACING = MultiBlockTypes.MB_1x2x1.clone().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    // endregion

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

    static void creativeModeTab(String furnitureSet, UnaryOperator<CreativeModeTab.Builder> builderFunc)
    {
        CreativeModeTabEvent.REGISTER.addListener(register -> register.registerCreativeModeTab(furnitureSet, builder -> builderFunc.apply(builder.title(Component.translatable("itemGroup.%s.%s".formatted(FantasyFurniture.ID, furnitureSet))))));
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
        return table(furnitureSet, "wide", MB_1x1x2_FACING)
                .hitbox(baseShape, AllVoxelShapes::getTableWideShape)
                .renderType(() -> RenderType::cutout)
        ;
    }

    static BlockBuilder<SimpleMultiBlock.WithHorizontalFacing> tableLarge(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return table(furnitureSet, "large", MB_2x1x2_FACING)
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

    private static <T extends Block & SeatBlock.MultiBlock> BlockBuilder<T> seat(String furnitureSet, String seatType, MultiBlockType multiBlockType, MultiBlockFactory<T> factory)
    {
        return BlockBuilders
                .multiBlock(FantasyFurniture.ID, "%s/%s".formatted(furnitureSet, seatType), multiBlockType, factory)
                .transform(FurnitureSets::applySeatProperties)
        ;
    }

    private static BlockBuilder<SimpleSeatBlock.WithMultiBlock> seat(String furnitureSet, String seatType, MultiBlockType multiBlockType)
    {
        return seat(furnitureSet, seatType, multiBlockType, SimpleSeatBlock.WithMultiBlock::new);
    }

    static BlockBuilder<SimpleSeatBlock.WithMultiBlock> bench(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "bench", MB_1x1x2_FACING).hitbox(baseShape, AllVoxelShapes::getBenchShape);
    }

    static BlockBuilder<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> chair(String furnitureSet, Supplier<VoxelShape> baseShape)
    {
        return seat(furnitureSet, "chair", MB_1x2x1_FACING, SimpleSeatBlock.WithMultiBlock.AtOriginOnly::new)
                .hitbox(baseShape, AllVoxelShapes::getChairShape)
                .renderType(() -> RenderType::cutout)
        ;
    }
    // endregion
}
