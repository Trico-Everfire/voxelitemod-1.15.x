package com.tricoeverfire.voxelite.items;

import java.util.ArrayList;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item.Properties;

public class ArmorBuilder {
	
	private final String nameHelmet;
	private final String nameChestplate;
	private final String nameLeggings;
	private final String nameBoots;
	private final String textureName;
	private final Properties builder;
	private final IArmorMaterial materialIn;
	private ArmorBase[] armorSet;
	
	public ArmorBuilder(String name, IArmorMaterial materialIn, Properties builder) {
		this.nameHelmet = name+"_helmet";
		this.nameChestplate = name+"_chestplate";
		this.nameLeggings = name+"_leggings";
		this.nameBoots = name+"_boots";
		this.textureName = name;
		this.builder = builder;
		this.materialIn = materialIn;
		this.armorSet = makeArmorSetList();
	}

	private ArmorBase[] makeArmorSetList() {
		ArrayList<ArmorBase> ABase = new ArrayList<ArmorBase>();
		for(int i = 0; i < 4; i++) {
		ArmorBase piece;
		System.out.println(i);
		switch(i) {
		case 0:{
			piece = new ArmorBase(this.nameHelmet, this.textureName, this.materialIn, EquipmentSlotType.HEAD, this.builder);
			break;
		}
		case 1:{
			piece = new ArmorBase(this.nameChestplate, this.textureName, this.materialIn, EquipmentSlotType.CHEST, this.builder);
			break;
		}
		case 2:{
			piece = new ArmorBase(this.nameLeggings, this.textureName, this.materialIn, EquipmentSlotType.LEGS, this.builder);
			break;
		}
		case 3:{
			piece = new ArmorBase(this.nameBoots, this.textureName, this.materialIn, EquipmentSlotType.FEET, this.builder);
			break;
		}
		default:{
			piece = new ArmorBase("error", this.textureName, this.materialIn, EquipmentSlotType.FEET, this.builder);
			break;
		}
		
	}
	ABase.add(piece);	
}
		return ABase.toArray(new ArmorBase[ABase.size()]);
}
	
	
	public ArmorBase[] getArmorList() {
		return this.armorSet;
	}
	
}