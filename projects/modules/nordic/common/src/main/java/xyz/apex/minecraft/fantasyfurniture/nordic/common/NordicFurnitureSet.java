package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderTypes;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.block.NordicSofa;

@ApiStatus.NonExtendable
public interface NordicFurnitureSet
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture_nordic";

    NordicFurnitureSet INSTANCE = Services.singleton(NordicFurnitureSet.class);

    Registrar REGISTRAR = Registrar.create(ID);

    BlockEntry<NordicSofa> SOFA = FurnitureSets.sofa(REGISTRAR, NordicSofa::new).register();

    default void bootstrap()
    {
        HitBoxes.bootstrap();
        REGISTRAR.register();
        registerGenerators();
    }

    private void registerGenerators()
    {
        var descriptionKey = "pack.%s.description".formatted(ID);

        ProviderTypes.LANGUAGES.addListener(ID, (provider, lookup) -> provider
                .enUS()
                    .add(descriptionKey, "Fantasy's Furniture - Nordic")
                .end()
        );

        ProviderTypes.registerDefaultMcMetaGenerator(ID, Component.translatable(descriptionKey));
    }
}
