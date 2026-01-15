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

    /**
     * AKA: "Where the actual fuck is this thing?"
     */
    private val path: String

    /**
     * Constructs from two separate strings (domain & path).
     *
     * **The provided domain and path WILL be validated.**
     *
     * @throws ResourceLocationException if either the domain or path fail validate.
     */
    constructor(domain: String, path: String) {
        this.domain = assertValidDomain(domain)
        this.path = assertValidPath(path)
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
        var i = this.domain.compareTo(other.domain)
        if (i == 0) i = this.path.compareTo(other.path)
        return i
    }

    override fun hashCode(): Int {
        return 31 * this.domain.hashCode() + this.path.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SaneResLoc) return false
        val otherResLoc: SaneResLoc = other
        return this.domain == otherResLoc.domain && this.path == otherResLoc.path
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
            var domain = "minecraft" // TODO: Nuke this unsafe bullshit
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

        /**
         * Asserts that the given domain:
         * 1. Is NOT Empty
         * 2. ONLY contains chars conforming to `[a-z0-9_.-]`
         * @return The provided domain, if all checks pass.
         * @throws ResourceLocationException If any checks fail.
         */
        fun assertValidDomain(domain: String): String {
            // fail fast for completely invalid domains
            if (domain.isEmpty()) throw ResourceLocationException("Domain cannot be empty!")
            if (!domain.all { validDomainChar(it) }) throw ResourceLocationException(
                "Domain '$domain' contains non [a-z0-9_.-] characters!"
            )

            return domain
        }

        /**
         * Asserts that the given path:
         * 1. Is NOT Empty
         * 2. ONLY contains chars conforming to `[a-z0-9/_.-]`
         * 3. Does NOT start or end with `/`
         * 4. Does NOT contain any empty segments (`//`) or dot-segments (`/./` or `/../`)
         * @return The provided path, if all checks pass.
         * @throws ResourceLocationException If any checks fail.
         */
        fun assertValidPath(path: String): String {
            // fail fast for completely invalid paths
            if (path.isEmpty()) throw ResourceLocationException("Path cannot be empty!")
            if (!path.all { validPathChar(it) }) throw ResourceLocationException(
                "Path '$path' contains non [a-z0-9/_.-] characters!"
            )

            // check for leading/trailing slashes & dot/empty segments,
            // because sooner or later, SOMEONE will try providing such a path.
            if (path.startsWith('/') || path.endsWith('/')) throw ResourceLocationException(
                "Path '$path' cannot start or end with '/'!"
            )
            val segments = path.split('/')
            if (segments.any { it.isEmpty() || it == "." || it == ".." }) throw ResourceLocationException(
                "Path '$path' contains empty segments or dot-segments!"
            )

            return path
        }

        /**
         * @return `true` if the specified char would be valid in a [SaneResLoc.domain] (i.e. matches `[a-z0-9_.-]`)
         */
        fun validDomainChar(domainChar: Char): Boolean {
            return domainChar in 'a'..'z' || domainChar in '0'..'9' ||
                domainChar == '_' || domainChar == '.' || domainChar == '-'
        }

        /**
         * @return `true` if the specified char would be valid in a [SaneResLoc.path] (i.e. matches `[a-z0-9/_.-]`)
         */
        fun validPathChar(pathChar: Char): Boolean {
            return pathChar in 'a'..'z' || pathChar in '0'..'9' ||
                pathChar == '/' || pathChar == '_' || pathChar == '.' || pathChar == '-'
        }
    }
}


