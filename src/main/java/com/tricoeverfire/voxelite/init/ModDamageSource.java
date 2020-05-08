package com.tricoeverfire.voxelite.init;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class ModDamageSource {

	public static DamageSource HAMMERSLAM = null;
	public static final DamageSource VOXDAM = (new DamageSource("voxelized"));
	
	   public static DamageSource doHammerDamage(@Nullable Entity source, @Nullable Entity indirectEntityIn) {
		    if(source == null) {
		    	return new DamageSource("hammer_slam");
		    }
		   return (new IndirectEntityDamageSource("hammer_slam", source, indirectEntityIn));
	   }
	
	   public enum ModDamageSources{
		   
	   }

	   
	   
}

