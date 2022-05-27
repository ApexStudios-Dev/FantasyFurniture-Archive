package xyz.apex.forge.fantasyfurniture.client.renderer.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.init.FFRegistry;

import java.util.function.Function;

public final class WidowBloomModel extends Model
{
	public static final ResourceLocation TEXTURE = FFRegistry.getInstance().id("textures/models/decorations/venthyr/widowbloom.png");

	private final ModelRenderer main;
	private final ModelRenderer urn;
	private final ModelRenderer widowbloom;
	private final ModelRenderer leaf_6_r1;
	private final ModelRenderer leaf_5_r1;
	private final ModelRenderer leaf_4_r1;
	private final ModelRenderer leaf_3_r1;
	private final ModelRenderer leaf_2_r1;
	private final ModelRenderer leaf_1_r1;
	private final ModelRenderer stem_6_r1;
	private final ModelRenderer stem_5_r1;
	private final ModelRenderer stem_4_r1;
	private final ModelRenderer stem_3_r1;
	private final ModelRenderer stem_2_r1;
	private final ModelRenderer bloom_1;
	private final ModelRenderer bloom_1e_r1;
	private final ModelRenderer bloom_1d_r1;
	private final ModelRenderer bloom_1c_r1;
	private final ModelRenderer bloom_1b_r1;
	private final ModelRenderer bloom_1a_r1;
	private final ModelRenderer bloom_2;
	private final ModelRenderer bloom_2e_r1;
	private final ModelRenderer bloom_2d_r1;
	private final ModelRenderer bloom_2c_r1;
	private final ModelRenderer bloom_2b_r1;
	private final ModelRenderer bloom_2a_r1;
	private final ModelRenderer bloom_3;
	private final ModelRenderer bloom_3e_r1;
	private final ModelRenderer bloom_3d_r1;
	private final ModelRenderer bloom_3c_r1;
	private final ModelRenderer bloom_3b_r1;
	private final ModelRenderer bloom_3a_r1;

	public WidowBloomModel(Function<ResourceLocation, RenderType> renderTypeFunction)
	{
		super(renderTypeFunction);

		texWidth = 64;
		texHeight = 64;

		main = new ModelRenderer(this);
		main.setPos(0F, 16F, 0F);

		urn = new ModelRenderer(this);
		urn.setPos(8F, 0F, -8F);
		main.addChild(urn);
		urn.texOffs(20, 20).addBox(-10F, 6F, 6F, 4F, 2F, 4F, 0F, false);
		urn.texOffs(20, 26).addBox(-9.5F, 5F, 6.5F, 3F, 1F, 3F, 0F, false);
		urn.texOffs(0, 17).addBox(-10.5F, 2F, 5.5F, 5F, 3F, 5F, 0F, false);
		urn.texOffs(0, 9).addBox(-11F, 0F, 5F, 6F, 2F, 6F, 0F, false);
		urn.texOffs(0, 0).addBox(-11.5F, -2F, 4.5F, 7F, 2F, 7F, 0F, false);
		urn.texOffs(0, 25).addBox(-6F, -3F, 5F, 1F, 1F, 6F, 0F, false);
		urn.texOffs(21, 0).addBox(-11F, -3F, 5F, 1F, 1F, 6F, 0F, false);
		urn.texOffs(0, 32).addBox(-10F, -3F, 10F, 4F, 1F, 1F, 0F, false);
		urn.texOffs(29, 26).addBox(-10F, -3F, 5F, 4F, 1F, 1F, 0F, false);

		widowbloom = new ModelRenderer(this);
		widowbloom.setPos(8F, 8F, -8F);
		main.addChild(widowbloom);
		widowbloom.texOffs(20, 30).addBox(-9F, -14F, 7F, 2F, 4F, 2F, 0F, false);

		leaf_6_r1 = new ModelRenderer(this);
		leaf_6_r1.setPos(-2.3997F, -17.5092F, 10.1867F);
		widowbloom.addChild(leaf_6_r1);
		setRotationAngle(leaf_6_r1, 0.9126F, 1.2606F, 1.4594F);
		leaf_6_r1.texOffs(-1, 34).addBox(-1.5F, 0F, -1F, 3F, 0F, 3F, 0F, false);

		leaf_5_r1 = new ModelRenderer(this);
		leaf_5_r1.setPos(-3.25F, -18F, 10F);
		widowbloom.addChild(leaf_5_r1);
		setRotationAngle(leaf_5_r1, 1.6107F, 1.2606F, 1.4594F);
		leaf_5_r1.texOffs(19, 2).addBox(-1.5F, 0F, -2F, 3F, 0F, 2F, 0F, false);

		leaf_4_r1 = new ModelRenderer(this);
		leaf_4_r1.setPos(-8.6989F, -15.5435F, 11.3568F);
		widowbloom.addChild(leaf_4_r1);
		setRotationAngle(leaf_4_r1, -0.6273F, -0.379F, 0.2442F);
		leaf_4_r1.texOffs(15, 9).addBox(-1.5F, -0.3377F, -0.3817F, 3F, 0F, 3F, 0F, false);

		leaf_3_r1 = new ModelRenderer(this);
		leaf_3_r1.setPos(-8.6989F, -15.5435F, 11.3568F);
		widowbloom.addChild(leaf_3_r1);
		setRotationAngle(leaf_3_r1, 0.1145F, -0.379F, 0.2442F);
		leaf_3_r1.texOffs(19, 4).addBox(-1.5F, -0.5066F, -2.0526F, 3F, 0F, 2F, 0F, false);

		leaf_2_r1 = new ModelRenderer(this);
		leaf_2_r1.setPos(-5.9199F, -13.6992F, 5.244F);
		widowbloom.addChild(leaf_2_r1);
		setRotationAngle(leaf_2_r1, 0.3054F, -0.6545F, 0F);
		leaf_2_r1.texOffs(15, 12).addBox(-1.5F, 0F, -2F, 3F, 0F, 3F, 0F, false);

		leaf_1_r1 = new ModelRenderer(this);
		leaf_1_r1.setPos(-6.5F, -14F, 6F);
		widowbloom.addChild(leaf_1_r1);
		setRotationAngle(leaf_1_r1, -0.5672F, -0.6545F, 0F);
		leaf_1_r1.texOffs(15, 26).addBox(-1.5F, 0F, 0F, 3F, 0F, 2F, 0F, false);

		stem_6_r1 = new ModelRenderer(this);
		stem_6_r1.setPos(-7.4517F, -17.0188F, 8F);
		widowbloom.addChild(stem_6_r1);
		setRotationAngle(stem_6_r1, 1.3245F, -0.7409F, -0.7817F);
		stem_6_r1.texOffs(0, 9).addBox(-0.5F, -1.5F, -0.5F, 1F, 3F, 1F, 0F, false);

		stem_5_r1 = new ModelRenderer(this);
		stem_5_r1.setPos(-8.5F, -12.5F, 8F);
		widowbloom.addChild(stem_5_r1);
		setRotationAngle(stem_5_r1, 0F, 0F, -0.7418F);
		stem_5_r1.texOffs(0, 17).addBox(0.5F, -4F, 0F, 1F, 3F, 1F, 0F, false);

		stem_4_r1 = new ModelRenderer(this);
		stem_4_r1.setPos(-5.3008F, -19.6994F, 10.4432F);
		widowbloom.addChild(stem_4_r1);
		setRotationAngle(stem_4_r1, -0.3843F, 0.0829F, 0.202F);
		stem_4_r1.texOffs(29, 0).addBox(-1F, -2F, -1F, 2F, 4F, 2F, 0F, false);

		stem_3_r1 = new ModelRenderer(this);
		stem_3_r1.setPos(-7.2322F, -16.7156F, 9.2072F);
		widowbloom.addChild(stem_3_r1);
		setRotationAngle(stem_3_r1, -0.2849F, 0.274F, 0.7459F);
		stem_3_r1.texOffs(12, 30).addBox(-1F, -2.5F, -1F, 2F, 4F, 2F, 0F, false);

		stem_2_r1 = new ModelRenderer(this);
		stem_2_r1.setPos(-8F, -15.927F, 8.8806F);
		widowbloom.addChild(stem_2_r1);
		setRotationAngle(stem_2_r1, -0.3927F, 0F, 0F);
		stem_2_r1.texOffs(28, 30).addBox(-1F, -0.5F, -1F, 2F, 3F, 2F, 0F, false);

		bloom_1 = new ModelRenderer(this);
		bloom_1.setPos(-8.5F, -13.5F, 8F);
		widowbloom.addChild(bloom_1);

		bloom_1e_r1 = new ModelRenderer(this);
		bloom_1e_r1.setPos(-3.5755F, -4.3821F, 2.3206F);
		bloom_1.addChild(bloom_1e_r1);
		setRotationAngle(bloom_1e_r1, -0.6981F, 0F, -0.7418F);
		bloom_1e_r1.texOffs(0, 2).addBox(-1.5F, -0.5F, 0F, 3F, 1F, 0F, 0F, false);

		bloom_1d_r1 = new ModelRenderer(this);
		bloom_1d_r1.setPos(-3.5551F, -4.3599F, -1.3536F);
		bloom_1.addChild(bloom_1d_r1);
		setRotationAngle(bloom_1d_r1, 0.7854F, 0F, -0.7418F);
		bloom_1d_r1.texOffs(0, 13).addBox(-1.5F, -0.5F, 0F, 3F, 1F, 0F, 0F, false);

		bloom_1c_r1 = new ModelRenderer(this);
		bloom_1c_r1.setPos(-4.9217F, -3.1087F, 0.5F);
		bloom_1.addChild(bloom_1c_r1);
		setRotationAngle(bloom_1c_r1, 0F, 0F, -1.5272F);
		bloom_1c_r1.texOffs(0, 2).addBox(0F, -0.5F, -1.5F, 0F, 1F, 3F, 0F, false);

		bloom_1b_r1 = new ModelRenderer(this);
		bloom_1b_r1.setPos(-2.1885F, -5.6122F, 0.5F);
		bloom_1.addChild(bloom_1b_r1);
		setRotationAngle(bloom_1b_r1, 0F, 0F, 0.0436F);
		bloom_1b_r1.texOffs(0, 3).addBox(0F, -0.5F, -1.5F, 0F, 1F, 3F, 0F, false);

		bloom_1a_r1 = new ModelRenderer(this);
		bloom_1a_r1.setPos(0F, 1F, 0F);
		bloom_1.addChild(bloom_1a_r1);
		setRotationAngle(bloom_1a_r1, 0F, 0F, -0.7418F);
		bloom_1a_r1.texOffs(8, 25).addBox(-0.5F, -6F, -1F, 3F, 2F, 3F, 0F, false);

		bloom_2 = new ModelRenderer(this);
		bloom_2.setPos(-5.4517F, -19.0188F, 8F);
		widowbloom.addChild(bloom_2);

		bloom_2e_r1 = new ModelRenderer(this);
		bloom_2e_r1.setPos(-1.8417F, 0.5189F, -4.0087F);
		bloom_2.addChild(bloom_2e_r1);
		setRotationAngle(bloom_2e_r1, 1.3898F, 0.0288F, -0.955F);
		bloom_2e_r1.texOffs(0, 0).addBox(0F, -0.5F, -1.5F, 0F, 1F, 3F, 0F, false);

		bloom_2d_r1 = new ModelRenderer(this);
		bloom_2d_r1.setPos(0.099F, -1.4074F, -1.5066F);
		bloom_2.addChild(bloom_2d_r1);
		setRotationAngle(bloom_2d_r1, 0.1585F, -1.3876F, 0.4599F);
		bloom_2d_r1.texOffs(0, 1).addBox(0F, -0.5F, -1.5F, 0F, 1F, 3F, 0F, false);

		bloom_2c_r1 = new ModelRenderer(this);
		bloom_2c_r1.setPos(-2.3538F, -1.5048F, -2.4248F);
		bloom_2.addChild(bloom_2c_r1);
		setRotationAngle(bloom_2c_r1, 0.5391F, -0.7409F, -0.7817F);
		bloom_2c_r1.texOffs(0, 0).addBox(-1.5F, -0.5F, 0F, 3F, 1F, 0F, 0F, false);

		bloom_2b_r1 = new ModelRenderer(this);
		bloom_2b_r1.setPos(0.6112F, 0.6171F, -3.091F);
		bloom_2.addChild(bloom_2b_r1);
		setRotationAngle(bloom_2b_r1, 2.1099F, -0.7409F, -0.7817F);
		bloom_2b_r1.texOffs(0, 1).addBox(-1.5F, -0.5F, 0F, 3F, 1F, 0F, 0F, false);

		bloom_2a_r1 = new ModelRenderer(this);
		bloom_2a_r1.setPos(-2F, 2F, 0F);
		bloom_2.addChild(bloom_2a_r1);
		setRotationAngle(bloom_2a_r1, 1.3245F, -0.7409F, -0.7817F);
		bloom_2a_r1.texOffs(25, 7).addBox(-1.5F, -3.5F, -1.5F, 3F, 2F, 3F, 0F, false);

		bloom_3 = new ModelRenderer(this);
		bloom_3.setPos(-5.3008F, -19.6994F, 10.4432F);
		widowbloom.addChild(bloom_3);

		bloom_3e_r1 = new ModelRenderer(this);
		bloom_3e_r1.setPos(0.068F, -3.5753F, 0.3728F);
		bloom_3.addChild(bloom_3e_r1);
		setRotationAngle(bloom_3e_r1, 0.0569F, 0.0206F, 0.7721F);
		bloom_3e_r1.texOffs(15, 16).addBox(1.3358F, -2.5144F, -2.16F, 0F, 1F, 4F, 0F, false);

		bloom_3d_r1 = new ModelRenderer(this);
		bloom_3d_r1.setPos(0.068F, -3.5753F, 0.3728F);
		bloom_3.addChild(bloom_3d_r1);
		setRotationAngle(bloom_3d_r1, -0.0206F, 0.0569F, -0.7999F);
		bloom_3d_r1.texOffs(15, 17).addBox(-1.313F, -2.4926F, -2.16F, 0F, 1F, 4F, 0F, false);

		bloom_3c_r1 = new ModelRenderer(this);
		bloom_3c_r1.setPos(0.068F, -3.5753F, 0.3728F);
		bloom_3.addChild(bloom_3c_r1);
		setRotationAngle(bloom_3c_r1, -0.7597F, 0.0548F, -0.0132F);
		bloom_3c_r1.texOffs(24, 12).addBox(-1.9846F, -2.3904F, 1.2108F, 4F, 1F, 0F, 0F, false);

		bloom_3b_r1 = new ModelRenderer(this);
		bloom_3b_r1.setPos(0.068F, -3.5753F, 0.3728F);
		bloom_3.addChild(bloom_3b_r1);
		setRotationAngle(bloom_3b_r1, 0.8111F, 0.0548F, -0.0132F);
		bloom_3b_r1.texOffs(29, 28).addBox(-1.9846F, -2.6167F, -1.4381F, 4F, 1F, 0F, 0F, false);

		bloom_3a_r1 = new ModelRenderer(this);
		bloom_3a_r1.setPos(0.068F, -3.5753F, 0.3728F);
		bloom_3.addChild(bloom_3a_r1);
		setRotationAngle(bloom_3a_r1, 0.0257F, 0.0548F, -0.0132F);
		bloom_3a_r1.texOffs(20, 13).addBox(-1.9846F, -0.1263F, -2.16F, 4F, 3F, 4F, 0F, false);
	}

	public WidowBloomModel()
	{
		this(RenderType::entityTranslucent);
	}

	@Override
	public void renderToBuffer(MatrixStack pose, IVertexBuilder buffer, int packedLight, int packedOverlay, float r, float g, float b, float a)
	{
		main.render(pose, buffer, packedLight, packedOverlay, r, g, b, a);
	}

	public static void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
