package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.Item;

public class ItemBase extends Item{
	
	public boolean Voxelresistence;
	public @Nullable Item turnToOnVoxelization;


	

	
	
	
	public ItemBase(String name) {
		this(name,new Properties().group(ModItemGroups.voxelitemoditems));

		
	}
	public ItemBase(String name,Properties props) {
		super(props);
		
		setRegistryName(Main.location(name));
		this.Voxelresistence = false;
		this.turnToOnVoxelization = null;
		ModItems.ITEMS.add(this);

	
	}	
	public ItemBase(String name, boolean doesvoxelize, @Nullable Item voxelizeTo) {
		this(name);
		this.Voxelresistence = doesvoxelize;
		this.turnToOnVoxelization = voxelizeTo;
	}
	

	

}
