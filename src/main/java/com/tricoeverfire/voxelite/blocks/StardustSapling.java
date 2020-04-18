package com.tricoeverfire.voxelite.blocks;

import java.util.Random;
import java.util.function.Supplier;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

public class StardustSapling extends BushBlock implements IGrowable {

	
	public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(14.0d, 1.0d, 16.0d, 1.0d, 14.0d, 0.0d);
	private final Supplier<Tree> TREE;
	
	public StardustSapling(String name, Supplier<Tree> supplier, Properties properties) {
		super(properties);
		setRegistryName(Main.location(name));
		
		Item thisblock = new BlockItem(this, new Item.Properties().group(ModItemGroups.voxelitemodblocks));
		thisblock.setRegistryName(Main.location(name));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(thisblock);
		this.TREE = supplier;
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		Block block = state.getBlock();
		return block == ModBlocks.STARDUSTGROWTH ? true : super.isValidGround(state, worldIn, pos);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		// TODO Auto-generated method stub
		super.tick(state, worldIn, pos, rand);
		if(!worldIn.isAreaLoaded(pos, 1)) {
			return;
		}
		if(worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0) {
			this.grow(worldIn, pos, state, rand);
		}
	}
	
	public void grow(ServerWorld worldIn, BlockPos pos, BlockState state, Random rand) {
		if(state.get(STAGE) == 0) {
			worldIn.setBlockState(pos, state.cycle(STAGE), 4);
			
		} else {
			if(!ForgeEventFactory.saplingGrowTree(worldIn, rand, pos)) return;
			this.TREE.get().func_225545_a_(worldIn, worldIn.getChunkProvider().getChunkGenerator(), pos, state, rand);
		}
		
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		
		return SHAPE;
	}
	

	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		
		
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		this.grow(worldIn, pos, state, rand);

	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		// TODO Auto-generated method stub
		builder.add(STAGE);
	}

}
