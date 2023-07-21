package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.entries.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entries.MenuEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entries.RecipeEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

@ApiStatus.NonExtendable
public interface FurnitureStation
{
    int INPUT_SLOT_LEFT = 0;
    int INPUT_SLOT_RIGHT = 1;
    int INPUT_SLOT_BINDING_AGENT = 2;
    int INPUT_SLOTS = 3;
    int OUTPUT_SLOT = 0;

    boolean GLOBAL_BINDING_AGENTS = true;

    BlockEntry<FurnitureStationBlock> BLOCK = FantasyFurniture.BUILDERS
            .block("furniture_station", FurnitureStationBlock::new)
            .copyInitialPropertiesFrom(() -> Blocks.CRAFTING_TABLE)
            .noOcclusion()
            .simpleItem()
    .register();

    MenuEntry<FurnitureStationMenu> MENU = MenuEntry.register(FantasyFurniture.ID, "furniture_station", FurnitureStationMenu::forServer, FurnitureStationMenu::forClient, () -> () -> FurnitureStationMenuScreen::new);
    RecipeEntry<FurnitureStationRecipe> RECIPE = RecipeEntry.register(FantasyFurniture.ID, "furniture_station", FurnitureStationRecipe.Serializer::getInstance);

    static void bootstrap()
    {
    }

    static boolean isGenericBindingAgent(ItemStack stack)
    {
        return stack.is(Items.CLAY_BALL);
    }

    static boolean isValidBindingAgent(FurnitureStationRecipe recipe, ItemStack stack)
    {
        if(stack.isEmpty())
            return false;

        var recipeBindingAgent = recipe.getBindingAgent();

        // is either global binding agent or recipe specified
        if(GLOBAL_BINDING_AGENTS)
            return isGenericBindingAgent(stack) || (!recipeBindingAgent.isEmpty() && recipeBindingAgent.test(stack));
        else
        {
            // global binding agents are "disabled"
            // recipe specified agents, should override the global agent
            if(!recipeBindingAgent.isEmpty())
                return recipeBindingAgent.test(stack);

            // recipe did not specify binding agent
            // check if is global agent
            return isGenericBindingAgent(stack);
        }
    }
}
