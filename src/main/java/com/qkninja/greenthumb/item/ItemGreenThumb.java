package com.qkninja.greenthumb.item;

import com.qkninja.greenthumb.creativetab.CreativeTabGreenThumb;
import com.qkninja.greenthumb.reference.Reference;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Generic Item for Network Mod
 *
 * @author QKninja
 */
public class ItemGreenThumb extends Item
{
    public ItemGreenThumb()
    {
        super();
        this.setCreativeTab(CreativeTabGreenThumb.GREENTHUMB_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", unwrapName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", unwrapName(super.getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() // TODO: Fix Rendering
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public String getUnwrappedUnlocalizedName ()
    {
        String unlocalized = this.getUnlocalizedName();
        return unlocalized.substring(unlocalized.indexOf(".") + 1);
    }

    private static String unwrapName(String unlocalizedName) // TODO: Move to helper class.
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
