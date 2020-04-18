package com.tricoeverfire.voxelite.special;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

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
		
		
		
		if(isEntityInBlock(entity,fluid.getDefaultState().getBlockState().getBlock())) {
			tick();
		}
		
	}
	
	public boolean isEntityInBlock(Entity entity, Block block) {
		if (entity.noClip) {
	         return false;
	      } else {
	         try (BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
	            for(int i = 0; i < 8; ++i) {
	               int j = MathHelper.floor(entity.getPosY() + (double)(((float)((i >> 0) % 2) - 0.5F) * 0.1F) + (double)entity.getEyeHeight());
	               int k = MathHelper.floor(entity.getPosX() + (double)(((float)((i >> 1) % 2) - 0.5F) * entity.getSize(entity.getPose()).width * 0.8F));
	               int l = MathHelper.floor(entity.getPosZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * entity.getSize(entity.getPose()).width * 0.8F));
	               if (blockpos$pooledmutable.getX() != k || blockpos$pooledmutable.getY() != j || blockpos$pooledmutable.getZ() != l) {
	                  blockpos$pooledmutable.setPos(k, j, l);
	                  if (entity.world.getBlockState(blockpos$pooledmutable) == block.getDefaultState()) {
	                     boolean flag = true;
	                     return flag;
	                  }
	               }
	            }

	            return false;
	         }
	      }
		
	}
	
	protected void tick() {
		System.out.println("inside the liquid");
		
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
