package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModDamageSource;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ToolHammer extends ItemBase {

	public ToolHammer(String name, Properties props) {
		super(name, props);
		// TODO Auto-generated constructor stub
	}

	public ToolHammer(String name) {
		this(name,new Properties().group(ModItemGroups.voxelitemoditems));

	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		
		if(stack.getItem() == ModItems.SUPER_CHARGED_AVOKINATE_HAMMER || EnchantmentHelper.getEnchantmentLevel(ModEnchantments.POWERHAMMERSWING, stack) > 0) {
			
			for(LivingEntity entity : worldIn.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().expand(4, 0, 4).offset(2, 0, 2))) {
				if(entity != playerIn) {
					
					
				entity.attackEntityFrom(ModDamageSource.HAMMERSLAM, 6);	
			//	worldIn.playSound(playerIn, pos, soundIn, category, volume, pitch);
				
				}
			}
			
			
			ActionResult.resultSuccess(stack);
		}
		
		
		return ActionResult.resultFail(stack);
	}

}
