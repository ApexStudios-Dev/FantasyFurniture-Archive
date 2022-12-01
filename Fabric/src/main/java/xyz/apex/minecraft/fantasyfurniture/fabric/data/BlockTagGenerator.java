package xyz.apex.minecraft.fantasyfurniture.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import xyz.apex.minecraft.apexcore.shared.registry.RegistryKeys;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockTagGenerator extends FabricTagProvider.BlockTagProvider
{
    static final TagKey<Block> WOOLS = TagKey.create(RegistryKeys.BLOCK, new ResourceLocation("c", "wools"));

    BlockTagGenerator(FabricDataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void generateTags()
    {
        tag(WOOLS).add(NordicSet.WOOL.get());
    }
}
