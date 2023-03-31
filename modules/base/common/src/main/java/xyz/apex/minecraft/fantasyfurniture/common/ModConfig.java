package xyz.apex.minecraft.fantasyfurniture.common;

public final class ModConfig
{
    public static final class MultiBlockRenderer
    {
        public static int validColorModel = Defaults.validColorModel;
        public static int validColorOutline = Defaults.validColorOutline;
        public static int invalidColorModel = Defaults.invalidColorModel;
        public static int invalidColorOutline = Defaults.invalidColorOutline;
        public static boolean usesBreathingEffect = Defaults.usesBreathingEffect;
        public static boolean renderModelOutline = Defaults.renderModelOutline;

        interface Defaults
        {
            int validColorModel = 0xFFFFFF; // white
            int validColorOutline = 0x0; // black
            int invalidColorModel = 0xEB3223; // red
            int invalidColorOutline = 0xEB3223; // red
            boolean usesBreathingEffect = true;
            boolean renderModelOutline = true;
        }
    }
}
