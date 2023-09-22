package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.BedComponent;

import java.util.function.BiFunction;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();

        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> onEntityInteract(level, player, hand, entity));

        EntitySleepEvents.ALLOW_BED.register((entity, sleepingPos, blockState, vanillaResult) -> {
            if(!(blockState.getBlock() instanceof BlockComponentHolder componentHolder))
                return InteractionResult.PASS;

            return componentHolder.hasComponent(BedComponent.COMPONENT_TYPE) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        });

        EntitySleepEvents.SET_BED_OCCUPATION_STATE.register((entity, sleepingPos, bedState, occupied) -> BlockComponentHolder.mapAsComponent(bedState, BedComponent.COMPONENT_TYPE, component -> {
            BlockPos headPos;
            BlockState headBlockState;

            var level = entity.level();
            var facing = bedState.getValue(HorizontalFacingBlockComponent.FACING);

            if(component.isHead(bedState))
            {
                headPos = sleepingPos;
                headBlockState = bedState;
            }
            else
            {
                headPos = sleepingPos.relative(facing.getOpposite());
                headBlockState = level.getBlockState(headPos);
            }

            var footPos = headPos.relative(facing);
            var footBlockState = level.getBlockState(footPos);

            level.setBlock(headPos, headBlockState.setValue(BedComponent.OCCUPIED, occupied), Block.UPDATE_ALL);
            level.setBlock(footPos, footBlockState.setValue(BedComponent.OCCUPIED, occupied), Block.UPDATE_ALL);

            return true;
        }).orElse(false));

        EntitySleepEvents.START_SLEEPING.register((entity, sleepingPos) -> {
            var level = entity.level();
            var blockState = level.getBlockState(sleepingPos);

            BlockComponentHolder.runAsComponent(blockState, BedComponent.COMPONENT_TYPE, component -> {
                if(component.isHead(blockState))
                    return;

                entity.setSleepingPos(sleepingPos.relative(blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite()));
            });
        });

        EntitySleepEvents.MODIFY_SLEEPING_DIRECTION.register((entity, sleepingPos, sleepingDirection) -> {
            var blockState = entity.level().getBlockState(sleepingPos);
            var direction = BedComponent.getBedDirection(blockState);
            return direction == null ? sleepingDirection : direction;
        });

        EntitySleepEvents.MODIFY_WAKE_UP_POSITION.register((entity, sleepingPos, bedState, wakeUpPos) -> BlockComponentHolder.mapAsComponent(bedState, BedComponent.COMPONENT_TYPE, component -> {
            var level = entity.level();
            var headPos = sleepingPos;
            var facing = bedState.getValue(HorizontalFacingBlockComponent.FACING);

            if(!component.isHead(bedState))
                headPos = sleepingPos.relative(facing.getOpposite());

            return BedBlock.findStandUpPosition(entity.getType(), level, headPos, facing, entity.getYRot()).orElse(null);
        }).orElse(wakeUpPos));
    }

    @Override
    public void registerPathNodeType(Block block, BiFunction<BlockState, Boolean, @Nullable BlockPathTypes> getPathNodeType)
    {
        LandPathNodeTypesRegistry.register(block, getPathNodeType::apply);
    }

    @Override
    public boolean canBurn(WorldlyContainer container, RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> items, int amount)
    {
        return AbstractFurnaceBlockEntity.canBurn(registryAccess, recipe, items, amount);
    }

    @Override
    public boolean burn(WorldlyContainer container, RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> items, int amount)
    {
        return AbstractFurnaceBlockEntity.burn(registryAccess, recipe, items, amount);
    }
}
