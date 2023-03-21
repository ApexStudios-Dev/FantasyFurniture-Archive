package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class TableSmallBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public TableSmallBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        if(NordicSet.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Nordic.TABLE_SMALL;
        else if(VenthyrSet.TABLE_SMALL.hasBlockState(blockState) || VenthyrSet.TABLE_SMALL_FANCY.hasBlockState(blockState)) return AllVoxelShapes.Venthyr.TABLE_SMALL;
        else if(DunmerSet.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Dunmer.TABLE_SMALL;
        else if(BoneSet.Wither.TABLE_SMALL.hasBlockState(blockState) || BoneSet.Skeleton.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Bone.TABLE_SMALL;
        else if(NecrolordSet.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Necrolord.TABLE_SMALL;
        else if(RoyalSet.TABLE_SMALL.hasBlockState(blockState)) return AllVoxelShapes.Royal.TABLE_SMALL;
        else return Shapes.block();
    }
}
