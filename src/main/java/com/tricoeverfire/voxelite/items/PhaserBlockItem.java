package com.tricoeverfire.voxelite.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class PhaserBlockItem extends BlockItem{
	
	//public final int NBTItemColor;

	public PhaserBlockItem(Block blockIn,  Properties builder) {
		super(blockIn, builder);
		this.addPropertyOverride(new ResourceLocation("inverse"),(stack, world, entity)->{
			if(stack.getOrCreateTag().getBoolean("inverse") == true) {
				return 1;
			}
			return 0;
		});
	}
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		
		if (this.isInGroup(group)) {
			
		ItemStack thisbutStandard = new ItemStack(this); //standard
		ItemStack thisbutStandardinverse = new ItemStack(this); //standard inversed
		thisbutStandardinverse.getOrCreateTag().putBoolean("inverse", true);
		ItemStack thisbutblue = new ItemStack(this);
		thisbutblue.getOrCreateTag().putString("color", "0x7384AC");
		thisbutStandardinverse.getOrCreateTag().putBoolean("inverse", true);
		thisbutStandardinverse.getOrCreateTag().putString("color", "0x7384AC");
		thisbutStandardinverse.setDisplayName(new TranslationTextComponent(thisbutStandardinverse.getTranslationKey()+" Inverse"));
		ItemStack thisbutblueinverse = new ItemStack(this); 
		thisbutblueinverse.getOrCreateTag().putBoolean("inverse", true);
		
		items.add(thisbutStandardinverse);
		items.add(thisbutblueinverse);
		items.add(thisbutStandard);
		items.add(thisbutblue);
		}
		

	}
	
	

}
