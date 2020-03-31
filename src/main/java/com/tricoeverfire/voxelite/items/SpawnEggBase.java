package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;

public class SpawnEggBase extends SpawnEggItem {

	public SpawnEggBase(String name ,EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
		super(typeIn, primaryColorIn, secondaryColorIn, builder);
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
		
	}

}
