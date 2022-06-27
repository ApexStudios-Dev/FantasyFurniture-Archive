package xyz.apex.forge.fantasyfurniture.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import xyz.apex.forge.apexcore.lib.net.AbstractPacket;
import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SyncFurnitureStationResultsPacket extends AbstractPacket
{
	private final ItemStack[] results;

	public SyncFurnitureStationResultsPacket()
	{
		super();

		results = FurnitureStation.getCraftingResults().stream().map(ItemStack::copy).toArray(ItemStack[]::new);
	}

	public SyncFurnitureStationResultsPacket(FriendlyByteBuf buffer)
	{
		super(buffer);

		var count = buffer.readInt();
		results = new ItemStack[count];
		IntStream.range(0, count).forEach(i -> results[i] = buffer.readItem());
	}

	@Override
	protected void encode(FriendlyByteBuf buffer)
	{
		buffer.writeInt(results.length);
		Arrays.stream(results).forEach(stack -> buffer.writeItemStack(stack, false));
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