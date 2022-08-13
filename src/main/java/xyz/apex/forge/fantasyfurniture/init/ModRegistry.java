package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import org.apache.commons.lang3.Validate;

import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.item.crafting.DyeableRecipe;

import static com.tterrag.registrate.providers.ProviderType.*;

public final class ModRegistry
{
	public static final String TXT_JEI_INGREDIENTS_KEY = "text.%s.jei.ingredients".formatted(Mods.FANTASY_FURNITURE);
	public static final String TXT_JEI_RESULTS_KEY = "text.%s.jei.results".formatted(Mods.FANTASY_FURNITURE);
	public static final Lazy<CreativeModeTab> CREATIVE_MODE_TAB = Lazy.of(CreativeTab::new);
	public static final TagKey<Item> DYEABLE_ITEMS = ItemTags.tag(Mods.FANTASY_FURNITURE, "dyeable");

	public static final BasicRegistrate REGISTRATE = BasicRegistrate.create(Mods.FANTASY_FURNITURE, registrate -> registrate
			.creativeModeTab(CREATIVE_MODE_TAB::get, "Fantasy's Furniture")
			.addDataGenerator(LANG, provider -> {
				provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
				provider.add(TXT_JEI_RESULTS_KEY, "Results");
			})
			.addDataGenerator(ITEM_TAGS, provider -> provider
					.tag(DYEABLE_ITEMS)
					.addTag(ModItemGroupCategories.ROYAL_TAG)
			)
	);

	public static final RegistryEntry<SimpleRecipeSerializer<DyeableRecipe>> DYEABLE_RECIPE_SERIALIZER = REGISTRATE
			.object("dyeable")
			.simple(ForgeRegistries.Keys.RECIPE_SERIALIZERS, () -> new SimpleRecipeSerializer<>(DyeableRecipe::new));

	public static final RegistryEntry<RecipeType<DyeableRecipe>> DYEABLE_RECIPE_TYPE = REGISTRATE
			.object("dyeable")
			.simple(ForgeRegistries.Keys.RECIPE_TYPES, () -> RecipeType.simple(new ResourceLocation(Mods.FANTASY_FURNITURE, "dyeable")));

	private static boolean bootstrap = false;

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		FurnitureStation.bootstrap();
		ModBlocks.bootstrap();
		ModItems.bootstrap();
		ModElements.bootstrap();
		ModItemGroupCategories.bootstrap();

		REGISTRATE.addDataGenerator(RECIPE, provider -> SpecialRecipeBuilder.special(DYEABLE_RECIPE_SERIALIZER.get()).save(provider, DYEABLE_RECIPE_SERIALIZER.getId().toString()));
	}

	private static final class CreativeTab extends CreativeModeTab
	{
		private CreativeTab()
		{
			super(Mods.FANTASY_FURNITURE);
		}

		@Override
		public ItemStack makeIcon()
		{
			return ModItems.NORDIC_BED_SINGLE.asStack();
		}
	}
}