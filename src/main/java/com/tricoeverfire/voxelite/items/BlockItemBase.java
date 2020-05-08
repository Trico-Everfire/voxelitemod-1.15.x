package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemBase extends BlockItem{

	
	public static Properties changeItemProperties(Properties properties) {
		return properties;
	}
	
	public static Properties getItemProperties(@Nullable Properties Builder) {
		Properties props = new Properties();
		if(Builder != null) {
			props = Builder;
		} else {
			props.group(ModItemGroups.voxelitemodblocks);
		
		}
		
		props = changeItemProperties(props);
		
		return props;
		
		
	}
	
	
	
	public BlockItemBase(Block blockIn, @Nullable Properties builder) {
		super(blockIn, getItemProperties(builder));

	}

}
