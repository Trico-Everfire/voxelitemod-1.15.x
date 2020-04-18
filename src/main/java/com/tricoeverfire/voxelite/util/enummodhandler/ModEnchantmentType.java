package com.tricoeverfire.voxelite.util.enummodhandler;

import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.items.ToolHammer;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;

public class ModEnchantmentType {

	public static final EnchantmentType HAMMER = EnchantmentType.create("HAMMER_TYPE", (item)->{
		return (item instanceof ToolHammer) && (item != ModItems.SUPER_CHARGED_AVOKINATE_HAMMER);
	});
	public static final EnchantmentType AVOKINATEGEAR = EnchantmentType.create("AVOKINATE_GEAR_TYPE", (item)->{

		for(Item armorPiece :ModItems.AVOKINATE_ARMOR) {
			if(armorPiece == item) {
				return true;
			}
		}
		return false;
				});
	
}
