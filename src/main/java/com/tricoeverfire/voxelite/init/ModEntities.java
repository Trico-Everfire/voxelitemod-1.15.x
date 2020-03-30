package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, Reference.MOD_ID);
	
	public static final RegistryObject<EntityType<VoxivanEntity>> VOXIVAN_ENTITY = ENTITIES.register("voxivan", ()-> EntityType.Builder.<VoxivanEntity>create(VoxivanEntity::new, EntityClassification.MONSTER).size(2f,4f).build(Main.location("voxivan").toString())); //create(VoxivanEntity::new, EntityClassification.MONSTER})

	


}
