package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.types.BlockEntityComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.apexcore.common.lib.hook.MenuHooks;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public class NordicMediumContainerBlock extends BaseBlockComponentHolder
{
    public NordicMediumContainerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType()
    {
        return NordicFurnitureSet.MEDIUM_CONTAINER_BLOCK_ENTITY.value();
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var result = super.use(blockState, level, pos, player, hand, hit);

        if(result.consumesAction())
            return result;

        var blockEntity = NordicFurnitureSet.MEDIUM_CONTAINER_BLOCK_ENTITY.getBlockEntity(level, pos);

        if(blockEntity == null)
            return InteractionResultHelper.BlockUse.noActionTaken();

        MenuHooks.get().openMenu(player, blockEntity.getRequiredComponent(BlockEntityComponentTypes.NAMEABLE).getName(), blockEntity, buffer -> { });
        return InteractionResultHelper.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos)
    {
        var blockEntity = NordicFurnitureSet.MEDIUM_CONTAINER_BLOCK_ENTITY.getBlockEntity(level, pos);
        return blockEntity == null ? null : MenuHooks.get().createMenuProvider(blockEntity.getRequiredComponent(BlockEntityComponentTypes.NAMEABLE).getName(), blockEntity, buffer -> { });
    }
}
