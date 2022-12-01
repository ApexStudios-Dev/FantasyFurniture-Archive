package xyz.apex.minecraft.fantasyfurniture.shared;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import xyz.apex.minecraft.apexcore.shared.registry.ModdedRegistries;
import xyz.apex.minecraft.apexcore.shared.registry.ModdedRegistry;
import xyz.apex.minecraft.apexcore.shared.registry.RegistryKeys;
import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistry;
import xyz.apex.minecraft.apexcore.shared.registry.item.DefaultedItemProperties;
import xyz.apex.minecraft.apexcore.shared.registry.item.ItemRegistry;
import xyz.apex.minecraft.fantasyfurniture.shared.init.*;

import java.util.function.Supplier;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    Supplier<BlockBehaviour.Properties> STONE_PROPERTIES = () -> BlockBehaviour.Properties.copy(Blocks.STONE);
    Supplier<BlockBehaviour.Properties> WOOL_PROPERTIES = () -> BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL);
    Supplier<BlockBehaviour.Properties> CARPET_PROPERTIES = () -> BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET);

    static void bootstrap()
    {
        DefaultedItemProperties.register(ID, properties -> properties.tab(CreativeModeTab.TAB_MISC));

        Registries.bootstrap();

        NordicSet.bootstrap();
        DunmerSet.bootstrap();
        VenthyrSet.bootstrap();
        BoneSet.bootstrap();
        RoyalSet.bootstrap();
        NecrolordSet.bootstrap();
    }

    interface Registries
    {
        BlockRegistry BLOCKS = BlockRegistry.create(ID);
        ItemRegistry ITEMS = ItemRegistry.create(ID);
        ModdedRegistry<MenuType<?>> MENUS = ModdedRegistries.get(RegistryKeys.MENU, ID);
        ModdedRegistry<ParticleType<?>> PARTICLE_TYPES = ModdedRegistries.get(RegistryKeys.PARTICLE_TYPE, ID);
        ModdedRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ModdedRegistries.get(RegistryKeys.RECIPE_SERIALIZER, ID);
        ModdedRegistry<RecipeType<?>> RECIPE_TYPES = ModdedRegistries.get(RegistryKeys.RECIPE_TYPE, ID);

        private static void bootstrap()
        {
        }
    }
}
