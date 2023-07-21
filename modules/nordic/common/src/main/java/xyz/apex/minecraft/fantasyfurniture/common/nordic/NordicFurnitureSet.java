package xyz.apex.minecraft.fantasyfurniture.common.nordic;

import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.RegistrarManager;
import xyz.apex.minecraft.apexcore.common.lib.registry.builders.BuilderManager;

@ApiStatus.NonExtendable
public interface NordicFurnitureSet
{
    String ID = "fantasyfurniture_nordic";

    RegistrarManager REGISTRAR_MANAGER = RegistrarManager.get(ID);
    BuilderManager<BuilderManager.Impl> BUILDERS = BuilderManager.create(REGISTRAR_MANAGER);

    default void bootstrap()
    {
        NordicBlocks.bootstrap();
        BUILDERS.creativeModeTab("main").icon(() -> NordicBlocks.WOOL.asStack()).displayItems(NordicFurnitureSet::buildCreativeModeTabContents).register();
        REGISTRAR_MANAGER.register();
    }

    private static void buildCreativeModeTabContents(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output)
    {
        output.accept(NordicBlocks.WOOL);
    }
}
