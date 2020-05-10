package com.tricoeverfire.voxelite.entities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.entities.SkelebatEntity;
import com.tricoeverfire.voxelite.entities.models.EntitySkelebatModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkelebatEntityRenderer extends MobRenderer<SkelebatEntity, EntitySkelebatModel> {
   private static final ResourceLocation BAT_TEXTURES = Main.location("textures/entity/curse_bat_uv.png");

   public SkelebatEntityRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new EntitySkelebatModel(), 0.25F);
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(SkelebatEntity entity) {
      return BAT_TEXTURES;
   }

   protected void preRenderCallback(SkelebatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
      matrixStackIn.scale(0.35F, 0.35F, 0.35F);
   }

   protected void applyRotations(SkelebatEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.getIsBatHanging()) {
         matrixStackIn.translate(0.0D, (double)-0.1F, 0.0D);
      } else {
         matrixStackIn.translate(0.0D, (double)(MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
      }

      super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
   }
}
