package xyz.apex.minecraft.fantasyfurniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

public enum CounterType implements StringRepresentable
{
    SINGLE("single"),
    CORNER("corner");

    private final String serializedName;

    CounterType(String serializedName)
    {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }
}
