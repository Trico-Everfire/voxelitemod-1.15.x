package com.tricoeverfire.voxelite.entities.pathnavigators;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BatPathNavigator extends GroundPathNavigator{

	public BatPathNavigator(MobEntity entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
		
	}

	public Path getPathToPos(BlockPos pos, int p_179680_2_) {
//	      if (this.world.getBlockState(pos).isAir()) {
//	         BlockPos blockpos;
//	         for(blockpos = pos.down(); blockpos.getY() > 0 && this.world.getBlockState(blockpos).isAir(); blockpos = blockpos.down()) {
//	            ;
//	         }
//
//	         if (blockpos.getY() > 0) {
//	            return super.getPathToPos(blockpos.up(), p_179680_2_);
//	         }
//
//	         while(blockpos.getY() < this.world.getHeight() && this.world.getBlockState(blockpos).isAir()) {
//	            blockpos = blockpos.up();
//	         }
//
//	         pos = blockpos;
//	      }

//	      if (!this.world.getBlockState(pos).getMaterial().isSolid()) {
//	         return super.getPathToPos(pos, p_179680_2_);
//	      } else {
	         BlockPos blockpos1;
	         for(blockpos1 = pos.up(); blockpos1.getY() < this.world.getHeight() && this.world.getBlockState(blockpos1).getMaterial().isSolid(); blockpos1 = blockpos1.up()) {
	            ;
	         }

	         return super.getPathToPos(blockpos1, p_179680_2_);
	    //  }
	   }
	
}
