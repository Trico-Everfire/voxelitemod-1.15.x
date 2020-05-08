package com.tricoeverfire.voxelite.enchantments;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class PowerHammerSwing extends Enchantment {

	public PowerHammerSwing(String name, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
		setRegistryName(Main.location(name));
		ModEnchantments.ENCHANTMENTS.add(this);
	}

	
	   public ITextComponent getDisplayName(int level) {
		      ITextComponent itextcomponent = new TranslationTextComponent(this.getName());

		      itextcomponent.applyTextStyle(TextFormatting.BLUE);

		      return itextcomponent;
		   }
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return false;
	}
	@Override
	public boolean canApply(ItemStack stack) {
	
		if(stack.getItem() == ModItems.AVOKINATE_HAMMER || stack.getItem() == ModItems.CHARGED_AVOKINATE_HAMMER) {
			return true;
		}
		
		return false;
	}
	
	
	
	 @Override
	public int getMaxLevel() {
		
		return 1;
	}
	
}
