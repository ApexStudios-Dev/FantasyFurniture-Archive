package xyz.apex.forge.fantasyfurniture.core.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import xyz.apex.forge.apexcore.lib.net.AbstractPacket;
import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.fantasyfurniture.common.menu.FurnitureStationMenu;

public class C2SSyncSelectedResultPacket extends AbstractPacket
{
	private final int selectedResult;

	public C2SSyncSelectedResultPacket(int selectedResult)
	{
		this.selectedResult = selectedResult;
	}

	public C2SSyncSelectedResultPacket(FriendlyByteBuf buffer)
	{
		this(buffer.readInt());
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		buffer.writeInt(selectedResult);
	}

	@Override
	protected void process(NetworkManager network, NetworkEvent.Context ctx)
	{
		ctx.enqueueWork(() -> {
			var player = ctx.getSender();
			var menu = (FurnitureStationMenu) player.containerMenu;
			menu.setSelectedResult(selectedResult);
		});
	}
}
