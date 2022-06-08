package xyz.apex.forge.fantasyfurniture.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.init.FFRegistry;

public final class WidowBloomModel extends Model
{
	public static final ResourceLocation TEXTURE = FFRegistry.getInstance().id("textures/models/decorations/venthyr/widowbloom.png");
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(FFRegistry.getInstance().id("widowbloom"), "main");

	private final ModelPart model;

	public WidowBloomModel(ModelPart root)
	{
		super(RenderType::entityTranslucentCull);

		model = root.getChild("venthyr_widowbloom");
	}

	@Override
	public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a)
	{
		model.render(pose, buffer, packedLight, packedOverlay, r, g, b, a);
	}

	public static LayerDefinition createBodyLayer() {
		var meshdefinition = new MeshDefinition();
		var partdefinition = meshdefinition.getRoot();
		var venthyr_widowbloom = partdefinition.addOrReplaceChild("venthyr_widowbloom", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		var urn = venthyr_widowbloom.addOrReplaceChild("urn", CubeListBuilder.create().texOffs(20, 20).addBox(-10.0F, 6.0F, 6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(20, 26).addBox(-9.5F, 5.0F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 17).addBox(-10.5F, 2.0F, 5.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 9).addBox(-11.0F, 0.0F, 5.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-11.5F, -2.0F, 4.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 25).addBox(-6.0F, -3.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(21, 0).addBox(-11.0F, -3.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 32).addBox(-10.0F, -3.0F, 10.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 26).addBox(-10.0F, -3.0F, 5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, -8.0F));
		var widowbloom = venthyr_widowbloom.addOrReplaceChild("widowbloom", CubeListBuilder.create().texOffs(20, 30).addBox(-9.0F, -14.0F, 7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		var leaf_6_r1 = widowbloom.addOrReplaceChild("leaf_6_r1", CubeListBuilder.create().texOffs(-1, 34).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3997F, -17.5092F, 10.1867F, 0.9126F, 1.2606F, 1.4594F));
		var leaf_5_r1 = widowbloom.addOrReplaceChild("leaf_5_r1", CubeListBuilder.create().texOffs(19, 2).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -18.0F, 10.0F, 1.6107F, 1.2606F, 1.4594F));
		var leaf_4_r1 = widowbloom.addOrReplaceChild("leaf_4_r1", CubeListBuilder.create().texOffs(15, 9).addBox(-1.5F, -0.3377F, -0.3817F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6989F, -15.5435F, 11.3568F, -0.6273F, -0.379F, 0.2442F));
		var leaf_3_r1 = widowbloom.addOrReplaceChild("leaf_3_r1", CubeListBuilder.create().texOffs(19, 4).addBox(-1.5F, -0.5066F, -2.0526F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6989F, -15.5435F, 11.3568F, 0.1145F, -0.379F, 0.2442F));
		var leaf_2_r1 = widowbloom.addOrReplaceChild("leaf_2_r1", CubeListBuilder.create().texOffs(15, 12).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9199F, -13.6992F, 5.244F, 0.3054F, -0.6545F, 0.0F));
		var leaf_1_r1 = widowbloom.addOrReplaceChild("leaf_1_r1", CubeListBuilder.create().texOffs(15, 26).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -14.0F, 6.0F, -0.5672F, -0.6545F, 0.0F));
		var stem_6_r1 = widowbloom.addOrReplaceChild("stem_6_r1", CubeListBuilder.create().texOffs(0, 9).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4517F, -17.0188F, 8.0F, 1.3245F, -0.7409F, -0.7817F));
		var stem_5_r1 = widowbloom.addOrReplaceChild("stem_5_r1", CubeListBuilder.create().texOffs(0, 17).addBox(0.5F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -12.5F, 8.0F, 0.0F, 0.0F, -0.7418F));
		var stem_4_r1 = widowbloom.addOrReplaceChild("stem_4_r1", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3008F, -19.6994F, 10.4432F, -0.3843F, 0.0829F, 0.202F));
		var stem_3_r1 = widowbloom.addOrReplaceChild("stem_3_r1", CubeListBuilder.create().texOffs(12, 30).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2322F, -16.7156F, 9.2072F, -0.2849F, 0.274F, 0.7459F));
		var stem_2_r1 = widowbloom.addOrReplaceChild("stem_2_r1", CubeListBuilder.create().texOffs(28, 30).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -15.927F, 8.8806F, -0.3927F, 0.0F, 0.0F));
		var bloom_1 = widowbloom.addOrReplaceChild("bloom_1", CubeListBuilder.create(), PartPose.offset(-8.5F, -13.5F, 8.0F));
		var r1a = bloom_1.addOrReplaceChild("5_r1", CubeListBuilder.create().texOffs(0, 2).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5755F, -4.3821F, 2.3206F, -0.6981F, 0.0F, -0.7418F));
		var r1b = bloom_1.addOrReplaceChild("4_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5551F, -4.3599F, -1.3536F, 0.7854F, 0.0F, -0.7418F));
		var r1c = bloom_1.addOrReplaceChild("3_r1", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9217F, -3.1087F, 0.5F, 0.0F, 0.0F, -1.5272F));
		var r1d = bloom_1.addOrReplaceChild("2_r1", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1885F, -5.6122F, 0.5F, 0.0F, 0.0F, 0.0436F));
		var r1e = bloom_1.addOrReplaceChild("1_r1", CubeListBuilder.create().texOffs(8, 25).addBox(-0.5F, -6.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7418F));
		var bloom_2 = widowbloom.addOrReplaceChild("bloom_2", CubeListBuilder.create(), PartPose.offset(-5.4517F, -19.0188F, 8.0F));
		var r2a = bloom_2.addOrReplaceChild("5_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8417F, 0.5189F, -4.0087F, 1.3898F, 0.0288F, -0.955F));
		var r2b = bloom_2.addOrReplaceChild("4_r2", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.099F, -1.4074F, -1.5066F, 0.1585F, -1.3876F, 0.4599F));
		var r2c = bloom_2.addOrReplaceChild("3_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3538F, -1.5048F, -2.4248F, 0.5391F, -0.7409F, -0.7817F));
		var r2d = bloom_2.addOrReplaceChild("2_r2", CubeListBuilder.create().texOffs(0, 1).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6112F, 0.6171F, -3.091F, 2.1099F, -0.7409F, -0.7817F));
		var r2e = bloom_2.addOrReplaceChild("1_r2", CubeListBuilder.create().texOffs(25, 7).addBox(-1.5F, -3.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 1.3245F, -0.7409F, -0.7817F));
		var bloom_3 = widowbloom.addOrReplaceChild("bloom_3", CubeListBuilder.create(), PartPose.offset(-5.3008F, -19.6994F, 10.4432F));
		var r3a = bloom_3.addOrReplaceChild("5_r3", CubeListBuilder.create().texOffs(15, 16).addBox(1.3358F, -2.5144F, -2.16F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.0569F, 0.0206F, 0.7721F));
		var r3b = bloom_3.addOrReplaceChild("4_r3", CubeListBuilder.create().texOffs(15, 17).addBox(-1.313F, -2.4926F, -2.16F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, -0.0206F, 0.0569F, -0.7999F));
		var r3c = bloom_3.addOrReplaceChild("3_r3", CubeListBuilder.create().texOffs(24, 12).addBox(-1.9846F, -2.3904F, 1.2108F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, -0.7597F, 0.0548F, -0.0132F));
		var r3d = bloom_3.addOrReplaceChild("2_r3", CubeListBuilder.create().texOffs(29, 28).addBox(-1.9846F, -2.6167F, -1.4381F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.8111F, 0.0548F, -0.0132F));
		var r3e = bloom_3.addOrReplaceChild("1_r3", CubeListBuilder.create().texOffs(20, 13).addBox(-1.9846F, -0.1263F, -2.16F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.0257F, 0.0548F, -0.0132F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
