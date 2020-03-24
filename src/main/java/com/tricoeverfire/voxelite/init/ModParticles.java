package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Reference.MOD_ID);
	
	
    
    public static final RegistryObject<BasicParticleType> STARDUST_GROWTH_BLOCK_PARTICLE = PARTICLES.register("stardust_growth_block_particle", ()->
    	new BasicParticleType(false)
    );
}
