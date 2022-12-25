package xyz.apex.minecraft.fantasyfurniture.shared.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.fantasyfurniture.shared.init.AllVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public class SimpleBlock extends Block
{
    public SimpleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        if(NordicSet.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Nordic.TABLE_SMALL;
        return super.getShape(blockState, level, pos, ctx);
    }
}
