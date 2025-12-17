package io.thedogofchaos.legacydelight.client;

import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import io.thedogofchaos.legacydelight.LegacyDelight;
import io.thedogofchaos.legacydelight.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.


    @Override
    public void init(FMLInitializationEvent event) {
        ModelRegistry.registerModid(LegacyDelight.MODID);
        super.init(event);
    }
}
