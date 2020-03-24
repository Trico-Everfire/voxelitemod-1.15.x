package com.tricoeverfire.voxelite.world;

import java.util.function.LongFunction;

import com.tricoeverfire.voxelite.init.ModBiomes;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;

public class VoxeliteWorldType extends WorldType{

	public VoxeliteWorldType() {
		super("voxelite");
		
	}
	
@Override
public ChunkGenerator<?> createChunkGenerator(World world) {
	
	ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkgeneratortype4 = ChunkGeneratorType.SURFACE;
    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;
    
    SingleBiomeProviderSettings singlebiomeprovidersettings = biomeprovidertype.func_226840_a_(world.getWorldInfo()).setBiome(ModBiomes.VOXELITE_BIOME.get());
    return chunkgeneratortype4.create(world, biomeprovidertype.create(singlebiomeprovidersettings), chunkgeneratortype4.createSettings());

}
}
