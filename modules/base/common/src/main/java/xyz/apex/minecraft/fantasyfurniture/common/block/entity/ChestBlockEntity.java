package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.ChestMenu;

import java.util.function.Consumer;

public class ChestBlockEntity extends BaseBlockEntityComponentHolder
{
    public static final int ROWS = 3;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    protected ChestBlockEntity(Consumer<Registrar> registrarConsumer, BlockEntityType<? extends ChestBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, blockEntityType, pos, blockState);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        registrar.register(BlockEntityComponentTypes.CONTAINER).withSize(SLOT_COUNT);
        registrar.register(BlockEntityComponentTypes.LOCKABLE);
        registrar.register(BlockEntityComponentTypes.LOOTABLE);
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
        registrar.register(BlockEntityComponentTypes.MENU_PROVIDER).setMenuType(AllMenuTypes.CHEST::get, ChestMenu::forServer, extraData -> {});

        if(getBlockState().getBlock() instanceof BlockComponentHolder holder && holder.hasComponent(DyeableBlockComponent.COMPONENT_TYPE)) registrar.register(DyeableBlockEntityComponent.COMPONENT_TYPE);
    }
}
