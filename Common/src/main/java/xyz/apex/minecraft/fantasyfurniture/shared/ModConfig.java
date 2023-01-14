package xyz.apex.minecraft.fantasyfurniture.shared;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = FantasyFurniture.ID)
public final class ModConfig implements ConfigData
{
    @ConfigEntry.Gui.CollapsibleObject
    public final MultiBlockRenderer multiBlockRenderer = new MultiBlockRenderer();

    public static final class MultiBlockRenderer
    {
        @ConfigEntry.ColorPicker
        public int validColorModel = 0xFFFFFF; // white

        @ConfigEntry.ColorPicker
        public int validColorOutline = 0x0; // black

        @ConfigEntry.ColorPicker
        public int invalidColorModel = 0xEB3223; // red

        @ConfigEntry.ColorPicker
        public int invalidColorOutline = 0xEB3223; // red

        public boolean usesBreathingEffect = true;

        public boolean renderModelOutline = true;

        private MultiBlockRenderer() {}
    }
}
