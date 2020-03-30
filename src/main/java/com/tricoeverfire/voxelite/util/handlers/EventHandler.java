package com.tricoeverfire.voxelite.util.handlers;

import java.util.List;

import com.tricoeverfire.voxelite.init.ModBiomes;
import com.tricoeverfire.voxelite.init.ModFluids;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.special.LiquidPhysics;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID,bus = EventBusSubscriber.Bus.FORGE)
public class EventHandler {

	
	@SubscribeEvent
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
    	
      LiquidPhysics physics = new LiquidPhysics(entity,ModFluids.voxelfuildssource);
      physics.init();
    	
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
	
	
}
