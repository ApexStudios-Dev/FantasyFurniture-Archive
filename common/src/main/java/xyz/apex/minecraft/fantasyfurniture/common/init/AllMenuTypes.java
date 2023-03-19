package xyz.apex.minecraft.fantasyfurniture.common.init;

import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.OvenMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.menu.OvenMenu;

public interface AllMenuTypes
{
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION = MenuEntry.registerWithScreen(FantasyFurniture.ID, "furniture_station", FurnitureStationMenu::forClient, () -> FurnitureStationMenuScreen::new);
    // MenuEntry<ChestMenu> CHEST = FantasyFurniture.REGISTRAR.menu("chest", ChestMenu::forClient, () -> ChestMenuScreen::new);
    // MenuEntry<BookshelfMenu> BOOKSHELF = FantasyFurniture.REGISTRAR.menu("bookshelf", BookshelfMenu::forClient, () -> BookshelfMenuScreen::new);
    // MenuEntry<DeskMenu> DESK = FantasyFurniture.REGISTRAR.menu("desk", DeskMenu::forClient, () -> DeskMenuScreen::new);
    // MenuEntry<DrawerMenu> DRAWER = FantasyFurniture.REGISTRAR.menu("drawer", DrawerMenu::forClient, () -> DrawerMenuScreen::new);
    // MenuEntry<DresserMenu> DRESSER = FantasyFurniture.REGISTRAR.menu("dresser", DresserMenu::forClient, () -> DresserMenuScreen::new);
    // MenuEntry<LockboxMenu> LOCKBOX = FantasyFurniture.REGISTRAR.menu("lockbox", LockboxMenu::forClient, () -> LockboxMenuScreen::new);
    // MenuEntry<WardrobeMenu> WARDROBE = FantasyFurniture.REGISTRAR.menu("wardrobe", WardrobeMenu::forClient, () -> WardrobeMenuScreen::new);
    MenuEntry<OvenMenu> OVEN = MenuEntry.registerWithScreen(FantasyFurniture.ID, "oven", (containerId, playerInventory, player, extraData) -> new OvenMenu(containerId, player, extraData), () -> OvenMenuScreen::new);
    // MenuEntry<CounterMenu> COUNTER = FantasyFurniture.REGISTRAR.menu("counter", CounterMenu::forClient, () -> CounterMenuScreen::new);

    static void bootstrap() {}
}
