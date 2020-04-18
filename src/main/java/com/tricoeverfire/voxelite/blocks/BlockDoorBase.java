package com.tricoeverfire.voxelite.blocks;

import java.util.ArrayList;

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
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockDoorBase extends DoorBlock{
	
	protected boolean openable;
	protected final @Nullable Item item;
	protected final @Nullable Block counterDoor;
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
	
	
	public BlockDoorBase(String name, Material materialIn, @Nullable Item keyItem, @Nullable Block counterDoor,boolean openable,boolean unbreakable) {
		super(getBlockProperties(materialIn,unbreakable).notSolid());
		this.openable = openable;
		setRegistryName(Main.location(name));
		this.item = keyItem;
		this.counterDoor = counterDoor;
		ModBlocks.BLOCKS.add(this);
		
		Item.Properties blockItemProperties = new Item.Properties();
		blockItemProperties.group(ModItemGroups.voxelitemodblocks);
		
		
		Item thisblock = new BlockItem(this,blockItemProperties);
	
		thisblock.setRegistryName(Main.location(name));
		
		
		
		ModItems.ITEMS.add(thisblock); 
	}




	
	   @Override
	   public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

		   if(this.item != null && this.counterDoor != null) {
			   
		   
	    	  if (player.getHeldItemMainhand().getItem() == this.item) {
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
//	    		  if(!world.isRemote()) {
//	    		  System.out.println();
//	    		  System.out.println(world.getBlockState(pos).get(FACING));
//	    		  System.out.println(world.getBlockState(pos).get(HINGE));
//	    		  System.out.println(world.getBlockState(pos).get(OPEN));
//	    		  System.out.println(world.getBlockState(pos).get(HALF));
//	    		  }
		    		BlockState newstate = this.counterDoor.getDefaultState();
		    		newstate = newstate.with(OPEN, state.get(OPEN));
		    		newstate = newstate.with(HINGE, state.get(HINGE));
		    		newstate = newstate.with(FACING, state.get(FACING)); 

		    		
		    		world.setBlockState(bottompos, newstate.with(HALF, DoubleBlockHalf.LOWER),16);
		    		world.setBlockState(toppos, newstate.with(HALF, DoubleBlockHalf.UPPER), 16);
		    		
		    		//Checks for second door next to it, if it found a matching door (two locks next to each other) it will open both doors.
		    		ArrayList<BlockPos> posarr = new ArrayList<BlockPos>();
		    		BlockPos currentpos;
		    		for (int i = 0; i < 1; i++) {
		    			
		    			
		    			posarr.clear();
		    			if(i == 0) {
		    			currentpos = toppos;
		    			} else {
		    			currentpos = bottompos;
		    			}
			    		posarr.add(currentpos.north());
			    		posarr.add(currentpos.east());
			    		posarr.add(currentpos.south());
			    		posarr.add(currentpos.west());

			    		for (int j = 0; j < 4; j++) {
					    	BlockState currentState = world.getBlockState(currentpos);
					    	BlockState counterDoorState = world.getBlockState(posarr.get(j));
				    	if(counterDoorState.getBlock() == this) {

				    //	boolean isSameHinge = counterDoorState.get(HINGE) == currentState.get(HINGE); //checks if hinge is facing the same direction as counter door if so, counter door will not be unlocked
				    	boolean isSameFacing = haveFacingHinges(currentpos,posarr.get(j),counterDoorState,currentState);
				    	
				    	if(isSameFacing) {
				    		
				    		if(i == 0) {
				    			world.setBlockState(posarr.get(j).down(), this.counterDoor.getDefaultState().with(OPEN, currentState.get(OPEN)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, counterDoorState.get(HINGE)).with(FACING, currentState.get(FACING)),16);
				    			world.setBlockState(posarr.get(j), this.counterDoor.getDefaultState().with(OPEN, currentState.get(OPEN)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, counterDoorState.get(HINGE)).with(FACING, currentState.get(FACING)),16);
				    		} else {
				    			world.setBlockState(posarr.get(j).up(), this.counterDoor.getDefaultState().with(OPEN, currentState.get(OPEN)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, counterDoorState.get(HINGE)).with(FACING, currentState.get(FACING)),16);
				    			world.setBlockState(posarr.get(j), this.counterDoor.getDefaultState().with(OPEN, currentState.get(OPEN)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, counterDoorState.get(HINGE)).with(FACING, currentState.get(FACING)),16);
				    	
				    		}
			    		}
				    		}
							
						}
		    	
		    		
		    		}
		    		
		    		return ActionResultType.SUCCESS;
	    	  }
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
	   
	   
	   //credit to diesieben07 for 99% of this method's code
	   protected static boolean haveFacingHinges(BlockPos pos1, BlockPos pos2, BlockState state1, BlockState state2) {
		      Direction facing1 = state1.get(DoorBlock.FACING);
		      Direction facing2 = state2.get(DoorBlock.FACING);
		      if (facing1 != facing2)  {
		         return false;
		      }
		     // System.out.println("passed");
		      DoorHingeSide hingeSide1 = state1.get(DoorBlock.HINGE);
		      DoorHingeSide hingeSide2 = state2.get(DoorBlock.HINGE);
		      Direction hingeDir1 = hingeSide1 == DoorHingeSide.LEFT ? facing1.rotateYCCW() : facing1.rotateY();
		      Direction hingeDir2 = hingeSide2 == DoorHingeSide.LEFT ? facing2.rotateYCCW() : facing2.rotateY();
		 
		      Direction facingRotY = facing1.rotateY();
		      if (pos1.offset(facingRotY).equals(pos2))
		      {
		         // pos2 is in the direction of facingRotY
		         if (facingRotY != hingeDir2.getOpposite())
		         {
		            return false;
		         }
		      }
		      else
		      {
		         // pos1 is in the direction of facingRotY
		         if (facingRotY != hingeDir1.getOpposite())
		         {
		            return false;
		         }
		      }
		      
		 
		      Direction facingRotYCCW = facing1.rotateYCCW();
		      if (pos1.offset(facingRotYCCW).equals(pos2))
		      {
		         // pos2 is in the direction of facingRotYCCW
		         if (facingRotYCCW != hingeDir2.getOpposite())
		         {
		            return false;
		         }
		      }
		      else
		      {
		         // pos1 is in the direction of facingRotYCCW
		         if (facingRotYCCW != hingeDir1.getOpposite())
		         {
		            return false;
		         }
		      }
		 
		      return true;
		   }
	   
	   
	   
	   
	   
	   
	   
//	   @Nullable
//	   public BlockState getStateForPlacement(BlockItemUseContext context) {
//	      BlockPos blockpos = context.getPos();
//	      if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
//	         World world = context.getWorld();
//	         boolean flag = world.isBlockPowered(blockpos) || world.isBlockPowered(blockpos.up());
//	         return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HINGE, this.getHingeSide(context)).with(POWERED, Boolean.valueOf(flag)).with(OPEN, Boolean.valueOf(flag)).with(HALF, DoubleBlockHalf.LOWER);
//	      } else {
//	         return null;
//	      }
//	   }
//	
//	   private DoorHingeSide getHingeSide(BlockItemUseContext p_208073_1_) {
//		      IBlockReader iblockreader = p_208073_1_.getWorld();
//		      BlockPos blockpos = p_208073_1_.getPos();
//		      Direction direction = p_208073_1_.getPlacementHorizontalFacing();
//		      BlockPos blockpos1 = blockpos.up();
//		      Direction direction1 = direction.rotateYCCW();
//		      BlockPos blockpos2 = blockpos.offset(direction1);
//		      BlockState blockstate = iblockreader.getBlockState(blockpos2);
//		      BlockPos blockpos3 = blockpos1.offset(direction1);
//		      BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
//		      Direction direction2 = direction.rotateY();
//		      BlockPos blockpos4 = blockpos.offset(direction2);
//		      BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
//		      BlockPos blockpos5 = blockpos1.offset(direction2);
//		      BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
//		      int i = (blockstate.isCollisionShapeOpaque(iblockreader, blockpos2) ? -1 : 0) + (blockstate1.isCollisionShapeOpaque(iblockreader, blockpos3) ? -1 : 0) + (blockstate2.isCollisionShapeOpaque(iblockreader, blockpos4) ? 1 : 0) + (blockstate3.isCollisionShapeOpaque(iblockreader, blockpos5) ? 1 : 0);
//		      boolean flag = blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
//		      boolean flag1 = blockstate2.getBlock() == this && blockstate2.get(HALF) == DoubleBlockHalf.LOWER;
//		      if ((!flag || flag1) && i <= 0) {
//		         if ((!flag1 || flag) && i >= 0) {
//		            int j = direction.getXOffset();
//		            int k = direction.getZOffset();
//		            Vec3d vec3d = p_208073_1_.getHitVec();
//		            double d0 = vec3d.x - (double)blockpos.getX();
//		            double d1 = vec3d.z - (double)blockpos.getZ();
//		            return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
//		         } else {
//		            return DoorHingeSide.LEFT;
//		         }
//		      } else {
//		         return DoorHingeSide.RIGHT;
//		      }
//		   }
	



}
