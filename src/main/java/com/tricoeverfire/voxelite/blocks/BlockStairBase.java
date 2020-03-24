package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockStairBase extends StairsBlock {

	
	public BlockStairBase(String name, BlockState state,boolean unbreakable) {
		
		
		this(name,state,getProperty(state,unbreakable));
	}
	

	
	private static Properties  getProperty(BlockState state,boolean unbreakable) {
		Properties property = Block.Properties.create(state.getMaterial());
		if(unbreakable) {
			property.hardnessAndResistance(16000000);
		}
		
		
		return property;
	}

	

	public BlockStairBase(String name, BlockState state) {
		this(name,state,Block.Properties.create(state.getMaterial()));
	}
	
	@SuppressWarnings("deprecation")
	public BlockStairBase(String name,BlockState state, Properties properties) {
		super(state, properties);
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		
	}

}
