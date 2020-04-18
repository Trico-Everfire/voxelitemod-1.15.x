package com.tricoeverfire.voxelite.init;

import java.util.ArrayList;

import com.tricoeverfire.voxelite.enchantments.AntiVoxeliteDepletion;
import com.tricoeverfire.voxelite.enchantments.PowerHammerSwing;
import com.tricoeverfire.voxelite.util.enummodhandler.ModEnchantmentType;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;

public class ModEnchantments {

	public static final ArrayList<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	public static final EquipmentSlotType[] ARMORLIST = new EquipmentSlotType[] {EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET};
	

	public static final Enchantment ANTIVOXELITEDEPLETION = new AntiVoxeliteDepletion("voxelite_depletion_resistance", Rarity.VERY_RARE,ModEnchantmentType.AVOKINATEGEAR, ARMORLIST);
	public static final Enchantment POWERHAMMERSWING = new PowerHammerSwing("super_hammer_swing", Rarity.VERY_RARE, ModEnchantmentType.HAMMER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND,EquipmentSlotType.OFFHAND} );

}
