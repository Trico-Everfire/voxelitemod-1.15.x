package com.tricoeverfire.voxelite.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TestBlockEntityPlayer extends BlockBase{

	public TestBlockEntityPlayer(String name, Material material) {
		super(name,material);
		setSoundType(SoundType.GLASS);
		setHardness(15.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe",2);
		setLightLevel(0.7F);
		

	}
	

	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		
		World world = (World) worldIn;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		List<PlayerEntity> player = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x,y,z,x+50,y+50,z+50));

		for(Entity entity : player) {
			//entity.setNoGravity(false);
			entity.setMotion(entity.getMotion().x, entity.getMotion().y + 2 , entity.getMotion().z);
		System.out.println("hi");
			//	entity.setPose(Pose.SWIMMING);
		}
		
	}
	
@Override
public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
	
	
	World world = (World) worldIn;
	double x = pos.getX();
	double y = pos.getY();
	double z = pos.getZ();
	
	List<PlayerEntity> player = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x,y,z,x+50,y+50,z+50));

	for(Entity entity : player) {
		//entity.setNoGravity(false);
		entity.setMotion(entity.getMotion().x, entity.getMotion().y + 2 , entity.getMotion().z);
	System.out.println("hi");
		//	entity.setPose(Pose.SWIMMING);
	}
	
	
	super.randomTick(state, worldIn, pos, random);
}

	@Override
	public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand) {
	//	System.out.println("gfy");
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		List<PlayerEntity> player = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x,y,z,x+50,y+50,z+50));
	
		for(Entity entity : player) {
			//entity.setNoGravity(false);
		//	entity.setMotion(entity.getMotion().x, entity.getMotion().y + 2 , entity.getMotion().z);
		//System.out.println("hi");
			//	entity.setPose(Pose.SWIMMING);
		}
		
		
		
		
		
		super.animateTick(stateIn, world, pos, rand);
	}
	

	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		//System.out.println("sauce");
	//	player.setSwimming(true);
	//	player.setPose(Pose.SWIMMING);
		Vec3d hitresult = hit.getHitVec();
		
		
		  double step = 2*Math.PI/60;  // see note 1
		  double h = 0d; 
		  double k = 0d;
		  double r = 2;
		  
	

		    for(double theta=0;  theta < 2*Math.PI;  theta+=step)
		     { double x = h + r*Math.cos(theta);
		       double y = k - r*Math.sin(theta);    //note 2.
		       worldIn.addParticle(ParticleTypes.PORTAL, hitresult.x, hitresult.y, hitresult.z, x, 0.0d, y);
		     
		     }
		
//		System.out.println(new VoxeliteLiquid.Source().getRegistryName());
//		System.out.println(Fluids.WATER.getAttributes().getFlowingTexture());
//		System.out.println(ModFluids.VOXELITELIQUID.getAttributes().getFlowingTexture());
		return ActionResultType.SUCCESS;
	}

}
