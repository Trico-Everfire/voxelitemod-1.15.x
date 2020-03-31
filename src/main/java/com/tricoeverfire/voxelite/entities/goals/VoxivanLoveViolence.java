package com.tricoeverfire.voxelite.entities.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class VoxivanLoveViolence extends Goal{
	
	private LivingEntity entity;


	VoxivanLoveViolence(LivingEntity entity){
	this.entity = entity;	
	}

	@Override
	public boolean shouldExecute() {
		
		return false;
	}
	
	
	   public boolean isBreedingItem(ItemStack stack) {
		      return stack.getItem() == Items.WHEAT;
		   }

//		   public boolean processInteract(PlayerEntity player, Hand hand) {
//		      ItemStack itemstack = player.getHeldItem(hand);
//		      if (this.isBreedingItem(itemstack)) {
//		         if (!this.world.isRemote && this.getGrowingAge() == 0 && this.canBreed()) {
//		            this.consumeItemFromStack(player, itemstack);
//		            this.setInLove(player);
//		            player.func_226292_a_(hand, true);
//		            return true;
//		         }
//
//		         if (this.isChild()) {
//		            this.consumeItemFromStack(player, itemstack);
//		            this.ageUp((int)((float)(-this.getGrowingAge() / 20) * 0.1F), true);
//		            return true;
//		         }
//		      }
//
//		      return super.processInteract(player, hand);
//		   }

}
