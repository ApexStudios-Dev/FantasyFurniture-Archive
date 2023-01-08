package xyz.apex.minecraft.fantasyfurniture.shared;

import dev.architectury.hooks.level.entity.PlayerHooks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import xyz.apex.minecraft.apexcore.shared.platform.ModPlatform;
import xyz.apex.minecraft.apexcore.shared.registry.Registrar;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.EntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.RecipeEntry;
import xyz.apex.minecraft.apexcore.shared.util.Properties;
import xyz.apex.minecraft.apexcore.shared.util.Tags;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.SeatRenderer;
import xyz.apex.minecraft.fantasyfurniture.shared.entity.Seat;
import xyz.apex.minecraft.fantasyfurniture.shared.init.*;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.FurnitureStationRecipe;

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

    TagKey<EntityType<?>> SEAT_BLACKLIST = Tags.EntityTypes.tag(ID, "seat_blacklist");

    @Override
    default void initialize()
    {
        ModPlatform.super.initialize();

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
    }

    static boolean isEntityValidForSeat(Entity entity)
    {
        if(entity instanceof Player player && PlayerHooks.isFake(player)) return false; // disallow FakePlayers
        if(entity.getType().is(SEAT_BLACKLIST)) return false;
        return entity instanceof LivingEntity;
    }
}
