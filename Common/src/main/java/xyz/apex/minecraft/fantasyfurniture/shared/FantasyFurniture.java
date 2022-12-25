package xyz.apex.minecraft.fantasyfurniture.shared;

import xyz.apex.minecraft.fantasyfurniture.shared.init.*;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    static void bootstrap()
    {
        // DefaultedItemProperties.register(ID, properties -> properties.tab(CreativeModeTab.TAB_MISC));

        AllVoxelShapes.bootstrap();
        NordicSet.bootstrap();
        DunmerSet.bootstrap();
        VenthyrSet.bootstrap();
        BoneSet.bootstrap();
        RoyalSet.bootstrap();
        NecrolordSet.bootstrap();
    }
}
