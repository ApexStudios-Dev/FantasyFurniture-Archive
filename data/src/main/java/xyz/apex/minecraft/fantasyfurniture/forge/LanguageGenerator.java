package xyz.apex.minecraft.fantasyfurniture.forge;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

public final class LanguageGenerator extends LanguageProvider
{
    public LanguageGenerator(PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addConfigValues();

        addBlock(FantasyFurniture.FURNITURE_STATION_BLOCK, "Furniture Station");
        add(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().toLanguageKey("category.rei"), "Furniture Station");
        add(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().toLanguageKey("emi.category"), "Furniture Station");

        addBlock(NordicSet.WOOL, "Nordic Wool");
        addBlock(NordicSet.CARPET, "Nordic Carpet");
        addBlock(NordicSet.WALL_LIGHT, "Nordic Wall Light");
        addBlock(NordicSet.FLOOR_LIGHT, "Nordic Floor Light");
        addBlock(NordicSet.TABLE_LARGE, "Nordic Table Large");
        addBlock(NordicSet.TABLE_SMALL, "Nordic Table Small");
        addBlock(NordicSet.TABLE_WIDE, "Nordic Table Wide");
        addBlock(NordicSet.BENCH, "Nordic Bench");
        addBlock(NordicSet.CHAIR, "Nordic Chair");
        addBlock(NordicSet.CHANDELIER, "Nordic Chandelier");
        addBlock(NordicSet.CUSHION, "Nordic Cushion");
        addBlock(NordicSet.STOOL, "Nordic Stool");
        addBlock(NordicSet.CHEST, "Nordic Chest");
        addBlock(NordicSet.BOOKSHELF, "Nordic Bookshelf");
        addBlock(NordicSet.DESK_LEFT, "Nordic Desk Left");
        addBlock(NordicSet.DESK_RIGHT, "Nordic Desk Right");
        addBlock(NordicSet.DRAWER, "Nordic Drawer");
        addBlock(NordicSet.DRESSER, "Nordic Dresser");
        addBlock(NordicSet.LOCKBOX, "Nordic Lockbox");
        addBlock(NordicSet.WARDROBE_BOTTOM, "Nordic Wardrobe Bottom");
        addBlock(NordicSet.WARDROBE_TOP, "Nordic Wardrobe Top");
        addBlock(NordicSet.PAINTING_WIDE, "Nordic Painting Wide");
        addBlock(NordicSet.PAINTING_SMALL, "Nordic Painting Small");
        addBlock(NordicSet.OVEN, "Nordic Oven");
        addBlock(NordicSet.DOOR_DOUBLE, "Nordic Door Double");
        addBlock(NordicSet.DOOR_SINGLE, "Nordic Door Single");
        addBlock(NordicSet.BED_SINGLE, "Nordic Bed Single");
        addBlock(NordicSet.BED_DOUBLE, "Nordic Bed Double");
        addCreativeModeTab(NordicSet.NAME, "Nordic");
    }

    private void addCreativeModeTab(String key, String name)
    {
        add("itemGroup.%s.%s".formatted(FantasyFurniture.ID, key), name);
    }

    private void addConfigValues()
    {
        add("text.autoconfig.%s.title".formatted(FantasyFurniture.ID), "Fantasy's Furniture");

        var category = "multiBlockRenderer";
        addConfig(category, "MultiBlockRenderer");
        addConfig(category, "validColorModel", "Valid Color (Model)");
        addConfig(category, "validColorOutline", "Valid Color (Outline)");
        addConfig(category, "invalidColorModel", "Invalid Color (Model)");
        addConfig(category, "invalidColorOutline", "Invalid Color (Outline)");
        addConfig(category, "usesBreathingEffect", "Uses Breathing Effect");
        addConfig(category, "renderModelOutline", "Render Model Outline");
    }

    private void addConfig(@Nullable String prefix, String key, String value)
    {
        var i18nKey = prefix == null ? key : "%s.%s".formatted(prefix, key);
        var translationKey = "text.autoconfig.%s.option.%s".formatted(FantasyFurniture.ID, i18nKey);
        add(translationKey, value);
    }

    private void addConfig(String key, String value)
    {
        addConfig(null, key, value);
    }
}
