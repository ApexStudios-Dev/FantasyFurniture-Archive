package xyz.apex.forge.fantasyfurniture.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.forge.commonality.Mods;

public final class SkullBlossomsModel extends Model
{
	public static final ResourceLocation TEXTURE_SKELETON = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/models/decorations/bone_skeleton_skull_blossoms.png");
	public static final ResourceLocation TEXTURE_WITHER = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/models/decorations/bone_wither_skull_blossoms.png");
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Mods.FANTASY_FURNITURE, "bone_skull_blossoms"), "main");

	private final ModelPart model;

	public SkullBlossomsModel(ModelPart root)
	{
		super(RenderType::entityTranslucentCull);

		model = root.getChild("bone_skull_blossoms");
	}

	@Override
	public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a)
	{
		model.render(pose, buffer, packedLight, packedOverlay, r, g, b, a);
	}

	public static LayerDefinition createBodyLayer()
	{
		var meshDefinition = new MeshDefinition();
		var partDefinition = meshDefinition.getRoot();
		var bone_skull_blossoms = partDefinition.addOrReplaceChild("bone_skull_blossoms", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		bone_skull_blossoms.addOrReplaceChild("vase", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(18, 15).addBox(-4.0F, -8.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(9, 16).addBox(3.0F, -8.0F, -4.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 24).addBox(-4.0F, -8.0F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 6).addBox(-4.0F, -8.0F, -4.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		var blossom = bone_skull_blossoms.addOrReplaceChild("blossom", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		var roots = blossom.addOrReplaceChild("roots", CubeListBuilder.create().texOffs(24, 28).addBox(-3.3866F, 3.857F, -3.1353F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.3866F, -17.857F, 2.1353F));
		roots.addOrReplaceChild("root_7_r1", CubeListBuilder.create().texOffs(6, 32).addBox(-1.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4229F, -0.4549F, -1.4519F));
		roots.addOrReplaceChild("root_6_r1", CubeListBuilder.create().texOffs(32, 8).addBox(0.75F, 0.0F, -1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6366F, 4.857F, -2.3853F, 2.2748F, 1.1009F, -0.8549F));
		roots.addOrReplaceChild("root_5_r1", CubeListBuilder.create().texOffs(31, 21).addBox(-1.0F, -1.25F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6366F, 4.857F, -2.3853F, -0.1605F, -0.3873F, -2.6321F));
		roots.addOrReplaceChild("root_5_r2", CubeListBuilder.create().texOffs(32, 10).addBox(-1.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2033F, -0.1019F, -1.9659F, -0.3491F, -0.6109F, -0.6545F));
		roots.addOrReplaceChild("root_4_r1", CubeListBuilder.create().texOffs(31, 23).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3866F, 2.357F, -2.3853F, 0.0F, -0.6109F, -0.6545F));
		roots.addOrReplaceChild("root_3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7623F, -1.0161F, -3.0982F, 0.4363F, 0.0F, -0.4363F));
		roots.addOrReplaceChild("root_2_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3495F, 2.0138F, -2.1353F, 0.0F, 0.0F, -0.4363F));

		var skulls = blossom.addOrReplaceChild("skulls", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		skulls.addOrReplaceChild("skull_5_r1", CubeListBuilder.create().texOffs(22, 22).addBox(-1.25F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5156F, -15.6092F, -3.696F, 0.5388F, -0.005F, 0.0585F));
		skulls.addOrReplaceChild("skull_4_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, -1.25F, -1.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5105F, -18.8501F, -1.484F, 0.2927F, -0.063F, 0.3367F));
		skulls.addOrReplaceChild("skull_3_r1", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -1.75F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.232F, -20.5124F, 3.0955F, -0.2683F, -0.012F, 0.1696F));
		skulls.addOrReplaceChild("skull_2_r1", CubeListBuilder.create().texOffs(12, 26).addBox(-1.25F, -1.25F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2783F, -14.8491F, 1.4432F, -0.0358F, -0.037F, -0.3973F));
		skulls.addOrReplaceChild("skull_1_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-1.25F, -1.0F, -2.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2484F, -22.9662F, -0.9257F, -0.0832F, 0.0262F, -0.132F));

		var leaves = blossom.addOrReplaceChild("leaves", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		leaves.addOrReplaceChild("10_r1", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, 0.25F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5F, -4.5F, 0.6545F, 0.0F, 0.0F));
		leaves.addOrReplaceChild("9_r1", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, 0.25F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5455F, -4.8562F, 1.309F, 0.0F, 0.0F));
		leaves.addOrReplaceChild("8_r1", CubeListBuilder.create().texOffs(15, 15).addBox(-2.0F, 0.25F, -3.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.5455F, -4.8562F, 1.309F, 0.0F, 0.0F));
		leaves.addOrReplaceChild("7_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, 0.25F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.5F, -4.5F, 0.6545F, 0.0F, 0.0F));
		leaves.addOrReplaceChild("6_r1", CubeListBuilder.create().texOffs(9, 15).addBox(-1.5F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6278F, -12.5564F, 1.2085F, -0.3686F, -0.0971F, 1.0409F));
		leaves.addOrReplaceChild("5_r1", CubeListBuilder.create().texOffs(15, 18).addBox(-1.75F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1043F, -14.5383F, 0.0377F, -0.2751F, -0.2664F, 0.5362F));
		leaves.addOrReplaceChild("4_r1", CubeListBuilder.create().texOffs(7, 26).addBox(-1.5F, -0.4301F, 0.1141F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1325F, -18.7706F, 2.1096F, -0.7634F, -0.1466F, -0.1888F));
		leaves.addOrReplaceChild("3_r1", CubeListBuilder.create().texOffs(23, 15).addBox(-1.5F, -0.2867F, -2.6593F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1325F, -18.7706F, 2.1096F, -0.1525F, -0.1466F, -0.1888F));
		leaves.addOrReplaceChild("2_r1", CubeListBuilder.create().texOffs(28, 28).addBox(-2.25F, 1.0F, -3.25F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0405F, -13.308F, -3.1442F, 0.829F, 0.6981F, 0.0F));
		leaves.addOrReplaceChild("1_r1", CubeListBuilder.create().texOffs(23, 18).addBox(-2.25F, 1.0F, -1.25F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -13.0F, -2.5F, 0.2618F, 0.6981F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}
}
