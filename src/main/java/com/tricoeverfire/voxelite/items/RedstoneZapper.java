package com.tricoeverfire.voxelite.items;

import java.util.List;

import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedstoneZapper extends ItemBase{

	public RedstoneZapper(String name,int maxUses) {
	super(name,getProperies(maxUses));
	
	
	this.addPropertyOverride(new ResourceLocation("charge"),  new IItemPropertyGetter() {

		@Override
		@OnlyIn(Dist.CLIENT)
		public float call(ItemStack stack, World worldIn, LivingEntity entityIn) {
			
			if(entityIn == null) {
				return 1F;
			}
			else {
				
				if(entityIn.getActiveItemStack().getItem().getDamage(stack) == stack.getMaxDamage()) {
					return 0F;
				}else {
					return 1F;
				}
			
			}
			
		}
		
		
		
		
	});
	//setMaxStackSize(1);
	 

	ModItems.ITEMS.add(this);
	
	
}

private static Properties getProperies(int maxUses) {
	Properties props = new Item.Properties();
	props.maxStackSize(1);
	props.group(ModItemGroups.voxelitemoditems);
	props.maxDamage(maxUses);
	props.defaultMaxDamage(maxUses);
	props.setNoRepair();
	return props;
}

@Override
@OnlyIn(Dist.CLIENT)
public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	
	tooltip.add(newTextComponent("right click to stun enemies for 5 seconds"));
	tooltip.add(newTextComponent("(does 2 damage) 8 ticks to recharge (consumes 1 duribility)"));
	tooltip.add(newTextComponent(""));
	tooltip.add(newTextComponent("right click and sneak to stun enemies for 10 seconds"));
	tooltip.add(newTextComponent("(does 12 damage) 70 ticks to recharge (consumes 10 durability)"));
	
	super.addInformation(stack, worldIn, tooltip, flagIn);
}
private ITextComponent newTextComponent(String string) {
	ITextComponent component = new TranslationTextComponent(string);
	//component.appendText(string);
	return component;
}




@Override
public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand) {
//	player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 200);
	
	
	
		//player.getHeldItem(hand).getItem().readNBTShareTag(player.getHeldItem(hand), nbt.getCompoundTag("COOLDOWN"));
	
		System.out.println(player.getHeldItem(hand).getMaxDamage());
	
	if(player.getHeldItem(hand).getMaxDamage() <= player.getHeldItem(hand).getDamage()) {
	//	player.getHeldItem(hand).setItemDamage(player.getHeldItem(hand).getMaxDamage());
		for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
			if(player.inventory.getStackInSlot(i).getItem() == ModItems.REDSTONE_CHARGE) {
				player.inventory.getStackInSlot(i).shrink(1);
				player.getHeldItem(hand).setDamage(this.getMaxDamage(player.getHeldItem(hand)));
				return ActionResult.resultSuccess(player.getHeldItem(hand));
			}	
		}
		
		
		return ActionResult.resultFail(player.getHeldItem(hand));
			//ActionResult.resultSuccess(player.getHeldItem(hand));
	}
	
	
	
	
	AxisAlignedBB AABB; 
	

	int dmg =  player.getHeldItem(hand).getMaxDamage() - player.getHeldItem(hand).getDamage();
	//Console.println(dmg);
	
	if(player.isShiftKeyDown() && dmg >= 10) {
		
		float pwr = 1;
		if(Direction.NORTH == player.getHorizontalFacing()) {
			AABB = new AxisAlignedBB(player.getPosX() - 3, player.getPosY() - 2, player.getPosZ(), player.getPosX() + 3, player.getPosY() + 2, player.getPosZ() - 3);
			player.knockBack(player, pwr, 0, -2);
		} else if(Direction.SOUTH == player.getHorizontalFacing()) {
			AABB = new AxisAlignedBB(player.getPosX() + 3, player.getPosY() - 2, player.getPosZ(), player.getPosX() - 3, player.getPosY() + 2, player.getPosZ() + 3);
			player.knockBack(player, pwr, 0, 2);
		} else if(Direction.WEST == player.getHorizontalFacing()){
			AABB = new AxisAlignedBB(player.getPosX(), player.getPosY() - 2, player.getPosZ() - 5, player.getPosX() - 3, player.getPosY() + 2, player.getPosZ() + 3);
			player.knockBack(player, pwr, -2, 0);
		}else if(Direction.EAST == player.getHorizontalFacing()){
			AABB = new AxisAlignedBB(player.getPosX(), player.getPosY() - 2, player.getPosZ() + 5, player.getPosX() + 3, player.getPosY() + 2, player.getPosZ() - 3);
			player.knockBack(player, pwr, 2, 0);
		} else {
			AABB = new AxisAlignedBB(player.getPosX(), player.getPosY(), player.getPosZ(), player.getPosX(), player.getPosY(), player.getPosZ());
		}
		List<LivingEntity> entity = worldIn.getEntitiesWithinAABB(LivingEntity.class, AABB);
		

		if(entity.size() != 0) {
			
			
		for(int i = 0; entity.size() > i; i++) {
			if(entity.get(i) instanceof LivingEntity) {
			//	System.out.println(i);
				if(entity.get(i) != player) {
					new DamageSource("redstone_zapper");
					entity.get(i).attackEntityFrom(DamageSource.causePlayerDamage(player), 12);
					 entity.get(i).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200,10));
				}

				
			}	
		}
	}
		
		
	worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE,  SoundCategory.PLAYERS, 8, 0, true);
	player.getHeldItem(hand).damageItem(10, player, (ourEntity)->{ourEntity.sendBreakAnimation(hand);});
	player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 70);
	return ActionResult.resultSuccess(player.getHeldItem(hand));
		
	} else if (player.isShiftKeyDown()) {
	
		for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
			if(player.inventory.getStackInSlot(i).getItem() == ModItems.REDSTONE_CHARGE) {
				player.inventory.getStackInSlot(i).shrink(1);
				player.getHeldItem(hand).setDamage(0);
				return ActionResult.resultSuccess(player.getHeldItem(hand));//.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
			}	
		}
		
		return ActionResult.resultSuccess(player.getHeldItem(hand));
	}
	
	
	
	
	
	
	if(Direction.NORTH == player.getHorizontalFacing()) {
		AABB = new AxisAlignedBB(player.getPosX() - 3, player.getPosY() - 2, player.getPosZ(), player.getPosX() + 3, player.getPosY() + 2, player.getPosZ() - 3);
	} else if(Direction.SOUTH == player.getAdjustedHorizontalFacing()) {
		AABB = new AxisAlignedBB(player.getPosX() + 3, player.getPosY() - 2, player.getPosZ(), player.getPosX() - 3, player.getPosY() + 2, player.getPosZ() + 3);
	} else if(Direction.WEST == player.getHorizontalFacing()){
		AABB = new AxisAlignedBB(player.getPosX(), player.getPosY() - 2, player.getPosZ() - 5, player.getPosX() - 3, player.getPosY() + 2, player.getPosZ() + 3);
	}else if(Direction.EAST == player.getHorizontalFacing()){
		AABB = new AxisAlignedBB(player.getPosX(), player.getPosY() - 2, player.getPosZ() + 5, player.getPosX() + 3, player.getPosY() + 2, player.getPosZ() - 3);
	} else {
	 System.out.println("error!");
		AABB = new AxisAlignedBB(player.getPosX(), player.getPosY(), player.getPosZ(), player.getPosX(), player.getPosY(), player.getPosZ());
	}
	List<LivingEntity> entity = worldIn.getEntitiesWithinAABB(LivingEntity.class, AABB);
	

	if(entity.size() != 0) {
		
		
	for(int i = 0; entity.size() > i; i++) {
		if(entity.get(i) instanceof LivingEntity) {
		//	System.out.println(i);
			if(entity.get(i) != player) {
				new DamageSource("redstone_zapper");
				entity.get(i).attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
				 entity.get(i).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200,10)); //.addPotionEffect(Effects.SLOWNESS);
			}

			
		}	
	}
	player.getHeldItem(hand).damageItem(10, player, (ourEntity)->{ourEntity.sendBreakAnimation(hand);});

	worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE,  SoundCategory.PLAYERS, 5, 0, true);
	player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 8);
	return ActionResult.resultSuccess(player.getHeldItem(hand));
	}
	player.getHeldItem(hand).damageItem(10, player, (ourEntity)->{ourEntity.sendBreakAnimation(hand);});
	worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE,  SoundCategory.PLAYERS, 5, 0, true);
	player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 8);
	return ActionResult.resultSuccess(player.getHeldItem(hand));
	
	
}



}
