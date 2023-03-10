package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import xyz.apex.minecraft.apexcore.common.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.*;

import java.util.function.Supplier;

public interface AllBlockEntityTypes
{
    BlockEntityEntry<ChestBlockEntity> CHEST = register(
            "chest", ChestBlockEntity::new,
            NordicSet.CHEST, VenthyrSet.CHEST,
            DunmerSet.CHEST
    );

    BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF = register(
            "bookshelf", BookshelfBlockEntity::new,
            NordicSet.BOOKSHELF, VenthyrSet.BOOKSHELF,
            DunmerSet.BOOKSHELF
    );

    BlockEntityEntry<DeskBlockEntity> DESK = register(
            "desk", DeskBlockEntity::new,
            NordicSet.DESK_LEFT, NordicSet.DESK_RIGHT,
            VenthyrSet.DESK_LEFT, VenthyrSet.DESK_RIGHT,
            DunmerSet.DESK_LEFT, DunmerSet.DESK_RIGHT
    );

    BlockEntityEntry<DrawerBlockEntity> DRAWER = register(
            "drawer", DrawerBlockEntity::new,
            NordicSet.DRAWER, VenthyrSet.DRAWER,
            DunmerSet.DRAWER
    );

    BlockEntityEntry<DresserBlockEntity> DRESSER = register(
            "dresser", DresserBlockEntity::new,
            NordicSet.DRESSER, VenthyrSet.DRESSER,
            DunmerSet.DRESSER
    );

    BlockEntityEntry<LockboxBlockEntity> LOCKBOX = register(
            "lockbox", LockboxBlockEntity::new,
            NordicSet.LOCKBOX, VenthyrSet.LOCKBOX,
            DunmerSet.LOCKBOX
    );

    BlockEntityEntry<WardrobeBlockEntity> WARDROBE = register(
            "wardrobe", WardrobeBlockEntity::new,
            NordicSet.WARDROBE_BOTTOM, VenthyrSet.WARDROBE_BOTTOM,
            DunmerSet.WARDROBE_BOTTOM
    );

    BlockEntityEntry<OvenBlockEntity> OVEN = register(
            "oven", OvenBlockEntity::new,
            NordicSet.OVEN, VenthyrSet.OVEN,
            DunmerSet.OVEN
    );

    BlockEntityEntry<CounterBlockEntity> COUNTER = register(
            "counter", CounterBlockEntity::new,
            NordicSet.COUNTER, VenthyrSet.COUNTER,
            DunmerSet.COUNTER
    );

    static void bootstrap()
    {
    }

    @SafeVarargs
    private static <T extends BlockEntity> BlockEntityEntry<T> register(String name, BlockEntityBuilder.Factory<T> factory, Supplier<? extends Block>... validBlocks)
    {
        return FantasyFurniture.REGISTRAR.blockEntity(name, factory).validBlock(validBlocks).register();
    }
}
