package xyz.apex.forge.fantasyfurniture.core;

import com.google.common.collect.Lists;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.lib.util.RegistryHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.core.registrate.DataGenerators;

import java.util.List;

public final class FurnitureStation
{
	public static final int CLAY_SLOT = 0;
	public static final int WOOD_SLOT = 1;
	public static final int STONE_SLOT = 2;
	public static final int RESULT_SLOT = 3;

	// items with this tag can be crafted from the furniture station
	public static final TagKey<Item> CRAFTABLE = ItemTags.tag(Mods.FANTASY_FURNITURE, "craftable");

	public static final TagKey<Item> CLAY = ItemTags.forgeTag("clay_ball"); // TODO: Move to commonality?

	public static boolean isValidClay(ItemStack stack)
	{
		return stack.is(CLAY);
	}

	public static boolean isValidWood(ItemStack stack)
	{
		return stack.is(ItemTags.Vanilla.PLANKS);
	}

	public static boolean isValidStone(ItemStack stack)
	{
		return stack.is(ItemTags.Vanilla.STONE_CRAFTING_MATERIALS);
	}

	public static Component buildAcceptsAnyComponent(TagKey<Item> tag)
	{
		return Component.translatable(DataGenerators.TXT_ACCEPTS_ANY, Component.literal(tag.location().toString()).withStyle(ChatFormatting.ITALIC)).withStyle(ChatFormatting.GRAY);
	}

	public static List<ItemStack> buildResultsList()
	{
		var tag = RegistryHelper.getTags(ForgeRegistries.ITEMS).getTag(CRAFTABLE);
		var list = Lists.<ItemStack>newArrayList();
		tag.stream().map(Item::getDefaultInstance).forEach(list::add);
		list.forEach(stack -> stack.setCount(1));
		return list;
	}
}
