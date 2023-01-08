package xyz.apex.minecraft.fantasyfurniture.forge.dummies;

public class DummyHorizontalItemNameBlock extends DummyHorizontalFacingBlock
{
    public DummyHorizontalItemNameBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public String getDescriptionId()
    {
        return asItem().getDescriptionId();
    }
}
