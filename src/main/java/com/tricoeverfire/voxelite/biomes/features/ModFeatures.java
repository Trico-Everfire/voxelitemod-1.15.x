package com.tricoeverfire.voxelite.biomes.features;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.tricoeverfire.voxelite.biomes.VoxeliteBiome;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModFluids;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class ModFeatures {

	
	public static LiquidsConfig liquonfig = new LiquidsConfig(ModFluids.voxelfluidsblock.get().getFluid().getDefaultState(), true, 4, 1, ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE));
	
	   public static void addVoxeliteAvoklinateDisks(VoxeliteBiome biomeIn) {
	      biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(ModBlocks.AVOKINATE_ORE.getDefaultState(), 4, 1, Lists.newArrayList(ModBlocks.BLEACHED_BLOCK.getDefaultState(), ModBlocks.AVOKINATE_ORE.getDefaultState()))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));

	   }
	
	
	   public static void addVoxeliteSprings(VoxeliteBiome biomeIn) {
		    // biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(liquonfig).withPlacement(Placement.COUNT_BIASED_RANGE.configure(new CountRangeConfig(50, 8, 8, 256))));
		   // DefaultBiomeFeatures.addSprings(biomeIn);
	   }
	   
	
	
	   public static void addVoxeliteLakes(VoxeliteBiome biomeIn) {
		     biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(ModFluids.voxelfluidsblock.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
	   }
	
}
