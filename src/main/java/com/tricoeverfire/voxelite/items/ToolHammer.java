package com.tricoeverfire.voxelite.items;

import java.util.List;

import com.google.common.collect.Multimap;
import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.init.ModDamageSource;
import com.tricoeverfire.voxelite.init.ModEnchantments;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;
import com.tricoeverfire.voxelite.util.enummodhandler.ModItemTier;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

public class ToolHammer extends TieredItem {
	   private final float attackDamage;
	   private final float attackSpeed;
	   private final int maxCooldown = 10 * 60;
	   private int currentCooldown = 10 * 60;
	public ToolHammer(String name,float attackDamage,float attackSpeed,IItemTier tier, Properties props) {
		super(tier, props);
		
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.setRegistryName(Main.location(name));
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(currentCooldown > maxCooldown) {
			currentCooldown = maxCooldown;
		}
		
		if(currentCooldown < maxCooldown) {
			currentCooldown++;
		}

	}

	public ToolHammer(String name, float attackDamage,float attackSpeed,IItemTier tier) {
		this(name,attackDamage,attackSpeed,tier,new Properties().group(ModItemGroups.voxelitemoditems));

	}
	
	
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
	}
	
	   public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		      stack.damageItem(1, attacker, (lentity) -> {
		         lentity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		      });
		      return true;
		   }
	
	   public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		      if (state.getBlockHardness(worldIn, pos) != 0.0F) {
		         stack.damageItem(2, entityLiving, (p_220044_0_) -> {
		            p_220044_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
		      }

		      return true;
		   }
	
	
	   public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		      @SuppressWarnings("deprecation")
			Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		      if (equipmentSlot == EquipmentSlotType.MAINHAND) {
		         multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		         multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
		      }

		      return multimap;
		   }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getHeldItemMainhand();
		


		if(stack.getItem() == ModItems.SUPER_CHARGED_AVOKINATE_HAMMER || (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.POWERHAMMERSWING, stack) > 0 && stack.getItem() instanceof ToolHammer)) {
			
//			if(worldIn.isRemote()) {
//	        String s = ((AbstractClientPlayerEntity)Minecraft.getInstance().player).getSkinType();
//	        PlayerRenderer playerrenderer = Minecraft.getInstance().getRenderManager().getSkinMap().get(s);
//		
//	        playerrenderer.getEntityModel().bipedHead.rotationPointY = (float) Math.toRadians(-20F);
//	      //  playerrenderer.render(player, 0, 0, , bufferIn, packedLightIn);
//	        
//	        // System.out.println(playerrenderer.getEntityModel().render);
//			}
			if(currentCooldown < maxCooldown) {
				player.sendStatusMessage(new TranslationTextComponent("Cooldown of " + (maxCooldown - currentCooldown) / 60 + " seconds before next use" ), true);
				return ActionResult.resultPass(stack);
			}
			
		
			List<LivingEntity> entities = worldIn.getEntitiesWithinAABB(LivingEntity.class, player.getBoundingBox().grow(3, 1, 3), LivingEntity::isAlive);
			entities.remove(player);
			
			if(!worldIn.isRemote())
			if(!player.isCreative()) {
				currentCooldown = 0;
			}
			
			for(LivingEntity entity : entities) {
			
				  
	            if(entity instanceof PlayerEntity)
	            {
	                MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
	                if(!server.isPVPEnabled())
	                {
	                    continue;
	                }
	            }
	            
                if(worldIn instanceof ServerWorld)
                {
                    BlockState state = worldIn.getBlockState(entity.getPosition().down());
                    ServerWorld serverWorld = (ServerWorld) entity.world;
                    serverWorld.spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, state), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 50, 0, 0, 0, (double) 0.15F);
                    serverWorld.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
					
                float HammerDamage = (this.attackDamage + this.getTier().getAttackDamage()) / 2;
                float strengthFactor = 0.8F ;
                
                /* Cause the entity to bop up into the air */
                
                float entitySize = entity.getSize(entity.getPose()).height + entity.getSize(entity.getPose()).width;
                float tierStrenght = (this.getTier() == ModItemTier.CHARGED_AVOKINATE ? 1 : 2);
                double stompStrength = 0.8 / entitySize / tierStrenght;
               
                Vec3d direction = new Vec3d(entity.getPosX() - player.getPosX(), 0, entity.getPosZ() - player.getPosZ()).normalize();
                entity.setMotion(direction.x * stompStrength, stompStrength, direction.z * stompStrength);
                entity.addVelocity(direction.x * stompStrength, stompStrength, direction.z * stompStrength);
                entity.velocityChanged = true;
                /* Damage is applied last so mobs will still fly into air even when dead. It just looks better! */
                float distance = (float) (entity.getDistanceSq(player) / 4);
                float distanceFactor = Math.max(0.5F, 1.0F - distance / 5.0F);
                ModDamageSource.HAMMERSLAM = ModDamageSource.doHammerDamage(entity, player);
                entity.attackEntityFrom(ModDamageSource.HAMMERSLAM, HammerDamage * strengthFactor * distanceFactor * 2.0F);
                entity.setLastAttackedEntity(player);
                entity.setRevengeTarget(player);

		
				
				
			}
			float entitySize = player.getSize(player.getPose()).height + player.getSize(player.getPose()).width;
			// Vec3d direction = new Vec3d( player.getPosX(), 0, player.getPosZ()).normalize();
             float tierStrenght = (this.getTier() == ModItemTier.CHARGED_AVOKINATE ? 1 : 2);
             double stompStrength = 0.8 / entitySize / tierStrenght;
            player.setMotion(player.getMotion().x, player.getMotion().y + stompStrength, player.getMotion().z);
            player.addVelocity(0, stompStrength, 0);
			
			ActionResult.resultSuccess(stack);
		}
		
		
		return ActionResult.resultFail(stack);
	}

}
