package xyz.apex.forge.fantasyfurniture.net;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import xyz.apex.forge.apexcore.lib.net.AbstractPacket;
import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;

import java.util.List;

public final class ServerTransferFurnitureStationResultPacket extends AbstractPacket
{
	private final Item selectedResulted;

	public ServerTransferFurnitureStationResultPacket(Item selectedResulted)
	{
		this.selectedResulted = selectedResulted;
	}

	public ServerTransferFurnitureStationResultPacket(PacketBuffer buffer)
	{
		super(buffer);

		selectedResulted = buffer.readRegistryIdSafe(Item.class);
	}

	@Override
	protected void encode(PacketBuffer buffer)
	{
		buffer.writeRegistryId(selectedResulted);
	}

	@Override
	protected void process(NetworkManager network, NetworkEvent.Context ctx)
	{
		ServerPlayerEntity player = ctx.getSender();

		if(player == null)
			return;

		if(player.containerMenu instanceof FurnitureStationContainer)
		{
			FurnitureStationContainer container = (FurnitureStationContainer) player.containerMenu;
			List<ItemStack> results = container.getResults();

			for(int i = 0; i < results.size(); i++)
			{
				ItemStack stack = results.get(i);

				if(stack.getItem() == selectedResulted)
				{
					container.clickMenuButton(player, i);
					break;
				}
			}
		}
	}
}
