package xyz.apex.minecraft.fantasyfurniture.common.block.properties;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public interface ModBlockStateProperties
{
    EnumProperty<ShelfType> SHELF_TYPE = EnumProperty.create("shelf_type", ShelfType.class);
    EnumProperty<SofaType> SOFA_TYPE = EnumProperty.create("sofa_type", SofaType.class);
    EnumProperty<CounterType> COUNTER_TYPE = EnumProperty.create("counter_type", CounterType.class);
    EnumProperty<DyeColor> DYE_COLOR = EnumProperty.create("dye_color", DyeColor.class);
}
