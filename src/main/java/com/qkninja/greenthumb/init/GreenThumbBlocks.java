package com.qkninja.greenthumb.init;

import com.qkninja.greenthumb.block.BlockGreenThumb;
import com.qkninja.greenthumb.block.BlockGreenhouseEdge;
import com.qkninja.greenthumb.block.growable.BlockPlantCoal;
import com.qkninja.greenthumb.item.ItemGreenThumb;
import com.qkninja.greenthumb.reference.Reference;
import com.qkninja.greenthumb.tileentity.TileEntityGreenThumb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Initializes the blocks and tile entities in the mod.
 *
 * @author QKninja
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public final class GreenThumbBlocks
{

    public static final BlockGreenThumb GREENHOUSE_EDGE = new BlockGreenhouseEdge();
    public static final BlockGreenThumb PLANT_COAL = new BlockPlantCoal();


    public static void register()
    {
        GameRegistry.registerBlock(GREENHOUSE_EDGE, GREENHOUSE_EDGE.getUnwrappedUnlocalizedName());
        GameRegistry.registerBlock(PLANT_COAL, PLANT_COAL.getUnwrappedUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        GREENHOUSE_EDGE.initModel();
        PLANT_COAL.initModel();
    }
}
