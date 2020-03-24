package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockLeafBase extends LeavesBlock{
	
	public BlockLeafBase(String name, Material material) {
		this(name, Block.Properties.create(material).notSolid().sound(SoundType.PLANT));
	}

	public BlockLeafBase(String name,Properties properties) {
		super(properties);
		setRegistryName(Main.location(name));
		
		FireBlock fire = (FireBlock) Blocks.FIRE;
		fire.setFireInfo(this, 5, 30);
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		
	}

}
