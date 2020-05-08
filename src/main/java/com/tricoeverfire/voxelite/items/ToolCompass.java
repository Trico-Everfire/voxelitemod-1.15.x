package com.tricoeverfire.voxelite.items;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.tricoeverfire.voxelite.init.ModItemGroups;
import com.tricoeverfire.voxelite.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.animation.ITimeValue;

public class ToolCompass extends Item{
	
	public ToolCompass(String name) {
		this(name, new Properties().maxStackSize(1).group(ModItemGroups.voxelitemoditems));
	}
	
	
	
	public ToolCompass(String name,Properties props)
    {
		super(props);
		setRegistryName(name);
		ModItems.ITEMS.add(this);
		
		
		
		
		this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
	         @OnlyIn(Dist.CLIENT)
	         private double rotation;
	         @OnlyIn(Dist.CLIENT)
	         private double rota;
	         @OnlyIn(Dist.CLIENT)
	         private long lastUpdateTick;

	         @OnlyIn(Dist.CLIENT)
	         public float call(ItemStack stack, @Nullable World world, @Nullable LivingEntity livingEntity) {
	            if (livingEntity == null && !stack.isOnItemFrame()) {
	               return 0.0F;
	            } else {
	               boolean flag = livingEntity != null;
	               Entity entity = (Entity)(flag ? livingEntity : stack.getItemFrame());
	               if (world == null) {
	                  world = entity.world;
	               }

	               double d0;
	               if (world.dimension.isSurfaceWorld()) {
	                  double d1 = flag ? (double)entity.rotationYaw : this.getFrameRotation((ItemFrameEntity)entity);
	                  d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
	                  double d2 = this.getSpawnToAngle(world, entity) / (double)((float)Math.PI * 2F);
	                  d0 = 0.5D - (d1 - 0.25D - d2);
	               } else {
	                  d0 = Math.random();
	               }

	               if (flag) {
	                  d0 = this.wobble(world, d0);
	               }

	               return MathHelper.positiveModulo((float)d0, 1.0F);
	            }
	         }

	         @OnlyIn(Dist.CLIENT)
	         private double wobble(World worldIn, double p_185093_2_) {
	            if (worldIn.getGameTime() != this.lastUpdateTick) {
	               this.lastUpdateTick = worldIn.getGameTime();
	               double d0 = p_185093_2_ - this.rotation;
	               d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
	               this.rota += d0 * 0.1D;
	               this.rota *= 0.8D;
	               this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0D);
	            }
	            
	            return this.rotation;
	         }

	         @OnlyIn(Dist.CLIENT)
	         private double getFrameRotation(ItemFrameEntity p_185094_1_) {
	            return (double)MathHelper.wrapDegrees(180 + p_185094_1_.getHorizontalFacing().getHorizontalIndex() * 90);
	         }

	         @OnlyIn(Dist.CLIENT)
	         private double getSpawnToAngle(IWorld p_185092_1_, Entity p_185092_2_) {
	            BlockPos blockpos = p_185092_1_.getSpawnPoint();
	            return Math.atan2((double)blockpos.getZ() - p_185092_2_.getPosZ(), (double)blockpos.getX() - p_185092_2_.getPosX());
	         }
	      });
	   
    }
	

	@Override
	public ImmutableMap<String, ITimeValue> getAnimationParameters(ItemStack stack, World world, LivingEntity entity) {
		
		return super.getAnimationParameters(stack, world, entity);
	}



}

