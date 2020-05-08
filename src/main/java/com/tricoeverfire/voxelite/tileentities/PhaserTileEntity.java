package com.tricoeverfire.voxelite.tileentities;

import com.tricoeverfire.voxelite.blocks.PhaserBlock;
import com.tricoeverfire.voxelite.init.ModTileEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PhaserTileEntity extends TileEntity implements ITickableTileEntity{
	
	public String ColorData = "0xffffff";
	public boolean Inverse = false;
	
	public PhaserTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		
	}
	public PhaserTileEntity() {
		this(ModTileEntityTypes.PHASERTILEENTITY.get());
	}
	
	
	
	
	@Override
	public void tick() {

		if(this.getBlockState().getBlock() instanceof PhaserBlock) {
		PhaserBlock thisblock = (PhaserBlock) this.getBlockState().getBlock();
		
		BlockState stateblock = thisblock.tick(this.world, this.getPos(),this.getBlockState(), this);

		this.world.setBlockState(pos, stateblock);

		}

	
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		//System.out.println(compound);
		
		this.Inverse = compound.getBoolean("inverse");
		this.setColor(compound.getString("color")); 

		
	}
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.putString("color", this.getColor());
		compound.putBoolean("inverse", this.Inverse);
		
		
		return compound;
		
	}
	
	
    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        
        // Write values you need in the client here
        compound.putString("color", this.ColorData);
        compound.putBoolean("inverse", this.Inverse);
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        // Read values stored by getUpdateTag here
    	this.ColorData = tag.getString("color");
    	this.Inverse = tag.getBoolean("inverse");
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        handleUpdateTag(packet.getNbtCompound());

        // If you need to redraw the block for the purposes of custom models or IBlockColors
        BlockState state = getBlockState();
        world.notifyBlockUpdate(pos, state, state, 3);
        this.requestModelDataUpdate();
    }
	
	
	public String getColor() {
	
		return ColorData;
	}
	
	public void setColor(String data) {
		this.ColorData = data;
	}
	public boolean getInverse() {
		return this.Inverse;
	}
	public void setInverse(boolean inverse) {
		this.Inverse = inverse;
	}
	
	
	

}
