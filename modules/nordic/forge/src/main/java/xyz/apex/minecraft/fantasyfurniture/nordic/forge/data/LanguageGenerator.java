package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(NordicFurnitureSet.Blocks.WOOL, "Nordic Wool");
        addBlock(NordicFurnitureSet.Blocks.CARPET, "Nordic Carpet");
        addBlock(NordicFurnitureSet.Blocks.WALL_LIGHT, "Nordic Wall Light");
        addBlock(NordicFurnitureSet.Blocks.FLOOR_LIGHT, "Nordic Floor Light");
        addBlock(NordicFurnitureSet.Blocks.TABLE_LARGE, "Nordic Table Large");
        addBlock(NordicFurnitureSet.Blocks.TABLE_SMALL, "Nordic Table Small");
        addBlock(NordicFurnitureSet.Blocks.TABLE_WIDE, "Nordic Table Wide");
        addBlock(NordicFurnitureSet.Blocks.BENCH, "Nordic Bench");
        addBlock(NordicFurnitureSet.Blocks.CHAIR, "Nordic Chair");
        addBlock(NordicFurnitureSet.Blocks.CEILING_LIGHT, "Nordic Ceiling Light");
        addBlock(NordicFurnitureSet.Blocks.CUSHION, "Nordic Cushion");
        addBlock(NordicFurnitureSet.Blocks.STOOL, "Nordic Stool");
        addBlock(NordicFurnitureSet.Blocks.CHEST, "Nordic Chest");
        addBlock(NordicFurnitureSet.Blocks.BOOKSHELF, "Nordic Bookshelf");
        addBlock(NordicFurnitureSet.Blocks.DESK_LEFT, "Nordic Desk Left");
        addBlock(NordicFurnitureSet.Blocks.DESK_RIGHT, "Nordic Desk Right");
        addBlock(NordicFurnitureSet.Blocks.DRAWER, "Nordic Drawer");
        addBlock(NordicFurnitureSet.Blocks.DRESSER, "Nordic Dresser");
        addBlock(NordicFurnitureSet.Blocks.LOCKBOX, "Nordic Lockbox");
        addBlock(NordicFurnitureSet.Blocks.WARDROBE_BOTTOM, "Nordic Wardrobe Bottom");
        addBlock(NordicFurnitureSet.Blocks.WARDROBE_TOP, "Nordic Wardrobe Top");
        addBlock(NordicFurnitureSet.Blocks.PAINTING_SMALL, "Nordic Painting Wide");
        addBlock(NordicFurnitureSet.Blocks.PAINTING_WIDE, "Nordic Painting Small");
        addBlock(NordicFurnitureSet.Blocks.OVEN, "Nordic Oven");
        addBlock(NordicFurnitureSet.Blocks.DOOR_DOUBLE, "Nordic Door Double");
        addBlock(NordicFurnitureSet.Blocks.DOOR_SINGLE, "Nordic Door Single");
        addBlock(NordicFurnitureSet.Blocks.BED_SINGLE, "Nordic Bed Single");
        addBlock(NordicFurnitureSet.Blocks.BED_DOUBLE, "Nordic Bed Double");
        addBlock(NordicFurnitureSet.Blocks.SHELF, "Nordic Shelf");
        addBlock(NordicFurnitureSet.Blocks.SOFA, "Nordic Sofa");
        addBlock(NordicFurnitureSet.Blocks.COUNTER, "Nordic Counter");
        addCreativeModeTab(null, "Nordic");
    }
}
