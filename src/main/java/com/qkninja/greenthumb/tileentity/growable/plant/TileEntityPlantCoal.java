package com.qkninja.greenthumb.tileentity.growable.plant;

import net.minecraft.item.ItemStack;

/**
 * Growable to produce coal.
 *
 * @author QKninja
 */
public class TileEntityPlantCoal extends GrowablePlant
{
    public TileEntityPlantCoal(ItemStack yield, int cycleLength)
    {
        super(yield, cycleLength);
    }

    @Override
    public boolean canGrow()
    {
        return super.canGrow() &&
                getWorld().getLightFromNeighbors(getPos()) == 0; // Requires light level zero to grow.
    }
}
