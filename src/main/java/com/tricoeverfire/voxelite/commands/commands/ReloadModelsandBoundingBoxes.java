package com.tricoeverfire.voxelite.commands.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class ReloadModelsandBoundingBoxes {

     public static ArgumentBuilder<CommandSource, ?> register() {
    	 
               return Commands.literal("reloadMABB")
            		   .requires(cs->cs.getDisplayName().getFormattedText() == "Dev" || true)
            		   .executes(ctx -> {
            			  ctx.getSource().sendFeedback(new TranslationTextComponent("commands.voxelitemod.reloadMABB"), true);
            			  System.out.println(ctx.getSource().getDisplayName().getFormattedText().contains("Dev"));
            			  	  Minecraft.getInstance().reloadResources();
            			  //Minecraft.getInstance().getModelManager().reload(stage, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor)
//            			  ForgeRegistries.BLOCKS.forEach((BL)->{
//            				
//            			  });
            			   return 0; 
            		   });
            		   										
    }
     

	
	
}
