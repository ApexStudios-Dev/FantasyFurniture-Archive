package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

public final class LanguageGenerator extends LanguageProvider
{
    public LanguageGenerator(PackOutput packOutput)
    {
        super(packOutput, FantasyFurnitureDataMod.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(Nordic.WOOL, "Nordic Wool");
        addBlock(Nordic.CARPET, "Nordic Carpet");
        addBlock(Nordic.WALL_LIGHT, "Nordic Wall Light");
        addBlock(Nordic.FLOOR_LIGHT, "Nordic Floor Light");
        addBlock(Nordic.TABLE_LARGE, "Nordic Table Large");
        addBlock(Nordic.TABLE_SMALL, "Nordic Table Small");
        addBlock(Nordic.TABLE_WIDE, "Nordic Table Wide");
        addBlock(Nordic.BENCH, "Nordic Bench");
        addBlock(Nordic.CHAIR, "Nordic Chair");
        addBlock(Nordic.CHANDELIER, "Nordic Chandelier");
        addBlock(Nordic.CUSHION, "Nordic Cushion");
        addBlock(Nordic.STOOL, "Nordic Stool");
        addBlock(Nordic.CHEST, "Nordic Chest");
        addBlock(Nordic.BOOKSHELF, "Nordic Bookshelf");
        addBlock(Nordic.DESK_LEFT, "Nordic Desk Left");
        addBlock(Nordic.DESK_RIGHT, "Nordic Desk Right");
        addBlock(Nordic.DRAWER, "Nordic Drawer");
        addBlock(Nordic.DRESSER, "Nordic Dresser");
        addBlock(Nordic.LOCKBOX, "Nordic Lockbox");
        addBlock(Nordic.WARDROBE_BOTTOM, "Nordic Wardrobe Bottom");
        addBlock(Nordic.WARDROBE_TOP, "Nordic Wardrobe Top");
        addCreativeModeTab(Nordic.NAME, "Nordic");
    }

    private void addCreativeModeTab(String key, String name)
    {
        add("itemGroup.%s.%s".formatted(FantasyFurnitureDataMod.ID, key), name);
    }
}
