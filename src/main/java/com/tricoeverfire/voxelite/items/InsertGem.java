package com.tricoeverfire.voxelite.items;

public class InsertGem {
	private final String name;
	private final int color;
	private final int harvestlevel;
	private final float duribility;
	private final float efficiency;
	private final float damage;
	private final float enchantability;
	

	public InsertGem(String name, int color,int harvestlevel,float duribility,float efficiency,float damage,float enchantability){
		
		this.name = name;
		this.color = color;
		this.harvestlevel = harvestlevel;
		this.duribility = duribility;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
		
		
	}
	
	public String getName() {
		return this.name;
	}
	public int getColor() {
		return this.color;
	}
	public float getHarvestLevel() {
		return this.harvestlevel;
	}
	public float getDuribility() {
		return this.duribility;
	}
	public float getEfficiency() {
		return this.efficiency;
	}
	public float getDamage() {
		return this.damage;
	}
	public float getEnchantability() {
		return this.enchantability;
	}
	
}
