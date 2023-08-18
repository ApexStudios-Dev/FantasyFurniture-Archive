package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.core.ApexTags;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.lib.registry.AbstractRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.factory.BlockFactory;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.PropertyDispatch;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LargeContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.MediumContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.SmallContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.property.ConnectionType;

@ApiStatus.NonExtendable
public interface FurnitureSets
{
    MultiBlockType MB_2x1x1 = MultiBlockType.builder().renderAtOriginOnly().with("XX").build();

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> wool(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("wool")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.WHITE_WOOL)
                .tag(BlockTags.WOOL)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends CarpetBlock> BlockBuilder<R, B, R> carpet(R registrar, BlockFactory<B> blockFactory, BlockEntry<? extends Block> wool)
    {
        return registrar
                .object("carpet")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.WHITE_CARPET)
                .tag(BlockTags.WOOL_CARPETS)
                .defaultBlockState((models, lookup, entry) -> models.carpet(entry.value(), models.getTexturePath(wool.value())))
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> stool(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("stool")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
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
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> paintingSmall(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("painting_small")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> paintingWide(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("painting_wide")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> drawer(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("drawer")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends BaseBlockComponentHolder> BlockBuilder<R, B, R> shelf(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("shelf")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant())
                        .with(connectionProperties(entry, ConnectionBlockComponent.SHELF_COMPONENT_TYPE))
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .item()
                    .model((provider, lookup, entry) -> provider
                            .getBuilder(ModelLocationUtils.getModelLocation(entry.value()))
                            .parent(ModelLocationUtils.getModelLocation(entry.value().getBlock(), "_single"))
                    )
                .build()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends BaseBlockComponentHolder> BlockBuilder<R, B, R> sofa(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("sofa")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant())
                        .with(connectionProperties(entry, ConnectionBlockComponent.SOFA_COMPONENT_TYPE))
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> bench(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("bench")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> lockbox(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("lockbox")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> deskLeft(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("desk_left")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> deskRight(R registrar, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("desk_right")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .defaultItem()
        ;
    }

    @SafeVarargs
    static <R extends AbstractRegistrar<R>> BlockEntityBuilder<R, SmallContainerBlockEntity, R> smallContainer(R registrar, BlockEntry<? extends Block>... blocks)
    {
        return registrar.object("small_container").blockEntity(SmallContainerBlockEntity::new).validBlocks(blocks);
    }

    @SafeVarargs
    static <R extends AbstractRegistrar<R>> BlockEntityBuilder<R, MediumContainerBlockEntity, R> mediumContainer(R registrar, BlockEntry<? extends Block>... blocks)
    {
        return registrar.object("medium_container").blockEntity(MediumContainerBlockEntity::new).validBlocks(blocks);
    }

    @SafeVarargs
    static <R extends AbstractRegistrar<R>> BlockEntityBuilder<R, LargeContainerBlockEntity, R> largeContainer(R registrar, BlockEntry<? extends Block>... blocks)
    {
        return registrar.object("large_container").blockEntity(LargeContainerBlockEntity::new).validBlocks(blocks);
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

    static <B extends BaseBlockComponentHolder> PropertyDispatch<PropertyDispatch.C1<ConnectionType>> connectionProperties(BlockEntry<B> entry, BlockComponentType<ConnectionBlockComponent> componentType)
    {
        var property = entry.value().getRequiredComponent(componentType).getProperty();
        var dispatch = PropertyDispatch.property(property);

        for(var connectionType : property.getPossibleValues())
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
