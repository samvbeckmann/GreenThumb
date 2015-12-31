package com.qkninja.greenthumb.block;

import net.minecraft.block.material.Material;

/**
 * GreenThumb, class made on 2015-12-30
 *
 * @author Sam Beckmann
 */
public class BlockGreenhouseEdge extends BlockGreenThumb
{
    public BlockGreenhouseEdge()
    {
        super(Material.glass);
        this.setUnlocalizedName("greenhouse_edge");
        this.setHardness(0.5F);
    }
}
