package com.qkninja.greenthumb.creativetab;

import com.qkninja.greenthumb.init.GreenThumbItems;
import com.qkninja.greenthumb.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Defines the Creative tab for Green Thumb mod.
 *
 * @author QKninja
 */
public class CreativeTabGreenThumb
{
    public static final CreativeTabs GREENTHUMB_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return GreenThumbItems.crystalShard;
        } // TODO: Add icon
    };
}
