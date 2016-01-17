package com.qkninja.greenthumb.init;

import com.qkninja.greenthumb.tileentity.growable.plant.TileEntityPlantCoal;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registers TileEntities in GreenThumb
 *
 * @author QKninja
 */
public class GreenThumbTileEntities
{
    public static void register()
    {
        GameRegistry.registerTileEntity(TileEntityPlantCoal.class, "plantCoal");
    }
}
