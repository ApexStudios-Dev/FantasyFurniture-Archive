package xyz.apex.forge.fantasyfurniture;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;

import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.block.furniture.DyeableBlock;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModItems;
import xyz.apex.forge.fantasyfurniture.init.ModRegistry;

import java.util.Optional;

@Mod(Mods.FANTASY_FURNITURE)
public final class FantasyFurniture
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final NetworkManager NETWORK = new NetworkManager(Mods.FANTASY_FURNITURE, "net", "1");
	public static final String NBT_DYE_COLOR = "DyeColor";
	public static final EnumProperty<DyeColor> DYE_COLOR_PROPERTY = EnumProperty.create("dye_color", DyeColor.class);

	public FantasyFurniture()
	{
		ModRegistry.bootstrap();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Client::new);

		// EventBusHelper breaks when using MissingMappings event
		// It registers to the mod bus since the event implements the IModBus interface
		// although the event is fired on the forge bus, notify forge about this? (should the event even implement the interface? it's a marker to say what bus events should be registered to and fired on)
		MinecraftForge.EVENT_BUS.addListener(this::onMissingMappings);
		MinecraftForge.EVENT_BUS.addListener(this::onBlockPlaced);
	}

	private void onMissingMappings(MissingMappingsEvent event)
	{
		var blockMappings = event.getMappings(Registry.BLOCK_REGISTRY, Mods.FANTASY_FURNITURE);

		if(!blockMappings.isEmpty())
		{
			for(var mapping : blockMappings)
			{
				switch(mapping.getKey().toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(ModBlocks.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(ModBlocks.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(ModBlocks.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(ModBlocks.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(ModBlocks.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(ModBlocks.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(ModBlocks.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(ModBlocks.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(ModBlocks.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(ModBlocks.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(ModBlocks.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(ModBlocks.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(ModBlocks.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(ModBlocks.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(ModBlocks.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(ModBlocks.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(ModBlocks.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(ModBlocks.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(ModBlocks.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(ModBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(ModBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(ModBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(ModBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(ModBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(ModBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(ModBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(ModBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(ModBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(ModBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(ModBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(ModBlocks.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(ModBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(ModBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(ModBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(ModBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(ModBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(ModBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(ModBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(ModBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(ModBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(ModBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(ModBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(ModBlocks.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}

		var itemMappings = event.getMappings(Registry.ITEM_REGISTRY, Mods.FANTASY_FURNITURE);

		if(!itemMappings.isEmpty())
		{
			for(var mapping : itemMappings)
			{
				switch(mapping.getKey().toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(ModItems.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(ModItems.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(ModItems.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(ModItems.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(ModItems.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(ModItems.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(ModItems.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(ModItems.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(ModItems.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(ModItems.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(ModItems.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(ModItems.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(ModItems.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(ModItems.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(ModItems.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(ModItems.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(ModItems.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(ModItems.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(ModItems.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(ModItems.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(ModItems.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(ModItems.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(ModItems.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(ModItems.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(ModItems.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(ModItems.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(ModItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(ModItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(ModItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(ModItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(ModItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(ModItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(ModItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(ModItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(ModItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(ModItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(ModItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(ModItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(ModItems.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(ModItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(ModItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(ModItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(ModItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(ModItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(ModItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(ModItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(ModItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(ModItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(ModItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(ModItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(ModItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(ModItems.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}
	}

	private void onBlockPlaced(BlockEvent.EntityPlaceEvent event)
	{
		var entity = event.getEntity();

		if(entity instanceof LivingEntity living)
		{
			var blockState = event.getPlacedBlock();

			if(hasDyeColorProperty(blockState))
			{
				for(var hand : InteractionHand.values())
				{
					var stack = living.getItemInHand(hand);
					var dyeColor = DyeColor.getColor(stack);

					if(dyeColor != null && DyeableBlock.setDyeColor(event.getLevel(), event.getPos(), blockState, dyeColor))
					{
						if(!(living instanceof Player player) || !player.isCreative())
							stack.shrink(1);
						return;
					}
				}
			}
		}
	}

	public static int tintFromDyeColor(DyeColor color)
	{
		var colors = color.getTextureDiffuseColors();
		var red = (int) (colors[0] * 255F);
		var green = (int) (colors[1] * 255F);
		var blue = (int) (colors[2] * 255F);

		return FastColor.ARGB32.color(255, red, green, blue);
	}

	public static Optional<DyeColor> getDyeColor(ItemStack stack)
	{
		var tag = stack.getTag();

		if(tag != null && tag.contains(NBT_DYE_COLOR, Tag.TAG_STRING))
		{
			var dyeName = tag.getString(NBT_DYE_COLOR);
			return Optional.ofNullable(DyeColor.byName(dyeName, null));
		}

		return Optional.empty();
	}

	@CanIgnoreReturnValue
	public static ItemStack setDyeColor(ItemStack stack, @Nullable DyeColor color)
	{
		var stackTag = stack.getTag();

		if(color == null)
		{
			if(stackTag != null && stackTag.contains(NBT_DYE_COLOR, Tag.TAG_STRING))
			{
				stackTag.remove(NBT_DYE_COLOR);
				stack.setTag(stackTag);
			}
		}
		else
		{
			if(stackTag == null)
				stackTag = new CompoundTag();

			stackTag.putString(NBT_DYE_COLOR, color.getSerializedName());
			stack.setTag(stackTag);
		}

		return stack;
	}

	public static boolean hasDyeColorProperty(BlockState blockState)
	{
		return blockState.hasProperty(DYE_COLOR_PROPERTY);
	}

	public static Optional<DyeColor> getDyeColor(BlockState blockState)
	{
		return blockState.getOptionalValue(DYE_COLOR_PROPERTY);
	}

	public static BlockState setDyeColor(BlockState blockState, DyeColor color)
	{
		return hasDyeColorProperty(blockState) ? blockState.setValue(DYE_COLOR_PROPERTY, color) : blockState;
	}

	private static final class Client
	{
		private Client()
		{
			EventBusHelper.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> {
				event.registerLayerDefinition(WidowBloomModel.LAYER_LOCATION, WidowBloomModel::createBodyLayer);
				event.registerLayerDefinition(SkullBlossomsModel.LAYER_LOCATION, SkullBlossomsModel::createBodyLayer);
			});

			EventBusHelper.addListener(RegisterParticleProvidersEvent.class, event -> {
				var particleEngine = Minecraft.getInstance().particleEngine;
				particleEngine.register(ModElements.SMALL_SOUL_FLAME.get(), FlameParticle.SmallFlameProvider::new);
			});
		}
	}
}