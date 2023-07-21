package xyz.apex.minecraft.fantasyfurniture.common.feature.station;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.hook.MenuHooks;

public final class FurnitureStationBlock extends BaseBlockComponentHolder
{
    FurnitureStationBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var result = super.use(blockState, level, pos, player, hand, hit);

        if(result.consumesAction())
            return result;
        if(player instanceof ServerPlayer sPlayer)
            MenuHooks.get().openMenu(sPlayer, getName(), (windowId, playerInventory) -> new FurnitureStationMenu(windowId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), pos)), extraData -> extraData.writeBlockPos(pos));

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos)
    {
        return FurnitureStation.MENU.asProvider(getName(), extraData -> extraData.writeBlockPos(pos));
    }
}
