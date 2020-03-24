package com.tricoeverfire.voxelite.util.handlers;

import com.tricoeverfire.voxelite.init.ModBiomes;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID,bus = EventBusSubscriber.Bus.FORGE)
public class EventHandler {

	
    @SubscribeEvent
    public static void onLivingUpdate(LivingUpdateEvent event) {
    	LivingEntity entity = event.getEntityLiving();
    //	System.out.println("aaa");
    	if(entity instanceof PlayerEntity) {
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
