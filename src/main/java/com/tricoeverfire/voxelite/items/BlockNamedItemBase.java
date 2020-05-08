package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;

public class BlockNamedItemBase extends BlockNamedItem {

	public BlockNamedItemBase(String name, Block blockIn, Properties properties) {
		super(blockIn, properties);
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}

}
