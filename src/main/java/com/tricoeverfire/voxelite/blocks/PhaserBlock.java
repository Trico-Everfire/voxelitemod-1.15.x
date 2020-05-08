package com.tricoeverfire.voxelite.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.blocks.stateproperties.ModStateProperties;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.init.ModTileEntityTypes;
import com.tricoeverfire.voxelite.items.PhaserBlockItem;
import com.tricoeverfire.voxelite.tileentities.PhaserTileEntity;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PhaserBlock extends AbstractGlassBlock{
	protected final @Nullable Item item;
	 public static final BooleanProperty PHASEMAKER = ModStateProperties.ITEMHELD;
	 public static final BooleanProperty INVERSE = ModStateProperties.INVERSE;
	 public final Item thisitemblock;
	 public int getColorValue = 0xffffff;
	 public final boolean inverse;

	public int getDefaultColor() {
		return getColorValue;
		
	}

	
	public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable) {
		//return getBlockProperties(material,unbreakable);
		return getBlockProperties(material,unbreakable,null,-21.5F);
	}
	public static Properties getBlockProperties(Material material,@Nullable boolean unbreakable, @Nullable SoundType sound, @Nullable float hardness) {
		Properties properties = Block.Properties.create(material);
		
		properties.notSolid();
		
		if(unbreakable) {
	//	properties.setBlockUnbreakable();
		properties.hardnessAndResistance(16000000);
		} else {
			
		}
		
		
		return properties;
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		
		ItemStack stacky = new ItemStack(this.thisitemblock);
		if(world.getTileEntity(pos) instanceof PhaserTileEntity) {
		PhaserTileEntity tentity = (PhaserTileEntity) world.getTileEntity(pos);

		stacky.getOrCreateTag().putString("color",tentity.getColor());
		stacky.getOrCreateTag().putBoolean("inverse", state.get(INVERSE));
		}
		
		return stacky;
	}
	
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		   if(adjacentBlockState.getBlock() == this) {
			   
			   return adjacentBlockState.get(PHASEMAKER) == true && state.get(PHASEMAKER) == true; 
		   }
		   
		   
		   
	      return super.isSideInvisible(state, adjacentBlockState, side);
	
	   }
	   
	public PhaserBlock(String name, Material materialIn, @Nullable Item keyItem, boolean unbreakable) {
		this(name, materialIn, keyItem, unbreakable, false);	
		}
	
	public PhaserBlock(String name, Material materialIn, @Nullable Item keyItem, boolean unbreakable, boolean inverse) {
		super(getBlockProperties(materialIn,unbreakable));
		
		setRegistryName(Main.location(name));
		this.item = keyItem;
		this.inverse = inverse;
		
		
		ModBlocks.BLOCKS.add(this);
		Item.Properties blockItemProperties = new Item.Properties().group(ModItemGroups.voxelitemodblocks);
		Item thisblock = new PhaserBlockItem(this,blockItemProperties);
	//	Item thisBlockDungeonBlue = new BlockItem(this.getDefaultState().with(P, 0x7384AC));
		
		thisblock.setRegistryName(Main.location(name));
		this.thisitemblock = thisblock;
		ModItems.ITEMS.add(thisblock);

	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(PHASEMAKER,INVERSE);
		super.fillStateContainer(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

			tooltip.add(new TranslationTextComponent(""));
			super.addInformation(stack, worldIn, tooltip, flagIn);
	}
@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
			return ModTileEntityTypes.PHASERTILEENTITY.get().create();
		}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
	
		
		
		//CompoundNBT tileEntityData = worldIn.getTileEntity(pos).getTileData();
	 worldIn.getTileEntity(pos);//.setColor(stack.getOrCreateTag().getString("color");
	 
	 if(worldIn.getTileEntity(pos) instanceof PhaserTileEntity) {
	 PhaserTileEntity tentiy = (PhaserTileEntity) worldIn.getTileEntity(pos);
	 if(stack.getOrCreateTag().getString("color") == "") {
		 tentiy.setColor("0xffffff");
	 }else {
	 tentiy.setColor(stack.getOrCreateTag().getString("color"));
	 }
	 }
	 
//		tileEntityData.putString("color", stack.getOrCreateTag().getString("color"));
		try {
			
		boolean bool = stack.getOrCreateTag().getBoolean("inverse");// == false ? false : stack.getOrCreateTag().getBoolean("inverse");
		state = state.with(INVERSE, Boolean.valueOf(bool));
		} catch(Exception e) {
			System.out.println("something went wrong :/");
			state =	state.with(INVERSE, Boolean.valueOf(false));
		}
		
		worldIn.setBlockState(pos, state, 2);
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		//tick(worldIn, pos);
	
		
	}
	   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
	
		   return !state.get(PHASEMAKER);//!!!this.inverse ? true : false;
		   
	   }
	
	   
	   @Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			
			return this.getDefaultState().with(PHASEMAKER, Boolean.valueOf(false));
		}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		
		

			
			if(!state.get(PHASEMAKER)) {
				return super.getCollisionShape(state, worldIn, pos, context);
			}
			return  VoxelShapes.empty();
		
		
		

		//context.getEntity();
	}
	
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		
		
	//	System.out.println("yes");
	      switch(type) {
	      case LAND:
	         return state.get(PHASEMAKER);
	      case WATER:
	         return false;
	      case AIR:
	         return state.get(PHASEMAKER);
	      default:
	         return false;
	      }

		
		
	}


	public BlockState tick(World worldIn, BlockPos pos, BlockState state, PhaserTileEntity tilentity) {

		//System.out.println(state.get(INVERSE));
	
			if(this.item == null) { 
				state = state.with(PHASEMAKER, state.get(INVERSE));
				return state;
			
			}
			
			//double dubby = (double) pos.getY() - 1.5;
			//System.out.println(dubby);
			BlockPos posright = new BlockPos((double) pos.getX() - 1d,(double) pos.getY() - 1d,(double) pos.getZ() - 1d);
			AxisAlignedBB box = new AxisAlignedBB(posright).expand(2, 4, 2);

			 for (LivingEntity entity : worldIn.getEntitiesWithinAABB(LivingEntity.class,box)) {
				 

				if(this.item == entity.getHeldItemMainhand().getItem() || this.item == entity.getHeldItemOffhand().getItem()) {
					
					 String val1 = this.item == entity.getHeldItemMainhand().getItem() ? entity.getHeldItemMainhand().getOrCreateTag().getString("color").toString() : "null";
					 String val1point5 = this.item == entity.getHeldItemOffhand().getItem() ? entity.getHeldItemOffhand().getOrCreateTag().getString("color").toString() : "null";
					 String val2 = tilentity.getColor();
					
					 
					 
					if(val1 == "") {
						val1 = "0xffffff";
					}
					if(val1point5 == "") {
						val1point5 = "0xffffff";
					}
					if(val2 == "") {
						val2 = "0xffffff";
					}
					
					
					if((val1.contains(val2) || val1point5.contains(val2))) {
						return state.with(PHASEMAKER, !state.get(INVERSE));
					}
					
				} 
			}
			
			
			 return state.with(PHASEMAKER, state.get(INVERSE));
		
	}


}
