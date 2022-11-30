package xyz.apex.minecraft.fantasyfurniture.shared;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import xyz.apex.minecraft.apexcore.shared.registry.ModdedRegistries;
import xyz.apex.minecraft.apexcore.shared.registry.ModdedRegistry;
import xyz.apex.minecraft.apexcore.shared.registry.RegistryKeys;
import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistry;
import xyz.apex.minecraft.apexcore.shared.registry.item.ItemRegistry;
import xyz.apex.minecraft.fantasyfurniture.shared.init.*;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    static void bootstrap()
    {
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
        ModdedRegistry<MenuType<?>> MENUS = ModdedRegistries.create(RegistryKeys.MENU, ID);
        ModdedRegistry<ParticleType<?>> PARTICLE_TYPES = ModdedRegistries.create(RegistryKeys.PARTICLE_TYPE, ID);
        ModdedRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ModdedRegistries.create(RegistryKeys.RECIPE_SERIALIZER, ID);
        ModdedRegistry<RecipeType<?>> RECIPE_TYPES = ModdedRegistries.create(RegistryKeys.RECIPE_TYPE, ID);

        private static void bootstrap()
        {
        }
    }
}
