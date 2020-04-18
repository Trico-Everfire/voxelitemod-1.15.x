package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.util.enummodhandler.ModEnchantmentType;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {//extends ItemGroup{

	
    public static final ItemGroup voxelitemodblocks = (new ItemGroup(ItemGroup.GROUPS.length,"voxelitemod_blocks") {
    	@Override
    	public ItemStack createIcon() {
    		// TODO Auto-generated method stub
    		return new ItemStack(ModBlocks.BLACKSTONEBRICK);
    	}
    });
    public static final ItemGroup voxelitemoditems = (new ItemGroup(ItemGroup.GROUPS.length,"voxelitemod_items") {
    	@Override
    	public ItemStack createIcon() {
    		// TODO Auto-generated method stub
    		return new ItemStack(ModItems.AVOKINATE_MEDAL);
    	}
    }).setRelevantEnchantmentTypes( new EnchantmentType[] {ModEnchantmentType.AVOKINATEGEAR,ModEnchantmentType.HAMMER});
    public static final ItemGroup voxelitemodfoods = new ItemGroup(ItemGroup.GROUPS.length,"voxelitemod_foods") {
    	@Override
    	public ItemStack createIcon() {
    		// TODO Auto-generated method stub
    		return new ItemStack(ModItems.CUCUMBER);
    	}
    };
	
//	public ModItemGroups(int index, String label,int type) {
//		super(index,label);
//		this.type = type;
//	}

//	@Override
//	public ItemStack createIcon() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

	
//@Override
//public ItemStack createIcon() {
//	
//	switch(type) {
//	case 0:
//		return new ItemStack(ModBlocks.BLACKSTONEBRICK);
//
//	case 1:
//		return new ItemStack(ModItems.AVOKINATE_MEDAL);
//	
//	case 2:
//		return new ItemStack(ModItems.CUCUMBER);
//	default:
//		return ItemStack.EMPTY;
//	
//	}
	
	
	
//}	
	
}
