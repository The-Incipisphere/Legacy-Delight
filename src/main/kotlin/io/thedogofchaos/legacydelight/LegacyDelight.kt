package io.thedogofchaos.legacydelight

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLInterModComms
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

    /**
     * This event is sent before anything else.
     * We read our config, create blocks, items, etc, and register them with the GameRegistry.
      */
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        LOG.info("Legacy Delight v" + Tags.VERSION + ", cooking up some meals to go!")
        proxy.preInit(event)
    }

    /**
     * This event is sent after [FMLPreInitializationEvent].
     * We do our mod setup.
     * We build whatever data structures we care about.
     * We register recipes.
     * We send [FMLInterModComms] to other mods, if needed.
     */
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    /**
     * This event is always sent last in the initialisation sequence.
     * We handle interactions with other mods, and complete our setup accordingly.
     */
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }

    /**
     * We do stuff you need to do to set up the server. register commands, tweak the server.
     */
    @Mod.EventHandler // register server commands in this event handler (Remove if not needed)
    fun serverStarting(event: FMLServerStartingEvent) {
        proxy.serverStarting(event)
    }

    fun resLoc(str: String): ResourceLocation = ResourceLocation(MODID, str);
}
