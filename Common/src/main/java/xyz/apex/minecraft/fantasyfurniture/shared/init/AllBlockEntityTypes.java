package xyz.apex.minecraft.fantasyfurniture.shared.init;

import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.ChestBlockEntity;

public interface AllBlockEntityTypes
{
    BlockEntityEntry<ChestBlockEntity> CHEST = FantasyFurniture.REGISTRAR
            .<ChestBlockEntity>blockEntity("chest", ChestBlockEntity::new)
                .validBlock(NordicSet.CHEST)
            .register();

    static void bootstrap()
    {
    }
}
