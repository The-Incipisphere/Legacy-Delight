package io.thedogofchaos.legacydelight.client

import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import io.thedogofchaos.legacydelight.LegacyDelight
import io.thedogofchaos.legacydelight.common.CommonProxy

class ClientProxy : CommonProxy() {
    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    public override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)
        ModelRegistry.registerModid(LegacyDelight.MODID)
    }
}
