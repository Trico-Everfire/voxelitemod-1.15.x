package com.tricoeverfire.voxelite.entities;

import com.tricoeverfire.voxelite.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoxivanEntity extends AgeableEntity{

	public VoxivanEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
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
		this.goalSelector.addGoal(1, new BreedGoal(this,1.0d));
	}

	
	
	
}
