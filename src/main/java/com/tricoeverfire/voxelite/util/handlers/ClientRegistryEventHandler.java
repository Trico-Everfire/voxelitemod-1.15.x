package com.tricoeverfire.voxelite.util.handlers;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModParticles;
import com.tricoeverfire.voxelite.particles.ParticleBase;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistryEventHandler {
    
	
    @SubscribeEvent()
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(ModParticles.STARDUST_GROWTH_BLOCK_PARTICLE.get(), ParticleBase.Factory::new);

        Main.LOGGER.info("Registered Particle Factories");
    }
   
}