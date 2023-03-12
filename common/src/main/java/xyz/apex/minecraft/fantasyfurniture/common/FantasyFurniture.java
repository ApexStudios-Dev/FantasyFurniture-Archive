package xyz.apex.minecraft.fantasyfurniture.common;

import dev.architectury.hooks.PackRepositoryHooks;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTabs;

import xyz.apex.minecraft.apexcore.common.platform.ModPlatform;
import xyz.apex.minecraft.apexcore.common.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.EntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.RecipeEntry;
import xyz.apex.minecraft.apexcore.common.util.Properties;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.renderer.SeatRenderer;
import xyz.apex.minecraft.fantasyfurniture.common.entity.Seat;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.function.Supplier;

public interface FantasyFurniture extends ModPlatform
{
    String ID = "fantasyfurniture";
    Registrar REGISTRAR = Registrar.create(ID);

    EntityEntry<Seat> SEAT_ENTITY = REGISTRAR
            .<Seat>entity("seat", Seat::new)
                .sized(.25F, .35F)
                .clientTrackingRange(5)
                .updateInterval(Integer.MAX_VALUE)
                .fireImmune()
                .renderer(() -> () -> SeatRenderer::new)
            .register();

    BlockEntry<FurnitureStationBlock> FURNITURE_STATION_BLOCK = REGISTRAR
            .block("furniture_station", FurnitureStationBlock::new)
                .noOcclusion()
                .initialProperties(Properties.BLOCK_PLANKS)
            .register();

    RecipeEntry<FurnitureStationRecipe> FURNITURE_STATION_RECIPE = REGISTRAR.recipe("furniture_station", FurnitureStationRecipe.Serializer::new);

    @Override
    default void initialize()
    {
        AllMultiBlockTypes.bootstrap();
        AllVoxelShapes.bootstrap();
        NordicSet.bootstrap();
        DunmerSet.bootstrap();
        VenthyrSet.bootstrap();
        BoneSet.bootstrap();
        RoyalSet.bootstrap();
        NecrolordSet.bootstrap();
        AllBlockEntityTypes.bootstrap();
        AllMenuTypes.bootstrap();

        CreativeTabRegistry.append(CreativeModeTabs.FUNCTIONAL_BLOCKS, FURNITURE_STATION_BLOCK::get);

        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> registerResourcePack("ctm", "ctm_support", () -> Component.literal("CTM Mod Support")));
    }

    private void registerResourcePack(@Nullable String requiredMod, String packId, Supplier<Component> displayName)
    {
        if(requiredMod != null && !requiredMod.isEmpty() && !isModLoaded(requiredMod)) return;

        asMod().findResource("packs", packId).ifPresent(path -> {
            var resources = new PathPackResources("%s:%s".formatted(ID, packId), path, true);
            PackRepositoryHooks.addSource(GameInstance.getClient().getResourcePackRepository(), onLoad -> {
                var pack = Pack.readMetaAndCreate("%s:builtin/%s".formatted(ID, packId), displayName.get(), false, builtInPackId -> resources, PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN);
                if(pack != null) onLoad.accept(pack);
            });
        });
    }
}
