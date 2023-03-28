package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.ChestBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Consumer;

public final class ChestBlock extends BaseEntityBlockComponentHolder<ChestBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public ChestBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<ChestBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.CHEST.get();
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.CHEST;
        else if(VenthyrSet.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.CHEST;
        else if(DunmerSet.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.CHEST;
        else if(BoneSet.Wither.CHEST.hasBlockState(blockState) || BoneSet.Skeleton.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.CHEST;
        else if(NecrolordSet.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.CHEST;
        else if(RoyalSet.CHEST.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.CHEST;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        if(!getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }
}
