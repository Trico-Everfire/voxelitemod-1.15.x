package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.init.ModItemGroups;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class AvokinatePickaxe extends PickaxeItem {

	
	public AvokinatePickaxe(String name, IItemTier tier) {
		this(name, tier, 550,5);
	}
	
	public AvokinatePickaxe(String name, IItemTier tier , float maxdmg, float attackdmg) {
		super(tier, (int) maxdmg, attackdmg, new Item.Properties().group(ModItemGroups.voxelitemoditems));
		
	}

}
