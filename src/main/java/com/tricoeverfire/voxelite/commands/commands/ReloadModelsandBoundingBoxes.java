package com.tricoeverfire.voxelite.commands.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class ReloadModelsandBoundingBoxes {

     @SuppressWarnings("deprecation")
	public static ArgumentBuilder<CommandSource, ?> register() {
    	 
               return Commands.literal("reloadMABB")
            		   .requires(cs->true)
            		   .executes(ctx -> {
            			  ctx.getSource().sendFeedback(new TranslationTextComponent("commands.voxelitemod.reloadMABB"), true);
            			 
            			  	  Minecraft.getInstance().reloadResources();

            			   return 0; 
            		   });
            		   										
    }
     

	
	
}
