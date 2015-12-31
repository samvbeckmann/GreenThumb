package com.qkninja.greenthumb.init;

import com.qkninja.greenthumb.block.BlockGreenThumb;
import com.qkninja.greenthumb.block.BlockGreenhouseEdge;
import com.qkninja.greenthumb.item.ItemGreenThumb;
import com.qkninja.greenthumb.reference.Reference;
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

    public static void register()
    {
        GameRegistry.registerBlock(GREENHOUSE_EDGE, GREENHOUSE_EDGE.getUnwrappedUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerRender(GREENHOUSE_EDGE, mesher);
    }

    private static void registerRender(BlockGreenThumb block, ItemModelMesher mesher)
    {
        Item item = Item.getItemFromBlock(block); // TODO: Fix up this to not have to manipulate strings.
        mesher.register(item, 0, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
