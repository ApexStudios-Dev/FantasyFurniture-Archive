package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.AbstractRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.factory.BlockFactory;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.PropertyDispatch;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.property.ConnectionType;

@ApiStatus.NonExtendable
public interface FurnitureSets
{
    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> wool(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("wool")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.WHITE_WOOL)
                .tag(FantasyFurniture.SITTABLE, BlockTags.WOOL)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends CarpetBlock> BlockBuilder<R, B, R> carpet(R registrar, BlockFactory<B> blockFactory, BlockEntry<? extends Block> wool)
    {
        return registrar
                .object("carpet")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.WHITE_CARPET)
                .tag(FantasyFurniture.SITTABLE, BlockTags.WOOL_CARPETS)
                .defaultBlockState((models, lookup, entry) -> models
                        .withParent(
                                ModelLocationUtils.getModelLocation(entry.value()),
                                models.existingModel(new ResourceLocation("block/carpet"))
                        )
                        .texture("wool", TextureMapping.getBlockTexture(wool.value()))
                )
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> stool(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("stool")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(
                        entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                                )
                        .with(facingProperties())
                )
                .tag(FantasyFurniture.SITTABLE, BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> cushion(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("cushion")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(
                        entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                                )
                        .with(facingProperties())
                )
                .tag(FantasyFurniture.SITTABLE, BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> sofa(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("sofa")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant())
                        .with(connectionProperties(entry))
                        .with(facingProperties())
                )
                .tag(FantasyFurniture.SITTABLE, BlockTags.MINEABLE_WITH_AXE)
                .item()
                    .model((provider, lookup, entry) -> provider
                            .getBuilder(ModelLocationUtils.getModelLocation(entry.value()))
                            .parent(ModelLocationUtils.getModelLocation(entry.value().getBlock(), "_single"))
                    )
                .build()
        ;
    }

    static RegistryEntry<CreativeModeTab> creativeModeTab(AbstractRegistrar<?> registrar, String title)
    {
        return registrar
                .object("items")
                .creativeModeTab()
                .displayItems((parameters, output) -> registrar
                        .getAll(Registries.ITEM)
                        .stream()
                        .filter(RegistryEntry::isBound)
                        .map(ItemEntry::cast)
                        .forEach(output::accept)
                )
                .lang("en_us", title)
        .register();
    }

    static <B extends Block> PropertyDispatch<PropertyDispatch.C1<ConnectionType>> connectionProperties(BlockEntry<B> entry)
    {
        var dispatch = PropertyDispatch.property(ConnectionBlockComponent.PROPERTY);

        for(var connectionType : ConnectionBlockComponent.PROPERTY.getPossibleValues())
        {
            dispatch = dispatch.select(connectionType, Variant.variant().model(ModelLocationUtils.getModelLocation(entry.value(), "_%s".formatted(connectionType.getSerializedName()))));
        }

        return dispatch;
    }

    static PropertyDispatch<PropertyDispatch.C1<Direction>> facingProperties()
    {
        return PropertyDispatch
                .property(BlockStateProperties.HORIZONTAL_FACING)
                .select(Direction.NORTH, Variant.variant())
                .select(Direction.EAST, Variant.variant().yRot(Variant.Rotation.R90))
                .select(Direction.SOUTH, Variant.variant().yRot(Variant.Rotation.R180))
                .select(Direction.WEST, Variant.variant().yRot(Variant.Rotation.R270))
        ;
    }
}
