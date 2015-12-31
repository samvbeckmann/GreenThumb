package com.qkninja.greenthumb.init;

import com.qkninja.greenthumb.item.*;
import com.qkninja.greenthumb.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Initializes all the items in the Network mod.
 *
 * @author QKninja
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class GreenThumbItems
{
    public static final ItemGreenThumb crystalShard = new ItemCrystalShard();

    public static void register()
    {
        GameRegistry.registerItem(crystalShard, /* Names.Items.ITEM_CORE */ crystalShard.getUnwrappedUnlocalizedName());
    }

    public static void registerRenders()
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerRender(crystalShard, mesher);
    }

    private static void registerRender(ItemGreenThumb item, ItemModelMesher mesher)
    {
        mesher.register(item, 0, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + item.getUnwrappedUnlocalizedName(), "inventory"));
    }
}
