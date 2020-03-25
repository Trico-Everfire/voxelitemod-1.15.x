package com.tricoeverfire.voxelite.blocks;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import com.google.common.collect.Sets;
import com.tricoeverfire.voxelite.init.ModBlocks;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class LiquidVoxelite extends FlowingFluidBlock{

	
	public static DamageSource VoxDam = (new DamageSource("voxelized"));
	
	@SuppressWarnings("deprecation")
	protected LiquidVoxelite(FlowingFluid fluidIn, Properties builder) {
		super(fluidIn, builder);
		// TODO Auto-generated constructor stub
	}

	public LiquidVoxelite(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
		super(supplier, p_i48368_1_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		// TODO Auto-generated method stub
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		
		worldIn.addParticle(ParticleTypes.SPLASH, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), 0, 0, 0);
		
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		//entityIn.setVelocity(entityIn., y, z);
		if(entityIn instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) entityIn;
		
			if(entity.isJumping) {
				Vec3d vec = entity.getMotion().add(0.0D, (double)0.04F * entity.getAttribute(entity.SWIM_SPEED).getValue(),0.0D);
				 entity.setMotion(vec);
			}

		}
	//	worldIn.addParticle(ParticleTypes.SPLASH, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), 0, 0, 0);
		
		
		
		if(!worldIn.isRemote) {

			if(entityIn instanceof LivingEntity) {

			
				float start = 5.0F;
				boolean unos = false;
				boolean dos = false;
				boolean tres = false;
				boolean quadre = false;
				entityIn.fallDistance = 0;
				
				LivingEntity entity = (LivingEntity) entityIn;
				
				handleWaterMovement(entity);
				
				BlockPos newposis = new BlockPos(entityIn);


				if(pos.getX() == newposis.getX() && pos.getY() == newposis.getY() && pos.getZ() == newposis.getZ()) {
				//	System.out.println("true!");
				
				List<ItemStack> armor = (List<ItemStack>) entity.getArmorInventoryList();

				CompoundNBT nbtboots = armor.get(0).getOrCreateTag();
				CompoundNBT nbtleggings = armor.get(1).getOrCreateTag();
				CompoundNBT nbtchest = armor.get(2).getOrCreateTag();
				CompoundNBT nbthelmet = armor.get(3).getOrCreateTag();

				
				Item boots = armor.get(0).getItem();
				Item leggings = armor.get(1).getItem();
				Item chestplate = armor.get(2).getItem();
				Item helmet = armor.get(3).getItem();
				
				int bootDamagemodifier = 1;
				int leggingsDamagemodifier = 1;
				int chestplateDamagemodifier = 1;
				int helmetDamagemodifier = 1;
				
				Map<Enchantment, Integer> bootMapping = EnchantmentHelper.getEnchantments(armor.get(0));
				Map<Enchantment, Integer> leggingsMapping = EnchantmentHelper.getEnchantments(armor.get(1));
				Map<Enchantment, Integer> chestplateMapping = EnchantmentHelper.getEnchantments(armor.get(2));
				Map<Enchantment, Integer> helmetHapping = EnchantmentHelper.getEnchantments(armor.get(3));
				
				//System.out.println(helmetHapping.isEmpty());
				if(!helmetHapping.isEmpty()) {
					if(helmetHapping.containsKey(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						switch(helmetHapping.get(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						case 1:
							helmetDamagemodifier = 2;
						break;
						case 2:
							helmetDamagemodifier = 3;
						break;
						case 3:
							helmetDamagemodifier = 4;
						break;
						}
					}

				}
				
				if(!chestplateMapping.isEmpty()) {
					if(chestplateMapping.containsKey(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						switch(chestplateMapping.get(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						case 1:
							chestplateDamagemodifier = 2;
						break;
						case 2:
							chestplateDamagemodifier = 3;
						break;
						case 3:
							chestplateDamagemodifier = 4;
						break;
						}
					}

				}
				
				if(!leggingsMapping.isEmpty()) {
					if(leggingsMapping.containsKey(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						switch(leggingsMapping.get(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						case 1:
							leggingsDamagemodifier = 2;
						break;
						case 2:
							leggingsDamagemodifier = 3;
						break;
						case 3:
							leggingsDamagemodifier = 4;
						break;
						}
					}

				}
				
				if(!bootMapping.isEmpty()) {
					if(bootMapping.containsKey(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						switch(bootMapping.get(ModEnchantments.ANTIVOXELITEDEPLETION)) {
						case 1:
							bootDamagemodifier = 2;
						break;
						case 2:
							bootDamagemodifier = 3;
						break;
						case 3:
							bootDamagemodifier = 4;
						break;
						}
					}

				}
				
				
				
				if(boots == ModItems.AVOKINATE_BOOTS)
				{
					start -= 1.2f;
					nbtboots.putFloat("microdamage", nbtboots.getFloat("microdamage") + 1F / bootDamagemodifier);
					unos = true;
				}
				if(leggings == ModItems.AVOKINATE_LEGGINGS)
				{
					start -= 1.2f;
					nbtleggings.putFloat("microdamage", nbtleggings.getFloat("microdamage") + 1F / leggingsDamagemodifier);
					dos = true;
				}
				if(helmet == ModItems.AVOKINATE_HELMET)
				{
					start -= 1.2f;
					nbthelmet.putFloat("microdamage", nbthelmet.getFloat("microdamage") + 1F / helmetDamagemodifier);
					tres = true;
				}
				if(chestplate == ModItems.AVOKINATE_CHESTPLATE)
				{
					start -= 1.2f;
					nbtchest.putFloat("microdamage", nbtchest.getFloat("microdamage") + 1F / chestplateDamagemodifier);
					quadre = true;
				}
				
				if(nbtboots.getFloat("microdamage") == 80) {
					armor.get(0).damageItem(1, entity,(eentity)->{eentity.sendBreakAnimation(EquipmentSlotType.FEET);});
					nbtboots.putFloat("microdamage", 0);
					armor.get(0).setTag(nbtboots);
					
				} else {
					armor.get(0).setTag(nbtboots);
				}
				
				if(nbtleggings.getFloat("microdamage") == 80) {
					armor.get(1).damageItem(1, entity,(eentity)->{eentity.sendBreakAnimation(EquipmentSlotType.LEGS);});
					nbtleggings.putFloat("microdamage", 0);
					armor.get(1).setTag(nbtleggings);
				} else {
					armor.get(1).setTag(nbtleggings);
				}
				
				if(nbthelmet.getFloat("microdamage") == 80) {
					armor.get(3).damageItem(1, entity,(eentity)->{eentity.sendBreakAnimation(EquipmentSlotType.HEAD);});
					nbthelmet.putFloat("microdamage", 0);
					armor.get(3).setTag(nbthelmet);
				} else {
					armor.get(3).setTag(nbthelmet);
				}
				
				if(nbtchest.getFloat("microdamage") == 80) {
					armor.get(2).damageItem(1, entity,(eentity)->{eentity.sendBreakAnimation(EquipmentSlotType.CHEST);});
					nbtchest.putFloat("microdamage", 0);
					armor.get(2).setTag(nbtchest);
				} else {
					armor.get(2).setTag(nbtchest);
				}
		
				
				if(unos&&dos&&tres&&quadre) {
					return;
				}
			entityIn.attackEntityFrom(VoxDam, start);
			
				} else {
				return;
			}
			}

			if(!(entityIn instanceof LivingEntity)) {
				
				if(entityIn instanceof ArrowEntity) {
					
				}
				
				if(!(entityIn instanceof ItemEntity)) {
					
					
					
					return;
				}
				
		      
		        Vec3d vec3d = entityIn.getMotion();
		        entityIn.setMotion(vec3d.x * (double)0.99F, vec3d.y + (double)(vec3d.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3d.z * (double)0.99F);
		          
				ItemEntity item = (ItemEntity) entityIn;
				final Set<Item> IMMUNE =  Sets.newHashSet(ModItems.SUPER_CHARGED_AVOKINATE_HAMMER,ModItems.CHARGED_AVOKINATE_HAMMER,ModItems.AVOKINATE_HAMMER,ModItems.VOXELIZED_SLUDGE,ModItems.AVOKINATE_AXE,ModItems.AVOKINATE_PICKAXE,ModItems.AVOKINATE_HOE,ModItems.AVOKINATE_BOOTS,ModItems.AVOKINATE_CHESTPLATE,ModItems.AVOKINATE_CRYSTAL,ModItems.AVOKINATE_DUST,ModItems.AVOKINATE_HELMET,ModItems.AVOKINATE_LEGGINGS,ModItems.AVOKINATE_MEDAL,ModItems.AVOKINATE_SPADE,ModItems.AVOKINATE_SWORD,ModItems.CHARGED_AVOKINATE_AXE,ModItems.CHARGED_AVOKINATE_CRYSTAL,ModItems.CHARGED_AVOKINATE_HOE,ModItems.CHARGED_AVOKINATE_PICKAXE,ModItems.CHARGED_AVOKINATE_SPADE,ModItems.CHARGED_AVOKINATE_SWORD,ModItems.AVOKINATE_SPEAR,ModBlocks.AVOKINATE_BLOCK.asItem(),ModBlocks.AVOKINATE_ORE.asItem(),ModBlocks.BLEACHED_BLOCK.asItem(),ModBlocks.VOXELIZED_DIRT.asItem(),ModBlocks.VOXELIZED_SAND.asItem(),ModBlocks.VOXELIZED_GRAVEL.asItem(),ModBlocks.VOXELIZED_STONE.asItem(),ModBlocks.VOXELIZED_LEAVES.asItem(),ModBlocks.VOXELIZED_LOG.asItem(),ModBlocks.VOXELIZED_COBBLESTONE.asItem(),ModBlocks.VOXELIZED_ORE.asItem());
				
				
				if(item.getItem().getItem() != ModItems.VOXELIZED_SLUDGE  && !IMMUNE.contains(item.getItem().getItem())) {

				item.setItem(new ItemStack(ModItems.VOXELIZED_SLUDGE,item.getItem().getCount())); 
				return; 
				}

			}
		}
		
		
		
		super.onEntityCollision(state, worldIn, pos, entityIn);
	}
	
	
	   public boolean handleWaterMovement(Entity entityIn) {
		      if (entityIn.getRidingEntity() instanceof BoatEntity) {
		    	  entityIn.inWater = false;
		      } else if (entityIn.handleFluidAcceleration(FluidTags.WATER)) {
		         if (!entityIn.inWater && !true) {
		        	 entityIn.doWaterSplashEffect();
		         }

		         entityIn.fallDistance = 0.0F;
		         entityIn.inWater = true;
		         entityIn.extinguish();
		      } else {
		    	  entityIn.inWater = false;
		      }

		      return entityIn.inWater;
		   }
	
	
//	protected void doWaterSplashEffect(Entity entity) {
//	   //   Entity entity = this.isBeingRidden() && this.getControllingPassenger() != null ? this.getControllingPassenger() : this;
//	      float f = false ? 0.2F : 0.9F;
//	      Vec3d vec3d = entity.getMotion();
//	      float f1 = MathHelper.sqrt(vec3d.x * vec3d.x * (double)0.2F + vec3d.y * vec3d.y + vec3d.z * vec3d.z * (double)0.2F) * f;
//	      if (f1 > 1.0F) {
//	         f1 = 1.0F;
//	      }
//
//	      if ((double)f1 < 0.25D) {
//	         entity.playSound(entity.getSplashSound(), f1, 1.0F + (entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.4F);
//	      } else {
//	         entity.playSound(entity.getHighspeedSplashSound(), f1, 1.0F + (entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.4F);
//	      }
//
//	      float f2 = (float)MathHelper.floor(entity.getPosY());
//
//	      for(int i = 0; (float)i < 1.0F + entity.size.width * 20.0F; ++i) {
//	         float f3 = (entity.rand.nextFloat() * 2.0F - 1.0F) * entity.size.width;
//	         float f4 = (entity.rand.nextFloat() * 2.0F - 1.0F) * entity.size.width;
//	         entity.world.addParticle(ParticleTypes.BUBBLE, entity.getPosX() + (double)f3, (double)(f2 + 1.0F), entity.getPosZ() + (double)f4, vec3d.x, vec3d.y - (double)(entity.rand.nextFloat() * 0.2F), vec3d.z);
//	      }
//
//	      for(int j = 0; (float)j < 1.0F + entity.size.width * 20.0F; ++j) {
//	         float f5 = (entity.rand.nextFloat() * 2.0F - 1.0F) * entity.size.width;
//	         float f6 = (entity.rand.nextFloat() * 2.0F - 1.0F) * entity.size.width;
//	         entity.world.addParticle(ParticleTypes.SPLASH, entity.getPosX() + (double)f5, (double)(f2 + 1.0F), entity.getPosZ() + (double)f6, vec3d.x, vec3d.y, vec3d.z);
//	      }
//
//	   }
//	

}
