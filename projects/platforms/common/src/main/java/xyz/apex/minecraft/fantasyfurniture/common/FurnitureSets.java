package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.core.ApexTags;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.lib.registry.AbstractRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.factory.BlockFactory;
import xyz.apex.minecraft.apexcore.common.lib.registry.generic.WoodTypeBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderTypes;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.PropertyDispatch;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.TableComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LargeContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.MediumContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.SmallContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.property.ConnectionType;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@ApiStatus.NonExtendable
public interface FurnitureSets
{
    MultiBlockType MB_2x1x1 = MultiBlockType.builder().renderAtOriginOnly().with("XX").build();
    MultiBlockType MB_2x2x1 = MultiBlockType.builder().renderAtOriginOnly().with("XX").with("XX").build();
    MultiBlockType MB_1x2x1 = MultiBlockType.builder().renderAtOriginOnly().with("X").with("X").build();

    static WoodType woodType(AbstractRegistrar<?> registrar, UnaryOperator<WoodTypeBuilder> builder)
    {
        return builder.apply(WoodTypeBuilder.builder()).register(new ResourceLocation(registrar.getOwnerId(), "wood_type"));
    }

    static WoodType woodType(AbstractRegistrar<?> registrar)
    {
        return woodType(registrar, UnaryOperator.identity());
    }

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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> wallLight(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("wall_light")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.WALL_TORCH)
                .sound(woodType.soundType())
                .lightLevel(14)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .renderType(() -> RenderType::cutout)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> floorLight(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("floor_light")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.TORCH)
                .sound(woodType.soundType())
                .lightLevel(14)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant().model(ModelLocationUtils.getModelLocation(entry.value()))))
                .tag(ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> table(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("table")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(entry.value(), Variant.variant())
                        .with(tableProperties(lookup.lookup(ProviderTypes.MODELS).getModelPath(entry.value())))
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> stool(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("stool")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> cushion(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("cushion")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> paintingSmall(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("painting_small")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> paintingWide(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("painting_wide")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> drawer(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("drawer")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends BaseBlockComponentHolder> BlockBuilder<R, B, R> shelf(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("shelf")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant()).with(connectionProperties(entry, ConnectionBlockComponent.SHELF_COMPONENT_TYPE)))
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .renderType(() -> RenderType::cutout)
                .item()
                    .model((provider, lookup, entry) -> provider
                            .getBuilder(ModelLocationUtils.getModelLocation(entry.value()))
                            .parent(ModelLocationUtils.getModelLocation(entry.value().getBlock(), "_single"))
                    )
                .build()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends BaseBlockComponentHolder> BlockBuilder<R, B, R> sofa(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("sofa")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant())
                        .with(connectionProperties(entry, ConnectionBlockComponent.SOFA_COMPONENT_TYPE, connectionType -> switch(connectionType) {
                            case INNER_LEFT, INNER_RIGHT -> ModelLocationUtils.getModelLocation(entry.value(), "_corner");
                            default -> ModelLocationUtils.getModelLocation(entry.value(), "_%s".formatted(connectionType.getSerializedName()));
                        }))
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> deskLeft(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("desk_left")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .renderType(() -> RenderType::cutout)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> deskRight(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("desk_right")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .renderType(() -> RenderType::cutout)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> chair(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("chair")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .renderType(() -> RenderType::cutout)
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> bench(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("bench")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> bookshelf(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("bookshelf")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> chest(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("chest")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> dresser(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("dresser")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> wardrobeBottom(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("wardrobe_bottom")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> wardrobeTop(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("wardrobe_top")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(
                                entry.value(),
                                Variant.variant()
                                       .model(ModelLocationUtils.getModelLocation(entry.value()))
                        )
                        .with(facingProperties())
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER)
                .renderType(() -> RenderType::cutout)
                .noOcclusion()
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> doorSingle(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("door_single")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_DOOR)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(entry.value(), Variant.variant())
                        .with(doorProperties(
                                PropertyDispatch.property(HorizontalFacingBlockComponent.FACING, DoorComponent.HINGE, DoorComponent.OPEN),
                                lookup.lookup(ProviderTypes.MODELS).getModelPath(entry.value())
                        ))
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS)
                .defaultItem()
                .with(builder -> {
                    // TODO: because implementation for builder provider callbacks is whack
                    //  each call to setProvider replaces the last
                    //  meaning this 2nd call to `.tag` replaces the first
                    if(woodType.setType().canOpenByHand())
                        builder.tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS, BlockTags.WOODEN_DOORS);
                    else
                        builder.tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS, BlockTags.WOODEN_DOORS);
                })
                .onRegister(block -> FantasyFurniture.INSTANCE.registerPathNodeType(block, DoorComponent::getPathNodeType))
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> doorDouble(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("door_double")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_DOOR)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(entry.value(), Variant.variant())
                        .with(doorProperties(
                                PropertyDispatch.property(HorizontalFacingBlockComponent.FACING, DoorComponent.HINGE, DoorComponent.OPEN),
                                lookup.lookup(ProviderTypes.MODELS).getModelPath(entry.value())
                        ))
                )
                .tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS)
                .defaultItem()
                .with(builder -> {
                    // TODO: because implementation for builder provider callbacks is whack
                    //  each call to setProvider replaces the last
                    //  meaning this 2nd call to `.tag` replaces the first
                    if(woodType.setType().canOpenByHand())
                        builder.tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS, BlockTags.WOODEN_DOORS);
                    else
                        builder.tag(BlockTags.MINEABLE_WITH_AXE, ApexTags.Blocks.PLACEMENT_VISUALIZER, BlockTags.DOORS, BlockTags.WOODEN_DOORS);
                })
                .onRegister(block -> FantasyFurniture.INSTANCE.registerPathNodeType(block, DoorComponent::getPathNodeType))
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> chandelier(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("chandelier")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .lightLevel(14)
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant().model(ModelLocationUtils.getModelLocation(entry.value()))))
                .defaultItem()
        ;
    }

    static <R extends AbstractRegistrar<R>, B extends Block> BlockBuilder<R, B, R> lockbox(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("lockbox")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.CHEST)
                .sound(woodType.soundType())
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

    static <R extends AbstractRegistrar<R>, B extends BaseBlockComponentHolder> BlockBuilder<R, B, R> counter(R registrar, WoodType woodType, BlockFactory<B> blockFactory)
    {
        return registrar
                .object("counter")
                .block(blockFactory)
                .copyInitialPropertiesFrom(() -> Blocks.OAK_PLANKS)
                .sound(woodType.soundType())
                .blockState((lookup, entry) -> MultiVariantBuilder.builder(entry.value(), Variant.variant())
                        .with(connectionProperties(entry, ConnectionBlockComponent.COUNTER_COMPONENT_TYPE, connectionType -> switch(connectionType) {
                            case INNER_LEFT, INNER_RIGHT, OUTER_LEFT, OUTER_RIGHT -> ModelLocationUtils.getModelLocation(entry.value(), "_corner");
                            default -> ModelLocationUtils.getModelLocation(entry.value(), "_single");
                        }))
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

    static PropertyDispatch.C3<Direction, DoorHingeSide, Boolean> doorProperties(PropertyDispatch.C3<Direction, DoorHingeSide, Boolean> dispatch, ResourceLocation model)
    {
        for(var facing : HorizontalFacingBlockComponent.FACING.getPossibleValues())
        {
            for(var hinge : DoorComponent.HINGE.getPossibleValues())
            {
                dispatch = doorProperty(doorProperty(dispatch, facing, hinge, true, model), facing, hinge, false, model);
            }
        }

        return dispatch;
    }

    static PropertyDispatch.C3<Direction, DoorHingeSide, Boolean> doorProperty(PropertyDispatch.C3<Direction, DoorHingeSide, Boolean> dispatch, Direction facing, DoorHingeSide hinge, boolean open, ResourceLocation model)
    {
        var rotation = facing.getOpposite().toYRot();

        if(open)
            rotation += 90F;
        if(hinge == DoorHingeSide.RIGHT && open)
            rotation += 180F;

        return dispatch.select(facing, hinge, open, Variant
                .variant()
                .model(switch(hinge) {
                    case LEFT -> open ? model.withSuffix("_flipped") : model;
                    case RIGHT -> open ? model : model.withSuffix("_flipped");
                })
                .yRot(rotation(rotation))
        );
    }

    static PropertyDispatch.C5<Boolean, Boolean, Boolean, Boolean, Boolean> tableProperties(ResourceLocation model)
    {
        return PropertyDispatch
                .property(TableComponent.ROTATED, TableComponent.NORTH, TableComponent.EAST, TableComponent.SOUTH, TableComponent.WEST)
                .generate((rotated, north, east, south, west) -> tableProperty(rotated, north, east, south, west, model));
    }

    static Variant tableProperty(boolean rotated, boolean north, boolean east, boolean south, boolean west, ResourceLocation model)
    {
        var suffix = "";
        var hasAll = north && east && south && west;

        if(north)
            suffix += rotated && !hasAll ? 'e' : 'n';
        if(east)
            suffix += rotated && !hasAll ? 's' : 'e';
        if(south)
            suffix += rotated && !hasAll ? 'w' : 's';
        if(west)
            suffix += rotated && !hasAll ? 'n' : 'w';

        if(!suffix.isEmpty())
        {
            suffix = switch(suffix) {
                default -> suffix;

                case "esn" -> "nes";
                case "swn" -> "nsw";
                case "ewn" -> "new";
                case "sn" -> "ns";
                case "en" -> "ne";
                case "wn" -> "nw";
            };

            model = model.withSuffix("_%s".formatted(suffix));
        }

        return Variant
                .variant()
                .model(model)
                .yRot(rotated ? Variant.Rotation.R270 : Variant.Rotation.R0);
    }

    static <B extends BaseBlockComponentHolder> PropertyDispatch<PropertyDispatch.C2<ConnectionType, Direction>> connectionProperties(BlockEntry<B> entry, BlockComponentType<ConnectionBlockComponent> componentType, Function<ConnectionType, ResourceLocation> modelProvider)
    {
        var property = entry.value().getRequiredComponent(componentType).getProperty();
        var dispatch = PropertyDispatch.property(property, HorizontalFacingBlockComponent.FACING);

        for(var facing : HorizontalFacingBlockComponent.FACING.getPossibleValues())
        {
            var rotation = switch(facing) {
                default -> 0F;
                case EAST -> 90F;
                case SOUTH -> 180F;
                case WEST -> 270;
            };

            for(var connectionType : property.getPossibleValues())
            {
                dispatch = dispatch.select(connectionType, facing, Variant
                        .variant()
                        .model(modelProvider.apply(connectionType))
                        .yRot(switch(connectionType) {
                            default -> rotation(rotation);
                            case INNER_RIGHT -> rotation(rotation + 90F);
                            case OUTER_RIGHT -> rotation(rotation - 90F);
                        })
                );
            }
        }

        return dispatch;
    }

    private static Variant.Rotation rotation(float rotation)
    {
        return switch(Mth.floor(rotation % 360)) {
            default -> Variant.Rotation.R0;
            case 90 -> Variant.Rotation.R90;
            case 180 -> Variant.Rotation.R180;
            case 270 -> Variant.Rotation.R270;
        };
    }

    static <B extends BaseBlockComponentHolder> PropertyDispatch<PropertyDispatch.C2<ConnectionType, Direction>> connectionProperties(BlockEntry<B> entry, BlockComponentType<ConnectionBlockComponent> componentType)
    {
        return connectionProperties(entry, componentType, connectionType -> ModelLocationUtils.getModelLocation(entry.value(), "_%s".formatted(connectionType.getSerializedName())));
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
