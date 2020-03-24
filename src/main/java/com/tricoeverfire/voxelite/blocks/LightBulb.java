package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LightBulb extends Block implements IWaterLoggable{
	
	
	private static boolean needsTransparency = false;
	
	public LightBulb(String name,Number num,Material material, boolean unbreaking, boolean transparent) {
		super(Block.Properties.create(material).notSolid().sound(SoundType.GLASS).hardnessAndResistance(15f).lightValue(setLightLevel(num)));
	
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		
	}
	
	public LightBulb(String name, Material material, boolean unbreaking, boolean transparent) {
		this(name,8,material,unbreaking, transparent);
		
		
	}
	
	public LightBulb(String name, Material material, boolean unbreakable) {
		this(name, 8, material, unbreakable,false);
		
	}


	public LightBulb(String name,Material material) {
		this(name,material,false);
		
	
	}
	

	protected static int setLightLevel(Number f) {
		
		int inter;
		
		//System.out.println((int)(f.floatValue() * 10f));
		if(f.floatValue() < 1) {
			inter = (int)(f.floatValue() * 10f);
		} else {
			inter = f.intValue();
		}
				
		
		//System.out.println(inter);
		
		return inter;
		
	}





	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		if(!needsTransparency) {
		return true;	
		} else {
		return false;
		}
	
	}
	
	
}
	

