package com.tricoeverfire.voxelite.entities.models;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tricoeverfire.voxelite.entities.extra.BackwardsCompatibleModelRenderer;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EntitySlugModel<T extends Entity> extends AgeableModel<T> {
	private final BackwardsCompatibleModelRenderer Main;
	private final BackwardsCompatibleModelRenderer MainBody;
	private final BackwardsCompatibleModelRenderer LeftAntenna;
	private final BackwardsCompatibleModelRenderer RightAntenna;
	private final BackwardsCompatibleModelRenderer Tail;
	
    public EntitySlugModel() {
    	this(0.0F, false);
    }
    
    public EntitySlugModel(float modelSize)
    {
        this(modelSize, 0.0F, 64, 32);
    }
    
    public EntitySlugModel(float modelSize, boolean p_i1168_2_) {
    	this(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }
    
	public EntitySlugModel(float modelSize, float modelsomething, int textureWidthIn, int textureHeightIn) {
		super(true, 10.0F, 4.0F);
		textureWidth = 32;
		textureHeight = 32;


		Main = new BackwardsCompatibleModelRenderer(this);
		Main.setRotationPoint(0, 0, 0);
		
		MainBody = new BackwardsCompatibleModelRenderer(this);
		MainBody.setRotationPoint(0.0F, 24.0F, 0.0F);
		Main.addChild(MainBody);
		MainBody.setBoxLayout(MainBody, 0, 0, -2.5F, -4.0F, -3.5F, 5, 4, 6, 0.0F, false);

		LeftAntenna = new BackwardsCompatibleModelRenderer(this);
		LeftAntenna.setRotationPoint(2.0F, 20.0F, -3.0F);
		setRotationAngle(LeftAntenna, 0.6109F, 0.0F, 0.0F);
		Main.addChild(LeftAntenna);
		LeftAntenna.setBoxLayout(LeftAntenna, 9, 12, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, false);

		RightAntenna = new BackwardsCompatibleModelRenderer(this);
		RightAntenna.setRotationPoint(-2.0F, 20.0F, -3.0F);
		setRotationAngle(RightAntenna, 0.6109F, 0.0F, 0.0F);
		Main.addChild(RightAntenna);
		RightAntenna.setBoxLayout(RightAntenna, 9, 12, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, false);

		Tail = new BackwardsCompatibleModelRenderer(this);
		Tail.setRotationPoint(0.0F, 24.0F, 0.0F);
		Main.addChild(Tail);
		Tail.setBoxLayout(Tail, 0, 11, -1.5F, -3.0F, 2.5F, 3, 3, 1, 0.0F, false);

	
		
		
		
		

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

	
	public void setRotationAngle(ModelRenderer BackwardsCompatibleModelRenderer, float x, float y, float z) {
		BackwardsCompatibleModelRenderer.rotateAngleX = x;
		BackwardsCompatibleModelRenderer.rotateAngleY = y;
		BackwardsCompatibleModelRenderer.rotateAngleZ = z;
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		

		
		return ImmutableList.of(RightAntenna,LeftAntenna);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
	
		return ImmutableList.of(MainBody,Tail);
	}
}