package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class DyeableBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<DyeableBlockComponent> COMPONENT_TYPE = BlockComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "dyeable"),
            DyeableBlockComponent::new
    );

    private DyeableBlockComponent(BlockComponentHolder holder)
    {
        super(holder);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var blockEntity = BaseEntityBlockComponentHolder.getBlockEntityUnsafe(blockState, level, pos);
        if(!(blockEntity instanceof BlockEntityComponentHolder holder)) return InteractionResult.FAIL;
        var dyeable = holder.getComponent(DyeableBlockEntityComponent.COMPONENT_TYPE);
        if(dyeable == null) return InteractionResult.FAIL;
        return dyeable.tryDyeOrClear(level, player, hand);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
    {
        if(!stack.hasCustomHoverName()) return;
        var blockEntity = BaseEntityBlockComponentHolder.getBlockEntityUnsafe(blockState, level, pos);
        if(!(blockEntity instanceof BlockEntityComponentHolder holder)) return;
        var component = holder.getComponent(DyeableBlockEntityComponent.COMPONENT_TYPE);
        if(component == null) return;

        var str = stack.getDisplayName().getString();
        if(str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') str = str.substring(1, str.length() - 1);

        var color = TextColor.parseColor(str);
        if(color != null) component.setColor(color);
    }
}
