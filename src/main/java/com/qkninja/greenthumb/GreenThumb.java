package com.qkninja.greenthumb;

import com.qkninja.greenthumb.handler.ConfigurationHandler;
import com.qkninja.greenthumb.init.GreenThumbBlocks;
import com.qkninja.greenthumb.init.GreenThumbItems;
import com.qkninja.greenthumb.proxy.CommonProxy;
import com.qkninja.greenthumb.reference.Reference;
import com.qkninja.greenthumb.utility.LogHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Main class for the Green Thumb mod
 *
 * @author QKninja
 * @version 1.8.9-0.0.0
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION /*, guiFactory = Reference.GUI_FACTORY_CLASS */)
public class GreenThumb
{
    @Mod.Instance(Reference.MOD_ID)
    public static GreenThumb instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        GreenThumbItems.register();

        GreenThumbBlocks.register();

        proxy.preInit();

//        FMLInterModComms.sendMessage("Waila", "register", "com.qkninja.greenthumb.thirdparty.waila.Waila.onWailaCall");

        LogHelper.info("Pre Initialization Complete!");

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        proxy.init();

//        Recipes.register();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();

        LogHelper.info("Post Initialization Complete!");

    }

//    @Mod.EventHandler
//    public void onIMCMessages(FMLInterModComms.IMCEvent event)
//    {
//
//    }
}
