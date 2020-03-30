package com.tricoeverfire.voxelite.special;

import java.util.function.Supplier;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.RegistryObject;

public class LiquidPhysics {
	
	public final Entity entity;
	public final Fluid fluid;
	@SuppressWarnings("unused")
	private Tag<Fluid> unused = FluidTags.WATER;
	
	public LiquidPhysics(Entity entity, Fluid fluidBlock) {
		this(entity, fluidBlock.delegate);
	}
	
	public LiquidPhysics(Entity entity, Supplier<? extends Fluid> fluidsblock) {
		
		this.entity = entity;
		this.fluid = fluidsblock.get().getFluid();
	}
	

//	public LiquidPhysics(LivingEntity entity2, RegistryObject<FlowingFluidBlock> voxelfluidsblock) {
//		this(entity, fluid);
//	}

	public void init() {
		tick();
	}
	
	protected void tick() {
		
		
		if(entity instanceof LivingEntity) {
			LivingEntity entityliving = (LivingEntity) entity;
			
			entityliving.travel(new Vec3d((double)entityliving.moveStrafing, (double)entityliving.moveVertical, (double)entityliving.moveForward));
		
		if(entityliving.isInWater()) {
			entityliving.setMotion(entityliving.getMotion().add(0.0D, (double)0.04F * entityliving.getAttribute(LivingEntity.SWIM_SPEED).getValue(), 0.0D));
		}
		
		}
	}
	
	protected void travel(Vec3d vec) {
		onMovement1();
	}
	
	protected void onMovement1() {
		
		if(entity instanceof PlayerEntity) {
			PlayerEntity entityplayer = (PlayerEntity) entity;
			
			
		
		if (entity.areEyesInFluid(FluidTags.WATER, true)) {
            int j = Math.round(MathHelper.sqrt(0 * 0 + 0 * 0 + 0 * 0) * 100.0F);
            if (j > 0) {
            	entityplayer.addStat(Stats.WALK_UNDER_WATER_ONE_CM, j);
            	entityplayer.addExhaustion(0.01F * (float)j * 0.01F);
            }
         } 
	   }
//
		
	}
	

}
