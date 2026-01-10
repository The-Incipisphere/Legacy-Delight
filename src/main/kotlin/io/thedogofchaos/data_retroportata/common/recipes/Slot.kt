package io.thedogofchaos.data_retroportata.common.recipes

// WELCOME TO RUST, BUCKOS
sealed class Slot {
    object Empty : Slot() { override fun toString() = "Empty" }
    data class Filled(val component: IComponent) : Slot()
}

