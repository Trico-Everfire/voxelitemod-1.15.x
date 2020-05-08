package com.tricoeverfire.voxelite.blocks;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RotatableHiddenGlassBlock extends DirectionalBlock{

	public RotatableHiddenGlassBlock(String name, Block fromblock) {
		this(name, Block.Properties.from(fromblock));
		
	}
	

	public RotatableHiddenGlassBlock(String name, Properties properties) {
		super(properties);
		
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
	
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		      builder.add(FACING);
		   }
	   
	   @Override
	   @Deprecated
	   public BlockState mirror(BlockState state, net.minecraft.util.Mirror mirrorIn) {
	      return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
	   }

	   

	public RotatableHiddenGlassBlock(String string, Material rock, boolean unbreakable) {
		this(string,getProperty(rock,unbreakable));
	}

	private static Properties  getProperty(Material material,boolean unbreakable) {
		Properties property = Block.Properties.create(material);
		if(unbreakable) {
			property.hardnessAndResistance(16000000);
		}
	return property;
	}
	
	
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
		      return this.getDefaultState().with(FACING, context.getFace());
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
	
	
	
}
