package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LanguageProvider;

final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addConfigValues();

        addBlock(FantasyFurniture.FURNITURE_STATION_BLOCK, "Furniture Station");
        add(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().toLanguageKey("category.rei"), "Furniture Station");
        add(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().toLanguageKey("emi.category"), "Furniture Station");
    }

    private void addConfigValues()
    {
        add("text.autoconfig.%s.title".formatted(FantasyFurniture.ID), "Fantasy's Furniture");

        var category = "multiBlockRenderer";
        addConfig(category, "MultiBlockRenderer");
        addConfig(category, "validColorModel", "Valid Color (Model)");
        addConfig(category, "validColorOutline", "Valid Color (Outline)");
        addConfig(category, "invalidColorModel", "Invalid Color (Model)");
        addConfig(category, "invalidColorOutline", "Invalid Color (Outline)");
        addConfig(category, "usesBreathingEffect", "Uses Breathing Effect");
        addConfig(category, "renderModelOutline", "Render Model Outline");
    }

    private void addConfig(@Nullable String prefix, String key, String value)
    {
        var i18nKey = prefix == null ? key : "%s.%s".formatted(prefix, key);
        var translationKey = "text.autoconfig.%s.option.%s".formatted(FantasyFurniture.ID, i18nKey);
        add(translationKey, value);
    }

    private void addConfig(String key, String value)
    {
        addConfig(null, key, value);
    }
}
