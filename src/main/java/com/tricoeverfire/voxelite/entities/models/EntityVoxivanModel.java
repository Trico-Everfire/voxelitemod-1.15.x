package com.tricoeverfire.voxelite.entities.models;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.entities.extra.BackwardsCompatibleModelRenderer;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EntityVoxivanModel<T extends VoxivanEntity> extends BipedModel<T> {
	private final BackwardsCompatibleModelRenderer Main;
	private final BackwardsCompatibleModelRenderer Head;
	private final BackwardsCompatibleModelRenderer headbase;
	private final BackwardsCompatibleModelRenderer Hair;
	private final BackwardsCompatibleModelRenderer LeftEar;
	private final BackwardsCompatibleModelRenderer RightEar;
	private final BackwardsCompatibleModelRenderer Torso;
	private final BackwardsCompatibleModelRenderer Fluff;
	private final BackwardsCompatibleModelRenderer fluffbase;
	private final BackwardsCompatibleModelRenderer LeftArm;
	private final BackwardsCompatibleModelRenderer RightArm;
	private final BackwardsCompatibleModelRenderer LeftLeg;
	private final BackwardsCompatibleModelRenderer RightLeg;
    public BipedModel.ArmPose leftArmPose;
    public BipedModel.ArmPose rightArmPose;
	
    public EntityVoxivanModel() {
    	this(0.0F, false);
    }
    
    public EntityVoxivanModel(float modelSize)
    {
        this(modelSize, 0.0F, 64, 32);
    }
    
    public EntityVoxivanModel(float modelSize, boolean p_i1168_2_) {
    	this(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }
    
	public EntityVoxivanModel(float modelSize, float modelsomething, int textureWidthIn, int textureHeightIn) {
		super(modelSize, modelsomething, textureWidthIn, textureHeightIn);
        this.leftArmPose = BipedModel.ArmPose.EMPTY;
        this.rightArmPose = BipedModel.ArmPose.EMPTY;
		textureWidth = 64;
		textureHeight = 64;

		Main = new BackwardsCompatibleModelRenderer(this);
		Main.setRotationPoint(0.0F, 24.0F, 0.0F);

		RightLeg = new BackwardsCompatibleModelRenderer(this);
		RightLeg.setRotationPoint(-2.5F, -13.0F, 0.0F);
		Main.addChild(RightLeg);
		RightLeg.setBoxLayout(RightLeg, 26, 24, -2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);
		RightLeg.setBoxLayout(RightLeg, 0, 0, -1.5F, 12.0F, -3.0F, 3, 1, 1, 0.0F, false);

		LeftLeg = new BackwardsCompatibleModelRenderer(this);
		LeftLeg.setRotationPoint(2.5F, -15.0F, -1.25F);
		Main.addChild(LeftLeg);
		LeftLeg.setBoxLayout(LeftLeg, 0, 32, -2.0F, 2.0F, -0.75F, 4, 13, 4, 0.0F, false);
		LeftLeg.setBoxLayout(LeftLeg, 0, 0, -1.5F, 14.0F, -1.75F, 3, 1, 1, 0.0F, false);

		RightArm = new BackwardsCompatibleModelRenderer(this);
		RightArm.setRotationPoint(-5.5F, -26.0F, 0.0F);
		Main.addChild(RightArm);
		RightArm.setBoxLayout(RightArm, 32, 0, -1.5F, -1.0F, -2.0F, 3, 14, 4, 0.0F, false);

		LeftArm = new BackwardsCompatibleModelRenderer(this);
		LeftArm.setRotationPoint(5.5F, -26.0F, 0.0F);
		Main.addChild(LeftArm);
		LeftArm.setBoxLayout(LeftArm, 36, 39, -1.5F, -1.0F, -2.0F, 3, 14, 4, 0.0F, false);

		Torso = new BackwardsCompatibleModelRenderer(this);
		Torso.setRotationPoint(0.0F, -22.0F, 0.0F);
		Main.addChild(Torso);
		Torso.setBoxLayout(Torso, 0, 16, -4.0F, -5.0F, -2.0F, 8, 12, 4, 0.0F, false);

		Fluff = new BackwardsCompatibleModelRenderer(this);
		Fluff.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(Fluff, 0.0F, 0.0F, -0.7854F);
		Torso.addChild(Fluff);
		Fluff.setBoxLayout(Fluff, 0, 16, 8.7071F, -12.7071F, -2.0F, 4, 4, 4, 0.0F, false);

		fluffbase = new BackwardsCompatibleModelRenderer(this);
		fluffbase.setRotationPoint(10.7071F, 11.2929F, 0.0F);
		Torso.addChild(fluffbase);
		fluffbase.setBoxLayout(fluffbase, 0, 16, -14.7071F, -6.0F, -2.0F, 8, 4, 4, 0.0F, false);

		Head = new BackwardsCompatibleModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Main.addChild(Head);

		headbase = new BackwardsCompatibleModelRenderer(this);
		headbase.setRotationPoint(0.0F, -27.0F, 0.0F);
		Head.addChild(headbase);
		headbase.setBoxLayout(headbase, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);
		headbase.setBoxLayout(headbase, 42, 34, -4.0F, -3.0F, -5.0F, 8, 3, 1, 0.0F, false);

		Hair = new BackwardsCompatibleModelRenderer(this);
		Hair.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Hair, 0.4363F, -0.7854F, -0.3491F);
		Head.addChild(Hair);
		Hair.setBoxLayout(Hair, 46, 0, 3.5298F, -34.7542F, 1.8045F, 4, 1, 4, 0.0F, false);
		Hair.setBoxLayout(Hair, 16, 43, 4.5298F, -35.7542F, 2.8045F, 4, 3, 4, 0.0F, false);

		LeftEar = new BackwardsCompatibleModelRenderer(this);
		LeftEar.setRotationPoint(5.625F, -35.625F, -2.1865F);
		setRotationAngle(LeftEar, 0.1745F, 0.0873F, 0.0F);
		Head.addChild(LeftEar);
		LeftEar.setBoxLayout(LeftEar, 26, 16, -1.625F, 0.625F, -1.125F, 1, 2, 6, 0.0F, false);
		LeftEar.setBoxLayout(LeftEar, 38, 18, -2.625F, -0.375F, -1.125F, 3, 1, 7, 0.0F, false);
		LeftEar.setBoxLayout(LeftEar, 0, 2, -1.625F, -0.375F, 5.875F, 1, 1, 1, 0.0F, false);
		LeftEar.setBoxLayout(LeftEar, 0, 0, -1.625F, 0.625F, 4.875F, 2, 1, 1, 0.0F, false);

		RightEar = new BackwardsCompatibleModelRenderer(this);
		RightEar.setRotationPoint(-4.5F, -34.5F, -2.3F);
		setRotationAngle(RightEar, 0.1745F, -0.0873F, 0.0F);
		Head.addChild(RightEar);
		RightEar.setBoxLayout(RightEar, 26, 16, -0.5F, -0.5F, -1.0115F, 1, 2, 6, 0.0F, false);
		RightEar.setBoxLayout(RightEar, 38, 18, -1.5F, -1.5F, -1.0115F, 3, 1, 7, 0.0F, false);
		RightEar.setBoxLayout(RightEar, 0, 2, -0.5F, -1.5F, 5.9885F, 1, 1, 1, 0.0F, false);
		RightEar.setBoxLayout(RightEar, 0, 0, -1.5F, -0.5F, 4.9885F, 2, 1, 1, 0.0F, false);

	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		
		Main.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}