package io.thedogofchaos.legacydelight

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import io.thedogofchaos.legacydelight.common.CommonProxy
import net.minecraft.util.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
    modid = LegacyDelight.MODID,
    version = Tags.VERSION,
    name = "Legacy Delight",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhlib@[0.8.34,);" + "required-after:forgelin@[2.0.3,);",
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
)
object LegacyDelight {
    const val MODID: String = "legacydelight";
    var LOG: Logger = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "io.thedogofchaos.legacydelight.client.ClientProxy",
        serverSide = "io.thedogofchaos.legacydelight.common.CommonProxy"
    )
    lateinit var proxy: CommonProxy;

    @Mod.EventHandler // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    fun preInit(event: FMLPreInitializationEvent) {
        LOG.info("Legacy Delight v" + Tags.VERSION + ", cooking up some meals to go!")
        proxy.preInit(event)
    }

    @Mod.EventHandler // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)

        // MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        // FMLCommonHandler.instance()
        // .bus()
        // .register(new FMLEventHandler());
    }

    @Mod.EventHandler // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }

    @Mod.EventHandler // register server commands in this event handler (Remove if not needed)
    fun serverStarting(event: FMLServerStartingEvent) {
        proxy.serverStarting(event)
    }

    fun resLoc(str: String): ResourceLocation = ResourceLocation(MODID, str);
}
