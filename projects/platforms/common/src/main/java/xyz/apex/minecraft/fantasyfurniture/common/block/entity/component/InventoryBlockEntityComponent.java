package xyz.apex.minecraft.fantasyfurniture.common.block.entity.component;

import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseContainerBlockEntityComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class InventoryBlockEntityComponent extends BaseContainerBlockEntityComponent<InventoryBlockEntityComponent>
{
    public static final BlockEntityComponentType<InventoryBlockEntityComponent> COMPONENT_TYPE = BlockEntityComponentType.register(FantasyFurniture.ID, "inventory", InventoryBlockEntityComponent::new);

    private InventoryBlockEntityComponent(BlockEntityComponentHolder componentHolder)
    {
        super(componentHolder);
    }
}
