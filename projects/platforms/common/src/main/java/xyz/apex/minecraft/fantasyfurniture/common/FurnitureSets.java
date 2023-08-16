package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.core.Direction;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.AbstractRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.factory.BlockFactory;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.PropertyDispatch;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.SofaBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.property.ConnectionType;

@ApiStatus.NonExtendable
public interface FurnitureSets
{
    static <R extends AbstractRegistrar<R>, B extends SofaBlock> BlockBuilder<R, B, R> sofa(R registrar, BlockFactory<B> blockFactory)
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
