package com.qkninja.greenthumb.tileentity.growable.plant;

import com.qkninja.greenthumb.tileentity.growable.GrowableGeneric;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Abstract TileEntity for a Growable of type "plant".
 * Overrides / Implements gaps in {@link GrowableGeneric} for type "plant".
 * Extended by all TileEntities of type "plant".
 *
 * @author QKninja
 */
public abstract class GrowablePlant extends GrowableGeneric
{

    public GrowablePlant(ItemStack yield, int cycleLength)
    {
        super(yield, cycleLength);
    }

    @Override
    public boolean canGrow()
    {
        return this.getWorld().getBlockState(this.getPos().north()).getBlock() == Blocks.air &&
                this.getWorld().getBlockState(this.getPos().south()).getBlock() == Blocks.air &&
                this.getWorld().getBlockState(this.getPos().east()).getBlock() == Blocks.air &&
                this.getWorld().getBlockState(this.getPos().west()).getBlock() == Blocks.air &&
                this.getWorld().getBlockState(this.getPos().up()).getBlock() == Blocks.air &&
                this.getWorld().getBlockState(this.getPos().down()).getBlock() == Blocks.farmland;
    }
}
