package xyz.apex.forge.fantasyfurniture.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllItems;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Mods.FANTASY_FURNITURE)
public final class CommonForgeEvents
{
	@SubscribeEvent
	public static void onMissingBlockMappings(RegistryEvent.MissingMappings<Block> event)
	{
		var blockMappings = event.getMappings(Mods.FANTASY_FURNITURE);

		if(!blockMappings.isEmpty())
		{
			for(var mapping : blockMappings)
			{
				switch(mapping.key.toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(AllBlocks.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(AllBlocks.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(AllBlocks.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(AllBlocks.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(AllBlocks.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(AllBlocks.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(AllBlocks.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(AllBlocks.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(AllBlocks.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(AllBlocks.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(AllBlocks.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(AllBlocks.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(AllBlocks.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(AllBlocks.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(AllBlocks.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(AllBlocks.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(AllBlocks.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(AllBlocks.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(AllBlocks.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(AllBlocks.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(AllBlocks.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(AllBlocks.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(AllBlocks.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(AllBlocks.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(AllBlocks.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(AllBlocks.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(AllBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(AllBlocks.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(AllBlocks.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(AllBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(AllBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(AllBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(AllBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(AllBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(AllBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(AllBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(AllBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(AllBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(AllBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(AllBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(AllBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(AllBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(AllBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(AllBlocks.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(AllBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(AllBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(AllBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(AllBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(AllBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(AllBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(AllBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(AllBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(AllBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(AllBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(AllBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(AllBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(AllBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(AllBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(AllBlocks.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onMissingItemMappings(RegistryEvent.MissingMappings<Item> event)
	{
		var itemMappings = event.getMappings(Mods.FANTASY_FURNITURE);

		if(!itemMappings.isEmpty())
		{
			for(var mapping : itemMappings)
			{
				switch(mapping.key.toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(AllItems.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(AllItems.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(AllItems.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(AllItems.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(AllItems.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(AllItems.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(AllItems.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(AllItems.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(AllItems.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(AllItems.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(AllItems.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(AllItems.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(AllItems.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(AllItems.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(AllItems.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(AllItems.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(AllItems.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(AllItems.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(AllItems.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(AllItems.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(AllItems.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(AllItems.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(AllItems.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(AllItems.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(AllItems.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(AllItems.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(AllItems.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(AllItems.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(AllItems.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(AllItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(AllItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(AllItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(AllItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(AllItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(AllItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(AllItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(AllItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(AllItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(AllItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(AllItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(AllItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(AllItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(AllItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(AllItems.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(AllItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(AllItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(AllItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(AllItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(AllItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(AllItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(AllItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(AllItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(AllItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(AllItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(AllItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(AllItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(AllItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(AllItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(AllItems.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event)
	{
		var entity = event.getEntity();

		if(entity instanceof LivingEntity living)
		{
			var blockState = event.getPlacedBlock();

			if(IDyeable.hasDyeColorProperty(blockState))
			{
				for(var hand : InteractionHand.values())
				{
					var stack = living.getItemInHand(hand);
					var dyeColor = DyeColor.getColor(stack);

					if(dyeColor != null && IDyeable.setDyeColor(event.getWorld(), event.getPos(), blockState, dyeColor))
					{
						if(!(living instanceof Player player) || !player.isCreative())
							stack.shrink(1);
						return;
					}
				}
			}
		}
	}
}
