package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockPillarBase extends RotatedPillarBlock {
	
	public BlockPillarBase(String name, Block fromblock) {
		this(name, Block.Properties.from(fromblock));
	}
	

	public BlockPillarBase(String name, Properties properties) {
		super(properties);
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
	
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
	}


	public BlockPillarBase(String string, Material rock, boolean unbreakable) {
		this(string,getProperty(rock,unbreakable));
	}

	private static Properties  getProperty(Material material,boolean unbreakable) {
		Properties property = Block.Properties.create(material);
		if(unbreakable) {
			property.hardnessAndResistance(16000000);
		}
	return property;
	}
	
}
