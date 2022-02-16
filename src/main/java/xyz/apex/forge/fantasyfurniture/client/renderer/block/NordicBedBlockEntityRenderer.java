package xyz.apex.forge.fantasyfurniture.client.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.block.NordicBedBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicBedBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.FFBlockEntities;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;

@OnlyIn(Dist.CLIENT)
public final class NordicBedBlockEntityRenderer extends TileEntityRenderer<NordicBedBlockEntity>
{
	private final ModelRenderer bedModel;

	public NordicBedBlockEntityRenderer(TileEntityRendererDispatcher dispatcher)
	{
		super(dispatcher);

		bedModel = new ModelRenderer(128, 128, 0, 0);
		bedModel.setPos(0.0F, 16.0F, 8.0F);


		ModelRenderer bed_frame = new ModelRenderer(128, 128, 0, 0);
		bed_frame.setPos(8.0F, 6.0F, -16.0F);
		bedModel.addChild(bed_frame);
		bed_frame.texOffs(16, 48).addBox(-16.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(8, 45).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(0, 41).addBox(-2.0F, -6.0F, 30.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(12, 37).addBox(-16.0F, -6.0F, 30.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(14, 56).addBox(-15.0F, -10.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(6, 53).addBox(-3.0F, -10.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(52, 14).addBox(-3.0F, -10.0F, 30.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(0, 49).addBox(-15.0F, -10.0F, 30.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(52, 6).addBox(-16.0F, -12.0F, 30.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(52, 10).addBox(-16.0F, -12.0F, 0.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(0, 12).addBox(-14.0F, -3.0F, 0.0F, 12.0F, 2.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(0, 8).addBox(-14.0F, -3.0F, 30.0F, 12.0F, 2.0F, 2.0F, 0.0F, false);
		bed_frame.texOffs(0, 23).addBox(-14.0F, -6.0F, 1.0F, 12.0F, 3.0F, 1.0F, 0.0F, false);
		bed_frame.texOffs(0, 32).addBox(-13.0F, -10.0F, 1.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
		bed_frame.texOffs(0, 19).addBox(-14.0F, -6.0F, 30.0F, 12.0F, 3.0F, 1.0F, 0.0F, false);
		bed_frame.texOffs(0, 27).addBox(-13.0F, -10.0F, 30.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
		bed_frame.texOffs(0, 52).addBox(-16.0F, -3.0F, 2.0F, 2.0F, 2.0F, 28.0F, 0.0F, false);
		bed_frame.texOffs(24, 22).addBox(-2.0F, -3.0F, 2.0F, 2.0F, 2.0F, 28.0F, 0.0F, false);
		bed_frame.texOffs(0, 0).addBox(-14.0F, -2.0F, 2.0F, 12.0F, 0.0F, 28.0F, 0.0F, false);

		ModelRenderer bedding = new ModelRenderer(128, 128, 0, 0);
		bedding.setPos(0.0F, -2.0F, 13.0F);
		bedModel.addChild(bedding);
		bedding.texOffs(46, 0).addBox(-6.0F, 3.0F, -5.0F, 12.0F, 0.0F, 6.0F, 0.0F, false);
		bedding.texOffs(0, 33).addBox(-6.0F, 3.0F, -5.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
		bedding.texOffs(0, 31).addBox(6.0F, 3.0F, -5.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
		bedding.texOffs(0, 28).addBox(-7.0F, 2.0F, -27.0F, 14.0F, 0.0F, 22.0F, 0.0F, false);
		bedding.texOffs(32, 33).addBox(-7.0F, 2.0F, -27.0F, 0.0F, 3.0F, 22.0F, 0.0F, false);
		bedding.texOffs(32, 30).addBox(7.0F, 2.0F, -27.0F, 0.0F, 3.0F, 22.0F, 0.0F, false);
		bedding.texOffs(0, 16).addBox(-7.0F, 2.0F, -5.0F, 14.0F, 3.0F, 0.0F, 0.0F, false);

		ModelRenderer pillow_r1 = new ModelRenderer(128, 128, 0, 0);
		pillow_r1.setPos(0.0F, 0.0F, 0.0F);
		bedding.addChild(pillow_r1);
		setRotationAngle(pillow_r1, -0.7854F, 0.0F, 0.0F);
		pillow_r1.texOffs(0, 0).addBox(-5.0F, -1.75F, -1.0F, 10.0F, 6.0F, 2.0F, 0.0F, false);

		ModelRenderer extra_height_pieces = new ModelRenderer(128, 128, 0, 0);
		extra_height_pieces.setPos(0.0F, 6.0F, -8.0F);
		bedModel.addChild(extra_height_pieces);
		extra_height_pieces.texOffs(34, 62).addBox(-8.0F, 0.0F, -8.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		extra_height_pieces.texOffs(34, 66).addBox(6.0F, 0.0F, -8.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		extra_height_pieces.texOffs(34, 70).addBox(6.0F, 0.0F, 22.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		extra_height_pieces.texOffs(34, 74).addBox(-8.0F, 0.0F, 22.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(NordicBedBlockEntity blockEntity, float partialTick, MatrixStack pose, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		World level = blockEntity.getLevel();
		Direction facing;
		int light = combinedLight;

		if(level == null)
			facing = Direction.SOUTH;
		else
		{
			BlockState blockState = blockEntity.getBlockState();
			BedPart bedPart = blockState.getValue(NordicBedBlock.PART);

			if(bedPart == BedPart.FOOT)
				return;

			facing = blockState.getValue(NordicBedBlock.FACING);
			BlockPos blockPos = blockEntity.getBlockPos();
			TileEntityMerger.ICallbackWrapper<? extends NordicBedBlockEntity> callback = TileEntityMerger.combineWithNeigbour(FFBlockEntities.NORDIC_BED.asBlockEntityType(), NordicBedBlock::getBlockType, NordicBedBlock::getConnectedDirection, NordicBedBlock.FACING, blockState, level, blockPos, (world, pos) -> false);
			light = callback.apply(new DualBrightnessCallback<>()).get(combinedLight);
		}

		renderBed(pose, buffer, facing, true, light, combinedOverlay);
	}

	private void renderBed(MatrixStack pose, IRenderTypeBuffer buffer, Direction facing, boolean flag, int combinedLight, int combinedOverlay)
	{
		pose.pushPose();
		Direction opposite = facing.getOpposite();

		pose.translate(0, 1.5D, 0);
		pose.mulPose(opposite.getRotation());
		pose.mulPose(Vector3f.XP.rotationDegrees(90));

		if(facing == Direction.NORTH)
			pose.translate(.5, 0, -1.5);
		else if(facing == Direction.EAST)
			pose.translate(.5, 0, -.5);
		else if(facing == Direction.SOUTH)
			pose.translate(-.5, 0, -.5);
		else if(facing == Direction.WEST)
			pose.translate(-.5, 0, -1.5);

		RenderType renderType = RenderType.entitySolid(FFRegistry.getInstance().id("textures/models/nordic_bed.png"));
		IVertexBuilder vertexBuilder = buffer.getBuffer(renderType);
		bedModel.render(pose, vertexBuilder, combinedLight, combinedOverlay);

		pose.popPose();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
