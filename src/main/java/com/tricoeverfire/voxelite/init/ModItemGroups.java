package com.tricoeverfire.voxelite.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups extends ItemGroup{

	private final int type;
    public static final ModItemGroups voxelitemodblocks = new ModItemGroups(ModItemGroups.GROUPS.length,"voxelitemod_blocks",0);
    public static final ModItemGroups voxelitemoditems = new ModItemGroups(ModItemGroups.GROUPS.length,"voxelitemod_items",1);
    public static final ModItemGroups voxelitemodfoods = new ModItemGroups(ModItemGroups.GROUPS.length,"voxelitemod_foods",2);
	
	public ModItemGroups(int index, String label,int type) {
		super(index,label);
		this.type = type;
	}
@Override
public ItemStack createIcon() {
	
	switch(type) {
	case 0:
		return new ItemStack(ModBlocks.BLACKSTONEBRICK);

	case 1:
		return new ItemStack(ModItems.AVOKINATE_MEDAL);
	
	case 2:
		return new ItemStack(ModItems.CUCUMBER);
	default:
		return ItemStack.EMPTY;
	
	}
	
	
	
}	
	
}
