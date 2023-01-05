package xyz.apex.minecraft.fantasyfurniture.shared.init;

import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.screen.ChestMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.ChestMenu;

public interface AllMenuTypes
{
    MenuEntry<ChestMenu> CHEST = FantasyFurniture.REGISTRAR.menu("chest", ChestMenu::new, () -> ChestMenuScreen::new);

    static void bootstrap()
    {
    }
}
