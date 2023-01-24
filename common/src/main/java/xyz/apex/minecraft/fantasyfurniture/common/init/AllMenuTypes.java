package xyz.apex.minecraft.fantasyfurniture.common.init;

import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.*;
import xyz.apex.minecraft.fantasyfurniture.common.menu.*;

public interface AllMenuTypes
{
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION = FantasyFurniture.REGISTRAR.menu("furniture_station", FurnitureStationMenu::new, () -> FurnitureStationMenuScreen::new);
    MenuEntry<ChestMenu> CHEST = FantasyFurniture.REGISTRAR.menu("chest", ChestMenu::new, () -> ChestMenuScreen::new);
    MenuEntry<BookshelfMenu> BOOKSHELF = FantasyFurniture.REGISTRAR.menu("bookshelf", BookshelfMenu::new, () -> BookshelfMenuScreen::new);
    MenuEntry<DeskMenu> DESK = FantasyFurniture.REGISTRAR.menu("desk", DeskMenu::new, () -> DeskMenuScreen::new);
    MenuEntry<DrawerMenu> DRAWER = FantasyFurniture.REGISTRAR.menu("drawer", DrawerMenu::new, () -> DrawerMenuScreen::new);
    MenuEntry<DresserMenu> DRESSER = FantasyFurniture.REGISTRAR.menu("dresser", DresserMenu::new, () -> DresserMenuScreen::new);
    MenuEntry<LockboxMenu> LOCKBOX = FantasyFurniture.REGISTRAR.menu("lockbox", LockboxMenu::new, () -> LockboxMenuScreen::new);
    MenuEntry<WardrobeMenu> WARDROBE = FantasyFurniture.REGISTRAR.menu("wardrobe", WardrobeMenu::new, () -> WardrobeMenuScreen::new);

    static void bootstrap()
    {
    }
}
