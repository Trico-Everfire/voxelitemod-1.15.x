package com.tricoeverfire.voxelite.entities;

import java.util.EnumSet;

import com.tricoeverfire.voxelite.entities.pathnavigators.BatPathNavigator;
import com.tricoeverfire.voxelite.init.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class SkelebatEntity extends BatEntity implements IMob{
	
	private BlockPos spawnPosition;
	private static final EntityPredicate playerPredicate = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
	
	public SkelebatEntity(EntityType<? extends BatEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new BatMovementController(this);
		this.navigator = new BatPathNavigator(this, worldIn);
	}

	
	   protected boolean shouldBurnInDay() {
		      return true;
		   }
	@Override
		protected float getJumpFactor() {
	      float f = this.world.getBlockState(new BlockPos(this)).getBlock().getJumpFactor();
	      float f1 = this.world.getBlockState(this.getPositionUnderneath()).getBlock().getJumpFactor();
	      return (double)f == 2.5D ? f1 : f;
		}
	@Override
	public void livingTick() {
		super.livingTick();
		
		
	      if (this.isAlive()) {
	          boolean flag = this.shouldBurnInDay() && this.isInDaylight();
	          if (flag) {
	             ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
	             if (!itemstack.isEmpty()) {
	                if (itemstack.isDamageable()) {
	                   itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
	                   if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
	                      this.sendBreakAnimation(EquipmentSlotType.HEAD);
	                      this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
	                   }
	                }

	                flag = false;
	             }

	             if (flag) {
	                this.setFire(8);
	             }
	          
	          }
	      }
	}
	
	   protected void registerAttributes() {
		      super.registerAttributes();
		      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
		   }
	   
	   @Override
	protected void updateAITasks() {
		  
		   BlockPos blockpos = new BlockPos(this);
		      BlockPos blockpos1 = blockpos.up();
		      if (this.getIsBatHanging()) {
		         if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
		            if (this.rand.nextInt(200) == 0) {
		               this.rotationYawHead = (float)this.rand.nextInt(360);
		            }

		            if (this.world.getClosestPlayer(playerPredicate, this) != null) {
		               this.setIsBatHanging(false);
		            //   this.world.playEvent((PlayerEntity)null, 1025, blockpos, 0);
			            if(this.world.isRemote()) {
				            ClientWorld cWorld = (ClientWorld) this.world;
				            cWorld.playSound(blockpos, ModSounds.SKELEBAT_TAKEOFF.get(), SoundCategory.HOSTILE, 0.05F, (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F + 1.0F, false);
				            }		           
			            }
		         } else {
		            this.setIsBatHanging(false);
		           // this.world.playEvent((PlayerEntity)null, 1025, blockpos, 0);
		            if(this.world.isRemote()) {
		            ClientWorld cWorld = (ClientWorld) this.world;
		            cWorld.playSound(blockpos, ModSounds.SKELEBAT_TAKEOFF.get(), SoundCategory.HOSTILE, 0.05F, (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F + 1.0F, false);
		            }
		         }
		      } else {
		         if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
		            this.spawnPosition = null;
		         }

		         if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
		            this.spawnPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.getPosY() + (double)this.rand.nextInt(6) - 2.0D, this.getPosZ() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
		         }

		         double d0 = (double)this.spawnPosition.getX() + 0.5D - this.getPosX();
		         double d1 = (double)this.spawnPosition.getY() + 0.1D - this.getPosY();
		         double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.getPosZ();
		         Vec3d vec3d = this.getMotion();
		         Vec3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double)0.1F, (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.1F, (Math.signum(d2) * 0.5D - vec3d.z) * (double)0.1F);
		         Vec3d vec3d2 = vec3d.add(0,  (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.1F, 0);
		         Vec3d vec3d3 = vec3d.add(0,0,0);
		         if(this.getAttackTarget() == null) { this.setMotion(vec3d1); }else { this.setMotion(vec3d2);};
		          
		         float f = (float)(MathHelper.atan2(vec3d1.z, vec3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
		         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
		         this.moveForward = 0.5F;
		         this.rotationYaw += f1;
		         if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
		            this.setIsBatHanging(true);
		         }
		      }
		   
	}
	   public class BatHanging extends Goal{

		@Override
		public boolean shouldExecute() {
			
			return false;
		}
		   
	   }
	   
	   /*
	    work from this :)
	     
	   BlockPos blockpos = new BlockPos(this);
		      BlockPos blockpos1 = blockpos.up();
		      if (this.getIsBatHanging()) {
		         if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
		            if (this.rand.nextInt(200) == 0) {
		               this.rotationYawHead = (float)this.rand.nextInt(360);
		            }

		            if (this.world.getClosestPlayer(playerPredicate, this) != null) {
		               this.setIsBatHanging(false);
		               this.world.playEvent((PlayerEntity)null, 1025, blockpos, 0);
		            }
		         } else {
		            this.setIsBatHanging(false);
		            this.world.playEvent((PlayerEntity)null, 1025, blockpos, 0);
		         }
		      } else {
		         if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
		            this.spawnPosition = null;
		         }

		         if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
		            this.spawnPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.getPosY() + (double)this.rand.nextInt(6) - 2.0D, this.getPosZ() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
		         }

		         double d0 = (double)this.spawnPosition.getX() + 0.5D - this.getPosX();
		         double d1 = (double)this.spawnPosition.getY() + 0.1D - this.getPosY();
		         double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.getPosZ();
		         Vec3d vec3d = this.getMotion();
		         Vec3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double)0.1F, (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.1F, (Math.signum(d2) * 0.5D - vec3d.z) * (double)0.1F);
		         Vec3d vec3d2 = vec3d.add(0,  (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.1F, 0);
		         Vec3d vec3d3 = vec3d.add(0,0,0);
		         if(this.getAttackTarget() == null) { this.setMotion(vec3d1); }else { this.setMotion(vec3d2);};
		          
		         float f = (float)(MathHelper.atan2(vec3d1.z, vec3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
		         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
		         this.moveForward = 0.5F;
		         this.rotationYaw += f1;
		         if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
		            this.setIsBatHanging(true);
		         }
		      }
	    */
	   
	   @Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO Auto-generated method stub
		return super.attackEntityFrom(source, amount);
	}
	   
	   public boolean attackEntityAsMob(Entity entityIn) {
		      boolean flag = super.attackEntityAsMob(entityIn);
		      if (flag) {
		         float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
		         if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F) {
		            entityIn.setFire(2 * (int)f);
		         }
		      }

		      return flag;
		   }
	   
	   @Override
		public boolean canBePushed() {
			return true;
		}
	   
	   
	   
	 @Override
	protected void registerGoals() {
		super.registerGoals();
//		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		
		 	this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	        this.goalSelector.addGoal(8, new BatLookAtGoal(this, PlayerEntity.class, 8.0F));
	        this.goalSelector.addGoal(0, new BatAttackGoal(this, 0.3D, true));
	        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
	        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, false));
	        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PigEntity.class, false));

		
	}
	 
	 public class BatLookAtGoal extends Goal{
		 protected final MobEntity entity;
		   protected Entity closestEntity;
		   protected final float maxDistance;
		   private int lookTime;
		   protected final float chance;
		   protected final Class<? extends LivingEntity> watchedClass;
		   protected final EntityPredicate entityPredicate;

		   public BatLookAtGoal(MobEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {
		      this(entityIn, watchTargetClass, maxDistance, 0.02F);
		   }

		   public BatLookAtGoal(MobEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance, float chanceIn) {
		      this.entity = entityIn;
		      this.watchedClass = watchTargetClass;
		      this.maxDistance = maxDistance;
		      this.chance = chanceIn;
		      this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		      if (watchTargetClass == PlayerEntity.class) {
		         this.entityPredicate = (new EntityPredicate()).setDistance((double)maxDistance).allowFriendlyFire().allowInvulnerable().setSkipAttackChecks().setCustomPredicate((p_220715_1_) -> {
		            return EntityPredicates.notRiding(entityIn).test(p_220715_1_);
		         });
		      } else {
		         this.entityPredicate = (new EntityPredicate()).setDistance((double)maxDistance).allowFriendlyFire().allowInvulnerable().setSkipAttackChecks();
		      }

		   }

		   /**
		    * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		    * method as well.
		    */
		   public boolean shouldExecute() {
		      if (this.entity.getRNG().nextFloat() >= this.chance) {
		         return false;
		      } else {
		         if (this.entity.getAttackTarget() != null) {
		            this.closestEntity = this.entity.getAttackTarget();
		         }

		         if (this.watchedClass == PlayerEntity.class) {
		            this.closestEntity = this.entity.world.getClosestPlayer(this.entityPredicate, this.entity, this.entity.getPosX(), this.entity.getPosYEye(), this.entity.getPosZ());
		         } else {
		            this.closestEntity = this.entity.world.func_225318_b(this.watchedClass, this.entityPredicate, this.entity, this.entity.getPosX(), this.entity.getPosYEye(), this.entity.getPosZ(), this.entity.getBoundingBox().grow((double)this.maxDistance, 3.0D, (double)this.maxDistance));
		         }

		         return this.closestEntity != null;
		      }
		   }

		   /**
		    * Returns whether an in-progress EntityAIBase should continue executing
		    */
		   public boolean shouldContinueExecuting() {
		      if (!this.closestEntity.isAlive()) {
		         return false;
		      } else if (this.entity.getDistanceSq(this.closestEntity) > (double)(this.maxDistance * this.maxDistance)) {
		         return false;
		      } else {
		         return this.lookTime > 0;
		      }
		   }

		   /**
		    * Execute a one shot task or start executing a continuous task
		    */
		   public void startExecuting() {
		      this.lookTime = 40 + this.entity.getRNG().nextInt(40);
		   }

		   /**
		    * Reset the task's internal state. Called when this task is interrupted by another one
		    */
		   public void resetTask() {
		      this.closestEntity = null;
		   }

		   /**
		    * Keep ticking a continuous task that has already been started
		    */
		   public void tick() {
			  //this.entity.world.addParticle(ParticleTypes.PORTAL, this.closestEntity.getPosX(), this.closestEntity.getPosYEye(), this.closestEntity.getPosZ(), 0, 0, 0);
		      this.entity.getLookController().setLookPosition((this.closestEntity.getPosX() ), this.closestEntity.getPosYEye(), ( this.closestEntity.getPosZ()));
		      --this.lookTime;
		   }
		 
	 }

	 public class BatMovementController extends MovementController{

		   public BatMovementController(MobEntity mob) {   
			super(mob);

		   }

		   public boolean isUpdating() {
		      return this.action == MovementController.Action.MOVE_TO;
		   }

		   public double getSpeed() {
		      return this.speed;
		   }

		   /**
		    * Sets the speed and location to move to
		    */
		   public void setMoveTo(double x, double y, double z, double speedIn) {
		      this.posX = x;
		      this.posY = y;
		      this.posZ = z;
		      this.speed = speedIn;
		      if (this.action != MovementController.Action.JUMPING) {
		         this.action = MovementController.Action.MOVE_TO;
		      }

		   }

		   public void strafe(float forward, float strafe) {
		      this.action = MovementController.Action.STRAFE;
		      this.moveForward = forward;
		      this.moveStrafe = strafe;
		      this.speed = 0.25D;
		   }

		   public void tick() {
		      if (this.action == MovementController.Action.STRAFE) {
		         float f = (float)this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
		         float f1 = (float)this.speed * f;
		         float f2 = this.moveForward;
		         float f3 = this.moveStrafe;
		         float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
		         if (f4 < 1.0F) {
		            f4 = 1.0F;
		         }

		         f4 = f1 / f4;
		         f2 = f2 * f4;
		         f3 = f3 * f4;
		         float f5 = MathHelper.sin(this.mob.rotationYaw * ((float)Math.PI / 180F));
		         float f6 = MathHelper.cos(this.mob.rotationYaw * ((float)Math.PI / 180F));
		         float f7 = f2 * f6 - f3 * f5;
		         float f8 = f3 * f6 + f2 * f5;
		         PathNavigator pathnavigator = this.mob.getNavigator();
		         if (pathnavigator != null) {
		            NodeProcessor nodeprocessor = pathnavigator.getNodeProcessor();
		            if (nodeprocessor != null && nodeprocessor.getPathNodeType(this.mob.world, MathHelper.floor(this.mob.getPosX() + (double)f7), MathHelper.floor(this.mob.getPosY()), MathHelper.floor(this.mob.getPosZ() + (double)f8)) != PathNodeType.WALKABLE) {
		               this.moveForward = 1.0F;
		               this.moveStrafe = 0.0F;
		               f1 = f;
		            }
		         }

		         this.mob.setAIMoveSpeed(f1);
		         this.mob.setMoveForward(this.moveForward);
		         this.mob.setMoveStrafing(this.moveStrafe);
		         this.action = MovementController.Action.WAIT;
		      } else if (this.action == MovementController.Action.MOVE_TO) {
		         this.action = MovementController.Action.WAIT;
		         double d0 = this.posX - this.mob.getPosX();
		         double d1 = this.posZ - this.mob.getPosZ();
		         double d2 = this.posY - this.mob.getPosY();
		         double d3 = d0 * d0 + d2 * d2 + d1 * d1;
		         if (d3 < (double)2.5000003E-7F) {
		            this.mob.setMoveForward(0.0F);
		            return;
		         }

		         float f9 = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
		         this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, f9, 90.0F);
		         this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
		         BlockPos blockpos = new BlockPos(this.mob);
		         BlockState blockstate = this.mob.world.getBlockState(blockpos);
		         Block block = blockstate.getBlock();
		         VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.world, blockpos);
		         if (d2 > (double)this.mob.stepHeight && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getWidth()) || !voxelshape.isEmpty() && this.mob.getPosY() < voxelshape.getEnd(Direction.Axis.Y) + (double)blockpos.getY() && !block.isIn(BlockTags.DOORS) && !block.isIn(BlockTags.FENCES)) {
		            this.mob.getJumpController().setJumping();
		            this.action = MovementController.Action.JUMPING;
		         }
		      } else if (this.action == MovementController.Action.JUMPING) {
		         this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
		         if (this.mob.onGround) {
		           this.action = MovementController.Action.WAIT;
		         }
		      } else {
		         this.mob.setMoveForward(0.0F);
		      }

		   }

		   /**
		    * Attempt to rotate the first angle to become the second angle, but only allow overall direction change to at max be
		    * third parameter
		    */
		   protected float limitAngle(float sourceAngle, float targetAngle, float maximumChange) {
		      float f = MathHelper.wrapDegrees(targetAngle - sourceAngle);
		      if (f > maximumChange) {
		         f = maximumChange;
		      }

		      if (f < -maximumChange) {
		         f = -maximumChange;
		      }

		      float f1 = sourceAngle + f;
		      if (f1 < 0.0F) {
		         f1 += 360.0F;
		      } else if (f1 > 360.0F) {
		         f1 -= 360.0F;
		      }

		      return f1;
		   }

		   public double getX() {
		      return this.posX;
		   }

		   public double getY() {
		      return this.posY;
		   }

		   public double getZ() {
		      return this.posZ;
		   }

		 
	 }
	 
	 public class MeleeAttackModifiedGoal extends Goal {
		    protected final SkelebatEntity attacker;
		    protected int attackTick;
		    private final double speedTowardsTarget;
		    private final boolean longMemory;
		    private Path path;
		    private int delayCounter;
		    private double targetX;
		    private double targetY;
		    private double targetZ;
		    protected final int attackInterval = 20;
		    private long gameTimer;
		    private int failedPathFindingPenalty = 0;
		    private boolean canPenalize = false;

		    public MeleeAttackModifiedGoal(SkelebatEntity creature, double speedIn, boolean useLongMemory) {
		        this.attacker = creature;
		        this.speedTowardsTarget = speedIn;
		        this.longMemory = useLongMemory;
		        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		    }

		    /**
		     * Returns whether the EntityAIBase should begin execution.
		     */
		    public boolean shouldExecute() {
		        long i = this.attacker.world.getGameTime();
		        if (i - this.gameTimer < 20L) {
		            return false;
		        } else {
		            this.gameTimer = i;
		            LivingEntity livingentity = this.attacker.getAttackTarget();
		            if (livingentity == null) {
		                return false;
		            } else if (!livingentity.isAlive()) {
		                return false;
		            } else {
		                if (canPenalize) {
		                    if (--this.delayCounter <= 0) {
		                        this.path = this.attacker.getNavigator().getPathToEntity(livingentity, 0);
		                        this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
		                        return this.path != null;
		                    } else {
		                        return true;
		                    }
		                }
		                this.path = this.attacker.getNavigator().getPathToEntity(livingentity, 0);
		                if (this.path != null) {
		                    return true;
		                } else {
		                    return this.getAttackReachSqr(livingentity) >= this.attacker.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
		                }
		            }
		        }
		    }

		    /**
		     * Returns whether an in-progress EntityAIBase should continue executing
		     */
		    public boolean shouldContinueExecuting() {
		        LivingEntity livingentity = this.attacker.getAttackTarget();
		        if (livingentity == null) {
		            return false;
		        } else if (!livingentity.isAlive()) {
		            return false;
		        } else if (!this.longMemory) {
		            return !this.attacker.getNavigator().noPath();
		        } else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(livingentity))) {
		            return false;
		        } else {
		            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
		        }
		    }

		    /**
		     * Execute a one shot task or start executing a continuous task
		     */
		    public void startExecuting() {
		        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
		        this.attacker.setAggroed(true);
		        this.delayCounter = 0;
		    }

		    /**
		     * Reset the task's internal state. Called when this task is interrupted by another one
		     */
		    public void resetTask() {
		        LivingEntity livingentity = this.attacker.getAttackTarget();
		        if (!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
		            this.attacker.setAttackTarget((LivingEntity)null);
		        }

		        this.attacker.setAggroed(false);
		        this.attacker.getNavigator().clearPath();
		    }

		    /**
		     * Keep ticking a continuous task that has already been started
		     */
		    public void tick() {
		        LivingEntity livingentity = this.attacker.getAttackTarget();
		        this.attacker.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
		        double d0 = this.attacker.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
		        --this.delayCounter;
		        if ((this.longMemory || this.attacker.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
		            this.targetX = livingentity.getPosX();
		            this.targetY = livingentity.getPosY();
		            this.targetZ = livingentity.getPosZ();
		            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
		            if (this.canPenalize) {
		                this.delayCounter += failedPathFindingPenalty;
		                if (this.attacker.getNavigator().getPath() != null) {
		                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
		                    if (finalPathPoint != null && livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
		                        failedPathFindingPenalty = 0;
		                    else
		                        failedPathFindingPenalty += 10;
		                } else {
		                    failedPathFindingPenalty += 10;
		                }
		            }
		            if (d0 > 1024.0D) {
		                this.delayCounter += 10;
		            } else if (d0 > 256.0D) {
		                this.delayCounter += 5;
		            }

		            if (!this.attacker.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
		                this.delayCounter += 15;
		            }
		        }

		        this.attackTick = Math.max(this.attackTick - 1, 0);
		        this.checkAndPerformAttack(livingentity, d0);
		    }

		    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		        double d0 = this.getAttackReachSqr(enemy);
		        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
		            this.attackTick = 20;
		            this.attacker.swingArm(Hand.MAIN_HAND);
		            this.attacker.attackEntityAsMob(enemy);
		        }

		    }

		    protected double getAttackReachSqr(LivingEntity attackTarget) {
		        return (double)(this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
		    }
		}
	 
	 public class BatAttackGoal extends MeleeAttackModifiedGoal {
		    private final SkelebatEntity bat;

		    public BatAttackGoal(SkelebatEntity bat, double speed, boolean useLongMemory) {
		        super(bat, speed, useLongMemory);
		        this.bat = bat;
		    }

		    /**
		     * Execute a one shot task or start executing a continuous task
		     */
		    public void startExecuting() {
		        super.startExecuting();
		    }

		    /**
		     * Reset the task's internal state. Called when this task is interrupted by another one
		     */
		    public void resetTask() {
		        super.resetTask();
		        this.bat.setAggroed(false);
		    }

		    /**
		     * Keep ticking a continuous task that has already been started
		     */
		    public void tick() {
		        super.tick();
		    
		    }
	 }
	
	   public CreatureAttribute getCreatureAttribute() {
		      return CreatureAttribute.UNDEAD;
		   }

}
