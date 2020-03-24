package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ToolSword extends SwordItem{

	public ToolSword(String name, IItemTier tier) {
		this(name, tier, 550,7);
	}
	
	public ToolSword(String name, IItemTier tier , float maxdmg, float attackdmg) {
		super(tier, (int) maxdmg, attackdmg, new Item.Properties().group(ModItemGroups.voxelitemoditems));
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	
}
