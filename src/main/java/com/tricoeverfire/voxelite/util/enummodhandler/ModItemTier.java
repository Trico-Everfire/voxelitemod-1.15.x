package com.tricoeverfire.voxelite.util.enummodhandler;

import java.util.function.Supplier;

import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier{

	   AVOKINATE(3, 2080, 6.0F, 4.5F, 11, () -> {
		      return Ingredient.fromItems(ModItems.AVOKINATE_CRYSTAL);
	   }),
	   CHARGED_AVOKINATE(4, 3160, 8.0F, 6.0F, 11, () -> {
		      return Ingredient.fromItems(ModItems.AVOKINATE_CRYSTAL);
	   });
	
	   private final int harvestLevel;
	   private final int maxUses;
	   private final float efficiency;
	   private final float attackDamage;
	   private final int enchantability;
	   private final LazyValue<Ingredient> repairMaterial;

	   private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
	      this.harvestLevel = harvestLevelIn;
	      this.maxUses = maxUsesIn;
	      this.efficiency = efficiencyIn;
	      this.attackDamage = attackDamageIn;
	      this.enchantability = enchantabilityIn;
	      this.repairMaterial = new LazyValue<>(repairMaterialIn);
	   }

	   public int getMaxUses() {
	      return this.maxUses;
	   }

	   public float getEfficiency() {
	      return this.efficiency;
	   }

	   public float getAttackDamage() {
	      return this.attackDamage;
	   }

	   public int getHarvestLevel() {
	      return this.harvestLevel;
	   }

	   public int getEnchantability() {
	      return this.enchantability;
	   }

	   public Ingredient getRepairMaterial() {
	      return this.repairMaterial.getValue();
	   }
	
}
