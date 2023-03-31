package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.hooks.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.hooks.PackRepositoryHooks;
import xyz.apex.minecraft.apexcore.common.platform.ModExecutor;
import xyz.apex.minecraft.apexcore.common.platform.ModLoader;
import xyz.apex.minecraft.apexcore.common.platform.Side;
import xyz.apex.minecraft.apexcore.common.platform.SideExecutor;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockBuilder;
import xyz.apex.minecraft.apexcore.common.registry.builder.EntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.EntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.RecipeEntry;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.renderer.SeatRenderer;
import xyz.apex.minecraft.fantasyfurniture.common.entity.Seat;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMultiBlockTypes;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

public interface FantasyFurniture
{
    String ID = "fantasyfurniture";

    EntityEntry<Seat> SEAT_ENTITY = EntityBuilder
            .<Seat>builder(ID, "seat", MobCategory.MISC, Seat::new)
                .sized(.25F, .35F)
                .clientTrackingRange(5)
                .updateInterval(Integer.MAX_VALUE)
                .fireImmune()
                .renderer(() -> SeatRenderer::new)
            .register();

    BlockEntry<FurnitureStationBlock> FURNITURE_STATION_BLOCK = BlockBuilder
            .builder(ID, "furniture_station", FurnitureStationBlock::new)
                .noOcclusion()
                .copyProperties(() -> Blocks.OAK_PLANKS)
            .register();

    RecipeEntry<FurnitureStationRecipe> FURNITURE_STATION_RECIPE = RecipeEntry.register(ID, "furniture_station", FurnitureStationRecipe.Serializer::new);

    default void bootstrap()
    {
        AllMultiBlockTypes.bootstrap();
        AllMenuTypes.bootstrap();

        ItemTags.bootstrap();

        CreativeModeTabHooks.getInstance().modify(CreativeModeTabs.FUNCTIONAL_BLOCKS, output -> output.accept(FURNITURE_STATION_BLOCK));

        registerResourcePack("ctm", "ctm_support", Component.literal("CTM Mod Support"));
        RegistryManager.register(ID);
    }

    private void registerResourcePack(@Nullable String requiredMod, String packId, Component displayName)
    {
        if(requiredMod != null && !requiredMod.isEmpty() && !ModLoader.getInstance().isModLoaded(requiredMod)) return;

        SideExecutor.runWhenOn(Side.CLIENT, () -> () -> ModExecutor.runIfLoaded(ID, () -> mod -> mod.findResource("packs", packId).ifPresent(path -> {
            var resources = new PathPackResources("%s:%s".formatted(ID, packId), path, true);
            PackRepositoryHooks.getInstance().registerPackRepository(Minecraft.getInstance().getResourcePackRepository(), onLoad -> {
                var pack = Pack.readMetaAndCreate("%s:builtin/%s".formatted(ID, packId), displayName, false, builtInPackId -> resources, PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN);
                if(pack != null) onLoad.accept(pack);
            });
        })));
    }

    static VoxelShape getCorrectedShape(BlockState blockState, VoxelShape shape)
    {
        if(!(blockState.getBlock() instanceof BlockComponentHolder holder)) return Shapes.block();

        var multiBlockComponent = holder.getComponent(BlockComponentTypes.MULTI_BLOCK);
        var result = shape;

        if(holder.hasComponent(BlockComponentTypes.HORIZONTAL_FACING)) result = VoxelShapeHelper.rotateHorizontal(result, blockState.getValue(HorizontalFacingBlockComponent.FACING));

        if(multiBlockComponent != null)
        {
            var multiBlockType = multiBlockComponent.getMultiBlockType();

            if(!multiBlockType.isOrigin(blockState))
            {
                var localSpace = multiBlockType.getLocalPosition(blockState);
                result = result.move(-localSpace.getX(), -localSpace.getY(), -localSpace.getZ());
            }
        }

        return result;
    }

    interface ItemTags
    {
        // TODO: replace with vanilla tag in 1.20
        //  'minecraft:bookshelf_books' is currently only included in the 1.20 feature packs
        //  which means if player does not enable exprimental features when creating their world
        //  our bookshelf block will not accept any inputs, as this tag is used to dictate
        //  what items can be inserted into it
        @Deprecated(forRemoval = true, since = "9.0.0")
        @ApiStatus.ScheduledForRemoval(inVersion = "10.0.0")
        TagKey<Item> BOOKSHELF_BOOKS = TagHelper.itemTag(ID, "bookshelf_books");

        private static void bootstrap() {}
    }
}