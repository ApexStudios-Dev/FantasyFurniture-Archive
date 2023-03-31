package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(NecrolordFurnitureSet.Blocks.WOOL, "Necrolord Wool");
        addBlock(NecrolordFurnitureSet.Blocks.CARPET, "Necrolord Carpet");
        addBlock(NecrolordFurnitureSet.Blocks.WALL_LIGHT, "Necrolord Wall Light");
        addBlock(NecrolordFurnitureSet.Blocks.FLOOR_LIGHT, "Necrolord Floor Light");
        addBlock(NecrolordFurnitureSet.Blocks.TABLE_LARGE, "Necrolord Table Large");
        addBlock(NecrolordFurnitureSet.Blocks.TABLE_SMALL, "Necrolord Table Small");
        addBlock(NecrolordFurnitureSet.Blocks.TABLE_WIDE, "Necrolord Table Wide");
        addBlock(NecrolordFurnitureSet.Blocks.BENCH, "Necrolord Bench");
        addBlock(NecrolordFurnitureSet.Blocks.CHAIR, "Necrolord Chair");
        addBlock(NecrolordFurnitureSet.Blocks.CEILING_LIGHT, "Necrolord Ceiling Light");
        addBlock(NecrolordFurnitureSet.Blocks.CUSHION, "Necrolord Cushion");
        addBlock(NecrolordFurnitureSet.Blocks.STOOL, "Necrolord Stool");
        addBlock(NecrolordFurnitureSet.Blocks.CHEST, "Necrolord Chest");
        addBlock(NecrolordFurnitureSet.Blocks.BOOKSHELF, "Necrolord Bookshelf");
        addBlock(NecrolordFurnitureSet.Blocks.DESK_LEFT, "Necrolord Desk Left");
        addBlock(NecrolordFurnitureSet.Blocks.DESK_RIGHT, "Necrolord Desk Right");
        addBlock(NecrolordFurnitureSet.Blocks.DRAWER, "Necrolord Drawer");
        addBlock(NecrolordFurnitureSet.Blocks.DRESSER, "Necrolord Dresser");
        addBlock(NecrolordFurnitureSet.Blocks.LOCKBOX, "Necrolord Lockbox");
        addBlock(NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM, "Necrolord Wardrobe Bottom");
        addBlock(NecrolordFurnitureSet.Blocks.WARDROBE_TOP, "Necrolord Wardrobe Top");
        addBlock(NecrolordFurnitureSet.Blocks.PAINTING_SMALL, "Necrolord Painting Wide");
        addBlock(NecrolordFurnitureSet.Blocks.PAINTING_WIDE, "Necrolord Painting Small");
        addBlock(NecrolordFurnitureSet.Blocks.OVEN, "Necrolord Oven");
        addBlock(NecrolordFurnitureSet.Blocks.DOOR_DOUBLE, "Necrolord Door Double");
        addBlock(NecrolordFurnitureSet.Blocks.DOOR_SINGLE, "Necrolord Door Single");
        addBlock(NecrolordFurnitureSet.Blocks.BED_SINGLE, "Necrolord Bed Single");
        addBlock(NecrolordFurnitureSet.Blocks.BED_DOUBLE, "Necrolord Bed Double");
        addBlock(NecrolordFurnitureSet.Blocks.SHELF, "Necrolord Shelf");
        addBlock(NecrolordFurnitureSet.Blocks.SOFA, "Necrolord Sofa");
        addBlock(NecrolordFurnitureSet.Blocks.COUNTER, "Necrolord Counter");
        addCreativeModeTab(null, "Necrolord");
    }
}
