package com.tricoeverfire.voxelite.items;

import net.minecraft.block.Block;

public class ItemBlock extends BlockItemBase{
	
	public ItemBlock(Block blockIn) {
		
		this(blockIn,getItemProperties(null));
	}
	

	public ItemBlock(Block blockIn, Properties builder) {
		super(blockIn, builder);
		
	}

}
