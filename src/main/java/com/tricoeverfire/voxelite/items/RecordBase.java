package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public class RecordBase extends ItemBase{

	public boolean Voxelresistence;
	public @Nullable Item turnToOnVoxelization;
	
	public RecordBase(String name, SoundEvent soundIn) {
		super(name);

		//setCreativeTab(Main.voxilitemod);

		this.Voxelresistence = false;
		this.turnToOnVoxelization = null;
		ModItems.ITEMS.add(this);
	}
	

}
