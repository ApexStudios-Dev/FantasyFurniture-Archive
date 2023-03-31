package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.forge.data.providers.BlockBenchModelConverter;
import xyz.apex.minecraft.apexcore.forge.data.providers.BlockBenchModelDeserializer;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;

public abstract class BlockBenchProvider extends BlockBenchModelConverter
{
    protected final ResourceLocation renderTypeCutout = new ResourceLocation("minecraft", "cutout");
    protected final ModelFile modelBlockBlock = new ModelFile.ExistingModelFile(new ResourceLocation("minecraft", "block/block"), existingFileHelper);

    protected BlockBenchProvider(GatherDataEvent event)
    {
        super(event, event.getModContainer().getModId());

        // we dont care about textures, we specify our own during conversion
        // and existing values could potentially throw errors if
        // pointing to none existent files
        BlockBenchModelDeserializer.PARSE_TEXTURES = false;
    }

    protected BlockModelBuilder convertDesk(@Nullable String subSetType, String type, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "desk_%s".formatted(type),
                "desk",
                tinted
        );
    }

    protected BlockModelBuilder convertWardrobe(@Nullable String subSetType, String type, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "wardrobe_%s".formatted(type),
                "wardrobe",
                tinted
        );
    }

    protected BlockModelBuilder convertDoor(@Nullable String subSetType, String doorType, boolean isLeft, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "door_%s_%s".formatted(doorType, isLeft ? "left" : "right"),
                "door_%s".formatted(doorType),
                tinted
        );
    }

    protected BlockModelBuilder convertShelf(@Nullable String subSetType, ShelfType shelfType, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "shelf_%s".formatted(shelfType.getSerializedName()),
                "shelf",
                tinted
        );
    }

    protected BlockModelBuilder convertSofa(@Nullable String subSetType, SofaType sofaType, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "sofa_%s".formatted(sofaType.getSerializedName()),
                "sofa",
                tinted
        );
    }

    protected BlockModelBuilder convertCounter(@Nullable String subSetType, CounterType counterType, boolean tinted)
    {
        return convertFurnitureEx(
                subSetType,
                "counter_%s".formatted(counterType.getSerializedName()),
                "counter",
                tinted
        );
    }

    protected BlockModelBuilder convertBasic(String blockName, boolean tinted)
    {
        return convert(
                new ResourceLocation(modId, "block/%s".formatted(blockName)),
                new ResourceLocation(modId, "block/%s/particle".formatted(blockName)),
                blockName,
                new ResourceLocation(modId, "block/%s/block".formatted(blockName)),
                tinted
        );
    }

    protected BlockModelBuilder convertFurniture(@Nullable String subSetType, String furnitureType, boolean tinted)
    {
        return convertFurnitureEx(subSetType, furnitureType, furnitureType, tinted);
    }

    protected BlockModelBuilder convertFurnitureEx(@Nullable String subSetType, String furnitureTypeFull, String furnitureTypeShort, boolean tinted)
    {
        var path = subSetType == null ? "block" : "block/%s".formatted(subSetType);

        return convert(
                new ResourceLocation(modId, "%s/%s".formatted(path, furnitureTypeFull)),
                new ResourceLocation(modId, "%s/particle".formatted(path)),
                furnitureTypeShort,
                new ResourceLocation(modId, "%s/%s".formatted(path, furnitureTypeShort)),
                tinted
        );
    }

    protected BlockModelBuilder convert(ResourceLocation model, ResourceLocation particle, String textureKey, ResourceLocation texturePath, boolean tinted)
    {
        var result = blockModelBuilder(model)
                .parent(modelBlockBlock)
                .texture("particle", particle)
                .texture(textureKey, texturePath)
        ;

        if(tinted) return result.texture("%s_tint".formatted(textureKey), texturePath.withPath("%s_tint"::formatted));
        return result;
    }
}
