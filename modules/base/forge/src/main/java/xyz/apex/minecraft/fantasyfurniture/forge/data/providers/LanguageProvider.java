package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;

public abstract class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider
{
    protected final String modId;

    protected LanguageProvider(GatherDataEvent event, String locale)
    {
        super(event.getGenerator().getPackOutput(), event.getModContainer().getModId(), locale);

        modId = event.getModContainer().getModId();
    }

    protected void addCreativeModeTab(@Nullable String subsetType, String value)
    {
        if(subsetType == null) add("itemGroup.%s.blocks".formatted(modId), value);
        else add("itemGroup.%s.%s.blocks".formatted(modId, subsetType), value);
    }
}
