package com.tricoeverfire.voxelite.entities;

import com.tricoeverfire.voxelite.init.ModEntities;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoxivanEntity extends AnimalEntity{

	public VoxivanEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		VoxivanEntity entity = new VoxivanEntity(ModEntities.VOXIVAN_ENTITY.get(),this.world);
		entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
		
		return entity;
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(0, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0d));
		this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.0f));
		this.goalSelector.addGoal(2, new FollowMobGoal(this, 1.0f, 5.0f, 8.0f));
		
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return stack.getItem() == ModItems.VOXELIZEDBREAD;
	}
	
	
}
