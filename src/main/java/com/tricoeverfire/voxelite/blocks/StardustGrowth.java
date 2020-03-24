package com.tricoeverfire.voxelite.blocks;

import java.util.Random;

import com.tricoeverfire.voxelite.init.ModParticles;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StardustGrowth extends BlockBase {

	public StardustGrowth(String name, Material material) {
		super(name,material);
		// TODO Auto-generated constructor stub
	}
	
	   @OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      super.animateTick(stateIn, worldIn, pos, rand);
	      if (rand.nextInt(5) == 0) {
	         worldIn.addParticle(ModParticles.STARDUST_GROWTH_BLOCK_PARTICLE.get(), (double)pos.getX() + (double)rand.nextFloat(), (double)pos.getY() + 1.1D, (double)pos.getZ() + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D);
	      }

	   }

}
