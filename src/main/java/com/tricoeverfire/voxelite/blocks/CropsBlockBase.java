package com.tricoeverfire.voxelite.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class CropsBlockBase extends CropsBlock {
	
	public final Block[] soil;
	public final @Nullable Item plantable;
	public final PlantType type;
	public final boolean rightClickHarbestable;

	public CropsBlockBase(String name, Block[] soil, @Nullable Item plantable, PlantType type, boolean rightClickHarvestable, Properties builder) {
		super(builder);
		this.soil = soil;
		this.rightClickHarbestable = rightClickHarvestable;
		this.plantable = plantable;
		this.type = type;
		setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
	}
	public CropsBlockBase(String name, Block[] soil, @Nullable Item plantable, PlantType type, Properties builder) {
		this(name, soil, plantable, type, false, builder);
	}
	
	


@Override
public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
		Hand handIn, BlockRayTraceResult hit) {
	int max = 3;
	int min = 1;
	Random random = new Random();
	
if(!worldIn.isRemote) {
	if(this.isMaxAge(state)) {
		
		worldIn.addEntity(new ItemEntity(worldIn,pos.getX(),pos.getY(),pos.getZ(),new ItemStack(plantable,random.nextInt(max) + min)));
		worldIn.setBlockState(pos,this.withAge(0));
		return ActionResultType.SUCCESS;
	}
	
}
	return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
}

public boolean isRightClickHarbestable() {
	return rightClickHarbestable;
}
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		for (Block block : soil) {
			if(state.getBlock() == block) {
				
				return true;
			}
		}
		return false;
	}
	
	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {

  		return type;
	}
	
	
	
	@Override
	protected IItemProvider getSeedsItem() {
		
		if(plantable == null) {
			return this;
		}
		return plantable;
	}
	

}
