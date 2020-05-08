package com.tricoeverfire.voxelite.entities.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.entities.models.EntityVoxivanModel;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("deprecation")
public class LayerVoxivanHeldItem extends LayerRenderer<VoxivanEntity,EntityVoxivanModel<VoxivanEntity>>{

	protected final MobRenderer<VoxivanEntity, EntityVoxivanModel<VoxivanEntity>> livingEntityRenderer;
	
	public  LayerVoxivanHeldItem(MobRenderer<VoxivanEntity, EntityVoxivanModel<VoxivanEntity>> livingEntityRendererIn) {
		super(livingEntityRendererIn);
		this.livingEntityRenderer = livingEntityRendererIn;
	}
	
	
	
	public void doRenderLayer(LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		 boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
	        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
	        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

	        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
	        {
	            GlStateManager.pushMatrix();

	            if (this.livingEntityRenderer.getEntityModel().isChild)
	            {
	                @SuppressWarnings("unused")
					float f = 0.5F;
	                GlStateManager.translated(0.0F, 0.75F, 0.0F);
	                GlStateManager.scaled(0.5F, 0.5F, 0.5F);
	            }

	            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT);
	            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT);
	            GlStateManager.popMatrix();
	        }
		
	}
	
    
	private void renderHeldItem(LivingEntity p_188358_1_, ItemStack p_188358_2_, ItemCameraTransforms.TransformType p_188358_3_, HandSide handSide)
    {
        if (!p_188358_2_.isEmpty())
        {
            GlStateManager.pushMatrix();

//            if (p_188358_1_.isShiftKeyDown())
//            {
//                GlStateManager.translated(0.0F, 0.2F, 0.0F);
//            }
            // Forge: moved this call down, fixes incorrect offset while sneaking.
          //  this.translateToHand(handSide);
            GlStateManager.rotatef(-110.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.05F);
            boolean flag = handSide == HandSide.RIGHT;
            GlStateManager.translated((float)(flag ? -1 : 0.1) / 16.0F, 0.015F, -0.845F);
       //     Minecraft.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
            GlStateManager.popMatrix();
        }
    }
    
    protected void translateToHand(HandSide p_191361_1_)
    {
      //  ((EntityVoxivanModel)this.livingEntityRenderer.getEntityModel()).postRenderArm(0.0625F, p_191361_1_);
    }

	
	public boolean shouldCombineTextures() {
		
		return false;
	}


	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			VoxivanEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		doRenderLayer(entitylivingbaseIn, headPitch, headPitch, headPitch, headPitch, headPitch, headPitch, headPitch);
		
	}

}
