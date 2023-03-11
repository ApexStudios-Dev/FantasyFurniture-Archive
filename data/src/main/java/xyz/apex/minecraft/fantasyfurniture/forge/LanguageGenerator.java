package xyz.apex.minecraft.fantasyfurniture.forge;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

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

        addCreativeModeTab(BoneSet.BASE_NAME, "Bone");

        nordic();
        venthyr();
        dunmer();
        boneWither();
        boneSkeleton();
        necrolord();
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

    private void dunmer()
    {
        addBlock(DunmerSet.WOOL, "Dunmer Wool");
        addBlock(DunmerSet.CARPET, "Dunmer Carpet");
        addBlock(DunmerSet.WALL_LIGHT, "Dunmer Wall Light");
        addBlock(DunmerSet.FLOOR_LIGHT, "Dunmer Floor Light");
        addBlock(DunmerSet.TABLE_LARGE, "Dunmer Table Large");
        addBlock(DunmerSet.TABLE_SMALL, "Dunmer Table Small");
        addBlock(DunmerSet.TABLE_WIDE, "Dunmer Table Wide");
        addBlock(DunmerSet.BENCH, "Dunmer Bench");
        addBlock(DunmerSet.CHAIR, "Dunmer Chair");
        addBlock(DunmerSet.CHANDELIER, "Dunmer Chandelier");
        addBlock(DunmerSet.CUSHION, "Dunmer Cushion");
        addBlock(DunmerSet.STOOL, "Dunmer Stool");
        addBlock(DunmerSet.CHEST, "Dunmer Chest");
        addBlock(DunmerSet.BOOKSHELF, "Dunmer Bookshelf");
        addBlock(DunmerSet.DESK_LEFT, "Dunmer Desk Left");
        addBlock(DunmerSet.DESK_RIGHT, "Dunmer Desk Right");
        addBlock(DunmerSet.DRAWER, "Dunmer Drawer");
        addBlock(DunmerSet.DRESSER, "Dunmer Dresser");
        addBlock(DunmerSet.LOCKBOX, "Dunmer Lockbox");
        addBlock(DunmerSet.WARDROBE_BOTTOM, "Dunmer Wardrobe Bottom");
        addBlock(DunmerSet.WARDROBE_TOP, "Dunmer Wardrobe Top");
        addBlock(DunmerSet.PAINTING_WIDE, "Dunmer Painting Wide");
        addBlock(DunmerSet.PAINTING_SMALL, "Dunmer Painting Small");
        addBlock(DunmerSet.OVEN, "Dunmer Oven");
        addBlock(DunmerSet.DOOR_DOUBLE, "Dunmer Door Double");
        addBlock(DunmerSet.DOOR_SINGLE, "Dunmer Door Single");
        addBlock(DunmerSet.BED_SINGLE, "Dunmer Bed Single");
        addBlock(DunmerSet.BED_DOUBLE, "Dunmer Bed Double");
        addBlock(DunmerSet.SHELF, "Dunmer Shelf");
        addBlock(DunmerSet.SOFA, "Dunmer Sofa");
        addBlock(DunmerSet.COUNTER, "Dunmer Counter");
        addCreativeModeTab(DunmerSet.NAME, "Dunmer");
    }

    private void boneWither()
    {
        addBlock(BoneSet.Wither.WOOL, "Bone Wither Wool");
        addBlock(BoneSet.Wither.CARPET, "Bone Wither Carpet");
        addBlock(BoneSet.Wither.WALL_LIGHT, "Bone Wither Wall Light");
        addBlock(BoneSet.Wither.FLOOR_LIGHT, "Bone Wither Floor Light");
        addBlock(BoneSet.Wither.TABLE_LARGE, "Bone Wither Table Large");
        addBlock(BoneSet.Wither.TABLE_SMALL, "Bone Wither Table Small");
        addBlock(BoneSet.Wither.TABLE_WIDE, "Bone Wither Table Wide");
        addBlock(BoneSet.Wither.BENCH, "Bone Wither Bench");
        addBlock(BoneSet.Wither.CHAIR, "Bone Wither Chair");
        addBlock(BoneSet.Wither.CHANDELIER, "Bone Wither Chandelier");
        addBlock(BoneSet.Wither.CUSHION, "Bone Wither Cushion");
        addBlock(BoneSet.Wither.STOOL, "Bone Wither Stool");
        addBlock(BoneSet.Wither.CHEST, "Bone Wither Chest");
        addBlock(BoneSet.Wither.BOOKSHELF, "Bone Wither Bookshelf");
        addBlock(BoneSet.Wither.DESK_LEFT, "Bone Wither Desk Left");
        addBlock(BoneSet.Wither.DESK_RIGHT, "Bone Wither Desk Right");
        addBlock(BoneSet.Wither.DRAWER, "Bone Wither Drawer");
        addBlock(BoneSet.Wither.DRESSER, "Bone Wither Dresser");
        addBlock(BoneSet.Wither.LOCKBOX, "Bone Wither Lockbox");
        addBlock(BoneSet.Wither.WARDROBE_BOTTOM, "Bone Wither Wardrobe Bottom");
        addBlock(BoneSet.Wither.WARDROBE_TOP, "Bone Wither Wardrobe Top");
        addBlock(BoneSet.Wither.PAINTING_WIDE, "Bone Wither Painting Wide");
        addBlock(BoneSet.Wither.PAINTING_SMALL, "Bone Wither Painting Small");
        addBlock(BoneSet.Wither.OVEN, "Bone Wither Oven");
        addBlock(BoneSet.Wither.DOOR_DOUBLE, "Bone Wither Door Double");
        addBlock(BoneSet.Wither.DOOR_SINGLE, "Bone Wither Door Single");
        addBlock(BoneSet.Wither.BED_SINGLE, "Bone Wither Bed Single");
        addBlock(BoneSet.Wither.BED_DOUBLE, "Bone Wither Bed Double");
        addBlock(BoneSet.Wither.SHELF, "Bone Wither Shelf");
        addBlock(BoneSet.Wither.SOFA, "Bone Wither Sofa");
        addBlock(BoneSet.Wither.COUNTER, "Bone Wither Counter");
        addCreativeModeTab(BoneSet.Wither.NAME, "Bone Wither");
    }

    private void boneSkeleton()
    {
        addBlock(BoneSet.Skeleton.WOOL, "Bone Skeleton Wool");
        addBlock(BoneSet.Skeleton.CARPET, "Bone Skeleton Carpet");
        addBlock(BoneSet.Skeleton.WALL_LIGHT, "Bone Skeleton Wall Light");
        addBlock(BoneSet.Skeleton.FLOOR_LIGHT, "Bone Skeleton Floor Light");
        addBlock(BoneSet.Skeleton.TABLE_LARGE, "Bone Skeleton Table Large");
        addBlock(BoneSet.Skeleton.TABLE_SMALL, "Bone Skeleton Table Small");
        addBlock(BoneSet.Skeleton.TABLE_WIDE, "Bone Skeleton Table Wide");
        addBlock(BoneSet.Skeleton.BENCH, "Bone Skeleton Bench");
        addBlock(BoneSet.Skeleton.CHAIR, "Bone Skeleton Chair");
        addBlock(BoneSet.Skeleton.CHANDELIER, "Bone Skeleton Chandelier");
        addBlock(BoneSet.Skeleton.CUSHION, "Bone Skeleton Cushion");
        addBlock(BoneSet.Skeleton.STOOL, "Bone Skeleton Stool");
        addBlock(BoneSet.Skeleton.CHEST, "Bone Skeleton Chest");
        addBlock(BoneSet.Skeleton.BOOKSHELF, "Bone Skeleton Bookshelf");
        addBlock(BoneSet.Skeleton.DESK_LEFT, "Bone Skeleton Desk Left");
        addBlock(BoneSet.Skeleton.DESK_RIGHT, "Bone Skeleton Desk Right");
        addBlock(BoneSet.Skeleton.DRAWER, "Bone Skeleton Drawer");
        addBlock(BoneSet.Skeleton.DRESSER, "Bone Skeleton Dresser");
        addBlock(BoneSet.Skeleton.LOCKBOX, "Bone Skeleton Lockbox");
        addBlock(BoneSet.Skeleton.WARDROBE_BOTTOM, "Bone Skeleton Wardrobe Bottom");
        addBlock(BoneSet.Skeleton.WARDROBE_TOP, "Bone Skeleton Wardrobe Top");
        addBlock(BoneSet.Skeleton.PAINTING_WIDE, "Bone Skeleton Painting Wide");
        addBlock(BoneSet.Skeleton.PAINTING_SMALL, "Bone Skeleton Painting Small");
        addBlock(BoneSet.Skeleton.OVEN, "Bone Skeleton Oven");
        addBlock(BoneSet.Skeleton.DOOR_DOUBLE, "Bone Skeleton Door Double");
        addBlock(BoneSet.Skeleton.DOOR_SINGLE, "Bone Skeleton Door Single");
        addBlock(BoneSet.Skeleton.BED_SINGLE, "Bone Skeleton Bed Single");
        addBlock(BoneSet.Skeleton.BED_DOUBLE, "Bone Skeleton Bed Double");
        addBlock(BoneSet.Skeleton.SHELF, "Bone Skeleton Shelf");
        addBlock(BoneSet.Skeleton.SOFA, "Bone Skeleton Sofa");
        addBlock(BoneSet.Skeleton.COUNTER, "Bone Skeleton Counter");
        addCreativeModeTab(BoneSet.Skeleton.NAME, "Bone Skeleton");
    }

    private void necrolord()
    {
        addBlock(NecrolordSet.WOOL, "Necrolord Wool");
        addBlock(NecrolordSet.CARPET, "Necrolord Carpet");
        addBlock(NecrolordSet.WALL_LIGHT, "Necrolord Wall Light");
        addBlock(NecrolordSet.FLOOR_LIGHT, "Necrolord Floor Light");
        addBlock(NecrolordSet.TABLE_LARGE, "Necrolord Table Large");
        addBlock(NecrolordSet.TABLE_SMALL, "Necrolord Table Small");
        addBlock(NecrolordSet.TABLE_WIDE, "Necrolord Table Wide");
        addBlock(NecrolordSet.BENCH, "Necrolord Bench");
        addBlock(NecrolordSet.CHAIR, "Necrolord Chair");
        addBlock(NecrolordSet.CHANDELIER, "Necrolord Chandelier");
        addBlock(NecrolordSet.CUSHION, "Necrolord Cushion");
        addBlock(NecrolordSet.STOOL, "Necrolord Stool");
        addBlock(NecrolordSet.CHEST, "Necrolord Chest");
        addBlock(NecrolordSet.BOOKSHELF, "Necrolord Bookshelf");
        addBlock(NecrolordSet.DESK_LEFT, "Necrolord Desk Left");
        addBlock(NecrolordSet.DESK_RIGHT, "Necrolord Desk Right");
        addBlock(NecrolordSet.DRAWER, "Necrolord Drawer");
        addBlock(NecrolordSet.DRESSER, "Necrolord Dresser");
        addBlock(NecrolordSet.LOCKBOX, "Necrolord Lockbox");
        addBlock(NecrolordSet.WARDROBE_BOTTOM, "Necrolord Wardrobe Bottom");
        addBlock(NecrolordSet.WARDROBE_TOP, "Necrolord Wardrobe Top");
        addBlock(NecrolordSet.PAINTING_WIDE, "Necrolord Painting Wide");
        addBlock(NecrolordSet.PAINTING_SMALL, "Necrolord Painting Small");
        addBlock(NecrolordSet.OVEN, "Necrolord Oven");
        addBlock(NecrolordSet.DOOR_DOUBLE, "Necrolord Door Double");
        addBlock(NecrolordSet.DOOR_SINGLE, "Necrolord Door Single");
        addBlock(NecrolordSet.BED_SINGLE, "Necrolord Bed Single");
        addBlock(NecrolordSet.BED_DOUBLE, "Necrolord Bed Double");
        addBlock(NecrolordSet.SHELF, "Necrolord Shelf");
        addBlock(NecrolordSet.SOFA, "Necrolord Sofa");
        addBlock(NecrolordSet.COUNTER, "Necrolord Counter");
        addCreativeModeTab(NecrolordSet.NAME, "Necrolord");
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
