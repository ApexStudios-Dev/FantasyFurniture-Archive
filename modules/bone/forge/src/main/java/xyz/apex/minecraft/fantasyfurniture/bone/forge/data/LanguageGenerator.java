package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
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
        wither();
        skeleton();
    }

    private void wither()
    {
        addBlock(BoneFurnitureSet.Wither.WOOL, "Bone Wither Wool");
        addBlock(BoneFurnitureSet.Wither.CARPET, "Bone Wither Carpet");
        addBlock(BoneFurnitureSet.Wither.WALL_LIGHT, "Bone Wither Wall Light");
        addBlock(BoneFurnitureSet.Wither.FLOOR_LIGHT, "Bone Wither Floor Light");
        addBlock(BoneFurnitureSet.Wither.TABLE_LARGE, "Bone Wither Table Large");
        addBlock(BoneFurnitureSet.Wither.TABLE_SMALL, "Bone Wither Table Small");
        addBlock(BoneFurnitureSet.Wither.TABLE_WIDE, "Bone Wither Table Wide");
        addBlock(BoneFurnitureSet.Wither.BENCH, "Bone Wither Bench");
        addBlock(BoneFurnitureSet.Wither.CHAIR, "Bone Wither Chair");
        addBlock(BoneFurnitureSet.Wither.CEILING_LIGHT, "Bone Wither Ceiling Light");
        addBlock(BoneFurnitureSet.Wither.CUSHION, "Bone Wither Cushion");
        addBlock(BoneFurnitureSet.Wither.STOOL, "Bone Wither Stool");
        addBlock(BoneFurnitureSet.Wither.CHEST, "Bone Wither Chest");
        addBlock(BoneFurnitureSet.Wither.BOOKSHELF, "Bone Wither Bookshelf");
        addBlock(BoneFurnitureSet.Wither.DESK_LEFT, "Bone Wither Desk Left");
        addBlock(BoneFurnitureSet.Wither.DESK_RIGHT, "Bone Wither Desk Right");
        addBlock(BoneFurnitureSet.Wither.DRAWER, "Bone Wither Drawer");
        addBlock(BoneFurnitureSet.Wither.DRESSER, "Bone Wither Dresser");
        addBlock(BoneFurnitureSet.Wither.LOCKBOX, "Bone Wither Lockbox");
        addBlock(BoneFurnitureSet.Wither.WARDROBE_BOTTOM, "Bone Wither Wardrobe Bottom");
        addBlock(BoneFurnitureSet.Wither.WARDROBE_TOP, "Bone Wither Wardrobe Top");
        addBlock(BoneFurnitureSet.Wither.PAINTING_SMALL, "Bone Wither Painting Wide");
        addBlock(BoneFurnitureSet.Wither.PAINTING_WIDE, "Bone Wither Painting Small");
        addBlock(BoneFurnitureSet.Wither.OVEN, "Bone Wither Oven");
        addBlock(BoneFurnitureSet.Wither.DOOR_DOUBLE, "Bone Wither Door Double");
        addBlock(BoneFurnitureSet.Wither.DOOR_SINGLE, "Bone Wither Door Single");
        addBlock(BoneFurnitureSet.Wither.BED_SINGLE, "Bone Wither Bed Single");
        addBlock(BoneFurnitureSet.Wither.BED_DOUBLE, "Bone Wither Bed Double");
        addBlock(BoneFurnitureSet.Wither.SHELF, "Bone Wither Shelf");
        addBlock(BoneFurnitureSet.Wither.SOFA, "Bone Wither Sofa");
        addBlock(BoneFurnitureSet.Wither.COUNTER, "Bone Wither Counter");
        addCreativeModeTab(BoneFurnitureSet.Wither.SUBSET_ID, "Bone Wither");
    }

    private void skeleton()
    {
        addBlock(BoneFurnitureSet.Skeleton.WOOL, "Bone Skeleton Wool");
        addBlock(BoneFurnitureSet.Skeleton.CARPET, "Bone Skeleton Carpet");
        addBlock(BoneFurnitureSet.Skeleton.WALL_LIGHT, "Bone Skeleton Wall Light");
        addBlock(BoneFurnitureSet.Skeleton.FLOOR_LIGHT, "Bone Skeleton Floor Light");
        addBlock(BoneFurnitureSet.Skeleton.TABLE_LARGE, "Bone Skeleton Table Large");
        addBlock(BoneFurnitureSet.Skeleton.TABLE_SMALL, "Bone Skeleton Table Small");
        addBlock(BoneFurnitureSet.Skeleton.TABLE_WIDE, "Bone Skeleton Table Wide");
        addBlock(BoneFurnitureSet.Skeleton.BENCH, "Bone Skeleton Bench");
        addBlock(BoneFurnitureSet.Skeleton.CHAIR, "Bone Skeleton Chair");
        addBlock(BoneFurnitureSet.Skeleton.CEILING_LIGHT, "Bone Skeleton Ceiling Light");
        addBlock(BoneFurnitureSet.Skeleton.CUSHION, "Bone Skeleton Cushion");
        addBlock(BoneFurnitureSet.Skeleton.STOOL, "Bone Skeleton Stool");
        addBlock(BoneFurnitureSet.Skeleton.CHEST, "Bone Skeleton Chest");
        addBlock(BoneFurnitureSet.Skeleton.BOOKSHELF, "Bone Skeleton Bookshelf");
        addBlock(BoneFurnitureSet.Skeleton.DESK_LEFT, "Bone Skeleton Desk Left");
        addBlock(BoneFurnitureSet.Skeleton.DESK_RIGHT, "Bone Skeleton Desk Right");
        addBlock(BoneFurnitureSet.Skeleton.DRAWER, "Bone Skeleton Drawer");
        addBlock(BoneFurnitureSet.Skeleton.DRESSER, "Bone Skeleton Dresser");
        addBlock(BoneFurnitureSet.Skeleton.LOCKBOX, "Bone Skeleton Lockbox");
        addBlock(BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM, "Bone Skeleton Wardrobe Bottom");
        addBlock(BoneFurnitureSet.Skeleton.WARDROBE_TOP, "Bone Skeleton Wardrobe Top");
        addBlock(BoneFurnitureSet.Skeleton.PAINTING_SMALL, "Bone Skeleton Painting Wide");
        addBlock(BoneFurnitureSet.Skeleton.PAINTING_WIDE, "Bone Skeleton Painting Small");
        addBlock(BoneFurnitureSet.Skeleton.OVEN, "Bone Skeleton Oven");
        addBlock(BoneFurnitureSet.Skeleton.DOOR_DOUBLE, "Bone Skeleton Door Double");
        addBlock(BoneFurnitureSet.Skeleton.DOOR_SINGLE, "Bone Skeleton Door Single");
        addBlock(BoneFurnitureSet.Skeleton.BED_SINGLE, "Bone Skeleton Bed Single");
        addBlock(BoneFurnitureSet.Skeleton.BED_DOUBLE, "Bone Skeleton Bed Double");
        addBlock(BoneFurnitureSet.Skeleton.SHELF, "Bone Skeleton Shelf");
        addBlock(BoneFurnitureSet.Skeleton.SOFA, "Bone Skeleton Sofa");
        addBlock(BoneFurnitureSet.Skeleton.COUNTER, "Bone Skeleton Counter");
        addCreativeModeTab(BoneFurnitureSet.Skeleton.SUBSET_ID, "Bone Skeleton");
    }
}
