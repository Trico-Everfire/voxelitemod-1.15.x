package com.tricoeverfire.voxelite.util.handlers;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.tricoeverfire.voxelite.init.ModBiomes;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID,bus = EventBusSubscriber.Bus.FORGE)
public class EventHandler {

	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onToolTipRender(RenderTooltipEvent e) {
		ItemStack stack = e.getStack();
		
	   	 String a = stack.getOrCreateTag().getString("color");
	    	// System.out.println(a);
	    	 int i = 0;
	    	 String stepin = "0xffffff";

	    	 if (a.contains("0x")) {
	    	try {
	    		i = Integer.decode(a);
	    		stepin = a;
	    	} catch(NumberFormatException ugh) {
	    		i= 0xffffff;
	    	}
	    	 
	    	 } else {
	    		 i= 0xffffff;
	    	 }
	    	
	    	 
	if(stack.getItem() == ModBlocks.HIDDENPHASERBLOCK.asItem() || stack.getItem() == ModItems.PHASER_CRYSTAL) {
		
	    	 IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
	    	 MatrixStack textStack = new MatrixStack();
	    	 textStack.translate(0, 0, 300);
	    	 Matrix4f textLocation = textStack.getLast().getMatrix();
	    	 RenderSystem.enableAlphaTest();
	    	 e.getFontRenderer().renderString(stepin, e.getX(), e.getY() + 12, i, false, textLocation, renderType, false, 0, 15728880);
	    	 renderType.finish();
	}
		//e.getFontRenderer().drawString(stepin, e.getX(), e.getY() + 10, i);
		
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onItemToolTip(ItemTooltipEvent event) {
		
		//System.out.println(" cock");
		for(Item stak : ModItems.AVOKINATE_ARMOR) {
		
		if(event.getItemStack().getItem() == stak) {
		 List<ITextComponent> evi = event.getToolTip();
		 try {
			
			 
			 	
				for(int tagcounter = 0; tagcounter < evi.size(); tagcounter++) {
			 		ITextComponent tags = evi.get(tagcounter);
			 		
			 		boolean tagisright = tags.equals(new TranslationTextComponent("item.durability", event.getItemStack().getMaxDamage() - event.getItemStack().getDamage(), event.getItemStack().getMaxDamage()));
					// System.out.println(tagisright);
						if(tagisright) {
							evi.set(tagcounter, new TranslationTextComponent("item.durability", event.getItemStack().getMaxDamage() - event.getItemStack().getDamage() +" | "+ event.getItemStack().getOrCreateTag().getFloat("microdamage")+" MD", event.getItemStack().getMaxDamage() + ""));
						} else {
							evi.set(evi.size() - 3, new TranslationTextComponent("item.durability", event.getItemStack().getMaxDamage() - event.getItemStack().getDamage() +" | "+ event.getItemStack().getOrCreateTag().getFloat("microdamage")+" MD", event.getItemStack().getMaxDamage() + ""));	
						}
			 		
			 	}
				 
			
			
		
			 //	}
		// System.out.println();
		 } catch(Exception e) {
			 
		 }
		 }
	}
		
		//event.getToolTip().
	}
	
    @SubscribeEvent
    public static void onLivingUpdate(LivingUpdateEvent event) {
    	LivingEntity entity = event.getEntityLiving();
    //	System.out.println("aaa");
    	
    	
    	if(entity instanceof PlayerEntity) {
    		
    		
    	//	ArmorBase.PlayerHoldingShift = entity.isShiftKeyDown();
    		//System.out.println(entity.getMotion().y <= 0);
    		if(entity.world.getBiome(entity.getPosition()) == ModBiomes.VOXELITE_BIOME.get()) {
    		if(entity.getMotion().y <= 0) {
    		entity.setMotion(entity.getMotion().x,entity.getMotion().y / 1.5,entity.getMotion().z);
    		entity.fallDistance = (float) (entity.getMotion().y / 1.5);
    		}
    	}
    }
  }
    
    @SubscribeEvent
    public static void onSetupAngles(RenderPlayerEvent.Post event) {

//    	int i = LivingRenderer.getPackedOverlay(event.getPlayer(), 0.0F);
//    	IVertexBuilder ivertexbuilder = event.getBuffers().getBuffer(RenderType.getEntitySolid(((AbstractClientPlayerEntity) event.getPlayer()).getLocationSkin()));	        
//    	for(int j = 0; j < 2; ++j) {
//    		
//    	MatrixStack matrixStackIn = event.getMatrixStack();
//        float f = MathHelper.lerp(event.getPartialRenderTick(), event.getPlayer().prevRotationYaw, event.getPlayer().rotationYaw) - MathHelper.lerp(event.getPartialRenderTick(), event.getPlayer().prevRenderYawOffset, event.getPlayer().renderYawOffset);
//        float f1 = MathHelper.lerp(event.getPartialRenderTick(), event.getPlayer().prevRotationPitch, event.getPlayer().rotationPitch);
//        matrixStackIn.push();
//
//        
//        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
//        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f1));
//        matrixStackIn.translate((double)(0.375F * (float)(j * 2 - 1)), 0.0D, 0.0D);
//        matrixStackIn.translate(0.0D, -0.375D, 0.0D);
//        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-f1));
//        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-f));
//        
//        float f2 = 1.3333334F;
//        matrixStackIn.scale(1.3333334F, 1.3333334F, 1.3333334F);
//        matrixStackIn.scale(-1, 1, -1);
//      //  matrixStackIn.translate(p_227861_1_, p_227861_3_, p_227861_5_);
//        event.getRenderer().getEntityModel().renderEars(matrixStackIn, ivertexbuilder, event.getLight(), i);
//        matrixStackIn.pop();
//     }

    }
	
	
}
