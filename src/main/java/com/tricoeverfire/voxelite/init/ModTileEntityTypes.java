package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.tileentities.OWGTileEntity;
import com.tricoeverfire.voxelite.tileentities.PhaserTileEntity;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);
	
	
	public static final RegistryObject<TileEntityType<PhaserTileEntity>> PHASERTILEENTITY = TILE_ENTITY_TYPE.register("phaser_tile_entity", ()->
		TileEntityType.Builder.create(PhaserTileEntity::new, ModBlocks.HIDDENPHASERBLOCK).build(null)
	);
	
	public static final RegistryObject<TileEntityType<OWGTileEntity>> OWGTILEENTITY = TILE_ENTITY_TYPE.register("owg_tile_entity", ()->
	TileEntityType.Builder.create(OWGTileEntity::new, ModBlocks.HIDDENGLASSWALLING).build(null)	
	);
}
