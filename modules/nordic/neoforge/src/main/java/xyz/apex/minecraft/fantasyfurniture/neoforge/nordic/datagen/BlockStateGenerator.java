package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicBlocks;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

import java.util.function.Supplier;

@ApiStatus.Internal
final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, NordicFurnitureSet.ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlockWithItem(NordicBlocks.WOOL);
    }

    private void simpleBlockWithItem(Supplier<? extends Block> block)
    {
        var blk = block.get();
        simpleBlockWithItem(blk, cubeAll(blk));
    }
}
