package com.tricoeverfire.voxelite.init;

import java.util.ArrayList;

import com.tricoeverfire.voxelite.enchantments.AntiVoxeliteDepletion;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ModEnchantments {

	public static final ArrayList<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	public static final EquipmentSlotType[] ARMORLIST = new EquipmentSlotType[] {EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET};
	

	public static final Enchantment ANTIVOXELITEDEPLETION = new AntiVoxeliteDepletion("voxelite_depletion_resistance", Rarity.VERY_RARE, EnchantmentType.ARMOR,ARMORLIST );
	

}
