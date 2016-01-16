package com.qkninja.greenthumb.init;

import com.qkninja.greenthumb.item.*;
import com.qkninja.greenthumb.reference.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        GameRegistry.registerItem(crystalShard, crystalShard.getUnwrappedUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        crystalShard.initModel();
    }
}
