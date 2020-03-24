package com.tricoeverfire.voxelite.particles;

import java.util.Random;

import com.tricoeverfire.voxelite.util.math.ModMathHelper;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleBase extends SpriteTexturedParticle{

	   private ParticleBase(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn) {
		      super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
		      float f = this.rand.nextFloat() * 0.1F + 0.2F;
		      this.particleRed = f;
		      this.particleGreen = f;
		      this.particleBlue = f;
		      this.setSize(0.02F, 0.02F);
		      this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
		      this.motionX *= (double)0.02F;
		      this.motionY *= (double)0.02F;
		      this.motionZ *= (double)0.02F;
		      this.maxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
		   }

		   public IParticleRenderType getRenderType() {
		      return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
		   }

		   public void move(double x, double y, double z) {
		      this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		      this.resetPositionToBB();
		   }

		   public void tick() {
		      this.prevPosX = this.posX;
		      this.prevPosY = this.posY;
		      this.prevPosZ = this.posZ;
		      if (this.maxAge-- <= 0) {
		         this.setExpired();
		      } else {
		         this.move(this.motionX, this.motionY, this.motionZ);
		         this.motionX *= 0.99D;
		         this.motionY *= 0.99D;
		         this.motionZ *= 0.99D;
		      }
		   }
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
	    private final IAnimatedSprite spriteSet;

	    public Factory(IAnimatedSprite p_i50495_1_) {
	        this.spriteSet = p_i50495_1_;
	    }

	    public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
	    	ParticleBase suspendedStardust = new ParticleBase(worldIn, x, y, z, zSpeed, zSpeed, zSpeed);
	    	suspendedStardust.selectSpriteRandomly(this.spriteSet);
	    	
	    	int hex;
	    	switch((int)ModMathHelper.GetRandomFloatBetween(3, 0)) {
	    	case 0:
	    	hex = 0xE6E6FA;
	    	case 1:
	    	hex = 0x9370DB;
	    	break;
	    	case 2:
	    	hex = 0x8A2BE2;
	    	break;
	    	case 3:
	    	hex = 0x9400D3;
	    	break;
	    	default:
	    	hex = 0x000000;
	    	break;
	    	}
	    	
	    	
	    	float[] colorData =  ModMathHelper.ParticleColorFromHex(hex);//ModMathHelper.ParticleColorFromRGB(red,green,blue);
	    	

	    	suspendedStardust.setColor(colorData[0],colorData[1],colorData[2]);

	        return suspendedStardust;
	    }
	}

}
