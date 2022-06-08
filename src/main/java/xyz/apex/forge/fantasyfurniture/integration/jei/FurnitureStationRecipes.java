package xyz.apex.forge.fantasyfurniture.integration.jei;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;

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

import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;
import xyz.apex.java.utility.tuple.Triple;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class FurnitureStationRecipes
{
	private final List<ItemStack> outputs;
	private final List<Ingredient> inputs;
	private final IDrawable furnitureSlotRenderer;
	private final IDrawable vanillaSlotRenderer;

	FurnitureStationRecipes(Collection<ItemStack> outputs, IJeiHelpers jei)
	{
		this.outputs = ImmutableList.copyOf(outputs);

		var gui = jei.getGuiHelper();
		furnitureSlotRenderer = gui.createDrawable(FurnitureStationContainerScreen.TEXTURE, 176, 18, 18, 18);
		vanillaSlotRenderer = gui.getSlotDrawable();

		inputs = Arrays.asList(
				Ingredient.of(FurnitureStation.CLAY),
				Ingredient.of(FurnitureStation.WOOD),
				Ingredient.of(FurnitureStation.STONE)
		);
	}

	void setIngredients(IIngredients ingredients)
	{
		ingredients.setInputIngredients(inputs);
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);
	}

	void setRecipe(IRecipeLayout recipeLayout)
	{
		var groups = recipeLayout.getItemStacks();
		var focus = recipeLayout.getFocus(VanillaTypes.ITEM);

		setupInputSlots(groups);
		setupOutputSlots(groups, focus);

		recipeLayout.moveRecipeTransferButton(110, 68);

		// blockRotation = 45F;
		// blockBobOffset = (float) (Math.random() * Math.PI * 2D);
	}

	void draw(PoseStack pose, IDrawable background)
	{
		renderBlock(pose, background);

		var font = Minecraft.getInstance().font;
		font.drawShadow(pose, new TranslatableComponent(FFRegistry.TXT_JEI_INGREDIENTS_KEY), 0F, 52F, -1);
		font.drawShadow(pose, new TranslatableComponent(FFRegistry.TXT_JEI_RESULTS_KEY), 0F, 84F, -1);
	}

	private boolean isItemValid(ItemStack stack, @Nullable IFocus<ItemStack> focus)
	{
		if(focus == null || focus.getMode() == IFocus.Mode.INPUT)
			return true;

		return ItemStack.isSame(stack, focus.getValue());
	}

	private void setupInputSlots(IGuiItemStackGroup groups)
	{
		var xSize = vanillaSlotRenderer.getWidth() + 2;
		var xSpacing = xSize + 1;
		var x = 0;

		for(var i = 0; i < inputs.size(); i++)
		{
			var input = inputs.get(i);

			groups.init(i, true, x, 65);
			groups.set(i, Arrays.asList(input.getItems()));
			groups.setBackground(i, vanillaSlotRenderer);

			x += xSpacing;
		}
	}

	private void setupOutputSlots(IGuiItemStackGroup groups, @Nullable IFocus<ItemStack> focus)
	{
		var itemsPerRow = 6;
		var itemsPerCol = 4;

		var xSize = furnitureSlotRenderer.getWidth() + 2;
		var ySize = furnitureSlotRenderer.getHeight() + 2;

		var xSpacing = xSize + 1;
		var ySpacing = ySize + 1;

		var xStart = 0;
		var yStart = 178 - ySpacing * itemsPerCol;

		var x = xStart;
		var y = yStart;

		var slotCounterStart = FurnitureStation.RESULT_SLOT;
		var slotCounter = slotCounterStart;

		var slotMap = Maps.<Integer, Triple<Integer, Integer, List<ItemStack>>>newHashMap();

		for(var stack : outputs)
		{
			if(!isItemValid(stack, focus))
				continue;

			var itemX = x;
			var itemY = y;

			var slotData = slotMap.computeIfAbsent(slotCounter, $ -> Triple.create(itemX, itemY, Lists.newArrayList()));
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

		slotMap.forEach((slot, slotData) -> {
			groups.init(slot, false, slotData.getLeft(), slotData.getMiddle());
			groups.set(slot, slotData.getRight());
			groups.setBackground(slot, furnitureSlotRenderer);
		});
	}

	private void renderBlock(PoseStack pose, IDrawable background)
	{
		var mc = Minecraft.getInstance();
		var blockState = FurnitureStation.BLOCK.defaultBlockState();
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
