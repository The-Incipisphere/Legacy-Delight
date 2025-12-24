package io.thedogofchaos.data_retroportata;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import io.thedogofchaos.legacydelight.LegacyDelight;
import io.thedogofchaos.legacydelight.Tags;
import io.thedogofchaos.legacydelight.common.CommonProxy;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
    modid = DataRetroportata.MODID,
    version = "1.0.0",
    name = "Data Retroportata",
    acceptedMinecraftVersions = "[1.7.10]"
)
public class DataRetroportata {
    public static final String MODID = "data_retroportata";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "io.thedogofchaos.data_retroportata.client.ClientProxy",
        serverSide = "io.thedogofchaos.data_retroportata.common.CommonProxy")
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
