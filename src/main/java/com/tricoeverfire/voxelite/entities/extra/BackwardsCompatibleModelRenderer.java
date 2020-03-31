package com.tricoeverfire.voxelite.entities.extra;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BackwardsCompatibleModelRenderer extends ModelRenderer {

	public BackwardsCompatibleModelRenderer(Model model) {
		super(model);
	
	}
	
	public ModelRenderer setBoxLayout(ModelRenderer renderer, int toffsetX, int toffsetY, float x, float y, float z, int size , int height, int depth, float scale, boolean probably) {
		renderer.setTextureOffset(toffsetX,toffsetY).addBox(x, y, z, size, height, depth, probably);
		
		return renderer;
	}

}
