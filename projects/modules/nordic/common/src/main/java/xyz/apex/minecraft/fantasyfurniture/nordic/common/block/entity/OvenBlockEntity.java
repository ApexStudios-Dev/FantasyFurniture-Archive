package xyz.apex.minecraft.fantasyfurniture.nordic.common.block.entity;

import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentRegistrar;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.FurnaceBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.FurnaceBlockEntityComponent;

public final class OvenBlockEntity extends FurnaceBlockEntity
{
    public OvenBlockEntity(BlockEntityType<? extends BaseBlockEntityComponentHolder> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected void registerComponents(BlockEntityComponentRegistrar registrar)
    {
        super.registerComponents(registrar);

        registrar.register(FurnaceBlockEntityComponent.COMPONENT_TYPE, component -> component
                .withRecipeType(RecipeType.SMOKING, RecipeBookType.SMOKER)
                .withMenuProperties(new ResourceLocation("textures/gui/container/smoker.png"), new ResourceLocation("container/smoker/lit_progress"), new ResourceLocation("container/smoker/burn_progress"), () -> SmokingRecipeBookComponent::new)
                .withBurnDurationModifier((fuel, burnDuration) -> burnDuration / 2)
        );
    }
}
