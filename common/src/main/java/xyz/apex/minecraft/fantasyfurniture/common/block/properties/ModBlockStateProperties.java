package xyz.apex.minecraft.fantasyfurniture.common.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public interface ModBlockStateProperties
{
    EnumProperty<ShelfType> SHELF_TYPE = EnumProperty.create("shelf_type", ShelfType.class);
}
