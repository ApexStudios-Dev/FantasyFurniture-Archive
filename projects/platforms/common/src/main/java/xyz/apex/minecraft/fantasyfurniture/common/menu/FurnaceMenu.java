package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.FurnaceBlockEntityComponent;

public final class FurnaceMenu extends AbstractFurnaceMenu
{
    private final FurnaceBlockEntityComponent furnace;

    public FurnaceMenu(MenuType<? extends FurnaceMenu> menuType, int syncId, Inventory inventory, FurnaceBlockEntityComponent furnace, Container container, ContainerData containerData)
    {
        super(menuType, furnace.getRecipeType(), furnace.getRecipeBookType(), syncId, inventory, container, containerData);

        this.furnace = furnace;
    }

    public FurnaceBlockEntityComponent getFurnace()
    {
        return furnace;
    }

    public ResourceLocation getMenuTexture()
    {
        return furnace.getMenuTexture();
    }

    public ResourceLocation getLitProgressSprite()
    {
        return furnace.getLitProgressSprite();
    }

    public ResourceLocation getBurnProgressSprite()
    {
        return furnace.getBurnProgressSprite();
    }

    @SideOnly(PhysicalSide.CLIENT)
    public AbstractFurnaceRecipeBookComponent getRecipeBookComponent()
    {
        return furnace.getRecipeBookComponent();
    }

    public static FurnaceMenu forNetwork(MenuType<? extends FurnaceMenu> menuType, int syncId, Inventory inventory, FriendlyByteBuf buffer)
    {
        var pos = buffer.readBlockPos();

        if(!(inventory.player.level().getBlockEntity(pos) instanceof BlockEntityComponentHolder componentHolder))
            throw new IllegalStateException("BlockEntity at position: %s must implement BlockEntityComponentHolder!".formatted(pos.toShortString()));

        var furnace = componentHolder.getRequiredComponent(FurnaceBlockEntityComponent.COMPONENT_TYPE);

        return new FurnaceMenu(menuType, syncId, inventory, furnace, new SimpleContainer(4), new SimpleContainerData(5));
    }
}
