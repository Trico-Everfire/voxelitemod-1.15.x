package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolHoe extends HoeItem {

	public ToolHoe(String name, IItemTier tier) {
		this(name, tier, 4,5);
	}
	
	public ToolHoe(String name, IItemTier tier , float maxdmg, float attackdmg) {
		super(tier, maxdmg, new Item.Properties().group(ModItemGroups.voxelitemoditems));
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}

}
