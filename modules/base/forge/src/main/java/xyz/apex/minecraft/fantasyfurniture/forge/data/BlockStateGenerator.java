package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        facingBlock(FantasyFurniture.FURNITURE_STATION_BLOCK);

        var tintedCube = tintedCube();
        tintedCubeAll(tintedCube);
        tintedCarpet();
    }

    private ModelFile tintedCarpet()
    {
        return models()
            .getBuilder(TINTED_CARPET_NAME.toString())
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/thin_block")))
            .renderType(RENDER_TYPE_CUTOUT_NAME)
            .texture("particle", "#wool_tint")
            .element()
                .from(0F, 0F, 0F)
                .to(16F, 1F, 16F)
                .face(Direction.DOWN)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.DOWN)
                .end()

                .face(Direction.UP)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool")
                .end()

                .face(Direction.NORTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.NORTH)
                .end()

                .face(Direction.SOUTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.SOUTH)
                .end()

                .face(Direction.WEST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.WEST)
                .end()

                .face(Direction.EAST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.EAST)
                .end()
            .end()

            .element()
                .from(0F, 0F, 0F)
                .to(16F, 1F, 16F)
                .face(Direction.DOWN)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.DOWN)
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()

                .face(Direction.UP)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool_tint")
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()

                .face(Direction.NORTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.NORTH)
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()

                .face(Direction.SOUTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.SOUTH)
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()

                .face(Direction.WEST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.WEST)
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()

                .face(Direction.EAST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.EAST)
                    .tintindex(DyeableBlockEntityComponent.TINT_INDEX)
                .end()
            .end();
    }

    private ModelFile tintedCube()
    {
        return models()
                .getBuilder(TINTED_CUBE_NAME.toString())
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block")))
                .renderType(RENDER_TYPE_CUTOUT_NAME)
                .element()
                    .from(0F, 0F, 0F)
                    .to(16F, 16F, 16F)
                    .allFaces((face, builder) -> builder.texture("#%s".formatted(face.getSerializedName())).cullface(face))
                    .end()

                .element()
                    .from(0F, 0F, 0F)
                    .to(16F, 16F, 16F)
                    .allFaces((face, builder) -> builder.texture("#%s_tint".formatted(face.getSerializedName())).cullface(face).tintindex(DyeableBlockEntityComponent.TINT_INDEX))
                .end();
    }

    private ModelFile tintedCubeAll(ModelFile tintedCube)
    {
        var model = models().getBuilder(TINTED_CUBE_ALL_NAME.toString())
                .parent(tintedCube)
                .texture("particle", "#all_tint")
        ;

        Direction.stream().map(Direction::getSerializedName).forEach(face -> model
                .texture("%s".formatted(face), "#all")
                .texture("%s_tint".formatted(face), "#all_tint")
        );

        return model;
    }
}
