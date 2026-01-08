package io.thedogofchaos.data_retroportata.common.recipes

import io.thedogofchaos.data_retroportata.common.util.SaneResLoc

interface IComponent {
    val type: SaneResLoc
    // fun matches(itemStack: ItemStack?): Boolean
}

// "It can't be that easy."
// "It's that easy."
interface IInputComponent : IComponent
interface IOutputComponent : IComponent
