package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.entities.SkelebatEntity;
import com.tricoeverfire.voxelite.entities.SlugEntity;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, Reference.MOD_ID);
	
	public static final RegistryObject<EntityType<VoxivanEntity>> VOXIVAN_ENTITY = ENTITIES.register("voxivan", ()-> EntityType.Builder.<VoxivanEntity>create(VoxivanEntity::new, EntityClassification.MONSTER).size(0.8f,2.4f).build(Main.location("voxivan").toString())); //create(VoxivanEntity::new, EntityClassification.MONSTER})
	public static final RegistryObject<EntityType<SlugEntity>> SLUG_ENTITY = ENTITIES.register("slug", ()-> EntityType.Builder.<SlugEntity>create(SlugEntity::new, EntityClassification.AMBIENT).size(0.4f,0.4f).build(Main.location("slug").toString()));
	public static final RegistryObject<EntityType<SkelebatEntity>> SKELEBAT_ENTITY = ENTITIES.register("skelebat", ()-> EntityType.Builder.<SkelebatEntity>create(SkelebatEntity::new, EntityClassification.MONSTER).size(0.5F, 0.9F).build(Main.location("skelebat").toString()));
	
//	public final NonNullLazy<EntityType<VoxivanEntity>> spellBallInit = NonNullLazy.of(() -> EntityType.Builder.<VoxivanEntity>create(VoxivanEntity::new, EntityClassification.MONSTER)
//            .size(0.8f,2.4f)
//            .setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build(Main.location("voxivan").toString()));

	//	public final NonNullLazy<EntityType<EssenceEntity>> essenceInit = NonNullLazy.of(() -> EntityType.Builder.<EssenceEntity>create(EssenceEntity::new, ESSENCE_CLASSIFICATION)
//            .size(0.5f, 0.5f)
//            .setTrackingRange(20).setUpdateInterval(5).setShouldReceiveVelocityUpdates(true).build(location("essence").toString()));
//	


}
