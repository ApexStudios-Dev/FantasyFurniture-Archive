package xyz.apex.minecraft.fantasyfurniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

public enum SofaType implements StringRepresentable
{
    LEFT("left"),
    RIGHT("right"),
    SINGLE("single"),
    CENTER("center"),
    CORNER("corner");

    private String serializedName;

    SofaType(String serializedName)
    {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }
}
