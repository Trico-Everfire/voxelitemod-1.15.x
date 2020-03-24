package com.tricoeverfire.voxelite.blocks;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockDoorBase extends DoorBlock{
	
	protected boolean openable;

	public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable) {
		//return getBlockProperties(material,unbreakable);
		return getBlockProperties(material,unbreakable,null,-21.5F);
	}
	public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable, @Nullable SoundType sound, @Nullable float hardness) {
		Properties properties = Block.Properties.create(material);
		
		
		
		if(unbreakable) {
	//	properties.setBlockUnbreakable();
		properties.hardnessAndResistance(16000000);
		} else {
			
		}
		
		
		return properties;
	}
	
	
	public BlockDoorBase(String name, Material materialIn, boolean openable,boolean unbreakable) {
		super(getBlockProperties(materialIn,unbreakable).notSolid());
		this.openable = openable;
		setRegistryName(Main.location(name));
	
		
		ModBlocks.BLOCKS.add(this);
		
		Item.Properties blockItemProperties = new Item.Properties();
		blockItemProperties.group(ModItemGroups.voxelitemodblocks);
		
		
		Item thisblock = new BlockItem(this,blockItemProperties);
	
		thisblock.setRegistryName(Main.location(name));
		
		
		
		ModItems.ITEMS.add(thisblock); 
	}




	
	   @Override
	   public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
	
		   
	    	  if (player.getHeldItemMainhand().getItem() == ModItems.BLACK_STONE_DUNGEON_KEY && this == ModBlocks.BLACKSTONEDOORLOCKED) {
	    		if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
	    		  BlockPos bottompos;
	    		  BlockPos toppos;
	    		  if(state.get(HALF) == DoubleBlockHalf.UPPER) {
		    			 bottompos = pos.down();
		    			 toppos = pos;
		    		} else {
		    			 bottompos = pos;
		    			 toppos = pos.up();
		    		}
		    		
		    		BlockState newstate = ModBlocks.BLACKSTONEDOOR.getDefaultState();
		    		newstate = newstate.with(OPEN, state.get(OPEN));
		    		newstate = newstate.with(HINGE, state.get(HINGE));
		    		newstate = newstate.with(FACING, state.get(FACING));
		    		
		    
		    		//world.set
		    		
		    		world.setBlockState(bottompos, newstate.with(HALF, DoubleBlockHalf.LOWER),16);
		    		world.setBlockState(toppos, newstate.with(HALF, DoubleBlockHalf.UPPER), 16);
		    		
		    		
		    		return ActionResultType.SUCCESS;
	    	  }
	    	  
	    	  if (player.getHeldItemMainhand().getItem() == ModItems.BLUE_DUNGEON_KEY && this == ModBlocks.BLUE_DUNGEON_DOOR_LOCKED) {
	    		if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
	    		  BlockPos bottompos;
	    		  BlockPos toppos;
	    		  if(state.get(HALF) == DoubleBlockHalf.UPPER) {
		    			 bottompos = pos.down();
		    			 toppos = pos;
		    		} else {
		    			 bottompos = pos;
		    			 toppos = pos.up();
		    		}
		    		
		    		BlockState newstate = ModBlocks.BLUE_DUNGEON_DOOR.getDefaultState();
		    		newstate = newstate.with(OPEN, state.get(OPEN));
		    		newstate = newstate.with(HINGE, state.get(HINGE));
		    		newstate = newstate.with(FACING, state.get(FACING)); 
		    		
		    
		    		//world.set
		    		
		    		world.setBlockState(bottompos, newstate.with(HALF, DoubleBlockHalf.LOWER),16);
		    		world.setBlockState(toppos, newstate.with(HALF, DoubleBlockHalf.UPPER), 16);
		    		
		    		if(world.getBlockState(bottompos.east()).getBlock() == ModBlocks.BLUE_DUNGEON_DOOR_LOCKED) {
		    			
		    		//world.setBlockState(bottompos.east(), ModBlocks.BLUE_DUNGEON_DOOR.getDefaultState().with(OPEN, state.get(OPEN)).with(HALF, DoubleBlockHalf.LOWER),16);
		    		//world.setBlockState(toppos.east(), ModBlocks.BLUE_DUNGEON_DOOR.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 16);
		    		}
		    		
		    		return ActionResultType.SUCCESS;
	    	  }
	    	  
		   
		   if (!openable) {
		         return ActionResultType.PASS;
		      } else {
		    	  

		         state = state.cycle(OPEN);
		         world.setBlockState(pos, state, 10);
		         world.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
		         return ActionResultType.SUCCESS;
		    	  }
	    	  
		   }
	   
	   private int getCloseSound() {
		      return this.material == Material.IRON ? 1011 : 1012;
		   }

	   private int getOpenSound() {
		      return this.material == Material.IRON ? 1005 : 1006;
		   }
	
	
	



}
