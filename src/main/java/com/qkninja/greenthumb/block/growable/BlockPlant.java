package com.qkninja.greenthumb.block.growable;

import com.qkninja.greenthumb.block.BlockGreenThumb;
import com.qkninja.greenthumb.tileentity.growable.plant.TileEntityPlantCoal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

/**
 * Abstract Growable of type "plant".
 * To be extended by all blocks of type "plant".
 *
 * @author QKninja
 */
public abstract class BlockPlant extends BlockGreenThumb implements ITileEntityProvider
{
    public BlockPlant()
    {
        super(Material.plants);
        setStepSound(soundTypeGrass);
        setHardness(0.1F);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) &&
                worldIn.getBlockState(pos.down()).getBlock() == Blocks.farmland;
    }

    @Override
    public boolean onBlockActivated(World worldIn,
                                    BlockPos pos,
                                    IBlockState state,
                                    EntityPlayer playerIn,
                                    EnumFacing side,
                                    float hitX,
                                    float hitY,
                                    float hitZ)
    {
        if (!worldIn.isRemote)
        {
            TileEntityPlantCoal tileEntity = (TileEntityPlantCoal) worldIn.getTileEntity(pos);
            if (tileEntity != null)
            {
                if (tileEntity.getStackInSlot(0) != null)
                {
                    ItemStack tileInventory = tileEntity.getStackInSlot(0);
                    tileEntity.decrStackSize(0, tileInventory.stackSize);
                    EntityItem entityitem = new EntityItem(worldIn, playerIn.posX, playerIn.posY - 1.0D, playerIn.posZ,
                            tileInventory);
                    worldIn.spawnEntityInWorld(entityitem);
                    if (!(playerIn instanceof FakePlayer))
                        entityitem.onCollideWithPlayer(playerIn);
                }
                return true;
            }
        }
        return false;
    }
}
