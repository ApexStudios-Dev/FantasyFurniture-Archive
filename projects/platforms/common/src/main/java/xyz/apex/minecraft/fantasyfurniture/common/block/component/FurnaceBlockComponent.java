package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

// required by all FurnaceBlockEntityComponents (or handle the LIT property yourself)
public final class FurnaceBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<FurnaceBlockComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "furnace", FurnaceBlockComponent::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    private FurnaceBlockComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState.setValue(LIT, false);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(LIT);
    }
}
