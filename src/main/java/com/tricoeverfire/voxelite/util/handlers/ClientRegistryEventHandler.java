package com.tricoeverfire.voxelite.util.handlers;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.init.ModParticles;
import com.tricoeverfire.voxelite.particles.ParticleBase;
import com.tricoeverfire.voxelite.tileentities.PhaserTileEntity;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistryEventHandler {
    
	public static final IBlockColor mycolors = (state, reader, pos, renderpass) -> {
        try {
        	PhaserTileEntity tileEntity = (PhaserTileEntity) reader.getTileEntity(pos);

		//System.out.println(tileEntity.getColor());

		String a = tileEntity.getColor();
   	 int i;
   	 if(a=="") {
   		// System.out.println(a);
   		 //here's the fault
   		  i = 0xffffff; //nteger.parseInt(a);
   	 } else if (a.contains("0x")) {
   	try {
   		i = Integer.decode(a);
   	} catch(NumberFormatException e) {
   		//System.out.println(e);
   		i = 0xffffff;
   	}
   	 
   	 }
   	 else {
   		  i = 0xffffff;
   	 }
		
 //  	 System.out.println(i);
   	 
		return i;
        } catch(Exception ex) {
        //	System.out.println(ex);
        	return 0xffffff;
        }
     };
	
     public static final IItemColor mycolorsItem = (stack, pass)->{
    	// System.out.println(stack.getOrCreateTag().getInt("color"));
    	 String a = stack.getOrCreateTag().getString("color");
    	// System.out.println(a);
    	 int i;
    	 if(a=="") {
    		// System.out.println(a);
    		  i = 0xffffff; //nteger.parseInt(a);
    	 } else if (a.contains("0x")) {
    	try {
    		i = Integer.decode(a);
    	} catch(NumberFormatException e) {
    		
    		i = 0xffffff;
    	}
    	 
    	 }
    	 else {
    		  i = 0xffffff;
    	 }
    	 
    	 return i;//(int) stack.getOrCreateTag().getInt("color");
     };
     
     @SubscribeEvent
     public static void onBlockColorRegister(ColorHandlerEvent.Block event) {
//BlockColors.init().register(mycolors, ModBlocks.HIDDENPHASERBLOCK);
//BlockColors.init().register(blockColor, blocksIn);
    	 //    	 BlockColors colors = new BlockColors();
//    	 colors.register(mycolors, ModBlocks.HIDDENPHASERBLOCK);
    	 
     event.getBlockColors().register(mycolors, ModBlocks.HIDDENPHASERBLOCK);
     }
     
     @SubscribeEvent
     public static void onItemColorRegister(ColorHandlerEvent.Item event) {
    
	event.getItemColors().register(mycolorsItem, ModBlocks.HIDDENPHASERBLOCK.asItem(),ModItems.PHASER_CRYSTAL);
     }
     
    @SubscribeEvent()
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(ModParticles.STARDUST_GROWTH_BLOCK_PARTICLE.get(), ParticleBase.Factory::new);

        Main.LOGGER.info("Registered Particle Factories");
    }
    

    
}