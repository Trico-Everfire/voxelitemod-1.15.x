package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class IronFence extends FenceBlock{

	public IronFence(String name) {
		this(name, Block.Properties.create(Material.IRON));
	}
	
	public IronFence(String name, Material material) {
		this(name, Block.Properties.create(material).notSolid());
	}

	
	public IronFence(String name, Properties properties) {
		super(properties);
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
	}
	
	
	
	

}
