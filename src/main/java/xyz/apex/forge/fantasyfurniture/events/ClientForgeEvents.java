package xyz.apex.forge.fantasyfurniture.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.apexcore.lib.event.client.BlockVisualizerEvent;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

import java.util.Optional;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Mods.FANTASY_FURNITURE, value = Dist.CLIENT)
public final class ClientForgeEvents
{
	@SubscribeEvent
	public static void onModifyVisualizer(BlockVisualizerEvent.ModifyContext event)
	{
		var ctx = event.getContext();
		var blockState = ctx.blockState();

		if(IDyeable.hasDyeColorProperty(blockState))
		{
			var stack = ctx.stack();
			var otherHand = ctx.hand() == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
			var otherStack = ctx.player().getItemInHand(otherHand);

			// try pull color from offhand item
			// if that fails pull from itemstack trying to be placed
			// if that fails pull from render blockstate
			// if that fails default to white
			var color = Optional.ofNullable(DyeColor.getColor(otherStack))
			                    .or(() -> IDyeable.getDyeColor(stack))
			                    .or(() -> IDyeable.getDyeColor(blockState))
			                    .orElse(DyeColor.WHITE)
			;

			event.setContext(ctx
					// actual color that will be set
					.with(IDyeable.setDyeColor(blockState, color))
					// required tint index for coloring to function
					.with(1)
			);
		}
	}
}
