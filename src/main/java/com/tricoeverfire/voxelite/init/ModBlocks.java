package com.tricoeverfire.voxelite.init;

import java.util.ArrayList;
import java.util.List;

import com.tricoeverfire.voxelite.blocks.AvokinateBlock;
import com.tricoeverfire.voxelite.blocks.AvokinateOre;
import com.tricoeverfire.voxelite.blocks.BleachedBlock;
import com.tricoeverfire.voxelite.blocks.BlockBase;
import com.tricoeverfire.voxelite.blocks.BlockDoorBase;
import com.tricoeverfire.voxelite.blocks.BlockFallingBase;
import com.tricoeverfire.voxelite.blocks.BlockLeafBase;
import com.tricoeverfire.voxelite.blocks.BlockLogBase;
import com.tricoeverfire.voxelite.blocks.BlockPillarBase;
import com.tricoeverfire.voxelite.blocks.BlockStairBase;
import com.tricoeverfire.voxelite.blocks.BlockWallBase;
import com.tricoeverfire.voxelite.blocks.CropsBlockBase;
import com.tricoeverfire.voxelite.blocks.FarmlandBlockBase;
import com.tricoeverfire.voxelite.blocks.IronFence;
import com.tricoeverfire.voxelite.blocks.IronFenceGate;
import com.tricoeverfire.voxelite.blocks.LightBulb;
import com.tricoeverfire.voxelite.blocks.OgoeBushBlock;
import com.tricoeverfire.voxelite.blocks.PhaserBlock;
import com.tricoeverfire.voxelite.blocks.ReinforcedGlass;
import com.tricoeverfire.voxelite.blocks.RotatableHiddenGlassBlock;
import com.tricoeverfire.voxelite.blocks.RoyalLight;
import com.tricoeverfire.voxelite.blocks.StardustGrowth;
import com.tricoeverfire.voxelite.blocks.StardustSapling;
import com.tricoeverfire.voxelite.blocks.TestBlockEntityPlayer;
import com.tricoeverfire.voxelite.blocks.VoxelizedOre;
import com.tricoeverfire.voxelite.util.enummodhandler.ModPlantType;
import com.tricoeverfire.voxelite.world.feature.StardustTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
//	public static final DeferredRegister<Block> DEFERREDBLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	
	//materials
//	public static final Material VOXELITELIQUID = (new MaterialLiquid(Material.IRON.getMaterialMapColor())).setNoPushMobility();;
	//public static final Material VOXELITE_LIQUID_MATERIAL = (new ModMaterialLiquid(MapColor.WATER)).getSetNoPushMobility();
	
	//blocks
	public static final Block BLEACHED_BLOCK = new BleachedBlock("bleached_block", Material.ROCK);
	public static final Block AVOKINATE_BLOCK = new AvokinateBlock("avokinate_block", Material.ROCK);
	public static final Block VOXELIZED_STONE = new BleachedBlock("voxelized_stone", Material.GLASS);
	public static final Block VOXELIZED_DIRT = new BlockBase("voxelized_dirt",Material.EARTH);
	public static final BlockFallingBase VOXELIZED_GRAVEL = new BlockFallingBase("voxelized_gravel", Material.SAND);
	public static final BlockFallingBase VOXELIZED_SAND = new BlockFallingBase("voxelized_sand",Material.SAND);
	public static final Block VOXELIZED_ORE = new VoxelizedOre("voxelized_ore",Material.ROCK);
	public static final Block VOXELIZED_LEAVES = new BlockLeafBase("voxelized_leaves",Material.LEAVES);
	public static final Block VOXELIZED_COBBLESTONE = new BleachedBlock("voxelized_cobblestone",Material.ROCK);
	public static final Block SPECIAL_WEAPONS_BLOCK = new BlockBase("special_weapons_block",Material.ROCK);
	public static final Block SPECIAL_WEAPONS_BLOCK_W_BORDER = new TestBlockEntityPlayer("special_weapons_block_w_border",Material.ROCK);
	public static final Block AVOKINATE_ORE = new AvokinateOre("avokinate_ore", Material.ROCK);
	public static final Block REINFORCED_GLASS = new ReinforcedGlass("reinforced_glass", Material.GLASS);
	public static final Block CRACKED_REINFORCED_GLASS = new ReinforcedGlass("cracked_reinforced_glass",Material.GLASS);
	public static final Block VOXELIZED_LOG = new BlockLogBase("voxelized_log");
	public static final IronFenceGate IRON_FENCE_GATE = new IronFenceGate("iron_fence_gate");
	public static final Block IRON_FENCE = new IronFence("iron_fence");
	public static final Block IRON_WALL = new BlockWallBase("iron_wall",Blocks.IRON_BLOCK);
	
	public static final Block STARDUST_DIRT = new BlockBase("stardust_dirt",Block.Properties.from(Blocks.DIRT));
	public static final Block FARSTONE = new BlockBase("far_stone",Material.EARTH);	
	public static final Block STARDUST_FARMLAND = new FarmlandBlockBase("stardust_farmland", ModBlocks.STARDUST_DIRT ,Block.Properties.from(Blocks.FARMLAND));
	
	public static final Block OGOE_BUSH = new OgoeBushBlock("ogoe_bush",Block.Properties.from(Blocks.SWEET_BERRY_BUSH));
//	public static final RegistryObject<Block> OGOE_BUSH = DEFERREDBLOCKS.register("ogoe_bush", ()->
//		new OgoeBushBlock("ogoe_bush",Block.Properties.from(Blocks.SWEET_BERRY_BUSH))
//	);
	public static final Block NEBU_WHEAT = new CropsBlockBase("nebu_wheat",new Block[] {ModBlocks.STARDUST_FARMLAND},ModItems.NEBUSEEDS,ModPlantType.STARDUST_PLANTS,Block.Properties.from(Blocks.WHEAT));
	
	
	
	public static final BlockBase BLACKSTONEBRICK = new BlockBase("black_stone_brick", Material.ROCK);


	public static final Block BLACKSTONEDOOR = new BlockDoorBase("black_stone_door",Material.ROCK,null,null,true,false);
	public static final Block BLACKSTONEDOORLOCKED = new BlockDoorBase("black_stone_door_locked",Material.ROCK,ModItems.BLACK_STONE_DUNGEON_KEY,ModBlocks.BLACKSTONEDOOR,false,true);
	public static final Block BLUE_DUNGEON_DOOR = new BlockDoorBase("blue_dungeon_door",Material.ROCK,null,null,true,false);
	public static final Block BLUE_DUNGEON_DOOR_LOCKED = new BlockDoorBase("blue_dungeon_door_locked",Material.ROCK,ModItems.BLUE_DUNGEON_KEY,ModBlocks.BLUE_DUNGEON_DOOR,false,true);
	
	public static final Block ROYALLANTERN = new RoyalLight("royal_lantern", Material.GLASS);
	public static final Block LIGHTBULB = new LightBulb("light_bulb",15,Material.GLASS,false,true);
	public static final BlockBase BLUEDUNGEONBLOCK = new BlockBase("blue_dungeon_block", Material.IRON);
	public static final BlockBase BLUEDUNGEONCEILING = new BlockBase("blue_dungeon_ceiling", Material.ROCK);
	public static final BlockBase BLUEDUNGEONFLOOR = new BlockBase("blue_dungeon_floor", Material.ROCK);
	public static final Block BLUEDUNGEONFLOORSTAIRS = new BlockStairBase("blue_dungeon_floor_stairs",BLUEDUNGEONFLOOR.getDefaultState());
	public static final Block BLUEDUNGEONWALL = new BlockPillarBase("blue_dungeon_wall", Material.ROCK,false);
	public static final Block SMOOTHBLUEDUNGEONWALL = new BlockBase("smooth_blue_dungeon_wall",Material.ROCK);
	
	public static final Block ROYALLANTERNUNBREAKABLE = new RoyalLight("royal_lantern_unbreakable", Material.GLASS,true);
	public static final BlockBase BLACKSTONEBRICKUNBREAKABLE = new BlockBase("black_stone_brick_unbreakable", Material.ROCK,true);
	public static final Block BLACKSTONEUNBREAKABLE = new BlockBase("black_stone_unbreakable", Material.ROCK,true);
	public static final BlockBase BLUEDUNGEONBLOCKUNBREAKABLE = new BlockBase("blue_dungeon_block_unbreakable", Material.IRON,true);
	public static final BlockBase BLUEDUNGEONCEILINGUNBREAKABLE = new BlockBase("blue_dungeon_ceiling_unbreakable", Material.ROCK,true);
	public static final BlockBase BLUEDUNGEONFLOORUNBREAKABLE = new BlockBase("blue_dungeon_floor_unbreakable", Material.ROCK,true);
	public static final Block BLUEDUNGEONFLOORSTAIRSUNBREAKABLE = new BlockStairBase("blue_dungeon_floor_stairs_unbreakable",BLUEDUNGEONFLOORUNBREAKABLE.getDefaultState(),true);
	public static final Block BLUEDUNGEONWALLUNBREAKABLE = new BlockPillarBase("blue_dungeon_wall_unbreakable", Material.ROCK,true);
	public static final Block SMOOTHBLUEDUNGEONWALLUNBREAKABLE = new BlockBase("smooth_blue_dungeon_wall_unbreakable",Material.ROCK,true);
	public static final Block GLOWINGDUNGEONCEILINGBLOCKUNBREAKABLE = new RoyalLight("glowing_dungeon_ceiling_block_unbreakable", Material.ROCK,true);

	//hidden/unassigned
	public static final Block HIDDENGLASSWALLING = new RotatableHiddenGlassBlock("blue_dungeon_stone_hidden_glass", ModBlocks.REINFORCED_GLASS);
	public static final Block HIDDENPHASERBLOCK = new PhaserBlock("phaser_block", Material.ROCK, ModItems.PHASER_CRYSTAL, true);
	
	public static final Block GLOWINGDUNGEONCEILINGBLOCK = new RoyalLight("glowing_dungeon_ceiling_block", Material.ROCK);
	public static final Block KITSUNEBLOCK = new BlockBase("kitsune_block", Material.ROCK);
	
	public static final Block STARDUSTGROWTH = new StardustGrowth("stardust_growth", Material.EARTH);
	public static final Block BLACKSTONE = new BlockBase("black_stone", Material.ROCK);
	public static final Block STARDUSTLEAVES = new BlockLeafBase("stardust_leaves", Material.ROCK);
	public static final Block STARDUST_LOG = new BlockLogBase("stardust_log");


	public static final Block STARDUST_SAPLING = new StardustSapling("stardust_sapling", ()->new StardustTree(), Block.Properties.from(Blocks.OAK_SAPLING));
	
	//liquids
	//public static final Block LIQUID_VOXELITE = new LiquidVoxeliteBlock("liquid_voxelite",ModFluid.LIQUID_VOXELITE,Material.WATER);
	//public static final Block LIQUID_VOXELITE = new FlowingLiquidBlockBase("liquid_voxelite",new VoxeliteLiquid.Source());
	
	//plants
	public static final Block ALPHA_BERRY_PLANT = new CropsBlockBase("alpha_berry_plant",new Block[] {ModBlocks.STARDUST_FARMLAND},ModItems.ALPHA_BERRY,ModPlantType.STARDUST_PLANTS,true,Block.Properties.from(Blocks.WHEAT));
	public static final Block OMEGA_BERRY_PLANT = new CropsBlockBase("omega_berry_plant",new Block[] {ModBlocks.STARDUST_FARMLAND},ModItems.OMEGA_BERRY,ModPlantType.STARDUST_PLANTS,true,Block.Properties.from(Blocks.WHEAT));
	
	//guiables
//	public static final Block GEARBENCH = new BlockGearBench("gearbench",Material.WOOD);
	

}