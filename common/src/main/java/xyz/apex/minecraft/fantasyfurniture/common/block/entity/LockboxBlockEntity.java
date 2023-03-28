package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.component.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LockboxMenu;

import java.util.function.Consumer;

public final class LockboxBlockEntity extends BaseBlockEntityComponentHolder
{
    public static final int ROWS = 3;
    public static final int COLS = 5;
    public static final int SLOT_COUNT = ROWS * COLS;

    public LockboxBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, AllBlockEntityTypes.LOCKBOX.get(), pos, blockState);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        registrar.register(BlockEntityComponentTypes.CONTAINER).withSize(SLOT_COUNT);
        registrar.register(BlockEntityComponentTypes.LOCKABLE);
        registrar.register(BlockEntityComponentTypes.LOOTABLE);
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
        registrar.register(BlockEntityComponentTypes.MENU_PROVIDER).setMenuType(AllMenuTypes.LOCKBOX::get, LockboxMenu::forServer, extraData -> {});
    }
}
