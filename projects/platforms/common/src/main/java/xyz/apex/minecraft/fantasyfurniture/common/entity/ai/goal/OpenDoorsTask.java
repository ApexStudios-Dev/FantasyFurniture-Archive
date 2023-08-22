package xyz.apex.minecraft.fantasyfurniture.common.entity.ai.goal;

import com.mojang.datafixers.kinds.OptionalBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.InteractWithDoor;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.declarative.MemoryAccessor;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.pathfinder.Node;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public interface OpenDoorsTask
{
    static BehaviorControl<LivingEntity> create()
    {
        var currentNode = new MutableObject<Node>(null);
        var counter = new MutableInt(0);

        return BehaviorBuilder.create(instance -> instance
                .group(instance.present(MemoryModuleType.PATH),
                        instance.registered(MemoryModuleType.DOORS_TO_CLOSE),
                        instance.registered(MemoryModuleType.NEAREST_LIVING_ENTITIES)
                )
                .apply(instance, (pathAccessor, doorsToCloseAccessor, mobsAccessor) -> (level, entity, time) -> {
                    var path = instance.get(pathAccessor);
                    var doorsToClose = instance.tryGet(doorsToCloseAccessor);

                    if(!path.notStarted() && !path.isDone())
                    {
                        var nextNode = path.getNextNode();

                        if(Objects.equals(currentNode.getValue(), nextNode))
                            counter.setValue(20);
                        else if(counter.decrementAndGet() > 0)
                            return false;

                        currentNode.setValue(nextNode);

                        var previousNode = path.getPreviousNode();

                        if(previousNode != null)
                            doorsToClose = tryOpenDoor(doorsToCloseAccessor, doorsToClose, level, previousNode.asBlockPos(), entity);

                        doorsToClose = tryOpenDoor(doorsToCloseAccessor, doorsToClose, level, nextNode.asBlockPos(), entity);
                        doorsToClose.ifPresent(set -> InteractWithDoor.closeDoorsThatIHaveOpenedOrPassedThrough(level, entity, previousNode, nextNode, set, instance.tryGet(mobsAccessor)));
                        return true;
                    }
                    else
                        return false;
                }));
    }

    private static Optional<Set<GlobalPos>> tryOpenDoor(MemoryAccessor<OptionalBox.Mu, Set<GlobalPos>> doorsToCloseAccessor, Optional<Set<GlobalPos>> doorsToClose, ServerLevel level, BlockPos pos, @Nullable LivingEntity entity)
    {
        final var blockState = level.getBlockState(pos);

        return BlockComponentHolder.mapAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            if(!blockState.getValue(DoorComponent.OPEN))
                component.setOpen(entity, level, pos, blockState, true);

            return InteractWithDoor.rememberDoorToClose(doorsToCloseAccessor, doorsToClose, level, pos);
        }).orElse(doorsToClose);
    }
}
