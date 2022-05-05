package xyz.apex.forge.fantasyfurniture.integration.jei;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;
import xyz.apex.java.utility.tuple.Triple;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FurnitureStationRecipes
{
	private final List<ItemStack> outputs;
	private final List<Ingredient> inputs;
	private final IJeiHelpers jei;
	private final IGuiHelper gui;
	private final IDrawable funitureSlotRenderer;
	private final IDrawable vanillaSlotRenderer;

	private float blockRotation = 0F;
	private float blockBobOffset = 0F;

	FurnitureStationRecipes(Collection<ItemStack> outputs, IJeiHelpers jei)
	{
		this.outputs = ImmutableList.copyOf(outputs);
		this.jei = jei;

		gui = jei.getGuiHelper();
		funitureSlotRenderer = gui.createDrawable(FurnitureStationContainerScreen.TEXTURE, 176, 18, 18, 18);
		vanillaSlotRenderer = gui.getSlotDrawable();

		inputs = Arrays.asList(
				Ingredient.of(Items.CLAY_BALL),
				Ingredient.of(Tags.Items.DYES_RED),
				Ingredient.of(Tags.Items.DYES_YELLOW),
				Ingredient.of(Tags.Items.DYES_BLUE)
		);
	}

	void setIngredients(IIngredients ingredients)
	{
		ingredients.setInputIngredients(inputs);
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);
	}

	void setRecipe(IRecipeLayout recipeLayout)
	{
		IGuiItemStackGroup groups = recipeLayout.getItemStacks();
		IFocus<ItemStack> focus = recipeLayout.getFocus(VanillaTypes.ITEM);

		setupInputSlots(groups);
		setupOutputSlots(groups, focus);

		recipeLayout.moveRecipeTransferButton(110, 0);

		// blockRotation = 45F;
		// blockBobOffset = (float) (Math.random() * Math.PI * 2D);
	}

	void draw(MatrixStack pose, IDrawable background)
	{
		renderBlock(pose, background);

		FontRenderer font = Minecraft.getInstance().font;
		font.drawShadow(pose, new TranslationTextComponent(FFRegistry.TXT_JEI_INGREDIENTS_KEY), 0F, 52F, -1);
		font.drawShadow(pose, new TranslationTextComponent(FFRegistry.TXT_JEI_RESULTS_KEY), 0F, 84F, -1);
	}

	private boolean isItemValid(ItemStack stack, @Nullable IFocus<ItemStack> focus)
	{
		if(focus == null || focus.getMode() == IFocus.Mode.INPUT)
			return true;

		return ItemStack.isSame(stack, focus.getValue());
	}

	private void setupInputSlots(IGuiItemStackGroup groups)
	{
		int xSize = vanillaSlotRenderer.getWidth() + 2;
		int xSpacing = xSize + 1;
		int x = xSpacing;

		for(int i = 0; i < inputs.size(); i++)
		{
			Ingredient input = inputs.get(i);

			groups.init(i, true, x, 65);
			groups.set(i, Arrays.asList(input.getItems()));
			groups.setBackground(i, vanillaSlotRenderer);

			x += xSpacing;
		}
	}

	private void setupOutputSlots(IGuiItemStackGroup groups, @Nullable IFocus<ItemStack> focus)
	{
		int itemsPerRow = 6;
		int itemsPerCol = 4;

		int xSize = funitureSlotRenderer.getWidth() + 2;
		int ySize = funitureSlotRenderer.getHeight() + 2;

		int xSpacing = xSize + 1;
		int ySpacing = ySize + 1;

		int xStart = 0;
		int yStart = 178 - ySpacing * itemsPerCol;

		int x = xStart;
		int y = yStart;

		int slotCounter = FurnitureStation.RESULT_SLOT;

		Map<Integer, Triple<Integer, Integer, List<ItemStack>>> slotMap = Maps.newHashMap();

		for(ItemStack stack : outputs)
		{
			if(!isItemValid(stack, focus))
				continue;

			int itemX = x;
			int itemY = y;

			Triple<Integer, Integer, List<ItemStack>> slotData = slotMap.computeIfAbsent(slotCounter, $ -> Triple.create(itemX, itemY, Lists.newArrayList()));
			slotData.getRight().add(stack);

			x += xSpacing;
			slotCounter++;

			if(x >= xStart + xSpacing * itemsPerRow)
			{
				x = xStart;
				y += ySpacing;

				if(y >= yStart + ySpacing * itemsPerCol)
				{
					slotCounter = 0;
					y = yStart;
				}
			}
		}

		slotMap.forEach((slot, slotData) -> {
			groups.init(slot, false, slotData.getLeft(), slotData.getMiddle());
			groups.set(slot, slotData.getRight());
			groups.setBackground(slot, funitureSlotRenderer);
		});
	}

	private void renderBlock(MatrixStack pose, IDrawable background)
	{
		Minecraft mc = Minecraft.getInstance();
		BlockState blockState = FurnitureStation.BLOCK.defaultBlockState();
		IBakedModel model = mc.getBlockRenderer().getBlockModel(blockState);

		float frameTime = mc.getFrameTime();
		float f1 = MathHelper.sin((mc.player.tickCount + frameTime) / 5F + blockBobOffset) * .5F + .5F;
		float f2 = model.getTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y();

		double blockYOffset = f1 + .25F * f2;
		blockRotation = (mc.player.tickCount + frameTime) / 1.25F + blockBobOffset;

		int width = background.getWidth();
		int height = background.getHeight();

		double centerX = width / 2D;
		double centerY = (height / 2D) - 64D;

		double y = centerY - 4D + blockYOffset;

		pose.pushPose();
		pose.translate(centerX, y, 10D);
		pose.scale(-32F, -32F, -32F);
		pose.translate(-.5D, -.5D, 0D);
		pose.mulPose(Vector3f.XP.rotationDegrees(-25F));
		pose.translate(.5D, 0D, -.5D);
		pose.mulPose(Vector3f.YP.rotationDegrees(blockRotation));
		pose.translate(-.5D, 0D, .5D);

		pose.pushPose();
		RenderSystem.color4f(1F, 1F, 1F, 1F);
		pose.translate(0D, 0D, -1D);

		mc.textureManager.bind(PlayerContainer.BLOCK_ATLAS);

		IRenderTypeBuffer.Impl buffer = mc.renderBuffers().bufferSource();
		mc.getBlockRenderer().renderBlock(blockState, pose, buffer, 15728880, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
		buffer.endBatch();

		pose.popPose();
		pose.popPose();
	}
}
