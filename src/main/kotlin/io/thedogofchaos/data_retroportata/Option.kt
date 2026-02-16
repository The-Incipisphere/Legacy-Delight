package io.thedogofchaos.data_retroportata

// WELCOME TO RUST, BUCKOS
@JvmInline
value class Option<out T> internal constructor(internal val value: Any) {
    companion object {
        /**
         * Used to deliberately represent presence of a given thing.
         *
         * **Cannot be set as null.**
         */
        fun <T : Any> some(value: T): Option<T> = Option(value)

        /**
         * Used to deliberately represent absence of a given thing.
         */
        fun <T> none(): Option<T> = Option(makeNone())
    }

    val isSome: Boolean get() = value !is None
    val isNone: Boolean get() = !isSome

    /**
     * Assertively grabs the value inside an [Option].
     * @throws NoSuchElementException If the Option contains None.
     */
    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = when {
        isSome -> value
        else -> throw NoSuchElementException("Called Option.unwrap() on a None value!")
    } as T

    internal object None

    override fun toString(): String = if (isSome) "Some(value=$value)" else "None"
}

internal fun makeNone(): Option.None = Option.None
