package xyz.apex.minecraft.fantasyfurniture.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import xyz.apex.minecraft.apexcore.shared.registry.RegistryKeys;

public final class ItemTagGenerator extends FabricTagProvider.ItemTagProvider
{
    static final TagKey<Item> WOOLS = TagKey.create(RegistryKeys.ITEM, new ResourceLocation("c", "wools"));

    ItemTagGenerator(FabricDataGenerator generator, BlockTagGenerator blockTags)
    {
        super(generator, blockTags);
    }

    @Override
    protected void generateTags()
    {
        copy(BlockTagGenerator.WOOLS, WOOLS);
    }
}
