package com.tricoeverfire.voxelite.models;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.tricoeverfire.voxelite.Main;

import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverride;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("deprecation")
public class OWGBakedModel extends BlockModel{
	public static final ResourceLocation SIDE_TEXTURE = Main.location("blue_dungeon_stone_hidden_glass");


	public OWGBakedModel(ResourceLocation parentLocation, List<BlockPart> elements, Map<String, Either<Material, String>> textures, boolean ambientOcclusion, GuiLight guiLight, ItemCameraTransforms cameraTransforms, List<ItemOverride> overrides) {
		super(parentLocation, elements, textures, ambientOcclusion, guiLight, cameraTransforms, overrides);
		
		
		
		
	}
@Override
public Collection<Material> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter,
		Set<Pair<String, String>> missingTextureErrors) {
	
	
	return super.getTextures(modelGetter, missingTextureErrors);
}
	

}
