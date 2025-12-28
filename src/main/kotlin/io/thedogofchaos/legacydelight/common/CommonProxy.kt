package io.thedogofchaos.legacydelight.common

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import io.thedogofchaos.legacydelight.Config
import io.thedogofchaos.legacydelight.LegacyDelight
import io.thedogofchaos.legacydelight.common.blocks.ModBlocks
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item

open class CommonProxy {
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    open fun preInit(event: FMLPreInitializationEvent) {
        Config.synchronizeConfiguration(event.suggestedConfigurationFile)

        ModBlocks.register()
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    open fun init(event: FMLInitializationEvent) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    open fun postInit(event: FMLPostInitializationEvent) {}

    // register server commands in this event handler (Remove if not needed)
    open fun serverStarting(event: FMLServerStartingEvent) {}

    companion object {
        var legacyDelightTab: CreativeTabs = object : CreativeTabs(LegacyDelight.MODID) {
            override fun getTabIconItem(): Item {
                // Full path to the field, for at-a-glance clarity.
                // May be replaced in future with one of the items from this mod.
                return Items.cooked_beef
            }
        }
    }
}
