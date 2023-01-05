package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.apache.commons.lang3.Validate;

import net.minecraft.client.renderer.block.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BlockBenchModelDeserializer
{
    private static boolean VALIDATE_REF_MODELS_EXIST = true;

    private static final Gson GSON = new GsonBuilder()
            // .registerTypeAdapter(BlockModel.class, new ExtendedBlockModelDeserializer())
            .registerTypeAdapter(BlockElement.class, new BlockElement.Deserializer())
            .registerTypeAdapter(BlockElementFace.class, new BlockElementFace.Deserializer())
            .registerTypeAdapter(BlockFaceUV.class, new BlockFaceUV.Deserializer())
            .registerTypeAdapter(ItemTransform.class, new ItemTransform.Deserializer())
            .registerTypeAdapter(ItemTransforms.class, new ItemTransforms.Deserializer())
            .registerTypeAdapter(ItemOverride.class, new ItemOverride.Deserializer())
            // .registerTypeAdapter(Transformation.class, new TransformationHelper.Deserializer())
            .create()
    ;

    public static BlockModelBuilder blockModelBuilder(ResourceLocation modelPath, Path filePath, ExistingFileHelper existingFileHelper)
    {
        var root = deserialize(filePath);
        return builder(root, modelPath, existingFileHelper, new BlockModelBuilder(modelPath, existingFileHelper));
    }

    public static ItemModelBuilder itemModelBuilder(ResourceLocation modelPath, Path filePath, ExistingFileHelper existingFileHelper)
    {
        var root = deserialize(filePath);
        var builder = builder(root, modelPath, existingFileHelper, new ItemModelBuilder(modelPath, existingFileHelper));

        if(root.has("overrides"))
        {
            for(var jsonElement : GsonHelper.getAsJsonArray(root, "overrides"))
            {
                var itemOverride = GSON.getAdapter(ItemOverride.class).fromJsonTree(jsonElement);

                itemOverride.getPredicates().forEach(predicate -> builder
                        .override()
                        .model(getModel(itemOverride.getModel(), existingFileHelper, VALIDATE_REF_MODELS_EXIST))
                        .predicate(predicate.getProperty(), predicate.getValue())
                        .end()
                );
            }
        }

        return builder;
    }

    @SuppressWarnings("ConstantValue")
    public static <T extends ModelBuilder<T>> T builder(JsonObject root, ResourceLocation modelPath, ExistingFileHelper existingFileHelper, T builder)
    {
        BlockBenchModelConverter.LOGGER.info("Loading BlockBench Model '{}'", modelPath);
        builder.ao(GsonHelper.getAsBoolean(root, "ambientocclusion", true));

        if(root.has("parent"))
        {
            var parentPath = new ResourceLocation(GsonHelper.getAsString(root, "parent"));
            builder.parent(getModel(parentPath, existingFileHelper, VALIDATE_REF_MODELS_EXIST));
        }

        if(root.has("render_type"))
        {
            var renderType = new ResourceLocation(GsonHelper.getAsString(root, "render_type"));
            builder.renderType(renderType);
        }

        if(root.has("gui_light"))
        {
            var guiLight = BlockModel.GuiLight.getByName(GsonHelper.getAsString(root, "gui_light"));
            builder.guiLight(guiLight);
        }

        if(root.has("elements"))
        {
            for(var jsonElement : GsonHelper.getAsJsonArray(root, "elements"))
            {
                var element = GSON.getAdapter(BlockElement.class).fromJsonTree(jsonElement);

                var elementBuilder = builder
                        .element()
                        .from(element.from.x(), element.from.y(), element.from.z())
                        .to(element.to.x(), element.to.y(), element.to.z())
                        .shade(element.shade)
                ;

                if(element.rotation != null) elementRotation(elementBuilder, element.rotation);

                element.faces.forEach((face, elementFace) -> elementBuilder
                        .face(face)
                        .cullface(elementFace.cullForDirection)
                        .tintindex(elementFace.tintIndex)
                        .texture(elementFace.texture)
                        .uvs(elementFace.uv.uvs[0], elementFace.uv.uvs[1], elementFace.uv.uvs[2], elementFace.uv.uvs[3])
                        .rotation(getRotation(elementFace.uv.rotation))
                        .emissivity(elementFace.emissivity)
                        .end()
                );
            }
        }

        if(root.has("display"))
        {
            var itemTransforms = GSON.getAdapter(ItemTransforms.class).fromJsonTree(GsonHelper.getAsJsonObject(root, "display"));
            var transformsBuilder = builder.transforms();

            transform(transformsBuilder, itemTransforms.thirdPersonLeftHand, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
            transform(transformsBuilder, itemTransforms.thirdPersonRightHand, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            transform(transformsBuilder, itemTransforms.firstPersonLeftHand, ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND);
            transform(transformsBuilder, itemTransforms.firstPersonRightHand, ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND);
            transform(transformsBuilder, itemTransforms.head, ItemTransforms.TransformType.HEAD);
            transform(transformsBuilder, itemTransforms.gui, ItemTransforms.TransformType.GUI);
            transform(transformsBuilder, itemTransforms.ground, ItemTransforms.TransformType.GROUND);
            transform(transformsBuilder, itemTransforms.fixed, ItemTransforms.TransformType.FIXED);

            itemTransforms.moddedTransforms.forEach((key, value) -> transform(transformsBuilder, value, key));
        }

        if(root.has("textures"))
        {
            var texturesJson = GsonHelper.getAsJsonObject(root, "textures");
            texturesJson.keySet().forEach(key -> builder.texture(key, GsonHelper.getAsString(texturesJson, key)));
        }

        // existingFileHelper.trackGenerated(modelPath, PackType.CLIENT_RESOURCES, ".json", "models");
        return builder;
    }

    private static JsonObject deserialize(Path filePath)
    {
        try
        {
            try(var reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8))
            {
                var json = GsonHelper.fromJson(GSON, reader, JsonObject.class);
                Validate.notNull(json);
                return json;
            }
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static <T extends ModelBuilder<T>> void elementRotation(ModelBuilder<T>.ElementBuilder elementBuilder, BlockElementRotation rotation)
    {
        // origin is multiplied by 0.0625 during deserialization
        // we divide by same value here to get back the raw values
        var rotationOrigin = rotation.origin();
        var oX = rotationOrigin.x / .0625F;
        var oY = rotationOrigin.y / .0625F;
        var oZ = rotationOrigin.z / .0625F;

        elementBuilder
                .rotation()
                .angle(rotation.angle())
                .axis(rotation.axis())
                .origin(oX, oY, oZ)
                .rescale(rotation.rescale())
                .end()
        ;
    }

    private static <T extends ModelBuilder<T>> void transform(ModelBuilder<T>.TransformsBuilder builder, ItemTransform transform, ItemTransforms.TransformType transformType)
    {
        // translation is multiplied by 0.0625 during deserialization
        // we divide by same value here to get back the raw values
        var tX = transform.translation.x() / .0625F;
        var tY = transform.translation.y() / .0625F;
        var tZ = transform.translation.z() / .0625F;

        builder.transform(transformType)
               .translation(tX, tY, tZ)
               .scale(transform.scale.x(), transform.scale.y(), transform.scale.z())
               .leftRotation(transform.rotation.x(), transform.rotation.y(), transform.rotation.z())
               .rightRotation(transform.rightRotation.x(), transform.rightRotation.y(), transform.rightRotation.z())
               .end()
        ;
    }

    private static ModelBuilder.FaceRotation getRotation(int rotation)
    {
        return switch(rotation) {
            case 0 -> ModelBuilder.FaceRotation.ZERO;
            case 90 -> ModelBuilder.FaceRotation.CLOCKWISE_90;
            case 180 -> ModelBuilder.FaceRotation.UPSIDE_DOWN;
            case 270 -> ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90;
            default -> throw new JsonParseException("Unknown Block Face Rotation value: '%d'".formatted(rotation));
        };
    }

    private static ModelFile getModel(ResourceLocation modelPath, ExistingFileHelper existingFileHelper, boolean validateExists)
    {
        if(validateExists)
        {
            var model = new ModelFile.ExistingModelFile(modelPath, existingFileHelper);
            model.assertExistence();
            return model;
        }

        return new ModelFile.UncheckedModelFile(modelPath);
    }
}
