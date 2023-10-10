package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.FurnaceBlockEntityComponent;

@Mixin(FurnaceResultSlot.class)
public abstract class MixinFurnaceResultSlot
{
    @Shadow @Final private Player player;

    @Inject(
            method = "checkTakeAchievements",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/inventory/FurnaceResultSlot;removeCount:I",
                    shift = At.Shift.BEFORE,
                    ordinal = 1
            )
    )
    private void FantasyFurniture$checkTakeAchievements(ItemStack stack, CallbackInfo ci)
    {
        var self = (FurnaceResultSlot) (Object) this;

        if(player instanceof ServerPlayer sPlayer)
        {
            if(self.container instanceof FurnaceBlockEntityComponent furnace)
                furnace.awardUsedRecipesAndPopExperience(sPlayer);
            else if(self.container instanceof BlockEntity blockEntity)
                BlockEntityComponentHolder.runAsComponent(blockEntity, FurnaceBlockEntityComponent.COMPONENT_TYPE, component -> component.awardUsedRecipesAndPopExperience(sPlayer));
        }
    }
}
