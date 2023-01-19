package xyz.apex.forge.fantasyfurniture;

import xyz.apex.forge.apexcore.registrate.entry.MenuEntry;
import xyz.apex.forge.fantasyfurniture.client.screen.*;
import xyz.apex.forge.fantasyfurniture.common.menu.*;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

@SuppressWarnings("ConstantConditions")
public interface AllMenus
{
	MenuEntry<LargeInventoryMenu> LARGE_INVENTORY_MENU = REGISTRATE.menu("large_inventory_menu", LargeInventoryMenu::new, () -> LargeInventoryMenuScreen::new);
	MenuEntry<MediumInventoryMenu> MEDIUM_INVENTORY_MENU = REGISTRATE.menu("medium_inventory_menu", MediumInventoryMenu::new, () -> MediumInventoryMenuScreen::new);
	MenuEntry<SmallInventoryMenu> SMALL_INVENTORY_MENU = REGISTRATE.menu("small_inventory_menu", SmallInventoryMenu::new, () -> SmallInventoryMenuScreen::new);
	MenuEntry<BookshelfMenu> BOOKSHELF_MENU = REGISTRATE.menu("bookshelf", BookshelfMenu::new, () -> BookshelfMenuScreen::new);
	MenuEntry<OvenMenu> OVEN_MENU = REGISTRATE.menu("oven", OvenMenu::new, () -> OvenMenuScreen::new);
	MenuEntry<CookieJarMenu> COOKIE_JAR_MENU = REGISTRATE.menu("cookie_jar", CookieJarMenu::new, () -> CookieJarMenuScreen::new);
	MenuEntry<FurnitureStationMenu> FURNITURE_STATION = REGISTRATE.menu("furniture_station", FurnitureStationMenu::new, () -> FurnitureStationMenuScreen::new);
	MenuEntry<StockingMenu> STOCKING_MENU = REGISTRATE.menu("stocking", StockingMenu::new, () -> StockingMenuScreen::new);

	static void bootstrap()
	{
	}
}
