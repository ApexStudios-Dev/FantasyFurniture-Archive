package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.hooks.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.hooks.PackRepositoryHooks;
import xyz.apex.minecraft.apexcore.common.platform.ModExecutor;
import xyz.apex.minecraft.apexcore.common.platform.ModLoader;
import xyz.apex.minecraft.apexcore.common.platform.Side;
import xyz.apex.minecraft.apexcore.common.platform.SideExecutor;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.registry.builder.EntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.EntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.RecipeEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.renderer.SeatRenderer;
import xyz.apex.minecraft.fantasyfurniture.common.entity.Seat;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    EntityEntry<Seat> SEAT_ENTITY = EntityBuilder
            .<Seat>builder(ID, "seat", MobCategory.MISC, Seat::new)
                .sized(.25F, .35F)
                .clientTrackingRange(5)
                .updateInterval(Integer.MAX_VALUE)
                .fireImmune()
                .renderer(() -> SeatRenderer::new)
            .register();

    BlockEntry<FurnitureStationBlock> FURNITURE_STATION_BLOCK = BlockBuilder
            .builder(ID, "furniture_station", FurnitureStationBlock::new)
                .noOcclusion()
                .copyProperties(() -> Blocks.OAK_PLANKS)
            .register();

    RecipeEntry<FurnitureStationRecipe> FURNITURE_STATION_RECIPE = RecipeEntry.register(ID, "furniture_station", FurnitureStationRecipe.Serializer::new);

    default void bootstrap()
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

        CreativeModeTabHooks.getInstance().modify(CreativeModeTabs.FUNCTIONAL_BLOCKS, output -> output.accept(FURNITURE_STATION_BLOCK));

        registerResourcePack("ctm", "ctm_support", Component.literal("CTM Mod Support"));
        RegistryManager.register(ID);
    }

    private void registerResourcePack(@Nullable String requiredMod, String packId, Component displayName)
    {
        if(requiredMod != null && !requiredMod.isEmpty() && !ModLoader.getInstance().isModLoaded(requiredMod)) return;

        SideExecutor.runWhenOn(Side.CLIENT, () -> () -> ModExecutor.runIfLoaded(ID, () -> mod -> mod.findResource("packs", packId).ifPresent(path -> {
            var resources = new PathPackResources("%s:%s".formatted(ID, packId), path, true);
            PackRepositoryHooks.getInstance().registerPackRepository(Minecraft.getInstance().getResourcePackRepository(), onLoad -> {
                var pack = Pack.readMetaAndCreate("%s:builtin/%s".formatted(ID, packId), displayName, false, builtInPackId -> resources, PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN);
                if(pack != null) onLoad.accept(pack);
            });
        })));
    }
}
