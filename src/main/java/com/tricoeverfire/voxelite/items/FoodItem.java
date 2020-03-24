package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.init.ModItemGroups;

import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class FoodItem extends ItemBase {
	
	//public ItemFood(String name,)
	protected static boolean ismeat = false;
	protected static boolean caneatfast = false;
	protected static boolean alwaysedible = false;
	protected static int hunger = 1;
	protected static float saturation = (float) (hunger / 2);
	protected static float probability = 0.0f;
	protected static Effect effect = null;
	
	

	public FoodItem(String name) {
		super(name,new Properties().group(ModItemGroups.voxelitemodfoods).food(createFoodSystem()));
		
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
	
	
	
	
	protected static Food createFoodSystem() {
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
