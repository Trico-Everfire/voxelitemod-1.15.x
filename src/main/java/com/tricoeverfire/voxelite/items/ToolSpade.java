package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class ToolSpade extends ShovelItem{

	public ToolSpade(String name, IItemTier tier) {
		this(name, tier, 5,4);
	}
	
	public ToolSpade(String name, IItemTier tier , float maxdmg, float attackdmg) {
		super(tier, maxdmg, attackdmg, new Item.Properties().group(ModItemGroups.voxelitemoditems));
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	
}
