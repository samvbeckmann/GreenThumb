package com.qkninja.greenthumb.proxy;

import com.qkninja.greenthumb.init.GreenThumbBlocks;
import com.qkninja.greenthumb.init.GreenThumbItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Contains methods for client-side proxy operations.
 *
 * @author QKninja
 */
@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit()
    {
        registerRenderers();
    }

    @Override
    public void init()
    {
        /* NOOP */
    }

    @Override
    public void postInit()
    {
        /* NOOP */
    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    private void registerRenderers()
    {
        GreenThumbItems.registerRenders();
        GreenThumbBlocks.registerRenders();
    }
}
