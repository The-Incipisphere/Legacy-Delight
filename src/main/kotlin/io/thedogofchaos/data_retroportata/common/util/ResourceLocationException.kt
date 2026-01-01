package io.thedogofchaos.data_retroportata.common.util

import org.apache.commons.lang3.StringEscapeUtils

/**
 * @author Mojang (originally)
 * @author TheDogOfChaos (Kotlin adaptation and 1.7.10 backport)
 */
class ResourceLocationException : RuntimeException {
    constructor(message: String?) : super("Malformed ResourceLocation: ${StringEscapeUtils.escapeJava(message)}")

    constructor(message: String?, cause: Throwable?) : super("Malformed ResourceLocation: ${StringEscapeUtils.escapeJava(message)}", cause)
}
