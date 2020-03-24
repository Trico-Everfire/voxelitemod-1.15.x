package com.tricoeverfire.voxelite.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AvokinateOre extends BlockBase{
	
	//public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);

	public AvokinateOre(String name, Material material) {
		super(name,material);
		setSoundType(SoundType.GLASS);
		setHardness(15.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe",2);
		setLightLevel(0.6F);

	
	}
	
//@Override
//public Block setHardness(float hardness) {
//	
//	return super.setHardness(hardness);
//}
//
//@Override
//public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
//	// TODO Auto-generated method stub
//	return super.addDestroyEffects(world, pos, manager);
//
//}
//	
//	
//drop
//
//	public Item getItemDropped(BlockState state, Random rand, int fortune) {
//		
//		return ModItems.AVOKINATE_CRYSTAL;
//	}
//	
//	@Override
//	 protected boolean canSilkHarvest()
//	{
//	    return true;
//	}

}
