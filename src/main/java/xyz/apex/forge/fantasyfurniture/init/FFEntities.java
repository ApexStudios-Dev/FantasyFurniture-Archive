package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;

import xyz.apex.forge.fantasyfurniture.client.renderer.entity.SeatEntityRenderer;
import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;
import xyz.apex.forge.utility.registrator.entry.EntityEntry;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FFEntities
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final EntityEntry<SeatEntity> SEAT = REGISTRY
			.<SeatEntity>entity("seat", EntityClassification.MISC, SeatEntity::new)
				.lang("Seat")
				.lang(EN_GB, "Seat")
				.sized(0F, 0F)
				.setCustomClientFactory((spawnEntity, level) -> new SeatEntity(level))
				.noSummon()
				.fireImmune()
				.immuneTo(() -> Blocks.TNT, () -> Blocks.LAVA)
				.renderer(() -> SeatEntityRenderer::new)
			.register();

	static void bootstrap()
	{
	}
}
