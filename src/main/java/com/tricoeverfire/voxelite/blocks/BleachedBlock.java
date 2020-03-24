package com.tricoeverfire.voxelite.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BleachedBlock extends BlockBase{

	public BleachedBlock(String name, Material material) {
		super(name,material);
		
		setSoundType(SoundType.GLASS);
		setHardness(6.0F);
		setResistance(12.0F);
		setHarvestLevel("pickaxe",2);
		setLightLevel(0.6F);
		

	}

	
}
