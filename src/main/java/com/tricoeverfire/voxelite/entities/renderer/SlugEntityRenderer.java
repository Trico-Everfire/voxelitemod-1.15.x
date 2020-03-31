package com.tricoeverfire.voxelite.entities.renderer;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.entities.SlugEntity;
import com.tricoeverfire.voxelite.entities.VoxivanEntity;
import com.tricoeverfire.voxelite.entities.models.EntitySlugModel;
import com.tricoeverfire.voxelite.entities.models.EntityVoxivanModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SlugEntityRenderer extends MobRenderer<SlugEntity, EntitySlugModel<SlugEntity>>{

	protected static final ResourceLocation TEXTURE = Main.location("textures/entity/slug.png");
	
	public SlugEntityRenderer(EntityRendererManager managerIn){
		super(managerIn, new EntitySlugModel<SlugEntity>(), 0.5f);
		
	}
	
	@Override
	public ResourceLocation getEntityTexture(SlugEntity entity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}
}
