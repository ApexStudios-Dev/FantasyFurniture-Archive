package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

import xyz.apex.forge.fantasyfurniture.block.entity.*;
import xyz.apex.forge.fantasyfurniture.client.screen.BookshelfMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.LargeInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.MediumInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.SmallInventoryMenuScreen;
import xyz.apex.forge.fantasyfurniture.menu.BookshelfMenu;
import xyz.apex.forge.fantasyfurniture.menu.LargeInventoryMenu;
import xyz.apex.forge.fantasyfurniture.menu.MediumInventoryMenu;
import xyz.apex.forge.fantasyfurniture.menu.SmallInventoryMenu;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;

public final class ModElements
{
	public static final ResourceLocation SMALL_STORAGE_TEXTURE = REGISTRATE.id("textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = REGISTRATE.id("textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = REGISTRATE.id("textures/gui/container/large_storage.png");

	public static final MenuEntry<LargeInventoryMenu> LARGE_INVENTORY_MENU = menu("large_inventory_menu", LargeInventoryMenu::new, () -> LargeInventoryMenuScreen::new);
	public static final MenuEntry<MediumInventoryMenu> MEDIUM_INVENTORY_MENU = menu("medium_inventory_menu", MediumInventoryMenu::new, () -> MediumInventoryMenuScreen::new);
	public static final MenuEntry<SmallInventoryMenu> SMALL_INVENTORY_MENU = menu("small_inventory_menu", SmallInventoryMenu::new, () -> SmallInventoryMenuScreen::new);

	public static final MenuEntry<BookshelfMenu> BOOKSHELF_MENU = menu("bookshelf", BookshelfMenu::new, () -> BookshelfMenuScreen::new);
	public static final BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = blockEntity("bookshelf", BookshelfBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_BOOKSHELF, ModBlocks.DUNMER_BOOKSHELF,
					ModBlocks.VENTHYR_BOOKSHELF
			)
	.register();

	public static final BlockEntityEntry<ChestBlockEntity> CHEST_BLOCK_ENTITY = blockEntity("chest", ChestBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_CHEST, ModBlocks.DUNMER_CHEST,
					ModBlocks.VENTHYR_CHEST
			)
	.register();

	public static final BlockEntityEntry<DeskBlockEntity> DESK_BLOCK_ENTITY = blockEntity("desk", DeskBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_DESK_LEFT, ModBlocks.NORDIC_DESK_RIGHT,
					ModBlocks.DUNMER_DESK_LEFT, ModBlocks.DUNMER_DESK_RIGHT,
					ModBlocks.VENTHYR_DESK_LEFT, ModBlocks.VENTHYR_DESK_RIGHT
			)
	.register();

	public static final BlockEntityEntry<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = blockEntity("drawer", DrawerBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_DRAWER, ModBlocks.DUNMER_DRAWER,
					ModBlocks.VENTHYR_DRAWER
			)
	.register();

	public static final BlockEntityEntry<DresserBlockEntity> DRESSER_BLOCK_ENTITY = blockEntity("dresser", DresserBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_DRESSER, ModBlocks.DUNMER_DRESSER,
					ModBlocks.VENTHYR_DRESSER
			)
	.register();

	public static final BlockEntityEntry<LockboxBlockEntity> LOCKBOX_BLOCK_ENTITY = blockEntity("lockbox", LockboxBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_LOCKBOX, ModBlocks.DUNMER_LOCKBOX,
					ModBlocks.VENTHYR_LOCKBOX
			)
	.register();

	public static final BlockEntityEntry<WardrobeBlockEntity> WARDROBE_BLOCK_ENTITY = blockEntity("wardrobe", WardrobeBlockEntity::new)
			.validBlocks(
					ModBlocks.NORDIC_WARDROBE_BOTTOM, ModBlocks.DUNMER_WARDROBE_BOTTOM,
					ModBlocks.VENTHYR_WARDROBE_BOTTOM
			)
	.register();

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <MENU extends AbstractContainerMenu, SCREEN extends Screen & MenuAccess<MENU>> MenuEntry<MENU> menu(String name, MenuBuilder.ForgeMenuFactory<MENU> menuFactory, NonNullSupplier<MenuBuilder.ScreenFactory<MENU, SCREEN>> screenFactorySupplier)
	{
		return REGISTRATE.object(name).menu(menuFactory, screenFactorySupplier).register();
	}

	private static <BLOCK_ENTITY extends BlockEntity> BlockEntityBuilder<Registrate, BLOCK_ENTITY, Registrate> blockEntity(String name, BlockEntityBuilder.BlockEntityFactory<BLOCK_ENTITY> blockEntityFactory)
	{
		return REGISTRATE.object(name).blockEntity(blockEntityFactory);
	}
	// endregion
}