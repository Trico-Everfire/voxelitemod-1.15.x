package com.tricoeverfire.voxelite.blocks.stateproperties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class ModStateProperties extends BlockStateProperties {
	public static final BooleanProperty ITEMHELD = BooleanProperty.create("itemheld");
	public static final BooleanProperty INVERSE = BooleanProperty.create("inverse");
//	public static final IntegerProperty PHASER_COLOR_SUPPORT = IntegerProperty.create("color", 0, 256*256*256);
	
}
