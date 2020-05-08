package com.tricoeverfire.voxelite.tileentities;

import com.tricoeverfire.voxelite.init.ModTileEntityTypes;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class OWGTileEntity extends TileEntity{

	
	
	public OWGTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		
	}
	
	public OWGTileEntity() {
		this(ModTileEntityTypes.OWGTILEENTITY.get());
	}

	
	
	
}
