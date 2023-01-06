package xyz.apex.minecraft.fantasyfurniture.shared.init;

import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.screen.*;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.*;

public interface AllMenuTypes
{
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
