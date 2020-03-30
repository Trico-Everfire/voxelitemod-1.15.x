package com.tricoeverfire.voxelite;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tricoeverfire.voxelite.init.ModBiomes;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModEntities;
import com.tricoeverfire.voxelite.init.ModFluids;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.init.ModParticles;
import com.tricoeverfire.voxelite.items.BucketBase;
import com.tricoeverfire.voxelite.util.Reference;
import com.tricoeverfire.voxelite.world.VoxeliteWorldType;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("voxelitemod")
public class Main
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    
    
    
    public Main() {
    	
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        // Register the setup method for modloading
    	modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
    	modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
    	modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);


        ModFluids.FLUIDS.register(modEventBus);
        ModFluids.BLOCKS.register(modEventBus);
        ModFluids.ITEMS.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        

    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	 new VoxeliteWorldType();
    	Biome.BIOMES.forEach((biome)->{
    	//	biome.addStructure(structureIn);
    	});
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    	
    	
    	
    	
    	RenderTypeLookup.setRenderLayer(ModBlocks.REINFORCED_GLASS, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(ModBlocks.CRACKED_REINFORCED_GLASS, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(ModBlocks.BLACKSTONEDOOR, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(ModBlocks.BLACKSTONEDOORLOCKED, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(ModBlocks.LIGHTBULB, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(ModBlocks.STARDUSTGROWTH, RenderType.getCutout());
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        	for(Block block : ModBlocks.BLOCKS) {
        		blockRegistryEvent.getRegistry().register(block);
        	}

            LOGGER.info("HELLO from Register Block");
        }
        
        @SubscribeEvent
        public static void onEnchantmentRegistry(final RegistryEvent.Register<Enchantment> event) {
      
        	for (Enchantment enchants : ModEnchantments.ENCHANTMENTS) {
        	event.getRegistry().register(enchants);
        	}
        }
        
        @SubscribeEvent
       	public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new item here
        	for(Item item : ModItems.ITEMS) {
        		itemRegistryEvent.getRegistry().register(item);
        		
        	}
        	
        	RegistryManager.ACTIVE.getRegistry(Fluid.class).forEach((fluid)->{
        		if(fluid.isSource(fluid.getDefaultState())) {
        			itemRegistryEvent.getRegistry().register(new BucketBase((fluid.getRegistryName()+"_"+Reference.MOD_ID+"_bucket").replace(":",""),fluid,new Item.Properties().group(ModItemGroups.voxelitemoditems).maxStackSize(1)));
        		}
        	//  itemRegistryEvent.getRegistry().register(ModFluids.voxelfluidbucket.get());
        	});
     //      ModFluids.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
            LOGGER.info("HELLO from Register Item");
    	}
        
        
        
        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> biomeRegistryEvent) {
        	ModBiomes.registerBiomes();
        	//biomeRegistryEvent.getRegistry().register(value);
        }
        
        
    }
    
    
    
    public static ResourceLocation location(String string) {
    	return new ResourceLocation(Reference.MOD_ID,string);
    }
}
