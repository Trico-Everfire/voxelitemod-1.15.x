package com.tricoeverfire.voxelite.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.tricoeverfire.voxelite.commands.commands.ReloadModelsandBoundingBoxes;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandsAnnex{

	public CommandsAnnex(CommandDispatcher<CommandSource> dispatcher) {
		
		
		dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("voxelitemod")
				.then(ReloadModelsandBoundingBoxes.register())
				
				
				);
		
	}

}
