package xyz.apex.minecraft.fantasyfurniture.common.block.property;

import net.minecraft.util.StringRepresentable;

public enum ConnectionType implements StringRepresentable
{
    SINGLE("single"),

    INNER_LEFT("inner_left"),
    INNER_RIGHT("inner_right"),

    OUTER_LEFT("outer_left"),
    OUTER_RIGHT("outer_right"),

    LEFT("left"),
    RIGHT("right"),

    CENTER("center");

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
