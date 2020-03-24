package com.tricoeverfire.voxelite.biomes;

import com.tricoeverfire.voxelite.biomes.features.ModFeatures;
import com.tricoeverfire.voxelite.biomes.generator.VoxeliteChunkGenerator;
import com.tricoeverfire.voxelite.biomes.generatorsettings.VoxeliteGeneratorSettings;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModFluids;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage.Decoration;

public class VoxeliteBiome extends Biome{

	public VoxeliteBiome(Builder biomeBuilder) {
		super(biomeBuilder);
	//	ModFeatures.addVoxeliteLakes(this);
	//	ModFeatures.addVoxeliteSprings(this);
	//	ModFeatures.addVoxeliteAvoklinateDisks(this);
		
	}
	

@Override
public void decorate(Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld worldIn,
		long seed, SharedSeedRandom random, BlockPos pos) {
	//System.out.println("sans");
	
	VoxeliteGeneratorSettings settings = new VoxeliteGeneratorSettings();
	settings.setDefaultBlock(ModBlocks.VOXELIZED_STONE.getDefaultState());
	settings.setDefaultFluid(ModFluids.voxelfluidsblock.get().getDefaultState());
	ChunkGenerator<?> chunkgen =  new VoxeliteChunkGenerator(worldIn, chunkGenerator.getBiomeProvider(),settings);
		
	super.decorate(stage, chunkgen, worldIn, seed, random, pos);
}

	
	
	
}
