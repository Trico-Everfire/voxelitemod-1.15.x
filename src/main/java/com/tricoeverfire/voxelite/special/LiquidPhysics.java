package com.tricoeverfire.voxelite.special;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectUtils;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class LiquidPhysics {
	
	public final Random rand = new Random();
	public @Nullable Entity entity;
	public @Nullable Fluid fluid;
	public boolean inLiquid;
	private boolean firstTime = true;
	@SuppressWarnings("unused")
	private Tag<Fluid> unused = FluidTags.WATER;
	
//	public LiquidPhysics(Entity entity, Fluid fluidBlock) {
//		this(entity, fluidBlock.delegate);
//	}
//	
//	public LiquidPhysics(Entity entity, Supplier<? extends Fluid> fluidsblock) {
//		
//		this.entity = entity;
//		this.fluid = fluidsblock.get();
//	}
	

//	public LiquidPhysics(LivingEntity entity2, RegistryObject<FlowingFluidBlock> voxelfluidsblock) {
//		this(entity, fluid);
//	}

	public LiquidPhysics() {
		
	}

	public void init(Fluid fluid, Entity entity) {
	
		this.entity = entity;
		this.fluid = fluid;
		if(this.entity == null || this.fluid == null) {
			
		} else {
			tick();
		}
		
	
		
	}
	
	
	//logic check to see if the entity is truly inside this block.
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
	
	
	//tick handling, 
	private void tick() {
	//	System.out.println("inside the liquid");
		handleWaterMovement();
		if(entity instanceof LivingEntity) {
			LivingEntity entityliving = (LivingEntity) entity;
			
			travel(new Vec3d((double)entityliving.moveStrafing, (double)entityliving.moveVertical, (double)entityliving.moveForward));
			entity.setMotion(entityliving.getMotion().add(0.0D, (double)0.04F * entityliving.getAttribute(LivingEntity.SWIM_SPEED).getValue(), 0.0D));
		}
	//	travel(new Vec3d((double)entity.moveStrafing, (double)entityliving.moveVertical, (double)entityliving.moveForward));
		//travel();
		//}
	}
	
	
	   @SuppressWarnings("deprecation")
	public boolean handleFluidAcceleration() {
		      AxisAlignedBB axisalignedbb = entity.getBoundingBox().shrink(0.001D);
		      int i = MathHelper.floor(axisalignedbb.minX);
		      int j = MathHelper.ceil(axisalignedbb.maxX);
		      int k = MathHelper.floor(axisalignedbb.minY);
		      int l = MathHelper.ceil(axisalignedbb.maxY);
		      int i1 = MathHelper.floor(axisalignedbb.minZ);
		      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
		      if (!entity.world.isAreaLoaded(i, k, i1, j, l, j1)) {
		         return false;
		      } else {
		         double d0 = 0.0D;
		         boolean flag = entity.isPushedByWater();
		         boolean flag1 = false;
		         Vec3d vec3d = Vec3d.ZERO;
		         int k1 = 0;

		         try (BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
		            for(int l1 = i; l1 < j; ++l1) {
		               for(int i2 = k; i2 < l; ++i2) {
		                  for(int j2 = i1; j2 < j1; ++j2) {
		                     blockpos$pooledmutable.setPos(l1, i2, j2);
		                     IFluidState ifluidstate = entity.world.getFluidState(blockpos$pooledmutable);
		                     if (true) {
		                        double d1 = (double)((float)i2 + ifluidstate.getActualHeight(entity.world, blockpos$pooledmutable));
		                        if (d1 >= axisalignedbb.minY) {
		                           flag1 = true;
		                           d0 = Math.max(d1 - axisalignedbb.minY, d0);
		                           if (flag) {
		                              Vec3d vec3d1 = ifluidstate.getFlow(entity.world, blockpos$pooledmutable);
		                              if (d0 < 0.4D) {
		                                 vec3d1 = vec3d1.scale(d0);
		                              }

		                              vec3d = vec3d.add(vec3d1);
		                              ++k1;
		                           }
		                        }
		                     }
		                  }
		               }
		            }
		         }

		         if (vec3d.length() > 0.0D) {
		            if (k1 > 0) {
		               vec3d = vec3d.scale(1.0D / (double)k1);
		            }

		            if (!(entity instanceof PlayerEntity)) {
		               vec3d = vec3d.normalize();
		            }

		            entity.setMotion(entity.getMotion().add(vec3d.scale(0.014D)));
		         }
		        // System.out.println();
		         //entity.getSubmergedHeight()= d0;
		         return flag1;
		      }
		   }
	
	   protected void doWaterSplashEffect(SoundEvent splashSound, SoundEvent highSpeedSplashSound) {
		      Entity entityIn = entity.isBeingRidden() && entity.getControllingPassenger() != null ? entity.getControllingPassenger() : entity;
		      float f = entityIn == entity ? 0.2F : 0.9F;
		      Vec3d vec3d = entityIn.getMotion();
		      float f1 = MathHelper.sqrt(vec3d.x * vec3d.x * (double)0.2F + vec3d.y * vec3d.y + vec3d.z * vec3d.z * (double)0.2F) * f;
		      if (f1 > 1.0F) {
		         f1 = 1.0F;
		      }

		      if ((double)f1 < 0.25D) {
		    	  entity.playSound(splashSound, f1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
		      } else {
		    	  entity.playSound(highSpeedSplashSound, f1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
		      }

		      float f2 = (float)MathHelper.floor(entity.getPosY());

		      for(int i = 0; (float)i < 1.0F + entity.getSize(entity.getPose()).width * 20.0F; ++i) {
		         float f3 = (this.rand.nextFloat() * 2.0F - 1.0F) * entity.getSize(entity.getPose()).width;
		         float f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * entity.getSize(entity.getPose()).width;
		         entity.world.addParticle(ParticleTypes.BUBBLE, entity.getPosX() + (double)f3, (double)(f2 + 1.0F), entity.getPosZ() + (double)f4, vec3d.x, vec3d.y - (double)(this.rand.nextFloat() * 0.2F), vec3d.z);
		      }

		      for(int j = 0; (float)j < 1.0F + entity.getSize(entity.getPose()).width * 20.0F; ++j) {
		         float f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * entity.getSize(entity.getPose()).width;
		         float f6 = (this.rand.nextFloat() * 2.0F - 1.0F) * entity.getSize(entity.getPose()).width;
		         entity.world.addParticle(ParticleTypes.SPLASH, entity.getPosX() + (double)f5, (double)(f2 + 1.0F), entity.getPosZ() + (double)f6, vec3d.x, vec3d.y, vec3d.z);
		      }

		   }
	
	   public boolean handleWaterMovement() {
		      if (entity.getRidingEntity() instanceof BoatEntity) {
		    	  inLiquid = false;
		      } else if (handleFluidAcceleration()) {
		         if (!inLiquid && this.firstTime) {
		        	 this.firstTime = false;
		        	 doWaterSplashEffect(SoundEvents.ENTITY_GENERIC_SPLASH,SoundEvents.ENTITY_GENERIC_SPLASH);
		         }

		         entity.fallDistance = 0.0F;
		         inLiquid = true;
		         entity.extinguish();
		      } else {
		    	  inLiquid = false;
		      }

		      return entity.isInWater();
		   }
	protected void travel() {
		
	}
	protected void travel(Vec3d vec) {
		entity.fallDistance = entity.fallDistance / 4;
		onMovement1();
		DrowningLogic();
		handleWaterMovement();
	//	handleFluidAcceleration();
	}
	
	   protected int decreaseAirSupply(int air) {
		      int i = EnchantmentHelper.getRespirationModifier((LivingEntity) entity);
		      return i > 0 && this.rand.nextInt(i + 1) > 0 ? air : air - 1;
		   }
	
	protected void DrowningLogic() {
		boolean flag = entity instanceof PlayerEntity;
	
	      boolean flag1 = flag && ((PlayerEntity)this.entity).abilities.disableDamage;
	      if (this.entity.isAlive()) {
	    	//  System.out.println(areEyesInFluid(this.fluid, entity, false));
	         if (areEyesInFluid(this.fluid, entity, false) && this.entity.world.getBlockState(new BlockPos(this.entity.getPosX(), this.entity.getPosYEye(), this.entity.getPosZ())).getBlock() != Blocks.BUBBLE_COLUMN) {
	            if (!((LivingEntity) this.entity).canBreatheUnderwater() && !EffectUtils.canBreatheUnderwater((LivingEntity) this.entity) && !flag1) {
	               this.entity.setAir(decreaseAirSupply(this.entity.getAir()));
	               if (this.entity.getAir() == -20) {
	                  this.entity.setAir(0);
	                  Vec3d vec3d = this.entity.getMotion();

	                  for(int i = 0; i < 8; ++i) {
	                     float f = this.rand.nextFloat() - this.rand.nextFloat();
	                     float f1 = this.rand.nextFloat() - this.rand.nextFloat();
	                     float f2 = this.rand.nextFloat() - this.rand.nextFloat();
	                     this.entity.world.addParticle(ParticleTypes.BUBBLE, this.entity.getPosX() + (double)f, this.entity.getPosY() + (double)f1, this.entity.getPosZ() + (double)f2, vec3d.x, vec3d.y, vec3d.z);
	                  }

	                  this.entity.attackEntityFrom(DamageSource.DROWN, 2.0F);
	               }
	            }

	            if (!this.entity.world.isRemote && this.entity.isPassenger() && this.entity.getRidingEntity() != null && !this.entity.getRidingEntity().canBeRiddenInWater(this.entity)) {
	               this.entity.stopRiding();
	            }
	         } else if (this.entity.getAir() < this.entity.getMaxAir()) {
	        	 //Math.min(currentAir + 4, this.getMaxAir());
	            //this.entity.setAir((this.entity.getAir()));
	        	 this.entity.setAir(Math.min(this.entity.getAir() + 4, this.entity.getMaxAir()));
	         }

//	         if (!this.entity.world.isRemote) {
//	            BlockPos blockpos = new BlockPos(this.entity);
//	            if (!Objects.equal(this.entity.prevBlockpos, blockpos)) {
//	               this.entity.prevBlockpos = blockpos;
//	               ((LivingEntity) this.entity).frostWalk(blockpos);
//	            }
//	         }
	}
	}

	
	protected void onMovement1() {
		
		if(entity instanceof PlayerEntity) {
			PlayerEntity entityplayer = (PlayerEntity) entity;
		if (areEyesInFluid(this.fluid, entityplayer, true)) {
			double xlogic = entityplayer.getPosX() - entityplayer.getPosX();
			double ylogic = entityplayer.getPosY() - entityplayer.getPosY();
			double zlogic = entityplayer.getPosZ() - entityplayer.getPosZ();
            int j = Math.round(MathHelper.sqrt(xlogic * xlogic + ylogic * ylogic + zlogic * zlogic) * 100.0F);
            if (j > 0) {
            	entityplayer.addStat(Stats.WALK_UNDER_WATER_ONE_CM, j);
            	entityplayer.addExhaustion(0.01F * (float)j * 0.01F);
            }
         } 
	   }
//
		
	}
	
	   public boolean areEyesInFluid(Fluid fluid, Entity entity, boolean checkChunkLoaded) {
		      if (entity.getRidingEntity() instanceof BoatEntity) {
		         return false;
		      } else {
		         double d0 = entity.getPosYEye();
		         BlockPos blockpos = new BlockPos(entity.getPosX(), d0, entity.getPosZ());
		         if (checkChunkLoaded && !entity.world.chunkExists(blockpos.getX() >> 4, blockpos.getZ() >> 4)) {
		            return false;
		         } else {
		            IFluidState ifluidstate = entity.world.getFluidState(blockpos);
		            
		            //removed fluidtag check to allow custom fluids to have the same logic.
		            return d0 < (double)(blockpos.getY() + ifluidstate.getActualHeight(entity.world, blockpos) + 0.11111111F);
		         }
		      }
		   }
	

}
