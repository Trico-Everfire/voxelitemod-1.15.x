package com.tricoeverfire.voxelite.fluids;

import net.minecraftforge.fluids.ForgeFlowingFluid;

public class CustomFluidMechanics{


	public class ModFluidsFlowing extends ForgeFlowingFluid.Flowing implements ILiquidMoveMechanics{

		protected ModFluidsFlowing(Properties properties) {
			super(properties);
		
		}


	}
	
	public class ModFluidsSource extends ForgeFlowingFluid.Source implements ILiquidMoveMechanics{

		protected ModFluidsSource(Properties properties) {
			super(properties);
		
		}


	}


}
