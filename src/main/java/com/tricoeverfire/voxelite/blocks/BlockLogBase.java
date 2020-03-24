package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockLogBase extends LogBlock {

	
	public BlockLogBase(String name) {
		this(name,MaterialColor.WOOD,Block.Properties.create(Material.WOOD));
	}
	
	
	public BlockLogBase(String name, MaterialColor verticalColorIn, Properties properties) {
		super(verticalColorIn, properties);
		setRegistryName(Main.location(name));
		
		FireBlock fire = (FireBlock) Blocks.FIRE;
		fire.setFireInfo(this, 5, 20);
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
	}



}
