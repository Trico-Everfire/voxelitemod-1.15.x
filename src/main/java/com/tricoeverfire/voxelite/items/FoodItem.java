package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class FoodItem extends Item {
	
	//public ItemFood(String name,)
	protected static boolean ismeat = false;
	protected static boolean caneatfast = false;
	protected static boolean alwaysedible = false;
	protected static int hunger = 1;
	protected static float saturation = (float) (hunger / 2);
	protected static float probability = 0.0f;
	protected static Effect effect = null;
	
	

	public FoodItem(String name, @Nullable Block block) {
		super(new Properties().group(ModItemGroups.voxelitemodfoods).food(createFoodSystem()));
		if(block == null) {
			setRegistryName(Main.location(name));
			 ModItems.ITEMS.add(this);

		} else {
			ModItems.ITEMS.add(new BlockNamedItemBase(name, block, new Properties().group(ModItemGroups.voxelitemodfoods).food(createFoodSystem())));
		}
		
	}
	public FoodItem(String name) {
		this(name,null);
		
		
	}
	public FoodItem(String name,int hunger, boolean ismeat) {
		this(name);
		FoodItem.ismeat = ismeat;
		FoodItem.hunger = hunger;
		
	}
	public FoodItem(String name, int hunger, boolean ismeat, float saturation, boolean caneatfast, boolean alwaysedible) {
		this(name,hunger,ismeat);
		FoodItem.saturation = saturation;
		FoodItem.caneatfast = caneatfast;
		FoodItem.alwaysedible = alwaysedible;
		
	}
	public FoodItem(String name, int hunger, boolean ismeat, float saturation, boolean caneatfast, boolean alwaysedible,Effect effect,float probability) {
		this(name,hunger,ismeat,saturation,caneatfast,alwaysedible);
		FoodItem.effect = effect;
		FoodItem.probability = probability;
	
	}
	
	//blockified
	public FoodItem(String name, Block block, int hunger, boolean ismeat) {
		this(name,block);
		FoodItem.ismeat = ismeat;
		FoodItem.hunger = hunger;
		
	}
	public FoodItem(String name, Block block, int hunger, boolean ismeat, float saturation, boolean caneatfast, boolean alwaysedible) {
		this(name,block,hunger,ismeat);
		FoodItem.saturation = saturation;
		FoodItem.caneatfast = caneatfast;
		FoodItem.alwaysedible = alwaysedible;
		
	}
	public FoodItem(String name, Block block, int hunger, boolean ismeat, float saturation, boolean caneatfast, boolean alwaysedible,Effect effect,float probability) {
		this(name,block,hunger,ismeat,saturation,caneatfast,alwaysedible);
		FoodItem.effect = effect;
		FoodItem.probability = probability;
	
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static Food createFoodSystem() {
		Builder newfood = new Food.Builder();
		
		newfood.hunger(hunger);
		newfood.saturation(saturation);
		
		if(ismeat) {
			newfood.meat();
		}
		if(caneatfast) {
			newfood.fastToEat();
		}
		if(alwaysedible) {
			newfood.setAlwaysEdible();
		}
		if(effect != null) {
			newfood.effect(new EffectInstance(effect), probability);
		}
		
		
		return newfood.build();
	}


//float saturationIn, boolean isMeat, boolean alwaysEdible, boolean fastEdible, List<Pair<EffectInstance, Float>> effectsIn)


}
