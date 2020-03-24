package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockWallBase extends WallBlock {

	public BlockWallBase(String name, Block fromblock) {
		this(name, Block.Properties.from(fromblock));
	}
	
	public BlockWallBase(String name, Properties properties) {
		super(properties);
		setRegistryName(Main.location(name));
	
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		
	}
	
	


}
