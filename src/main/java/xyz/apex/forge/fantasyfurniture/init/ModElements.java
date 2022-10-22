package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntityEntry;
import xyz.apex.forge.apexcore.registrate.entry.MenuEntry;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.block.entity.*;
import xyz.apex.forge.fantasyfurniture.client.renderer.SkullBlossomsBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.renderer.WidowBloomBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.screen.*;
import xyz.apex.forge.fantasyfurniture.data.ParticleProvider;
import xyz.apex.forge.fantasyfurniture.item.crafting.DyeableRecipe;
import xyz.apex.forge.fantasyfurniture.menu.*;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;

public final class ModElements
{
	public static final RegistryEntry<SimpleParticleType> SMALL_SOUL_FLAME = REGISTRATE
			.object("small_soul_fire_flame")
			.simple(ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false))
	;
	public static final RegistryEntry<SimpleParticleType> NECROLORD_FLAME = REGISTRATE
			.object("necrolord_flame")
			.simple(ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false))
	;
	public static final RegistryEntry<SimpleParticleType> SMALL_NECROLORD_FLAME = REGISTRATE
			.object("small_necrolord_flame")
			.simple(ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false))
	;

	public static final ResourceLocation SMALL_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/large_storage.png");
	public static final ResourceLocation OVEN_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/oven.png");
	public static final ResourceLocation COOKIE_JAR_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/cookie_jar.png");

	public static final RegistryEntry<SimpleRecipeSerializer<DyeableRecipe>> DYEABLE_RECIPE_SERIALIZER = REGISTRATE
			.object("dyeable")
			.simple(ForgeRegistries.Keys.RECIPE_SERIALIZERS, () -> new SimpleRecipeSerializer<>(DyeableRecipe::new))
	;

	public static final RegistryEntry<RecipeType<DyeableRecipe>> DYEABLE_RECIPE_TYPE = REGISTRATE
			.object("dyeable")
			.simple(ForgeRegistries.Keys.RECIPE_TYPES, () -> RecipeType.simple(new ResourceLocation(Mods.FANTASY_FURNITURE, "dyeable")))
	;

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

	public static final MenuEntry<OvenMenu> OVEN_MENU = REGISTRATE
			.object("oven")
			.menu(OvenMenu::new, () -> OvenMenuScreen::new)
	;

	public static final MenuEntry<CookieJarMenu> COOKIE_JAR_MENU = REGISTRATE
			.object("cookie_jar")
			.menu(CookieJarMenu::new, () -> CookieJarMenuScreen::new)
	;

	public static final BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRATE
			.object("bookshelf")
			.blockEntity(BookshelfBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_BOOKSHELF, ModBlocks.DUNMER_BOOKSHELF,
					ModBlocks.VENTHYR_BOOKSHELF, ModBlocks.BONE_SKELETON_BOOKSHELF,
					ModBlocks.BONE_WITHER_BOOKSHELF, ModBlocks.ROYAL_BOOKSHELF,
					ModBlocks.NECROLORD_BOOKSHELF
			)
			.register()
	;

	public static final BlockEntityEntry<ChestBlockEntity> CHEST_BLOCK_ENTITY = REGISTRATE
			.object("chest")
			.blockEntity(ChestBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_CHEST, ModBlocks.DUNMER_CHEST,
					ModBlocks.VENTHYR_CHEST, ModBlocks.BONE_SKELETON_CHEST,
					ModBlocks.BONE_WITHER_CHEST, ModBlocks.ROYAL_CHEST,
					ModBlocks.NECROLORD_CHEST
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
					ModBlocks.BONE_WITHER_DESK_LEFT, ModBlocks.BONE_WITHER_DESK_RIGHT,
					ModBlocks.ROYAL_DESK_LEFT, ModBlocks.ROYAL_DESK_RIGHT,
					ModBlocks.NECROLORD_DESK_LEFT, ModBlocks.NECROLORD_DESK_RIGHT
			)
			.register()
	;

	public static final BlockEntityEntry<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = REGISTRATE
			.object("drawer")
			.blockEntity(DrawerBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_DRAWER, ModBlocks.DUNMER_DRAWER,
					ModBlocks.VENTHYR_DRAWER, ModBlocks.BONE_SKELETON_DRAWER,
					ModBlocks.BONE_WITHER_DRAWER, ModBlocks.ROYAL_DRAWER,
					ModBlocks.NECROLORD_DRAWER
			)
			.register()
	;

	public static final BlockEntityEntry<DresserBlockEntity> DRESSER_BLOCK_ENTITY = REGISTRATE
			.object("dresser")
			.blockEntity(DresserBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_DRESSER, ModBlocks.DUNMER_DRESSER,
					ModBlocks.VENTHYR_DRESSER, ModBlocks.BONE_SKELETON_DRESSER,
					ModBlocks.BONE_WITHER_DRESSER, ModBlocks.ROYAL_DRESSER,
					ModBlocks.NECROLORD_DRESSER
			)
	.register();

	public static final BlockEntityEntry<LockboxBlockEntity> LOCKBOX_BLOCK_ENTITY = REGISTRATE
			.object("lockbox")
			.blockEntity(LockboxBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_LOCKBOX, ModBlocks.DUNMER_LOCKBOX,
					ModBlocks.VENTHYR_LOCKBOX, ModBlocks.BONE_SKELETON_LOCKBOX,
					ModBlocks.BONE_WITHER_LOCKBOX, ModBlocks.ROYAL_LOCKBOX,
					ModBlocks.NECROLORD_LOCKBOX
			)
			.register()
	;

	public static final BlockEntityEntry<WardrobeBlockEntity> WARDROBE_BLOCK_ENTITY = REGISTRATE
			.object("wardrobe")
			.blockEntity(WardrobeBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_WARDROBE_BOTTOM, ModBlocks.DUNMER_WARDROBE_BOTTOM,
					ModBlocks.VENTHYR_WARDROBE_BOTTOM, ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM,
					ModBlocks.BONE_WITHER_WARDROBE_BOTTOM, ModBlocks.ROYAL_WARDROBE_BOTTOM,
					ModBlocks.NECROLORD_WARDROBE_BOTTOM
			)
			.register()
	;

	public static final BlockEntityEntry<CounterBlockEntity> COUNTER_BLOCK_ENTITY = REGISTRATE
			.object("counter")
			.blockEntity(CounterBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_COUNTER, ModBlocks.DUNMER_COUNTER,
					ModBlocks.VENTHYR_COUNTER, ModBlocks.BONE_SKELETON_COUNTER,
					ModBlocks.BONE_WITHER_COUNTER, ModBlocks.ROYAL_COUNTER,
					ModBlocks.NECROLORD_COUNTER
			)
			.register()
	;

	public static final BlockEntityEntry<OvenBlockEntity> OVEN_BLOCK_ENTITY = REGISTRATE
			.object("oven")
			.blockEntity(OvenBlockEntity::new)
			.validBlock(
					ModBlocks.NORDIC_OVEN, ModBlocks.DUNMER_OVEN,
					ModBlocks.VENTHYR_OVEN, ModBlocks.BONE_SKELETON_OVEN,
					ModBlocks.BONE_WITHER_OVEN, ModBlocks.ROYAL_OVEN,
					ModBlocks.NECROLORD_OVEN
			)
			.register()
	;

	public static final BlockEntityEntry<CookieJarBlockEntity> COOKIE_JAR_BLOCK_ENTITY = REGISTRATE
			.object("cookie_jar")
			.blockEntity(CookieJarBlockEntity::new)
			.validBlock(ModBlocks.COOKIE_JAR)
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
		EventBusHelper.addListener(GatherDataEvent.class, event -> {
			var generator = event.getGenerator();

			var includeClient = event.includeClient();

			generator.addProvider(includeClient, new ParticleProvider(generator, event.getExistingFileHelper()) {
				@Override
				public void registerParticleDefs()
				{
					definition(SMALL_SOUL_FLAME.get()).texture(ParticleTypes.SOUL_FIRE_FLAME);
					definition(NECROLORD_FLAME.get()).texture(NECROLORD_FLAME.get());
					definition(SMALL_NECROLORD_FLAME.get()).texture(NECROLORD_FLAME.get());
				}
			});

		});
	}
}