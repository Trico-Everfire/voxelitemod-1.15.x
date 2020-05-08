package com.tricoeverfire.voxelite.items;

import java.util.List;

import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AvokinateCrystal extends ItemBase{
	
	private boolean isvalid = false;

	public AvokinateCrystal(String name) {
		super(name);
	
		
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("Right Click to remove Micro Damage (MD) from Avokinate Armor pieces"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		List<ItemStack> list =  (List<ItemStack>) playerIn.getArmorInventoryList();
		
		isvalid = false;
		
		list.forEach((its)->{
			for(Item h : ModItems.AVOKINATE_ARMOR) {
				if(h == its.getItem() && its.getOrCreateTag().getFloat("microdamage") != 0.0f) {
					
					its.getOrCreateTag().putFloat("microdamage", 0.0f);;
					if(!playerIn.isCreative()) playerIn.getHeldItem(handIn).shrink(1);
					isvalid = true;
					
				}
			}
		
			
		});
		
		return isvalid ? ActionResult.resultSuccess(playerIn.getHeldItem(handIn)) : ActionResult.resultPass(playerIn.getHeldItem(handIn)) ;
		//return ActionResult.resultPass(playerIn.getHeldItem(handIn));//super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
