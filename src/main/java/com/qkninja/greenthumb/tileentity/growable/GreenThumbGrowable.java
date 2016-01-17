package com.qkninja.greenthumb.tileentity.growable;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Defines a Growable. Extended by all
 */
public interface GreenThumbGrowable extends IInventory
{
    /**
     * Gets the yield of a Growable.
     *
     * @return ItemStack of one yield from the Growable.
     */
    ItemStack getYield();

    /**
     * Tests if a growable has the conditions needed to grow.
     *
     * @return True if all conditions are met, false otherwise.
     */
    boolean canGrow();
}
