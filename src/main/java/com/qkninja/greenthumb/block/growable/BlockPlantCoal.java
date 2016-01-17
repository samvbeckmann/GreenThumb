package com.qkninja.greenthumb.block.growable;

import com.qkninja.greenthumb.tileentity.growable.plant.TileEntityPlantCoal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Defines the Coal Plant Block
 *
 * @author QKninja
 */
public class BlockPlantCoal extends BlockPlant
{
    public BlockPlantCoal()
    {
        super();
        setUnlocalizedName("plantCoal");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityPlantCoal(new ItemStack(Items.coal, 1), 40); // TODO: Move to Config file.
    }
}
