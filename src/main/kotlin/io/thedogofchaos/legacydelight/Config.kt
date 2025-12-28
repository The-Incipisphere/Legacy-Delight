package io.thedogofchaos.legacydelight

import net.minecraftforge.common.config.Configuration
import java.io.File

object Config {
    var greeting: String? = "Hello World"

    fun synchronizeConfiguration(configFile: File?) {
        val configuration = Configuration(configFile)

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?")

        if (configuration.hasChanged()) {
            configuration.save()
        }
    }
}
