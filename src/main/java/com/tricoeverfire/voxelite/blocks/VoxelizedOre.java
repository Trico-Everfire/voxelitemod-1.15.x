package com.tricoeverfire.voxelite.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class VoxelizedOre extends BlockBase{

	public VoxelizedOre(String name,Material materialIn) {
		super(name,materialIn);

		setSoundType(SoundType.GLASS);
		setHardness(15.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe",2);
		setLightLevel(0.6F);
		

		
	}

	
	

}
