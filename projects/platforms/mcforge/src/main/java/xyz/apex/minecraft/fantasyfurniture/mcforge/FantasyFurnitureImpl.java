package xyz.apex.minecraft.fantasyfurniture.mcforge;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.mcforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurnitureClient;

import java.util.function.BiFunction;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();
        EventBuses.registerForJavaFML();
        PhysicalSide.CLIENT.runWhenOn(() -> FantasyFurnitureClient.INSTANCE::bootstrap);
    }

    @Override
    public void registerPathNodeType(Block block, BiFunction<BlockState, Boolean, @Nullable BlockPathTypes> getPathNodeType)
    {
        // TODO
        //PathNodeTYp;
    }

    @Override
    public boolean canBurn(WorldlyContainer container, RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> items, int amount)
    {
        if(!items.get(AbstractFurnaceBlockEntity.SLOT_INPUT).isEmpty() && recipe != null)
        {
            var result = ((RecipeHolder<Recipe<WorldlyContainer>>) recipe).value().assemble(container, registryAccess);

            if(result.isEmpty())
                return false;
            else
            {
                var stack = items.get(AbstractFurnaceBlockEntity.SLOT_RESULT);

                if(stack.isEmpty())
                    return true;
                else if(!ItemStack.isSameItem(stack, result))
                    return false;
                else if(stack.getCount() + result.getCount() <= amount && stack.getCount() + result.getCount() <= stack.getMaxStackSize())
                    return true;
                else
                    return stack.getCount() + result.getCount() <= result.getMaxStackSize();
            }
        }
        else
            return false;
    }

    @Override
    public boolean burn(WorldlyContainer container, RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> items, int amount)
    {
        if(recipe != null && canBurn(container, registryAccess, recipe, items, amount))
        {
            var input = items.get(AbstractFurnaceBlockEntity.SLOT_INPUT);
            var result = ((RecipeHolder<Recipe<WorldlyContainer>>) recipe).value().assemble(container, registryAccess);
            var current = items.get(AbstractFurnaceBlockEntity.SLOT_RESULT);

            if(current.isEmpty())
                items.set(AbstractFurnaceBlockEntity.SLOT_RESULT, result.copy());
            else if(current.is(result.getItem()))
                current.grow(result.getCount());

            if(input.is(Blocks.WET_SPONGE.asItem()) && !items.get(AbstractFurnaceBlockEntity.SLOT_FUEL).isEmpty() && items.get(AbstractFurnaceBlockEntity.SLOT_FUEL).is(Items.BUCKET))
                items.set(AbstractFurnaceBlockEntity.SLOT_FUEL, new ItemStack(Items.WATER_BUCKET));

            input.shrink(1);
            return true;
        }
        else
            return false;
    }
}
