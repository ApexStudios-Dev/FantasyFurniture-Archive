package xyz.apex.minecraft.fantasyfurniture.common.init;

import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.*;
import xyz.apex.minecraft.fantasyfurniture.common.menu.*;

public interface AllMenuTypes
{
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION = MenuEntry.registerWithScreen(FantasyFurniture.ID, "furniture_station", FurnitureStationMenu::forClient, () -> FurnitureStationMenuScreen::new);
    MenuEntry<ChestMenu> CHEST = MenuEntry.registerWithScreen(FantasyFurniture.ID, "chest", ChestMenu::forClient, () -> ChestMenuScreen::new);
    MenuEntry<BookshelfMenu> BOOKSHELF = MenuEntry.registerWithScreen(FantasyFurniture.ID, "bookshelf", BookshelfMenu::forClient, () -> BookshelfMenuScreen::new);
    MenuEntry<DeskMenu> DESK = MenuEntry.registerWithScreen(FantasyFurniture.ID, "desk", DeskMenu::forClient, () -> DeskMenuScreen::new);
    MenuEntry<DrawerMenu> DRAWER = MenuEntry.registerWithScreen(FantasyFurniture.ID, "drawer", DrawerMenu::forClient, () -> DrawerMenuScreen::new);
    MenuEntry<DresserMenu> DRESSER = MenuEntry.registerWithScreen(FantasyFurniture.ID, "dresser", DresserMenu::forClient, () -> DresserMenuScreen::new);
    MenuEntry<LockboxMenu> LOCKBOX = MenuEntry.registerWithScreen(FantasyFurniture.ID, "lockbox", LockboxMenu::forClient, () -> LockboxMenuScreen::new);
    MenuEntry<WardrobeMenu> WARDROBE = MenuEntry.registerWithScreen(FantasyFurniture.ID, "wardrobe", WardrobeMenu::forClient, () -> WardrobeMenuScreen::new);
    MenuEntry<OvenMenu> OVEN = MenuEntry.registerWithScreen(FantasyFurniture.ID, "oven", (containerId, playerInventory, player, extraData) -> new OvenMenu(containerId, player, extraData), () -> OvenMenuScreen::new);
    MenuEntry<CounterMenu> COUNTER = MenuEntry.registerWithScreen(FantasyFurniture.ID, "counter", CounterMenu::forClient, () -> CounterMenuScreen::new);

    static void bootstrap() {}
}
