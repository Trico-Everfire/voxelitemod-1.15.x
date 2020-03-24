package com.tricoeverfire.voxelite.blocks;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.items.ItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BlockFallingBase extends FallingBlock{

	private static String name;
	protected static SoundType sound;
	private static float hardness = -42.5f;
	private static float resistance = -42.5f;
	private static String harvestleveltooltype;
	private static int harvestlevellevel = -42;
	private static int lightlevel = -42;
	private static Properties blockprops;
	private static int LightOpacity = -42;
	 
	public BlockFallingBase(String name, Material material) {
		
		super( getBlockProperties(material,false));
		BlockFallingBase.name = name;
		setRegistryName(Main.location(name));
		setSoundType(SoundType.GROUND);
		
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	
protected void setLightLevel(Number f) {
		
		int inter;
		
		//System.out.println((int)(f.floatValue() * 10f));
		if(f.floatValue() < 1) {
			inter = (int)(f.floatValue() * 10f);
		} else {
			inter = f.intValue();
		}
				
		
		//System.out.println(inter);
		
		BlockFallingBase.lightlevel = inter;
		
	}



	protected void setHarvestLevel(String string, int i) {
		BlockFallingBase.harvestleveltooltype = string;
		BlockFallingBase.harvestlevellevel = i;
		
	}

	protected void setResistance(float f) {
		BlockFallingBase.resistance = f;
		
	}

	protected void setHardness(float f) {
		BlockFallingBase.hardness = f;
		
	}
	
	protected void setLightOpacity(int i) {
		LightOpacity = i;
		
	}

	
	public SoundType getSoundType() {
		return BlockFallingBase.sound;
	}
	
	public int getLightLevel() {
		
		return (int) BlockFallingBase.lightlevel;
	}

	public String getHavestLevelToolType() {
		return BlockFallingBase.harvestleveltooltype;
	}
	public int getHarvestLevelLevel() {
		return BlockFallingBase.harvestlevellevel;
	}
	public float getResistance() {
		return BlockFallingBase.resistance;
	}
	public float getHardness() {
		return BlockFallingBase.hardness;
	}
	public ItemGroup setCreativeTab(ItemGroup group) {
		return group;
	}
	
	public Properties getProperties() {
		return blockprops;
	}
	
	
	public static Properties changeBlockProperties(Properties properties) {
		return properties;
	}
		
		public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable) {
		
			Properties properties = Block.Properties.create(material);

			

			if(sound == null) {
				System.out.println("no sound found for this block");
			} else {
			properties.sound(sound);	
			}
			
			if(hardness == -42.5f && resistance == -42.5f) {

				System.out.println("no hardess or resistance set for this block");
				
			} else {
				if(hardness == -42.5f) {
					System.out.println("no hardess set for this block");
					properties.hardnessAndResistance(hardness,0);
				} else
				if(resistance == -42.5f) {
					System.out.println("no resistance set for this block");
					properties.hardnessAndResistance(0,resistance);
					
				} else {
					properties.hardnessAndResistance(hardness,resistance);
				}
				
			}
			
			if(harvestleveltooltype == null) {
				System.out.println("no harvestleveltooltype set for this block");
			} else {
				properties.harvestTool(ToolType.get(harvestleveltooltype));
			}
			if(harvestlevellevel == -42) {
				System.out.println("no harvestlevellevel set for this block");
			} else {
				properties.harvestLevel(harvestlevellevel);
			}
			
			
			if(lightlevel == -42) {
				System.out.println("no lightlevel set for this block");
			} else {
				properties.lightValue(lightlevel);
			}
			if(LightOpacity == -42) {
				
			} else {
				
			}
			
			
			
			if(unbreakable) {
		//	properties.setBlockUnbreakable();
			properties.hardnessAndResistance(16000000);
			} else {
				
			}
			
			
			properties = changeBlockProperties(properties);
			
			
			blockprops = properties;
			
			return properties;
		}
	

protected static void setSoundType(SoundType sound) {
	
	if(name == "voxelized_gravel") {
		sound = SoundType.GROUND;
	}
	if(name == "voxelized_sand") {
		sound = SoundType.SAND;
	}
	BlockFallingBase.sound = sound;
	
	//return sound;
}


}
