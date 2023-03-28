package xyz.apex.minecraft.fantasyfurniture.common.init;

import xyz.apex.minecraft.apexcore.common.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.*;

public interface AllBlockEntityTypes
{
    BlockEntityEntry<ChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "chest", registrar -> {}, ChestBlockEntity::new)
            .validBlocks(
                NordicSet.CHEST, VenthyrSet.CHEST,
                DunmerSet.CHEST, BoneSet.Skeleton.CHEST,
                BoneSet.Wither.CHEST, NecrolordSet.CHEST,
                RoyalSet.CHEST
            ).register();

    BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "bookshelf", registrar -> {}, BookshelfBlockEntity::new)
            .validBlocks(
                NordicSet.BOOKSHELF, VenthyrSet.BOOKSHELF,
                DunmerSet.BOOKSHELF, BoneSet.Wither.BOOKSHELF,
                BoneSet.Skeleton.BOOKSHELF, NecrolordSet.BOOKSHELF,
                RoyalSet.BOOKSHELF
            ).register();

    BlockEntityEntry<DeskBlockEntity> DESK = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "desk", registrar -> {}, DeskBlockEntity::new)
            .validBlocks(
                NordicSet.DESK_LEFT, NordicSet.DESK_RIGHT,
                VenthyrSet.DESK_LEFT, VenthyrSet.DESK_RIGHT,
                DunmerSet.DESK_LEFT, DunmerSet.DESK_RIGHT,
                BoneSet.Wither.DESK_LEFT, BoneSet.Wither.DESK_RIGHT,
                BoneSet.Skeleton.DESK_LEFT, BoneSet.Skeleton.DESK_RIGHT,
                NecrolordSet.DESK_LEFT, NecrolordSet.DESK_RIGHT,
                RoyalSet.DESK_LEFT, RoyalSet.DESK_RIGHT
            ).register();

    BlockEntityEntry<DrawerBlockEntity> DRAWER = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "drawer", registrar -> {}, DrawerBlockEntity::new)
            .validBlocks(
                NordicSet.DRAWER, VenthyrSet.DRAWER,
                DunmerSet.DRAWER, BoneSet.Wither.DRAWER,
                BoneSet.Skeleton.DRAWER, NecrolordSet.DRAWER,
                RoyalSet.DRAWER
            ).register();

    BlockEntityEntry<DresserBlockEntity> DRESSER = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "dresser", registrar -> {}, DresserBlockEntity::new)
            .validBlocks(
                    NordicSet.DRESSER, VenthyrSet.DRESSER,
                DunmerSet.DRESSER, BoneSet.Wither.DRESSER,
                BoneSet.Skeleton.DRESSER, NecrolordSet.DRESSER,
                RoyalSet.DRESSER
            ).register();

    BlockEntityEntry<LockboxBlockEntity> LOCKBOX = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "lockbox", registrar -> {}, LockboxBlockEntity::new)
            .validBlocks(
                    NordicSet.LOCKBOX, VenthyrSet.LOCKBOX,
                    DunmerSet.LOCKBOX, BoneSet.Wither.LOCKBOX,
                    BoneSet.Skeleton.LOCKBOX, NecrolordSet.LOCKBOX,
                    RoyalSet.LOCKBOX
            ).register();

    BlockEntityEntry<WardrobeBlockEntity> WARDROBE = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "wardrobe", registrar -> {}, WardrobeBlockEntity::new)
            .validBlocks(
                NordicSet.WARDROBE_BOTTOM, VenthyrSet.WARDROBE_BOTTOM,
                DunmerSet.WARDROBE_BOTTOM, BoneSet.Wither.WARDROBE_BOTTOM,
                BoneSet.Skeleton.WARDROBE_BOTTOM, NecrolordSet.WARDROBE_BOTTOM,
                RoyalSet.WARDROBE_BOTTOM
            ).register();

    BlockEntityEntry<OvenBlockEntity> OVEN = BlockEntityBuilder
            .builder(FantasyFurniture.ID, "oven", OvenBlockEntity::new)
            .validBlocks(
                    NordicSet.OVEN, VenthyrSet.OVEN,
                    DunmerSet.OVEN, BoneSet.Wither.OVEN,
                    BoneSet.Skeleton.OVEN, NecrolordSet.OVEN,
                    RoyalSet.OVEN
            ).register();

    BlockEntityEntry<CounterBlockEntity> COUNTER = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "counter", registrar -> {}, CounterBlockEntity::new)
            .validBlocks(
                NordicSet.COUNTER, VenthyrSet.COUNTER,
                DunmerSet.COUNTER, BoneSet.Wither.COUNTER,
                BoneSet.Skeleton.COUNTER, NecrolordSet.COUNTER,
                RoyalSet.COUNTER
            ).register();

    BlockEntityEntry<DyeableBlockEntity> DYEABLE = BlockEntityBuilder
            .builderWithComponents(FantasyFurniture.ID, "dyeable", registrar -> {}, DyeableBlockEntity::new)
            .validBlocks(
                    RoyalSet.WOOL
            )
            .register();

    static void bootstrap() {}
}
