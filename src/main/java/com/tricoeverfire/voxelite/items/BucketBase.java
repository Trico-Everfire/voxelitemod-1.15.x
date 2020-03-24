package com.tricoeverfire.voxelite.items;

import java.util.function.Supplier;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;

public class BucketBase extends BucketItem{
	
	
	@SuppressWarnings("deprecation")
	public BucketBase(String name, Fluid containedFluidIn, Properties builder) {
		super(containedFluidIn, builder);
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	

	public BucketBase(String name, Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
		
	}


	public BucketBase(Supplier<? extends Fluid> supplier, Properties group) {
		super(supplier,group);
		
	}
	
	@Override
	public String getTranslationKey() {
		return "Emerald " + getFluid().getDefaultState().getBlockState().getBlock().getNameTextComponent().getString() +" Bucket";
	}


}
