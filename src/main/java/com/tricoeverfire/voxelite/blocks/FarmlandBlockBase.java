package com.tricoeverfire.voxelite.blocks;

import java.util.Random;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FarmlandBlockBase extends FarmlandBlock {

	public Block turnto;
	
	public FarmlandBlockBase(String name, Block turnto, Properties builder) {
		super(builder);
		setRegistryName(Main.location(name));
		this.turnto = turnto;
		HoeItem.HOE_LOOKUP.put(turnto, this.getDefaultState());
		Item thisblock = new BlockItem(this,new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
	
	}
	
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
		      return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? turnto.getDefaultState() : super.getStateForPlacement(context);
		   }
	
	   private boolean hasCrops(IBlockReader worldIn, BlockPos pos) {
		      BlockState state = worldIn.getBlockState(pos.up());
		      return state.getBlock() instanceof net.minecraftforge.common.IPlantable && canSustainPlant(state, worldIn, pos, Direction.UP, (net.minecraftforge.common.IPlantable)state.getBlock());
		   }

	
	   private static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
		      for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
		         if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
		            return true;
		         }
		      }

		      return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
		   }
	
	   public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		      if (!state.isValidPosition(worldIn, pos)) {
		         turnToBlock(state, worldIn, pos);
		      } else {
		         int i = state.get(MOISTURE);
		         if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
		            if (i > 0) {
		               worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);
		            } else if (!hasCrops(worldIn, pos)) {
		               turnToBlock(state, worldIn, pos);
		            }
		         } else if (i < 7) {
		            worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);
		         }

		      }
		   }
	
	   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		      if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, turnto.getDefaultState(), fallDistance, entityIn)) { // Forge: Move logic to Entity#canTrample
		         turnToBlock(worldIn.getBlockState(pos), worldIn, pos);
		      }

		      entityIn.onLivingFall(fallDistance, 1.0F);
		   }
	
	   public void turnToBlock(BlockState state, World worldIn, BlockPos pos) {
		      worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, turnto.getDefaultState(), worldIn, pos));
		   }

}
