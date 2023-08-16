package xyz.apex.minecraft.fantasyfurniture.common.block.property;

import net.minecraft.util.StringRepresentable;

public enum ConnectionType implements StringRepresentable
{
    SINGLE("single"),
    LEFT("left"),
    RIGHT("right"),
    CENTER("center"),
    CORNER("corner");

    private final String serializedName;

    ConnectionType(String serializedName)
    {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }
}
