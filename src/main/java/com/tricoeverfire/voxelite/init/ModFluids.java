package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.blocks.LiquidVoxelite;
import com.tricoeverfire.voxelite.items.BucketBase;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

	
//	public static final FlowingFluid VOXELITELIQUIDFLOWING = new VoxeliteLiquid.Flowing();
//	
//	public static final FlowingFluid VOXELITELIQUID = new VoxeliteLiquid.Source();

	public static final ResourceLocation VOXSTILL =  new ResourceLocation(Reference.MOD_ID+":blocks/liquid_voxelite_still");
	public static final ResourceLocation VOXFLOW =   new ResourceLocation(Reference.MOD_ID+":blocks/liquid_voxelite_flow");

    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Reference.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    

    
    public static RegistryObject<FlowingFluid> voxelfuildssource = FLUIDS.register("liquid_voxelite_still", () ->
    new ForgeFlowingFluid.Source(ModFluids.voxeliteLiquidProperties)
    );
    public static RegistryObject<FlowingFluid> voxelfuildsflowing = FLUIDS.register("liquid_voxelite_flow", () ->
   
    
    new ForgeFlowingFluid.Flowing(ModFluids.voxeliteLiquidProperties)
    );
    
    public static RegistryObject<FlowingFluidBlock> voxelfluidsblock = BLOCKS.register("liquid_voxelite", () ->
    	new LiquidVoxelite(voxelfuildssource, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())
	);
   
	public static RegistryObject<Item> voxelfluidbucket = ITEMS.register("emerald_bucket_voxelite", () ->
	    new BucketBase(voxelfuildssource, new Item.Properties().containerItem(ModItems.EMERALD_BUCKET_EMPTY).maxStackSize(1).group(ModItemGroups.voxelitemoditems))
	);
	
	public static final ForgeFlowingFluid.Properties voxeliteLiquidProperties = new ForgeFlowingFluid.Properties(voxelfuildssource, voxelfuildsflowing, FluidAttributes.builder(VOXSTILL,VOXFLOW).luminosity(12).density(5)).bucket(voxelfluidbucket).block(voxelfluidsblock);



//    public void loadComplete(FMLLoadCompleteEvent event)
//    {
//        // some sanity checks
//        BlockState state = Fluids.WATER.getDefaultState().getBlockState();
//        BlockState state2 = Fluids.WATER.getAttributes().getBlock(null,null,Fluids.WATER.getDefaultState());
//        Validate.isTrue(state.getBlock() == Blocks.WATER && state2 == state);
//        ItemStack stack = Fluids.WATER.getAttributes().getBucket(new FluidStack(Fluids.WATER, 1));
//        Validate.isTrue(stack.getItem() == Fluids.WATER.getFilledBucket());
//    }

	   
}
