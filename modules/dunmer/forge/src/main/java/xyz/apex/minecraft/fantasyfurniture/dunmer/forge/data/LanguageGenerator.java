package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(DunmerFurnitureSet.Blocks.WOOL, "Dunmer Wool");
        addBlock(DunmerFurnitureSet.Blocks.CARPET, "Dunmer Carpet");
        addBlock(DunmerFurnitureSet.Blocks.WALL_LIGHT, "Dunmer Wall Light");
        addBlock(DunmerFurnitureSet.Blocks.FLOOR_LIGHT, "Dunmer Floor Light");
        addBlock(DunmerFurnitureSet.Blocks.TABLE_LARGE, "Dunmer Table Large");
        addBlock(DunmerFurnitureSet.Blocks.TABLE_SMALL, "Dunmer Table Small");
        addBlock(DunmerFurnitureSet.Blocks.TABLE_WIDE, "Dunmer Table Wide");
        addBlock(DunmerFurnitureSet.Blocks.BENCH, "Dunmer Bench");
        addBlock(DunmerFurnitureSet.Blocks.CHAIR, "Dunmer Chair");
        addBlock(DunmerFurnitureSet.Blocks.CEILING_LIGHT, "Dunmer Ceiling Light");
        addBlock(DunmerFurnitureSet.Blocks.CUSHION, "Dunmer Cushion");
        addBlock(DunmerFurnitureSet.Blocks.STOOL, "Dunmer Stool");
        addBlock(DunmerFurnitureSet.Blocks.CHEST, "Dunmer Chest");
        addBlock(DunmerFurnitureSet.Blocks.BOOKSHELF, "Dunmer Bookshelf");
        addBlock(DunmerFurnitureSet.Blocks.DESK_LEFT, "Dunmer Desk Left");
        addBlock(DunmerFurnitureSet.Blocks.DESK_RIGHT, "Dunmer Desk Right");
        addBlock(DunmerFurnitureSet.Blocks.DRAWER, "Dunmer Drawer");
        addBlock(DunmerFurnitureSet.Blocks.DRESSER, "Dunmer Dresser");
        addBlock(DunmerFurnitureSet.Blocks.LOCKBOX, "Dunmer Lockbox");
        addBlock(DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM, "Dunmer Wardrobe Bottom");
        addBlock(DunmerFurnitureSet.Blocks.WARDROBE_TOP, "Dunmer Wardrobe Top");
        addBlock(DunmerFurnitureSet.Blocks.PAINTING_SMALL, "Dunmer Painting Wide");
        addBlock(DunmerFurnitureSet.Blocks.PAINTING_WIDE, "Dunmer Painting Small");
        addBlock(DunmerFurnitureSet.Blocks.OVEN, "Dunmer Oven");
        addBlock(DunmerFurnitureSet.Blocks.DOOR_DOUBLE, "Dunmer Door Double");
        addBlock(DunmerFurnitureSet.Blocks.DOOR_SINGLE, "Dunmer Door Single");
        addBlock(DunmerFurnitureSet.Blocks.BED_SINGLE, "Dunmer Bed Single");
        addBlock(DunmerFurnitureSet.Blocks.BED_DOUBLE, "Dunmer Bed Double");
        addBlock(DunmerFurnitureSet.Blocks.SHELF, "Dunmer Shelf");
        addBlock(DunmerFurnitureSet.Blocks.SOFA, "Dunmer Sofa");
        addBlock(DunmerFurnitureSet.Blocks.COUNTER, "Dunmer Counter");
        addCreativeModeTab(null, "Dunmer");
    }
}
