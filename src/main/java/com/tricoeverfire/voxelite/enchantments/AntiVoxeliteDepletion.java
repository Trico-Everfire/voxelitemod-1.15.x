package com.tricoeverfire.voxelite.enchantments;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AntiVoxeliteDepletion extends Enchantment{

	public AntiVoxeliteDepletion(String name, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
		setRegistryName(Main.location(name));
		ModEnchantments.ENCHANTMENTS.add(this);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		// TODO Auto-generated method stub
		return enchantmentLevel + 11;
	}
	@Override
	public boolean canApply(ItemStack stack) {
	
		for(Item stacky : ModItems.AVOKINATE_ARMOR) {
			if(stacky == stack.getItem()) {
				return true;
			}
		}
		
		
		return false;
	}
	
	 @Override
	public int getMaxLevel() {
		
		return 3;
	}
}
