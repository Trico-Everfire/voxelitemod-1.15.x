package com.tricoeverfire.voxelite.blocks;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block{


	protected static SoundType sound;
	private static boolean needsTransparency = false;
	private static float hardness = -42.5f;
	private static float resistance = -42.5f;
	private static String harvestleveltooltype;
	private static int harvestlevellevel = -42;
	private static int lightlevel = -42;
	private static Properties blockprops;
	private static int LightOpacity = -42;
	
	public BlockBase(String name) {
		this(name, Material.WOOD);
	}
	
	public BlockBase(String name, Material material) {
		
		this(name, material,false);
	}
	
	public BlockBase(String name, boolean unbreakable) {
		this(name, Material.WOOD, unbreakable);
	}

	


	

public static Properties changeBlockProperties(Properties properties) {

	return properties;
}
	
	public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable, @Nullable Properties properts) {
	
		Properties properties;
		if(properts == null) {
		properties = Block.Properties.create(material);
		} else {
		properties = properts;
		}
		
		if(needsTransparency) {
			properties.notSolid();
		}

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

	


	public BlockBase(String name, Material material,boolean unbreakable) {
		
		super(getBlockProperties(material,unbreakable,null));
		setSoundType(SoundType.GLASS);
		setHardness(6.0F);
		setResistance(12.0F);
		setHarvestLevel("pickaxe",2);
		isTransparent(false);
		setRegistryName(Main.location(name));
		Item.Properties blockItemProperties = new Item.Properties();
		blockItemProperties.group(ModItemGroups.voxelitemodblocks);
		
		Item thisblock = new BlockItem(this,blockItemProperties);
	
		thisblock.setRegistryName(Main.location(name));
		
		//	setCreativeTab(Main.voxelitemod);
		//setCreativeTab();
		
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		//Item item = new ItemBlock();
	}


	


	protected void setSoundType(SoundType type){
		BlockBase.sound = type;
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
		
		BlockBase.lightlevel = inter;
		
	}



	protected void setHarvestLevel(String string, int i) {
		BlockBase.harvestleveltooltype = string;
		BlockBase.harvestlevellevel = i;
		
	}

	protected void setResistance(float f) {
		BlockBase.resistance = f;
		
	}

	protected void setHardness(float f) {
		BlockBase.hardness = f;
		
	}
	
	protected void setLightOpacity(int i) {
		LightOpacity = i;
		
	}

	
	public SoundType getSoundType() {
		return BlockBase.sound;
	}
	
	public int getLightLevel() {
		
		return (int) BlockBase.lightlevel;
	}

	public String getHavestLevelToolType() {
		return BlockBase.harvestleveltooltype;
	}
	public int getHarvestLevelLevel() {
		return BlockBase.harvestlevellevel;
	}
	public float getResistance() {
		return BlockBase.resistance;
	}
	public float getHardness() {
		return BlockBase.hardness;
	}
	public ItemGroup setCreativeTab(ItemGroup group) {
		return group;
	}
	
	public Properties getProperties() {
		return blockprops;
	}
	
	
	 protected static void isTransparent(boolean val) {
		needsTransparency = val;
		
	}

	 
	
	//isn't actually used, but it's rather used for backwards compatability, if you have a lot of blocks
	//you just copy this in -Trico Everfire.
	public void setUnlocalizedName(String Name) {
		
	}
	

	



	
	
}
