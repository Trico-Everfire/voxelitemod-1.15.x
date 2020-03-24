package com.tricoeverfire.voxelite.init;


import java.util.Collection;

import com.tricoeverfire.voxelite.biomes.VoxeliteBiome;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {

	
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,Reference.MOD_ID);
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES,Reference.MOD_ID);
	
//	public static final RegistryObject <Feature<?>> VOXELITELAKES = FEATURES.register("voxelitelakes", ()->
//	new SpringFeature(ModFeatures.liquonfig::deserialize)
//			);
	
	public static final RegistryObject<Biome> VOXELITE_BIOME = BIOMES.register("voxelite_biome", ()-> new VoxeliteBiome(new Biome.Builder().waterColor(0xffffff).waterFogColor(0xffffff).precipitation(RainType.RAIN).scale(1.2f).temperature(-0.5f).downfall(0.5f).depth(0.13f).category(Biome.Category.NONE).parent(null)
			.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(ModBlocks.BLEACHED_BLOCK.getDefaultState(),ModBlocks.VOXELIZED_STONE.getDefaultState(),ModBlocks.VOXELIZED_GRAVEL.getDefaultState()))));
	
	public static void registerBiomes() {
		registerBiome(VOXELITE_BIOME.get(),Type.getAll());
	}

	private static void registerBiome(Biome biome, Collection<Type> collection) {
	
		
		BiomeDictionary.addTypes(biome,  collection.toArray(new Type[collection.size()]));
		BiomeManager.addSpawnBiome(biome);
	}
	
	@SuppressWarnings("unused")
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
}
