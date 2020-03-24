package com.tricoeverfire.voxelite.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AvokinateBlock extends BlockBase{

	public AvokinateBlock(String name, Material material) {
		super(name,material);
		setSoundType(SoundType.GLASS);
		setHardness(15.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe",2);
		setLightLevel(0.7F);
		//this.recolorBlock(world, pos, side, color)

	}


}
