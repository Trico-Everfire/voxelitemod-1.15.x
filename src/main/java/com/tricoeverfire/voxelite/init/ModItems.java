package com.tricoeverfire.voxelite.init;

import java.util.ArrayList;
import java.util.List;

import com.tricoeverfire.voxelite.items.ArmorBuilder;
import com.tricoeverfire.voxelite.items.AvokinateCrystal;
import com.tricoeverfire.voxelite.items.AvokinatePickaxe;
import com.tricoeverfire.voxelite.items.BlockNamedItemBase;
import com.tricoeverfire.voxelite.items.BucketBase;
import com.tricoeverfire.voxelite.items.ElytraItemBase;
import com.tricoeverfire.voxelite.items.FoodItem;
import com.tricoeverfire.voxelite.items.ItemBase;
import com.tricoeverfire.voxelite.items.RedstoneZapper;
import com.tricoeverfire.voxelite.items.SpawnEggBase;
import com.tricoeverfire.voxelite.items.ToolAxe;
import com.tricoeverfire.voxelite.items.ToolCompass;
import com.tricoeverfire.voxelite.items.ToolHammer;
import com.tricoeverfire.voxelite.items.ToolHoe;
import com.tricoeverfire.voxelite.items.ToolPickaxe;
import com.tricoeverfire.voxelite.items.ToolSpade;
import com.tricoeverfire.voxelite.items.ToolSword;
import com.tricoeverfire.voxelite.util.Reference;
import com.tricoeverfire.voxelite.util.enummodhandler.ModArmorMaterials;
import com.tricoeverfire.voxelite.util.enummodhandler.ModItemTier;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> DEFERREDITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);


	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<Item> COLORABLE = new ArrayList<Item>();
	
	//materiels
	public static final ModItemTier MATERIAL_AVOKINATE = ModItemTier.AVOKINATE;
	public static final ModItemTier CHARGED_MATERIAL_AVOKINATE = ModItemTier.CHARGED_AVOKINATE;
	

	//public static final ArmorMaterial ARMOR_MATERIAL_AVOKINATE = EnumHelper.addArmorMaterial("armor_material_avokinate", Reference.MOD_ID + ":avokinate", 14, new int[] {2,7,5,3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F);
	//public static final ToolMaterial MATERIAL_IRONREINFORCED = EnumHelper.addToolMaterial("material_ironreinforced", 3, 1250, 5.0F, 4.0F, 11);
	//public static final EnumAction SWING = EnumHelper.addAction("swing");
	
		
//		public static final ToolMaterial WHITE = EnumHelper.addToolMaterial("white", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial GREEN = EnumHelper.addToolMaterial("green", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial RED = EnumHelper.addToolMaterial("red", 2, 1050, 4.0F, 10.0F, 11);
//		public static final ToolMaterial BLACK = EnumHelper.addToolMaterial("black", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial ORANGE = EnumHelper.addToolMaterial("orange", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial("bronze", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial YELLOW = EnumHelper.addToolMaterial("yellow", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial PURPLE = EnumHelper.addToolMaterial("purple", 2, 1050, 4.0F, 4.0F, 11);
//		public static final ToolMaterial BLUE = EnumHelper.addToolMaterial("blue", 2, 3000, 4.0F, 4.0F, 11);
//		public static final InsertGem PINKLE = new InsertGem("henry",0x0048ff,2,3000,4.0F,4.0F,11);
	

	public static final RegistryObject<SpawnEggBase> VOXIVANSPAWNEGG = DEFERREDITEMS.register("voxivan_spawn_egg", () -> new SpawnEggBase(ModEntities.VOXIVAN_ENTITY, 0xFFFFFF, 0xf07fae, new Item.Properties().group(ModItemGroups.voxelitemoditems)));
	public static final RegistryObject<SpawnEggBase> SLUGSPAWNEGG = DEFERREDITEMS.register("slug_spawn_egg", () -> new SpawnEggBase(ModEntities.SLUG_ENTITY, 0xFFFFFF, 0x9ef4ff, new Item.Properties().group(ModItemGroups.voxelitemoditems)));
	public static final RegistryObject<SpawnEggBase> SKELEBATSPAWNEGG = DEFERREDITEMS.register("skelebat_spawn_egg", () -> new SpawnEggBase(ModEntities.SKELEBAT_ENTITY, 0xFFFFFF, 0xb8b8b8, new Item.Properties().group(ModItemGroups.voxelitemoditems)));
	
//	public static final GemBase BLANKSLATE = new GemBase("blue_breaker_blade","breaker_blade",0x0048ff,2,3000,4.0F,4.0F,-1.4F,11F);
	
	
	//spawn eggs
//	public static final Item VOXIVANSPAWNITEM = new SpawnEggBase("voxivan_spawn_egg", ModEntities.VOXIVAN_ENTITY.get() ,0xffffff,0x000000, new Properties());
	
	//items
	public static final Item EMERALD_BUCKET_EMPTY = new BucketBase("emerald_bucket_empty", Fluids.EMPTY, new Item.Properties().group(ModItemGroups.voxelitemoditems).maxStackSize(1));
	public static final Item PHASER_CRYSTAL = new ItemBase("phaser_crystal") {
		public void addInformation(net.minecraft.item.ItemStack stack, net.minecraft.world.World worldIn, java.util.List<net.minecraft.util.text.ITextComponent> tooltip, net.minecraft.client.util.ITooltipFlag flagIn) {
			tooltip.add(new TranslationTextComponent(""));
			
		};
		public void fillItemGroup(net.minecraft.item.ItemGroup group, net.minecraft.util.NonNullList<net.minecraft.item.ItemStack> items) {
			
			if (this.isInGroup(group)) {
				
				ItemStack crystal = new ItemStack(this);
				ItemStack blueCrystal = new ItemStack(this);
				blueCrystal.getOrCreateTag().putString("color", "0x7384AC");
				
				items.add(crystal);
				items.add(blueCrystal);
			}
		};
	};
	
	//public static final BucketItem EMERALD_BUCKET_VOXELITE = new BucketBase("emerald_bucket_voxelite", ModFluids.voxelfuildssource, new Item.Properties().containerItem(ModItems.EMERALD_BUCKET_EMPTY).maxStackSize(1).group(ModItemGroups.voxelitemoditems));
	public static final Item AVOKINATE_CRYSTAL = new AvokinateCrystal("avokinate_crystal");
	public static final Item CHARGED_AVOKINATE_CRYSTAL = new ItemBase("charged_avokinate_crystal");
	public static final Item AVOKINATE_DUST = new ItemBase("avokinate_dust");
	public static final Item IRON_STICK = new ItemBase("iron_stick");
	public static final Item AVOKINATE_MEDAL = new ItemBase("avokinate_brace");
	public static final Item VOXELIZED_SLUDGE = new ItemBase("voxelized_sludge");
	public static final Item IRON_WIRE = new ItemBase("iron_wire");
	public static final Item REDSTONE_CHARGE = new ItemBase("redstone_charge");
	public static final Item VOXELIZED_CHUNK = new ItemBase("voxelized_chunk");
	public static final Item PEARL = new ItemBase("pearl");
	public static final ItemBase GRATER = new ItemBase("grater");
	public static final ItemBase KNIFE = new ItemBase("knife");
	public static final ItemBase MASHER = new ItemBase("masher");
	public static final ItemBase PEARLPOUCH = new ItemBase("pearl_pouch");
	public static final ItemBase SEAORB = new ItemBase("sea_orb");
	public static final ItemBase STILZSGEM = new ItemBase("stilzs_gem");
	//public static final ItemBase STILZSGEMDISTINGUISHEDOUTLINE = new ItemBase("stilzs_gem_distinguished_outline");
	public static final Item NEBUSEEDS = new BlockNamedItemBase("nebu_wheat_seeds",ModBlocks.NEBU_WHEAT,new Item.Properties().group(ModItemGroups.voxelitemoditems));
	public static final Item EBUWHEAT = new ItemBase("nebu_wheat");
	//need textures
	public static final Item BRONZE_CHUNK = new ItemBase("bronze_chunk");
	public static final Item BLACK_GEM = new ItemBase("black_gem");
	public static final Item RED_GEM = new ItemBase("red_gem");
	public static final Item ORANGE_GEM = new ItemBase("orange_gem");
	public static final Item GREEN_GEM = new ItemBase("green_gem");
	public static final Item WHITE_GEM = new ItemBase("white_gem");
	public static final Item BLUE_GEM = new ItemBase("blue_gem");
	public static final Item PURPLE_GEM = new ItemBase("purple_gem");
	public static final Item YELLOW_GEM = new ItemBase("yellow_gem");
	public static final Item GOLDEN_COMPASS = new ToolCompass("golden_compass");
	
	
	//tools
	public static final Item AVOKINATE_PICKAXE = new AvokinatePickaxe("avokinate_pickaxe", MATERIAL_AVOKINATE);
	public static final Item CHARGED_AVOKINATE_PICKAXE = new AvokinatePickaxe("charged_avokinate_pickaxe", CHARGED_MATERIAL_AVOKINATE);
	public static final Item AVOKINATE_SWORD = new ToolSword("avokinate_sword",MATERIAL_AVOKINATE, 10 , 6);
	public static final Item CHARGED_AVOKINATE_SWORD = new ToolSword("charged_avokinate_sword",CHARGED_MATERIAL_AVOKINATE,14,7);
	public static final Item AVOKINATE_SPADE = new ToolSpade("avokinate_spade",MATERIAL_AVOKINATE);
	public static final Item CHARGED_AVOKINATE_SPADE = new ToolSpade("charged_avokinate_spade",CHARGED_MATERIAL_AVOKINATE);
	public static final Item AVOKINATE_AXE = new ToolAxe("avokinate_axe",MATERIAL_AVOKINATE, 6.0F,-3.2F);
	public static final Item CHARGED_AVOKINATE_AXE = new ToolAxe("charged_avokinate_axe",CHARGED_MATERIAL_AVOKINATE,8.0F,0.5F);
	public static final Item AVOKINATE_HOE = new ToolHoe("avokinate_hoe",MATERIAL_AVOKINATE);
	public static final Item CHARGED_AVOKINATE_HOE = new ToolHoe("charged_avokinate_hoe", CHARGED_MATERIAL_AVOKINATE);
//	public static final ItemShears DIAMOND_SHEARS = new ToolShears("diamond_shears");
//	public static final ItemBow IRON_BOW = new ToolBow("iron_bow");
//	public static final ItemFishingRod IRON_FISHING_ROD = new ToolFishingRod("iron_fishing_rod");
	//public static final ItemArrow IRON_ARROW = new ToolArrow("iron_arrow");
	//public static final ItemArrow AVOKINATE_TIPPED_ARROW = new ToolArrow("avokinate_tipped_arrow");
	
	
	//armor
	public static final Item[] AVOKINATE_ARMOR = new ArmorBuilder("avokinate", ModArmorMaterials.AVOKINATE, new Item.Properties().maxStackSize(1).group(ModItemGroups.voxelitemoditems)).getArmorList();
	public static final Item AVOKINATE_HELMET = AVOKINATE_ARMOR[0];//new ArmorBase("avokinate_helmet",ARMOR_MATERIAL_AVOKINATE,1,EntityEquipmentSlot.HEAD);
	public static final Item AVOKINATE_CHESTPLATE = AVOKINATE_ARMOR[1];//new ArmorBase("avokinate_chestplate",ARMOR_MATERIAL_AVOKINATE,1,EntityEquipmentSlot.CHEST);
	public static final Item AVOKINATE_LEGGINGS = AVOKINATE_ARMOR[2];//new ArmorBase("avokinate_leggings",ARMOR_MATERIAL_AVOKINATE,2,EntityEquipmentSlot.LEGS);
	public static final Item AVOKINATE_BOOTS = AVOKINATE_ARMOR[3];//new ArmorBase("avokinate_boots",ARMOR_MATERIAL_AVOKINATE,1,EntityEquipmentSlot.FEET);
	
	
	//food
	public static final FoodItem ALPHA_BERRY = new FoodItem("alpha_berry",ModBlocks.ALPHA_BERRY_PLANT, 2, false);
	public static final FoodItem OMEGA_BERRY = new FoodItem("omega_berry",ModBlocks.OMEGA_BERRY_PLANT, 2, false);
	public static final FoodItem STARBUN = new FoodItem("star_bun",12,false);
	public static final FoodItem EMPTYJAR = new FoodItem("empty_jar",2,false);
	public static final FoodItem MINECRAFTEARTHGUMMY = new FoodItem("minecraft_earth_gummy",2,false);
	public static final FoodItem ONION = new FoodItem("onion",2,false);
	public static final FoodItem ONIONSLICE = new FoodItem("onion_slice",2,false);
	public static final FoodItem PICKLE = new FoodItem("pickle",2,false);
	public static final FoodItem PICKLESLICE = new FoodItem("pickle_slice",2,false);
	public static final FoodItem RARESTARDUSTLEAF = new FoodItem("rare_stardust_leaf",2,false);
	public static final FoodItem SWISSCHEESE = new FoodItem("swiss_cheese",2,false);
	public static final FoodItem SWISSCHEESESLICE = new FoodItem("swiss_cheese_slice",2,false);
	public static final FoodItem TOMATO = new FoodItem("tomato",2,false);
	public static final FoodItem TOMATOSLICE = new FoodItem("tomato_slice",2,false);
	public static final FoodItem VOXELIZEDAPPLE = new FoodItem("voxelized_apple",2,false);
	public static final FoodItem VOXELIZEDBEEF = new FoodItem("voxelized_beef",2,false);
	public static final FoodItem VOXELIZEDBEETROOT = new FoodItem("voxelized_beetroot",2,false);
	public static final FoodItem VOXELIZEDBREAD = new FoodItem("voxelized_bread",2,false);
	public static final FoodItem VOXELIZEDCARROT = new FoodItem("voxelized_carrot",2,false);
	public static final FoodItem VOXELIZEDCHICKEN = new FoodItem("voxelized_chicken",2,false);
	public static final FoodItem VOXELIZEDCLOWNFISH = new FoodItem("voxelized_clownfish",2,false);
	public static final FoodItem VOXELIZEDCOOKIE = new FoodItem("voxelized_cookie",2,false);
	public static final FoodItem VOXELIZEDFISH = new FoodItem("voxelized_fish",2,false);
	public static final FoodItem VOXELIZEDGOLDENAPPLE = new FoodItem("voxelized_golden_apple",2,false);
	public static final FoodItem VOXELIZEDMELON = new FoodItem("voxelized_melon",2,false);
	public static final FoodItem VOXELIZEDMUTTON = new FoodItem("voxelized_mutton",2,false);
	public static final FoodItem VOXELIZEDPORK = new FoodItem("voxelized_pork",2,false);
	public static final FoodItem VOXELIZEDPOTATO = new FoodItem("voxelized_potato",2,false);
	public static final FoodItem VOXELIZEDPUMPKINPIE = new FoodItem("voxelized_pumpkin_pie",2,false);
	public static final FoodItem VOXELIZEDRABBIT = new FoodItem("voxelized_rabbit",2,false);
	public static final FoodItem VOXELIZEDSALMON = new FoodItem("voxelized_salmon",2,false);
	public static final FoodItem WHITECHEESE = new FoodItem("white_cheese",2,false);
	public static final FoodItem WHITECHEESESLICE = new FoodItem("white_cheese_slice",2,false);
	public static final FoodItem APPLESLICE = new FoodItem("apple_slice",2,false);
	public static final FoodItem BLUEHEART = new FoodItem("blue_heart",2,false);
	public static final FoodItem CUCUMBER = new FoodItem("cucumber",2,false);
	public static final FoodItem HARDBOILEDEGG = new FoodItem("hard_boiled_egg",2,false);
	public static final FoodItem SCRAMBLEDEGG = new FoodItem("scrambled_egg",2,false);
	public static final FoodItem SUNNYSIDEUPEGG = new FoodItem("sunnyside_up_egg",2,false);
	
	
	//bulk
	public static final Item BLUE_DUNGEON_KEY = new ItemBase("blue_dungeon_key");
	public static final Item BLACK_STONE_DUNGEON_KEY = new ItemBase("black_stone_dungeon_key");
	
	public static final Item SUPER_CHARGED_AVOKINATE_HAMMER = new ToolHammer("super_charged_avokinate_hammer",12,0.8f,ModItemTier.CHARGED_AVOKINATE);
	public static final Item CHARGED_AVOKINATE_HAMMER = new ToolHammer("charged_avokinate_hammer",9,1,ModItemTier.CHARGED_AVOKINATE);
	public static final Item AVOKINATE_HAMMER = new ToolHammer("avokinate_hammer",6,1.3f,ModItemTier.AVOKINATE);
	
	public static final Item ANCIENT_PICKAXE = new ToolPickaxe("ancient_pickaxe",MATERIAL_AVOKINATE);
	public static final Item AVOKINATE_SPEAR = new ToolSword("avokinate_spear",MATERIAL_AVOKINATE);
	public static final Item REDSTONE_ZAPPER = new RedstoneZapper("redstone_zapper",40);
	
	/*
	public static final ItemSword PURPLE_MACE = new ToolSword("purple_mace",GEMS);
	public static final ItemSword PURPLE_SPEAR = new ToolSword("purple_spear",GEMS);
	public static final ItemSword PURPLE_TRIDENT = new ToolSword("purple_trident",GEMS);
	public static final ItemSword BLUE_BREAKER_BLADE = new ToolSword("blue_breaker_blade",GEMS);
	public static final ItemSword BLUE_WAND = new ToolSword("blue_wand",GEMS);
	public static final ItemSword BLUE_MACE = new ToolSword("blue_mace",GEMS);
	public static final ItemSword BLUE_TRIDENT = new ToolSword("blue_trident",GEMS);
	public static final ItemSword GREEN_STAFF = new ToolSword("green_staff",GEMS);
	public static final ItemSword GREEN_SPEAR = new ToolSword("green_spear",GEMS);
	public static final ItemSword GREEN_MACE = new ToolSword("green_mace",GEMS);
	public static final ItemSword GREEN_BREAKER_BLADE = new ToolSword("green_breaker_blade",GEMS);
	public static final ItemSword PINK_WAND = new ToolSword("pink_wand",GEMS);
	public static final ItemSword RED_BREAKER_BLADE = new ToolSword("red_breaker_blade",GEMS);
	public static final ItemSword RED_MACE = new ToolSword("red_mace",GEMS);
	public static final ItemSword RED_SPEAR = new ToolSword("red_spear",GEMS);
	public static final ItemSword RED_TRIDENT = new ToolSword("red_trident",GEMS);
	public static final ItemSword RED_STAFF = new ToolSword("red_staff",GEMS);
	public static final ItemSword BRONZE_SPEAR = new ToolSword("bronze_spear",GEMS);
	public static final ItemSword BRONZE_COMB_MACE = new ToolSword("bronze_comb_mace",GEMS);
	public static final ItemSword DIAMOND_SPEAR = new ToolSword("diamond_spear",GEMS);
	public static final ItemSword GOLD_SPEAR = new ToolSword("gold_spear",GEMS);
	public static final ItemSword BLACK_MACE = new ToolSword("black_mace",GEMS);
	public static final ItemSword RED_WAND = new ToolSword("red_wand",GEMS);
	public static final ItemSword PINK_STAFF = new ToolSword("pink_staff",GEMS);
	public static final ItemSword IRON_SPEAR = new ToolSword("iron_spear",GEMS);
	public static final ItemSword BLUE_SPEAR = new ToolSword("blue_spear",GEMS);
	public static final ItemSword BLUE_STAFF = new ToolSword("blue_staff",GEMS);
	public static final ItemSword GREEN_WAND = new ToolSword("green_wand",GEMS);
	*/
	
	//wings
	public static final Item VOXELITE_WINGS = new ElytraItemBase("voxelite_wings");







	public static final Item OGOE_BERRY = new BlockNamedItemBase("ogoe_berry",ModBlocks.OGOE_BUSH,new Item.Properties().food(new Food.Builder().fastToEat().hunger(2).saturation(2).setAlwaysEdible().build()));

	
	//reconds
//	public static final RecordBase STILS_SONG = new RecordBase("stils_song",ModSoundHandler.ENTITY_SLUG_SING);
	
	
	//set repair
	public ModItems() {
//		MATERIAL_AVOKINATE.setRepairItem(new ItemStack(AVOKINATE_CRYSTAL));
//		MATERIAL_IRONREINFORCED.setRepairItem(new ItemStack(Items.IRON_INGOT));
//		CHARGED_MATERIAL_AVOKINATE.setRepairItem(new ItemStack(CHARGED_AVOKINATE_CRYSTAL));
		
	}
	
}
