package xyz.apex.minecraft.fantasyfurniture.common.init;

import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.*;
import xyz.apex.minecraft.fantasyfurniture.common.menu.*;

public interface AllMenuTypes
{
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION = FantasyFurniture.REGISTRAR.menu("furniture_station", FurnitureStationMenu::new, () -> FurnitureStationMenuScreen::new);
    MenuEntry<ChestMenu> CHEST = FantasyFurniture.REGISTRAR.menu("chest", ChestMenu::forClient, () -> ChestMenuScreen::new);
    MenuEntry<BookshelfMenu> BOOKSHELF = FantasyFurniture.REGISTRAR.menu("bookshelf", BookshelfMenu::forClient, () -> BookshelfMenuScreen::new);
    MenuEntry<DeskMenu> DESK = FantasyFurniture.REGISTRAR.menu("desk", DeskMenu::forClient, () -> DeskMenuScreen::new);
    MenuEntry<DrawerMenu> DRAWER = FantasyFurniture.REGISTRAR.menu("drawer", DrawerMenu::forClient, () -> DrawerMenuScreen::new);
    MenuEntry<DresserMenu> DRESSER = FantasyFurniture.REGISTRAR.menu("dresser", DresserMenu::forClient, () -> DresserMenuScreen::new);
    MenuEntry<LockboxMenu> LOCKBOX = FantasyFurniture.REGISTRAR.menu("lockbox", LockboxMenu::forClient, () -> LockboxMenuScreen::new);
    MenuEntry<WardrobeMenu> WARDROBE = FantasyFurniture.REGISTRAR.menu("wardrobe", WardrobeMenu::forClient, () -> WardrobeMenuScreen::new);
    MenuEntry<OvenMenu> OVEN = FantasyFurniture.REGISTRAR.menu("oven", OvenMenu::new, () -> OvenMenuScreen::new);
    MenuEntry<CounterMenu> COUNTER = FantasyFurniture.REGISTRAR.menu("counter", CounterMenu::forClient, () -> CounterMenuScreen::new);

    static void bootstrap()
    {
    }
}
