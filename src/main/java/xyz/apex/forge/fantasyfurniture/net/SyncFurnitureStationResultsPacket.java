package xyz.apex.forge.fantasyfurniture.net;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import xyz.apex.forge.apexcore.lib.net.AbstractPacket;
import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

public class SyncFurnitureStationResultsPacket extends AbstractPacket
{
	private final ItemStack[] results;

	public SyncFurnitureStationResultsPacket()
	{
		super();

		results = FurnitureStation.getCraftingResults().stream().map(ItemStack::copy).toArray(ItemStack[]::new);
	}

	public SyncFurnitureStationResultsPacket(PacketBuffer buffer)
	{
		super(buffer);

		int count = buffer.readInt();
		results = new ItemStack[count];

		for(int i = 0; i < count; i++)
		{
			results[i] = buffer.readItem();
		}

		/*for(int i = 0; i < count; i++)
		{
			int index = buffer.readInt();
			Item item = buffer.readRegistryId();
			ItemStack stack = item.getDefaultInstance();

			if(buffer.readBoolean())
			{
				CompoundNBT stackTag = buffer.readNbt();
				stack.setTag(stackTag);
			}

			stack.setCount(1);
			results[index] = stack;
		}*/
	}

	@Override
	protected void encode(PacketBuffer buffer)
	{
		buffer.writeInt(results.length);
		for(ItemStack stack : results)
		{
			buffer.writeItemStack(stack, false);
		}

		/*for(int i = 0; i < results.length; i++)
		{
			ItemStack stack = results[i];

			buffer.writeInt(i);
			buffer.writeRegistryId(stack.getItem());
			CompoundNBT stackTag = stack.getTag();

			if(stackTag != null)
			{
				buffer.writeBoolean(true);
				buffer.writeNbt(stackTag);
			}
			else
				buffer.writeBoolean(false);
		}*/
	}

	@Override
	protected void process(NetworkManager network, NetworkEvent.Context ctx)
	{
		FurnitureStation.syncCraftingResultsFromServer(this);
	}

	public ItemStack[] getResults()
	{
		return results;
	}
}
