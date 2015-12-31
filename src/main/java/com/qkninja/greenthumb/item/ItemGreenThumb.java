package com.qkninja.greenthumb.item;

import com.qkninja.greenthumb.creativetab.CreativeTabGreenThumb;
import com.qkninja.greenthumb.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IIconRegister iconRegister) // TODO: Fix Rendering
//    {
//        itemIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName()));
//    }

    public String getUnwrappedUnlocalizedName ()
    {
        return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1);
    }

    private static String unwrapName(String unlocalizedName) // TODO: Move to helper class.
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
