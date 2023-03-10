package xyz.apex.minecraft.fantasyfurniture.forge;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

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

        nordic();
        venthyr();
    }

    private void nordic()
    {
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
        addBlock(NordicSet.SHELF, "Nordic Shelf");
        addBlock(NordicSet.SOFA, "Nordic Sofa");
        addBlock(NordicSet.COUNTER, "Nordic Counter");
        addCreativeModeTab(NordicSet.NAME, "Nordic");
    }

    private void venthyr()
    {
        addBlock(VenthyrSet.WOOL, "Venthyr Wool");
        addBlock(VenthyrSet.CARPET, "Venthyr Carpet");
        addBlock(VenthyrSet.WALL_LIGHT, "Venthyr Wall Light");
        addBlock(VenthyrSet.FLOOR_LIGHT, "Venthyr Floor Light");
        addBlock(VenthyrSet.TABLE_LARGE, "Venthyr Table Large");
        addBlock(VenthyrSet.TABLE_LARGE_FANCY, "Venthyr Table Large Fancy");
        addBlock(VenthyrSet.TABLE_SMALL, "Venthyr Table Small");
        addBlock(VenthyrSet.TABLE_SMALL_FANCY, "Venthyr Table Small Fancy");
        addBlock(VenthyrSet.TABLE_WIDE, "Venthyr Table Wide");
        addBlock(VenthyrSet.TABLE_WIDE_FANCY, "Venthyr Table Wide Fancy");
        addBlock(VenthyrSet.BENCH, "Venthyr Bench");
        addBlock(VenthyrSet.CHAIR, "Venthyr Chair");
        addBlock(VenthyrSet.CHANDELIER, "Venthyr Chandelier");
        addBlock(VenthyrSet.CUSHION, "Venthyr Cushion");
        addBlock(VenthyrSet.STOOL, "Venthyr Stool");
        addBlock(VenthyrSet.CHEST, "Venthyr Chest");
        addBlock(VenthyrSet.BOOKSHELF, "Venthyr Bookshelf");
        addBlock(VenthyrSet.DESK_LEFT, "Venthyr Desk Left");
        addBlock(VenthyrSet.DESK_RIGHT, "Venthyr Desk Right");
        addBlock(VenthyrSet.DRAWER, "Venthyr Drawer");
        addBlock(VenthyrSet.DRESSER, "Venthyr Dresser");
        addBlock(VenthyrSet.LOCKBOX, "Venthyr Lockbox");
        addBlock(VenthyrSet.WARDROBE_BOTTOM, "Venthyr Wardrobe Bottom");
        addBlock(VenthyrSet.WARDROBE_TOP, "Venthyr Wardrobe Top");
        addBlock(VenthyrSet.PAINTING_WIDE, "Venthyr Painting Wide");
        addBlock(VenthyrSet.PAINTING_SMALL, "Venthyr Painting Small");
        addBlock(VenthyrSet.OVEN, "Venthyr Oven");
        addBlock(VenthyrSet.DOOR_DOUBLE, "Venthyr Door Double");
        addBlock(VenthyrSet.DOOR_SINGLE, "Venthyr Door Single");
        addBlock(VenthyrSet.BED_SINGLE, "Venthyr Bed Single");
        addBlock(VenthyrSet.BED_DOUBLE, "Venthyr Bed Double");
        addBlock(VenthyrSet.SHELF, "Venthyr Shelf");
        addBlock(VenthyrSet.SOFA, "Venthyr Sofa");
        addBlock(VenthyrSet.COUNTER, "Venthyr Counter");
        addCreativeModeTab(VenthyrSet.NAME, "Venthyr");
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
