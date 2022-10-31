package xyz.apex.forge.fantasyfurniture.core.registrate;

import com.tterrag.registrate.providers.*;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.core.init.ACRegistry;
import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.apexcore.lib.block.ISeatBlock;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.AllItemGroupCategories;
import xyz.apex.forge.fantasyfurniture.AllRecipeSerializers;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.StackedBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.BedBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;
import xyz.apex.forge.fantasyfurniture.core.FurnitureStation;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

@SuppressWarnings({ "unchecked", "SuspiciousToArrayCall" })
public interface DataGenerators
{
	String TXT_JEI_INGREDIENTS_KEY = "text.%s.jei.ingredients".formatted(Mods.FANTASY_FURNITURE);
	String TXT_JEI_RESULTS_KEY = "text.%s.jei.results".formatted(Mods.FANTASY_FURNITURE);
	String TXT_ACCEPTS_ANY = "text.%s.recipe.accepts_any".formatted(Mods.FANTASY_FURNITURE);

	static void recipes(RegistrateRecipeProvider provider)
	{
		SpecialRecipeBuilder.special(AllRecipeSerializers.DYEABLE.get()).save(provider, AllRecipeSerializers.DYEABLE.getId().toString());
	}

	static void lang(RegistrateLangProvider provider)
	{
		REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().map(RegistryEntry::get).forEach(block -> {
			if(block instanceof ISeatBlock seat)
				provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
			if(block instanceof BedBlock bed)
				provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
			if(block instanceof StackedBlock stacked)
				provider.add(stacked.getStackableTranslationKey(), "Stackable");
		});

		provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
		provider.add(TXT_JEI_RESULTS_KEY, "Results");

		REGISTRATE.getAll(ForgeRegistries.Keys.BLOCKS)
		          .stream()
		          .map(RegistryEntry::get)
		          .filter(IDyeable.class::isInstance)
		          .map(IDyeable.class::cast)
		          .forEach(d -> provider.add(d.getDyeableTranslationKey(), "Dyeable"))
		;

		provider.add(TXT_ACCEPTS_ANY, "Accepts Any: %s");
	}

	static void blockTags(RegistrateTagsProvider<Block> provider)
	{
		provider.tag(ACRegistry.TAG_VISUALIZER)
		        .add(REGISTRATE
				        .getAll(ForgeRegistries.Keys.BLOCKS)
						.stream()
						.map(RegistryEntry::get)
						.filter(IMultiBlock.class::isInstance)
						.toArray(Block[]::new)
		        )
		;
	}

	static void itemTags(RegistrateItemTagsProvider provider)
	{
		var tag = provider.tag(IDyeable.TAG);

		REGISTRATE.getAll(ForgeRegistries.Keys.BLOCKS)
		          .stream()
		          .map(RegistryEntry::get)
		          .filter(IDyeable.class::isInstance)
		          .map(ItemLike::asItem)
		          .forEach(tag::add)
		;

		provider.tag(AllItemGroupCategories.BONE_TAG)
		        .addTags(AllItemGroupCategories.BONE_SKELETON_TAG, AllItemGroupCategories.BONE_WITHER_TAG)
		;

		provider.tag(FurnitureStation.CRAFTABLE);
		provider.tag(FurnitureStation.CLAY).add(Items.CLAY_BALL);
	}

	static void blockState(RegistrateBlockstateProvider provider)
	{
		// fantasyfurniture:block/dyeable/cube
			var cubeDyeable = provider
					.models()
			        .withExistingParent("%s:block/dyeable/cube".formatted(Mods.FANTASY_FURNITURE), new ResourceLocation(Mods.MINECRAFT, "block/block"))
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 16F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#%s".formatted(face.getSerializedName()))
								.cullface(face)
						)
					.end()
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 16F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#tint_%s".formatted(face.getSerializedName()))
								.cullface(face)
								.tintindex(0)
						)
					.end()
			;

			// fantasyfurniture:block/dyeable/cube_all
			provider.models()
			        .getBuilder("%s:block/dyeable/cube_all".formatted(Mods.FANTASY_FURNITURE))
					.parent(cubeDyeable)
					.texture("particle", "#tint_all")
					.texture("down", "#all")
					.texture("up", "#all")
					.texture("north", "#all")
					.texture("south", "#all")
					.texture("west", "#all")
					.texture("east", "#all")
					.texture("tint_down", "#tint_all")
					.texture("tint_up", "#tint_all")
					.texture("tint_north", "#tint_all")
					.texture("tint_south", "#tint_all")
					.texture("tint_west", "#tint_all")
					.texture("tint_east", "#tint_all")
			;

			// fantasyfurniture:block/dyeable/carpet
			provider.models()
			        .withExistingParent("%s:block/dyeable/carpet".formatted(Mods.FANTASY_FURNITURE), new ResourceLocation(Mods.MINECRAFT, "block/thin_block"))
					.texture("particle", "#tint_wool")
			        .element()
						.from(0F, 0F, 0F)
						.to(16F, 1F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#wool")
								.uvs(0F, face.getAxis().isVertical() ? 0F : 15F, 16F, 16F)
								.cullface(face == Direction.UP ? null : face)
						)
					.end()
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 1F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#tint_wool")
								.tintindex(0)
								.uvs(0F, face.getAxis().isVertical() ? 0F : 15F, 16F, 16F)
								.cullface(face == Direction.UP ? null : face)
						)
					.end()
			;
	}
}
