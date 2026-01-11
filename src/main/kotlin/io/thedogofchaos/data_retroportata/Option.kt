package io.thedogofchaos.data_retroportata

// WELCOME TO RUST, BUCKOS
@JvmInline
value class Option<out T> internal constructor(internal val value: Any?) {
    companion object {
        fun <T> some(value: T): Option<T> = Option(value)
        fun <T> none(): Option<T> = Option(makeNone())
    }

    val isSome: Boolean get() = value !is None
    val isNone: Boolean get() = !isSome

    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = when {
        isSome -> value
        else -> throw IllegalStateException("Called Option.unwrap() on a None value!")
    } as T

    internal class None
}

internal fun makeNone(): Any = Option.None()
