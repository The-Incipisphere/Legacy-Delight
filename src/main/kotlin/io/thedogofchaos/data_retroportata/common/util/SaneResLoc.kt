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
    private val domain: String
    private val path: String

    /**
     * Constructs from two seperate strings. Extremely basic, works just like a standard [ResourceLocation].
     *
     */
    private constructor(domain: String, path: String) {
        this.domain = assertValidDomain(domain, path)
        this.path = assertValidPath(domain, path)
    }

    /**
     * Constructs from a pair of two strings.
     */
    constructor(locationPair: Pair<String, String>) : this(locationPair.first, locationPair.second)

    /**
     * Constructs
     */
    constructor(resourceLocation: String) : this(unsafeSplitDomainAndPath(resourceLocation, ':'))

    override fun compareTo(other: SaneResLoc): Int {
        TODO("Not yet implemented")
    }

    /** Easy converter, for interacting with shit that expects a bare [ResourceLocation]. */
    fun toResourceLocation(): ResourceLocation = ResourceLocation(domain, path);

    companion object {
        /**
         * Splits a raw string into a [Pair] of the domain and path, based on a given [seperator].
         *
         * **DOES NOT validate the result.** May return empty or invalid strings.
         */
        private fun unsafeSplitDomainAndPath(resLoc: String, seperator: Char): Pair<String, String> {
            var domain = "minecraft"
            var path = resLoc

            val sepIndex: Int = resLoc.indexOf(seperator) // get the first seperator
            if (sepIndex >= 0) { // if the seperator is present...
                path = resLoc.substring(sepIndex + 1) // ...set the path to everything after the first separator.
                if (sepIndex >= 1) { // if there is anything before the first seperator...
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
         * @return `true` if the specified `path` is valid: consists only of `[a-z0-9/._-]` characters
         */
        fun isValidPath(path: String): Boolean {
            for (i in 0..<path.length) {
                if (!validPathChar(path[i])) {
                    return false
                }
            }
            return true
        }

        /**
         * @return `true` if the specified `domain` is valid: consists only of `[a-z0-9_.-]` characters
         */
        fun isValidDomain(domain: String): Boolean {
            for (i in 0..<domain.length) {
                if (!validDomainChar(domain[i])) {
                    return false
                }
            }
            return true
        }

        fun validPathChar(pathChar: Char): Boolean {
            return pathChar == '_' || pathChar == '-' || pathChar in 'a'..'z' || pathChar in '0'..'9' || pathChar == '/' || pathChar == '.'
        }

        fun validDomainChar(domainChar: Char): Boolean {
            return domainChar == '_' || domainChar == '-' || domainChar in 'a'..'z' || domainChar in '0'..'9' || domainChar == '.'
        }
    }
}


