package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntityEntry;
import xyz.apex.forge.apexcore.registrate.entry.MenuEntry;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.block.entity.*;
import xyz.apex.forge.fantasyfurniture.client.renderer.SkullBlossomsBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.renderer.WidowBloomBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.screen.BookshelfMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.LargeInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.MediumInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.SmallInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.data.ParticleProvider;
import xyz.apex.forge.fantasyfurniture.menu.BookshelfMenu;
import xyz.apex.forge.fantasyfurniture.menu.LargeInventoryMenu;
import xyz.apex.forge.fantasyfurniture.menu.MediumInventoryMenu;
import xyz.apex.forge.fantasyfurniture.menu.SmallInventoryMenu;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;

public final class ModElements
{
	private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Mods.FANTASY_FURNITURE);
	public static final RegistryObject<SimpleParticleType> SMALL_SOUL_FLAME = PARTICLE_TYPES.register("small_soul_fire_flame", () -> new SimpleParticleType(false));

	public static final ResourceLocation SMALL_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/large_storage.png");

	public static final MenuEntry<LargeInventoryMenu> LARGE_INVENTORY_MENU = REGISTRATE
			.object("large_inventory_menu")
			.menu(LargeInventoryMenu::new, () -> LargeInventoryMenuScreen::new)
	;

	public static final MenuEntry<MediumInventoryMenu> MEDIUM_INVENTORY_MENU = REGISTRATE
			.object("medium_inventory_menu")
			.menu(MediumInventoryMenu::new, () -> MediumInventoryMenuScreen::new)
	;

	public static final MenuEntry<SmallInventoryMenu> SMALL_INVENTORY_MENU = REGISTRATE
			.object("small_inventory_menu")
			.menu(SmallInventoryMenu::new, () -> SmallInventoryMenuScreen::new)
	;

	public static final MenuEntry<BookshelfMenu> BOOKSHELF_MENU = REGISTRATE
			.object("bookshelf")
			.menu(BookshelfMenu::new, () -> BookshelfMenuScreen::new)
	;

	public static final BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRATE
			.object("bookshelf")
			.blockEntity(BookshelfBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_BOOKSHELF, ModBlocks.DUNMER_BOOKSHELF,
					ModBlocks.VENTHYR_BOOKSHELF, ModBlocks.BONE_SKELETON_BOOKSHELF,
					ModBlocks.BONE_WITHER_BOOKSHELF
			)
			.register()
	;

	public static final BlockEntityEntry<ChestBlockEntity> CHEST_BLOCK_ENTITY = REGISTRATE
			.object("chest")
			.blockEntity(ChestBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_CHEST, ModBlocks.DUNMER_CHEST,
					ModBlocks.VENTHYR_CHEST, ModBlocks.BONE_SKELETON_CHEST,
					ModBlocks.BONE_WITHER_CHEST
			)
			.register()
	;

	public static final BlockEntityEntry<DeskBlockEntity> DESK_BLOCK_ENTITY = REGISTRATE
			.object("desk")
			.blockEntity(DeskBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_DESK_LEFT, ModBlocks.NORDIC_DESK_RIGHT,
					ModBlocks.DUNMER_DESK_LEFT, ModBlocks.DUNMER_DESK_RIGHT,
					ModBlocks.VENTHYR_DESK_LEFT, ModBlocks.VENTHYR_DESK_RIGHT,
					ModBlocks.BONE_SKELETON_DESK_LEFT, ModBlocks.BONE_SKELETON_DESK_RIGHT,
					ModBlocks.BONE_WITHER_DESK_LEFT, ModBlocks.BONE_WITHER_DESK_RIGHT
			)
			.register()
	;

	public static final BlockEntityEntry<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = REGISTRATE
			.object("drawer")
			.blockEntity(DrawerBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_DRAWER, ModBlocks.DUNMER_DRAWER,
					ModBlocks.VENTHYR_DRAWER, ModBlocks.BONE_SKELETON_DRAWER,
					ModBlocks.BONE_WITHER_DRAWER
			)
			.register()
	;

	public static final BlockEntityEntry<DresserBlockEntity> DRESSER_BLOCK_ENTITY = REGISTRATE
			.object("dresser")
			.blockEntity(DresserBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_DRESSER, ModBlocks.DUNMER_DRESSER,
					ModBlocks.VENTHYR_DRESSER, ModBlocks.BONE_SKELETON_DRESSER,
					ModBlocks.BONE_WITHER_DRESSER
			)
	.register();

	public static final BlockEntityEntry<LockboxBlockEntity> LOCKBOX_BLOCK_ENTITY = REGISTRATE
			.object("lockbox")
			.blockEntity(LockboxBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_LOCKBOX, ModBlocks.DUNMER_LOCKBOX,
					ModBlocks.VENTHYR_LOCKBOX, ModBlocks.BONE_SKELETON_LOCKBOX,
					ModBlocks.BONE_WITHER_LOCKBOX
			)
			.register()
	;

	public static final BlockEntityEntry<WardrobeBlockEntity> WARDROBE_BLOCK_ENTITY = REGISTRATE
			.object("wardrobe")
			.blockEntity(WardrobeBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_WARDROBE_BOTTOM, ModBlocks.DUNMER_WARDROBE_BOTTOM,
					ModBlocks.VENTHYR_WARDROBE_BOTTOM, ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM,
					ModBlocks.BONE_WITHER_WARDROBE_BOTTOM
			)
			.register()
	;

	public static final BlockEntityEntry<CounterBlockEntity> COUNTER_BLOCK_ENTITY = REGISTRATE
			.object("counter")
			.blockEntity(CounterBlockEntity::new)
			.validBlock(ModBlocks.ROYAL_COUNTER)
			.register()
	;

	public static final BlockEntityEntry<WidowBloomBlockEntity> VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY = REGISTRATE
			.object("decorations/widow_bloom")
			.blockEntity(WidowBloomBlockEntity::new)
			.validBlock(ModBlocks.VENTHYR_WIDOW_BLOOM)
			.renderer(() -> WidowBloomBlockEntityRenderer::new)
			.register()
	;

	public static final BlockEntityEntry<SkullBlossomsBlockEntity> BONE_SKULL_BLOSSOMS_BLOCK_ENTITY = REGISTRATE
			.object("decorations/bone_skull_blossoms")
			.blockEntity(SkullBlossomsBlockEntity::new)
			.validBlock(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS, ModBlocks.BONE_WITHER_SKULL_BLOSSOMS)
			.renderer(() -> SkullBlossomsBlockEntityRenderer::new)
			.register()
	;

	static void bootstrap()
	{
		PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

		EventBusHelper.addListener(GatherDataEvent.class, event -> {
			var generator = event.getGenerator();

			var includeClient = event.includeClient();

			generator.addProvider(includeClient, new ParticleProvider(generator, event.getExistingFileHelper()) {
				@Override
				public void registerParticleDefs()
				{
					SMALL_SOUL_FLAME.ifPresent(particleType -> definition(particleType).texture(ParticleTypes.SOUL_FIRE_FLAME));
				}
			});

		});
	}
}