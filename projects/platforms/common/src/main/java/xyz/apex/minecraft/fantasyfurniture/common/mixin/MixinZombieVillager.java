package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.level.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.BedComponent;

@Mixin(ZombieVillager.class)
public abstract class MixinZombieVillager
{
    // IntelliJ (MCDev Plugin) complain about a lot of stuff here,
    // but you can safely ignore it, known issue with ModifyConstant and instanceof redirections
    // code still compiles and runs as expected
    @ModifyConstant(
            method = "getConversionProgress",
            constant = @Constant(classValue = BedBlock.class)
    )
    private boolean FantasyFurniture$isOpen(Object obj, Class objClas)
    {
        if(obj instanceof BlockComponentHolder componentHolder)
            return componentHolder.hasComponent(BedComponent.COMPONENT_TYPE);
        else if(obj instanceof BedBlock)
            return true;
        else
            return false;
    }
}
