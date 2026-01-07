package io.thedogofchaos.data_retroportata.common.util

import net.minecraft.util.ResourceLocation

/**
 * A sane wrapper of [ResourceLocation], because modern minecraft's ResourceLocation has
 * such useful things that just aren't present on this version.
 *
 * @author Mojang (originally)
 * @author TheDogOfChaos (Kotlin adaptation and 1.7.10 backport)
 */
class SaneResLoc : Comparable<SaneResLoc> {
    /**
     * AKA: Namespace, Mod ID, Datapack ID
     */
    private val domain: String
    private val path: String

    /**
     * Constructs from two separate strings (domain & path).
     *
     * The provided Domain and Path are both validated.
     * @throws ResourceLocationException if either the domain or path contain
     */
    constructor(domain: String, path: String) {
        this.domain = assertValidDomain(domain, path)
        this.path = assertValidPath(domain, path)
    }

    /**
     * Constructs from a pair of two strings.
     */
    constructor(locationPair: Pair<String, String>) : this(locationPair.first, locationPair.second)

    /**
     *
     */
    constructor(resourceLocation: String) : this(unsafeSplitDomainAndPath(resourceLocation, ':'))

    override fun compareTo(other: SaneResLoc): Int {
        TODO("Not yet implemented")
    }

    override fun toString(): String = "$domain:$path"

    /** Easy converter, for interacting with shit that expects a bare [ResourceLocation]. */
    fun toResourceLocation(): ResourceLocation = ResourceLocation(domain, path);

    fun toPair(): Pair<String, String> = domain to path

    companion object {
        /**
         * Splits a raw string into a [Pair] of the domain and path, based on a given [separator].
         *
         * **This method DOES NOT validate results itself, and may return strings that fail validation.**
         *
         * @see [validDomainChar]
         * @see [validPathChar]
         */
        private fun unsafeSplitDomainAndPath(resLoc: String, separator: Char): Pair<String, String> {
            var domain = "minecraft"
            var path = resLoc

            val sepIndex: Int = resLoc.indexOf(separator) // get the first separator
            if (sepIndex >= 0) { // if the separator is present...
                path = resLoc.substring(sepIndex + 1) // ...set the path to everything after the first separator.
                if (sepIndex >= 1) { // if there is anything before the first separator...
                    domain = resLoc.substring(0, sepIndex); // ...set the domain to everything before the first separator.
                }
            }
            return domain to path // we don't validate here, we just cast and return the raw shit
        }

        private fun assertValidDomain(domain: String, path: String): String {
            if (!isValidDomain(domain)) {
                throw ResourceLocationException("Non [a-z0-9_.-] character in domain of ResourceLocation: $domain:$path")
            } else return domain
        }

        private fun assertValidPath(domain: String, path: String): String {
            if (!isValidPath(path)) {
                throw ResourceLocationException("Non [a-z0-9_.-] character in path of ResourceLocation: $domain:$path")
            } else return path
        }

        /**
         * @return `true` if the specified `domain` is valid
         * (i.e. consists only of characters matching `[a-z0-9_.-]`)
         */
        fun isValidDomain(domain: String) = domain.all { validDomainChar(it) }

        /**
         * @return `true` if the specified `path` is valid
         * (i.e. consists only of characters that match `[a-z0-9/_.-]`)
         */
        fun isValidPath(path: String) = path.all { validPathChar(it) }

        /**
         * @return `true` if the specified char would be valid in a [SaneResLoc.domain] (i.e. matches `[a-z0-9_.-]`)
         */
        fun validDomainChar(domainChar: Char): Boolean {
            return domainChar in 'a'..'z' || domainChar in '0'..'9' || domainChar == '_' || domainChar == '.' || domainChar == '-'
        }

        /**
         * @return `true` if the specified char would be valid in a [SaneResLoc.path] (i.e. matches `[a-z0-9/_.-]`)
         */
        fun validPathChar(pathChar: Char): Boolean {
            return pathChar in 'a'..'z' || pathChar in '0'..'9' || pathChar == '/' || pathChar == '_' || pathChar == '.' || pathChar == '-'
        }
    }
}


