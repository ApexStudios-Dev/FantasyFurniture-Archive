package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(RoyalFurnitureSet.Blocks.WOOL, "Royal Wool");
        addBlock(RoyalFurnitureSet.Blocks.CARPET, "Royal Carpet");
        addBlock(RoyalFurnitureSet.Blocks.WALL_LIGHT, "Royal Wall Light");
        addBlock(RoyalFurnitureSet.Blocks.FLOOR_LIGHT, "Royal Floor Light");
        addBlock(RoyalFurnitureSet.Blocks.TABLE_LARGE, "Royal Table Large");
        addBlock(RoyalFurnitureSet.Blocks.TABLE_SMALL, "Royal Table Small");
        addBlock(RoyalFurnitureSet.Blocks.TABLE_WIDE, "Royal Table Wide");
        addBlock(RoyalFurnitureSet.Blocks.BENCH, "Royal Bench");
        addBlock(RoyalFurnitureSet.Blocks.CHAIR, "Royal Chair");
        addBlock(RoyalFurnitureSet.Blocks.CEILING_LIGHT, "Royal Ceiling Light");
        addBlock(RoyalFurnitureSet.Blocks.CUSHION, "Royal Cushion");
        addBlock(RoyalFurnitureSet.Blocks.STOOL, "Royal Stool");
        addBlock(RoyalFurnitureSet.Blocks.CHEST, "Royal Chest");
        addBlock(RoyalFurnitureSet.Blocks.BOOKSHELF, "Royal Bookshelf");
        addBlock(RoyalFurnitureSet.Blocks.DESK_LEFT, "Royal Desk Left");
        addBlock(RoyalFurnitureSet.Blocks.DESK_RIGHT, "Royal Desk Right");
        addBlock(RoyalFurnitureSet.Blocks.DRAWER, "Royal Drawer");
        addBlock(RoyalFurnitureSet.Blocks.DRESSER, "Royal Dresser");
        addBlock(RoyalFurnitureSet.Blocks.LOCKBOX, "Royal Lockbox");
        addBlock(RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM, "Royal Wardrobe Bottom");
        addBlock(RoyalFurnitureSet.Blocks.WARDROBE_TOP, "Royal Wardrobe Top");
        addBlock(RoyalFurnitureSet.Blocks.PAINTING_SMALL, "Royal Painting Wide");
        addBlock(RoyalFurnitureSet.Blocks.PAINTING_WIDE, "Royal Painting Small");
        addBlock(RoyalFurnitureSet.Blocks.OVEN, "Royal Oven");
        addBlock(RoyalFurnitureSet.Blocks.DOOR_DOUBLE, "Royal Door Double");
        addBlock(RoyalFurnitureSet.Blocks.DOOR_SINGLE, "Royal Door Single");
        addBlock(RoyalFurnitureSet.Blocks.BED_SINGLE, "Royal Bed Single");
        addBlock(RoyalFurnitureSet.Blocks.BED_DOUBLE, "Royal Bed Double");
        addBlock(RoyalFurnitureSet.Blocks.SHELF, "Royal Shelf");
        addBlock(RoyalFurnitureSet.Blocks.SOFA, "Royal Sofa");
        addBlock(RoyalFurnitureSet.Blocks.COUNTER, "Royal Counter");
        addCreativeModeTab(null, "Royal");
    }
}
