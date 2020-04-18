package com.tricoeverfire.voxelite.entities.renderer;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.entities.models.EntityVoxivanModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class VoxivanEntityRenderer extends MobRenderer<VoxivanEntity, EntityVoxivanModel<VoxivanEntity>>{

	protected static final ResourceLocation TEXTURE = Main.location("textures/entity/voxivan.png");
	
	public VoxivanEntityRenderer(EntityRendererManager managerIn){
		super(managerIn, new EntityVoxivanModel<VoxivanEntity>(), 0.5f);
		this.addLayer(new HeldItemLayer<VoxivanEntity, EntityVoxivanModel<VoxivanEntity>>(this));
		this.addLayer(new BipedArmorLayer<>(this, new EntityVoxivanModel<VoxivanEntity>(0.5F, true),  new EntityVoxivanModel<VoxivanEntity>(1.0F, true)));
	}
	
	@Override
	public ResourceLocation getEntityTexture(VoxivanEntity entity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}
}
