package xyz.apex.forge.fantasyfurniture.core.integration.jei;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import org.apache.commons.lang3.tuple.Triple;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.model.data.EmptyModelData;

import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationMenuScreen;
import xyz.apex.forge.fantasyfurniture.core.FurnitureStation;
import xyz.apex.forge.fantasyfurniture.core.registrate.DataGenerators;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class FurnitureStationRecipes
{
	public static final RecipeType<FurnitureStationRecipes> RECIPE_TYPE = RecipeType.create(Mods.FANTASY_FURNITURE, "furniture_station", FurnitureStationRecipes.class);

	private final List<ItemStack> outputs;
	private final List<Ingredient> inputs;
	private final IDrawable furnitureSlotRenderer;
	private final IDrawable vanillaSlotRenderer;

	FurnitureStationRecipes(Collection<ItemStack> outputs, IJeiHelpers jei)
	{
		this.outputs = ImmutableList.copyOf(outputs);

		var gui = jei.getGuiHelper();
		furnitureSlotRenderer = gui.createDrawable(FurnitureStationMenuScreen.TEXTURE, 176, 18, 18, 18);
		vanillaSlotRenderer = gui.getSlotDrawable();

		inputs = Arrays.asList(
				Ingredient.of(FurnitureStation.CLAY),
				Ingredient.of(ItemTags.Vanilla.PLANKS),
				Ingredient.of(ItemTags.Vanilla.STONE_CRAFTING_MATERIALS)
		);
	}

	void setRecipe(IRecipeLayoutBuilder builder)
	{
		// recipe setup
		builder.moveRecipeTransferButton(110, 68);

		// blockRotation = 45F;
		// blockBobOffset = (float) (Math.random() * Math.PI * 2D);

		// input
		var xSize = vanillaSlotRenderer.getWidth() + 2;
		var xSpacing = xSize + 1;
		var x = 0;

		for(var i = 0; i < inputs.size(); i++)
		{
			var input = inputs.get(i);

			builder.addSlot(RecipeIngredientRole.INPUT, x + 1, 65 + 1)
					.addIngredients(input)
					.setBackground(vanillaSlotRenderer, -1, -1)
			;

			x += xSpacing;
		}

		// output
		var itemsPerRow = 6;
		var itemsPerCol = 4;

		xSize = furnitureSlotRenderer.getWidth() + 2;
		var ySize = furnitureSlotRenderer.getHeight() + 2;

		xSpacing = xSize + 1;
		var ySpacing = ySize + 1;

		var xStart = 0;
		var yStart = 178 - ySpacing * itemsPerCol;

		x = xStart;
		var y = yStart;

		var slotCounterStart = FurnitureStation.RESULT_SLOT;
		var slotCounter = slotCounterStart;

		var slotMap = Maps.<Integer, Triple<Integer, Integer, List<ItemStack>>>newHashMap();

		for(var stack : outputs)
		{
			var itemX = x + 1;
			var itemY = y + 1;

			var slotData = slotMap.computeIfAbsent(slotCounter, $ -> Triple.of(itemX, itemY, Lists.newArrayList()));
			slotData.getRight().add(stack);

			x += xSpacing;
			slotCounter++;

			if(x >= xStart + xSpacing * itemsPerRow)
			{
				x = xStart;
				y += ySpacing;

				if(y >= yStart + ySpacing * itemsPerCol)
				{
					slotCounter = slotCounterStart;
					y = yStart;
				}
			}
		}

		slotMap.forEach((slot, slotData) -> builder
				.addSlot(RecipeIngredientRole.OUTPUT, slotData.getLeft(), slotData.getMiddle())
				.addIngredients(Ingredient.of(slotData.getRight().stream()))
				.setBackground(furnitureSlotRenderer, -1, -1)
		);
	}

	void draw(PoseStack pose, IDrawable background)
	{
		renderBlock(pose, background);

		var font = Minecraft.getInstance().font;
		font.drawShadow(pose, new TranslatableComponent(DataGenerators.TXT_JEI_INGREDIENTS_KEY), 0F, 52F, -1);
		font.drawShadow(pose, new TranslatableComponent(DataGenerators.TXT_JEI_RESULTS_KEY), 0F, 84F, -1);
	}

	private void renderBlock(PoseStack pose, IDrawable background)
	{
		var mc = Minecraft.getInstance();
		var blockState = AllBlocks.FURNITURE_STATION.defaultBlockState();
		var model = mc.getBlockRenderer().getBlockModel(blockState);

		var frameTime = mc.getFrameTime();
		var blockBobOffset = 0F;
		var f1 = Mth.sin((mc.player.tickCount + frameTime) / 5F + blockBobOffset) * .5F + .5F;
		var f2 = model.getTransforms().getTransform(ItemTransforms.TransformType.GROUND).scale.y();

		var blockYOffset = f1 + .25F * f2;
		var blockRotation = (mc.player.tickCount + frameTime) / 1.25F + blockBobOffset;

		var width = background.getWidth();
		var height = background.getHeight();

		var centerX = width / 2D;
		var centerY = (height / 2D) - 64D;

		var y = centerY - 4D + blockYOffset;

		pose.pushPose();
		pose.translate(centerX, y, 10D);
		pose.scale(-32F, -32F, -32F);
		pose.translate(-.5D, -.5D, 0D);
		pose.mulPose(Vector3f.XP.rotationDegrees(-25F));
		pose.translate(.5D, 0D, -.5D);
		pose.mulPose(Vector3f.YP.rotationDegrees(blockRotation));
		pose.translate(-.5D, 0D, .5D);

		pose.pushPose();
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		pose.translate(0D, 0D, -1D);

		RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);

		MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
		mc.getBlockRenderer().renderSingleBlock(blockState, pose, buffer, 15728880, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
		buffer.endBatch();

		pose.popPose();
		pose.popPose();
	}
}
