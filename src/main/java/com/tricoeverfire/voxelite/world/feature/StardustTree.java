package com.tricoeverfire.voxelite.world.feature;

import java.util.Random;

import com.tricoeverfire.voxelite.init.ModBlocks;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class StardustTree extends Tree{
	
	public static final TreeFeatureConfig STARDUST_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.STARDUST_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.STARDUSTLEAVES.getDefaultState()), new BlobFoliagePlacer(0, 3)).baseHeight(14).heightRandA(5).foliageHeight(9).setSapling((IPlantable) ModBlocks.STARDUST_SAPLING).build());

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		
		return Feature.NORMAL_TREE.withConfiguration(STARDUST_TREE_CONFIG);
	}

}
