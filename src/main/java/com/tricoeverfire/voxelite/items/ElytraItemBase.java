package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class ElytraItemBase extends ElytraItem{
	
	public boolean Voxelresistence;
	public @Nullable Item turnToOnVoxelization;

	public ElytraItemBase(String name) {
		this(name,new Properties().group(ModItemGroups.voxelitemoditems).maxDamage(432).rarity(Rarity.UNCOMMON));

		
	}
	public ElytraItemBase(String name,Properties props) {
		super(props);
		
		setRegistryName(Main.location(name));
		this.Voxelresistence = false;
		this.turnToOnVoxelization = null;
		ModItems.ITEMS.add(this);

	
	}	
	public ElytraItemBase(String name, boolean doesvoxelize, @Nullable Item voxelizeTo) {
		this(name);
		this.Voxelresistence = doesvoxelize;
		this.turnToOnVoxelization = voxelizeTo;
	}

}
