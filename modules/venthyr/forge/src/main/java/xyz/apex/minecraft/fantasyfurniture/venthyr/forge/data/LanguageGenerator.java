package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(VenthyrFurnitureSet.Blocks.WOOL, "Venthyr Wool");
        addBlock(VenthyrFurnitureSet.Blocks.CARPET, "Venthyr Carpet");
        addBlock(VenthyrFurnitureSet.Blocks.WALL_LIGHT, "Venthyr Wall Light");
        addBlock(VenthyrFurnitureSet.Blocks.FLOOR_LIGHT, "Venthyr Floor Light");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_LARGE, "Venthyr Table Large");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY, "Venthyr Table Large Fancy");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_SMALL, "Venthyr Table Small");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY, "Venthyr Table Small Fancy");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_WIDE, "Venthyr Table Wide");
        addBlock(VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY, "Venthyr Table Wide Fancy");
        addBlock(VenthyrFurnitureSet.Blocks.BENCH, "Venthyr Bench");
        addBlock(VenthyrFurnitureSet.Blocks.CHAIR, "Venthyr Chair");
        addBlock(VenthyrFurnitureSet.Blocks.CEILING_LIGHT, "Venthyr Ceiling Light");
        addBlock(VenthyrFurnitureSet.Blocks.CUSHION, "Venthyr Cushion");
        addBlock(VenthyrFurnitureSet.Blocks.STOOL, "Venthyr Stool");
        addBlock(VenthyrFurnitureSet.Blocks.CHEST, "Venthyr Chest");
        addBlock(VenthyrFurnitureSet.Blocks.BOOKSHELF, "Venthyr Bookshelf");
        addBlock(VenthyrFurnitureSet.Blocks.DESK_LEFT, "Venthyr Desk Left");
        addBlock(VenthyrFurnitureSet.Blocks.DESK_RIGHT, "Venthyr Desk Right");
        addBlock(VenthyrFurnitureSet.Blocks.DRAWER, "Venthyr Drawer");
        addBlock(VenthyrFurnitureSet.Blocks.DRESSER, "Venthyr Dresser");
        addBlock(VenthyrFurnitureSet.Blocks.LOCKBOX, "Venthyr Lockbox");
        addBlock(VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM, "Venthyr Wardrobe Bottom");
        addBlock(VenthyrFurnitureSet.Blocks.WARDROBE_TOP, "Venthyr Wardrobe Top");
        addBlock(VenthyrFurnitureSet.Blocks.PAINTING_SMALL, "Venthyr Painting Wide");
        addBlock(VenthyrFurnitureSet.Blocks.PAINTING_WIDE, "Venthyr Painting Small");
        addBlock(VenthyrFurnitureSet.Blocks.OVEN, "Venthyr Oven");
        addBlock(VenthyrFurnitureSet.Blocks.DOOR_DOUBLE, "Venthyr Door Double");
        addBlock(VenthyrFurnitureSet.Blocks.DOOR_SINGLE, "Venthyr Door Single");
        addBlock(VenthyrFurnitureSet.Blocks.BED_SINGLE, "Venthyr Bed Single");
        addBlock(VenthyrFurnitureSet.Blocks.BED_DOUBLE, "Venthyr Bed Double");
        addBlock(VenthyrFurnitureSet.Blocks.SHELF, "Venthyr Shelf");
        addBlock(VenthyrFurnitureSet.Blocks.SOFA, "Venthyr Sofa");
        addBlock(VenthyrFurnitureSet.Blocks.COUNTER, "Venthyr Counter");
        addCreativeModeTab(null, "Venthyr");
    }
}
