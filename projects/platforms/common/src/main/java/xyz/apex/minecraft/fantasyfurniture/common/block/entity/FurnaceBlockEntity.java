package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.types.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.FurnaceBlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnaceMenu;

import java.util.List;

public class FurnaceBlockEntity extends BaseBlockEntityComponentHolder implements RecipeHolder, StackedContentsCompatible
{
    public FurnaceBlockEntity(BlockEntityType<? extends BaseBlockEntityComponentHolder> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected void registerComponents(BlockEntityComponentRegistrar registrar)
    {
        registrar.register(BlockEntityComponentTypes.LOOT_TABLE);
        registrar.register(BlockEntityComponentTypes.LOCK_CODE);
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
        registrar.register(FurnaceBlockEntityComponent.COMPONENT_TYPE);
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory)
    {
        return new FurnaceMenu(FantasyFurniture.FURNACE_MENU.value(), windowId, playerInventory, getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE), this, this);
    }

    // TODO: Move these into BaseBlockEntityComponentHolder?
    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe)
    {
        getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE).setRecipeUsed(recipe);
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed()
    {
        return getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE).getRecipeUsed();
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> items)
    {
        getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE).awardUsedRecipes(player, items);
    }

    @Override
    public boolean setRecipeUsed(Level level, ServerPlayer player, Recipe<?> recipe)
    {
        return getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE).setRecipeUsed(level, player, recipe);
    }

    @Override
    public void fillStackedContents(StackedContents contents)
    {
        getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE).fillStackedContents(contents);
    }
}
