package xyz.apex.minecraft.fantasyfurniture.shared;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import xyz.apex.minecraft.apexcore.shared.platform.Platform;
import xyz.apex.minecraft.apexcore.shared.registry.builders.EntityBuilders;
import xyz.apex.minecraft.apexcore.shared.registry.entry.EntityEntry;
import xyz.apex.minecraft.apexcore.shared.util.Tags;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.SeatRenderer;
import xyz.apex.minecraft.fantasyfurniture.shared.entity.Seat;
import xyz.apex.minecraft.fantasyfurniture.shared.init.*;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    EntityEntry<Seat> SEAT_ENTITY = EntityBuilders
            .<Seat>builder(ID, "seat", Seat::new)
                .sized(.25F, .35F)
                .clientTrackingRange(5)
                .updateInterval(Integer.MAX_VALUE)
                .fireImmune()
                .renderer(() -> SeatRenderer::new)
            .register();

    TagKey<EntityType<?>> SEAT_BLACKLIST = Tags.EntityTypes.tag(ID, "seat_blacklist");

    static void bootstrap()
    {
        // DefaultedItemProperties.register(ID, properties -> properties.tab(CreativeModeTab.TAB_MISC));

        AllVoxelShapes.bootstrap();
        NordicSet.bootstrap();
        DunmerSet.bootstrap();
        VenthyrSet.bootstrap();
        BoneSet.bootstrap();
        RoyalSet.bootstrap();
        NecrolordSet.bootstrap();
    }

    static boolean isEntityValidForSeat(Entity entity)
    {
        if(Platform.INSTANCE.isFakePlayer(entity)) return false; // disallow FakePlayers
        if(entity.getType().is(SEAT_BLACKLIST)) return false;
        return entity instanceof LivingEntity;
    }
}
