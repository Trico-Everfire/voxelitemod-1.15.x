package com.tricoeverfire.voxelite.blocks;

import java.util.Random;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReinforcedGlass extends Block{

	private final String name;
	public ReinforcedGlass(String name, Material material) 
	{

		super(Block.Properties.create(material).notSolid().sound(SoundType.GLASS).hardnessAndResistance(15f));
		setRegistryName(Main.location(name));
		this.name = name;
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);

//		setSoundType(SoundType.GLASS);
//		setHardness(1.0F);
//		setResistance(15.0F);
//		setLightOpacity(3);


	}

	







	@OnlyIn(Dist.CLIENT)
	   public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return 1.0F;
	   }

	   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	      return true;
	   }

	   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return false;
	   }

	   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return false;
	   }

	   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	      return false;
	   }
	   
	   @SuppressWarnings("deprecation")
	   @OnlyIn(Dist.CLIENT)
	   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
	      return adjacentBlockState.getBlock() == this ? true : super.isSideInvisible(state, adjacentBlockState, side);
	   }
	   

	
	

	










	@SuppressWarnings("unused")
	private int quantityDropped(Random random)
	{
		if(this.name == "cracked_reinforced_glass") {
			int max = 3;
			int min = 0;
			
			int rand = random.nextInt(max) + min;
			System.out.println(rand);
		    return rand;
		    
		} else {
		int max = 4;
		int min = 2;
		
	    return random.nextInt(max) + min;
	    
		}
	}
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
    {
        return false;
    }
	
	

	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) {
		if(this.name == "cracked_reinforced_glass") {
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
			world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3, 0, true);
		} else {

			world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 3, 0, true);
	
		}
	}
	


	
	


   

	
}
