package io.thedogofchaos.legacydelight;

import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import io.thedogofchaos.legacydelight.common.CommonProxy;

@Mod(
    modid = LegacyDelight.MODID,
    version = Tags.VERSION,
    name = "Legacy Delight",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhlib@[0.8.34,);"
)
public class LegacyDelight {

    public static final String MODID = "legacydelight";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "io.thedogofchaos.legacydelight.client.ClientProxy",
        serverSide = "io.thedogofchaos.legacydelight.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        LegacyDelight.LOG.info("Legacy Delight v" + Tags.VERSION + ", cooking up some meals to go!");
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        // MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        // FMLCommonHandler.instance()
        // .bus()
        // .register(new FMLEventHandler());
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    public static ResourceLocation resLoc(String str) {
        return new ResourceLocation(MODID, str);
    };
}
