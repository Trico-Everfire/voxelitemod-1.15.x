package com.tricoeverfire.voxelite.entities.models;


import com.google.common.collect.ImmutableList;
import com.tricoeverfire.voxelite.entities.SkelebatEntity;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EntitySkelebatModel extends SegmentedModel<SkelebatEntity> {
   private final ModelRenderer batHead;
   private final ModelRenderer batBody;
   private final ModelRenderer batRightWing;
   private final ModelRenderer batLeftWing;
   private final ModelRenderer batOuterRightWing;
   private final ModelRenderer batOuterLeftWing;

   public EntitySkelebatModel() {
    /*
	   this.textureWidth = 64;
      this.textureHeight = 64;
      this.batHead = new ModelRenderer(this, 0, 0);
      this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F);
      ModelRenderer modelrenderer = new ModelRenderer(this, 24, 0);
      modelrenderer.addBox(-4.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
      this.batHead.addChild(modelrenderer);
      ModelRenderer modelrenderer1 = new ModelRenderer(this, 24, 0);
      modelrenderer1.mirror = true;
      modelrenderer1.addBox(1.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
      this.batHead.addChild(modelrenderer1);
      this.batBody = new ModelRenderer(this, 0, 16);
      this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6.0F, 12.0F, 6.0F);
      this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10.0F, 6.0F, 1.0F);
      this.batRightWing = new ModelRenderer(this, 42, 0);
      this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
      this.batOuterRightWing = new ModelRenderer(this, 24, 16);
      this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
      this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
      this.batLeftWing = new ModelRenderer(this, 42, 0);
      this.batLeftWing.mirror = true;
      this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
      this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
      this.batOuterLeftWing.mirror = true;
      this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
      this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
      this.batBody.addChild(this.batRightWing);
      this.batBody.addChild(this.batLeftWing);
      this.batRightWing.addChild(this.batOuterRightWing);
      this.batLeftWing.addChild(this.batOuterLeftWing);
      */
	   
	   textureWidth = 64;
		textureHeight = 64;

		batHead = new ModelRenderer(this);
		batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		batHead.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		batHead.setTextureOffset(1, 42).addBox(-2.9F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		batHead.setTextureOffset(1, 42).addBox(0.9F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		ModelRenderer modelrenderer = new ModelRenderer(this);
		modelrenderer.setRotationPoint(0.0F, 0.0F, 0.0F);
		batHead.addChild(modelrenderer);
		modelrenderer.setTextureOffset(25, 2).addBox(2.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelRenderer modelrenderer1 = new ModelRenderer(this);
		modelrenderer1.setRotationPoint(0.0F, 0.0F, 0.0F);
		batHead.addChild(modelrenderer1);
		modelrenderer1.setTextureOffset(25, 2).addBox(-4.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		batBody = new ModelRenderer(this);
		batBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		batBody.setTextureOffset(0, 16).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
		batBody.setTextureOffset(0, 45).addBox(-2.5F, 5.0F, 0.0F, 5.0F, 7.0F, 2.0F, 0.0F, false);
		batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10.0F, 6.0F, 1.0F);
		
		batRightWing = new ModelRenderer(this);
		batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		batBody.addChild(batRightWing);
		batRightWing.setTextureOffset(42, 0).addBox(-12.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F, 0.0F, false);

		batOuterRightWing = new ModelRenderer(this);
		batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
		batRightWing.addChild(batOuterRightWing);
		batOuterRightWing.setTextureOffset(24, 16).addBox(-8.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);

		batLeftWing = new ModelRenderer(this);
		batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		batBody.addChild(batLeftWing);
		batLeftWing.setTextureOffset(42, 0).addBox(2.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F, 0.0F, true);

		batOuterLeftWing = new ModelRenderer(this);
		batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
		batLeftWing.addChild(batOuterLeftWing);
		batOuterLeftWing.setTextureOffset(24, 16).addBox(0.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F, 0.0F, true);
   }

   public Iterable<ModelRenderer> getParts() {
      return ImmutableList.of(this.batHead, this.batBody);
   }

   /**
    * Sets this entity's model rotation angles
    */
   public void setRotationAngles(SkelebatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      if (entityIn.getIsBatHanging()) {
         this.batHead.rotateAngleX = -headPitch * ((float)Math.PI / 180F);
         this.batHead.rotateAngleY = ((float)Math.PI / 180F); //(float)Math.PI - netHeadYaw *
         this.batHead.rotateAngleZ = (float)Math.PI;
         this.batHead.setRotationPoint(0.0F, -1.0F, 0.0F);
         this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
         this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
         this.batBody.rotateAngleY = ((float)Math.PI);
         this.batBody.rotateAngleX = ((float)Math.PI);
         this.batRightWing.rotateAngleX = -0.15707964F;
         this.batRightWing.rotateAngleY = -1.2566371F;
         this.batOuterRightWing.rotateAngleY = -1.7278761F;
         this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
      } else {
         this.batHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
         this.batHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
         this.batHead.rotateAngleZ = 0.0F;
         this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.batBody.rotateAngleX = ((float)Math.PI / 4F) + MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
         this.batBody.rotateAngleY = 0.0F;
         this.batRightWing.rotateAngleY = MathHelper.cos(ageInTicks * 1.3F) * (float)Math.PI * 0.25F;
         this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
         this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
         this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
      }

   }
   
}
