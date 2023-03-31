package xyz.apex.minecraft.fantasyfurniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

public enum ShelfType implements StringRepresentable
{
    LEFT("left"),
    RIGHT("right"),
    SINGLE("single"),
    CENTER("center");

    private final String serializedName;

    ShelfType(String serializedName)
    {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }
}
