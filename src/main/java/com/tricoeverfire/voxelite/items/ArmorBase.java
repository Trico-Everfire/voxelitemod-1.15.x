package com.tricoeverfire.voxelite.items;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class ArmorBase extends ArmorItem{
	
	private final String textureName;

	public ArmorBase(String name, String texturename ,IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		this.textureName = texturename;
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	
@Override
public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
	
	if(slot == EquipmentSlotType.LEGS) {
		return Reference.MOD_ID+":textures/models/armor/"+this.textureName+"_layer_2.png";
	} else {
		return Reference.MOD_ID+":textures/models/armor/"+this.textureName+"_layer_1.png";
	}
	
	
}

}

