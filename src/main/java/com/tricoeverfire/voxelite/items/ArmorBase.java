package com.tricoeverfire.voxelite.items;

import java.util.List;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorBase extends ArmorItem{
	
	private final String textureName;
	@SuppressWarnings("unused")
	private final boolean hasMicroDamage;
	public static boolean PlayerHoldingShift = false;
	
	public ArmorBase(String name, String texturename ,IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
	this(texturename, texturename, false ,materialIn, slot, builder);
	}
	public ArmorBase(String name, String texturename, boolean damage ,IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		this.textureName = texturename;
		this.hasMicroDamage = damage;
		
		setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	@Override
	public boolean isDamaged(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.isDamaged(stack);
	}
	
@Override
@OnlyIn(Dist.CLIENT)
public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	
	try {
		
	if(stack != null) {

		if(Screen.hasShiftDown() && !Screen.hasAltDown()) {
			tooltip.add(new TranslationTextComponent("Micro Damage (MD) is a measurement of which Avokinate Armor's voxelite damage is measured, every 100 micro damage is equal to 1 Item damage."));
		} else {
			tooltip.add(new TranslationTextComponent("Press Shift for more Information"));
		}

		
//	String string = String.valueOf(stack.getOrCreateTag().getFloat("microdamage"));
	//tooltip.add(new TranslationTextComponent( string + " Micro Damage" ));
	
	}
	
	} catch(NullPointerException exception) {
		
	}
	
	super.addInformation(stack, worldIn, tooltip, flagIn);

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

