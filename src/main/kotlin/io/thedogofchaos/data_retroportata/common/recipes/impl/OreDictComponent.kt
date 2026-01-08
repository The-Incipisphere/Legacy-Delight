package io.thedogofchaos.data_retroportata.common.recipes.impl

import io.thedogofchaos.data_retroportata.MOD_ID
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc

data class OreDictComponent(
    val oreDict: String
) : IInputComponent { // Yes, this implements only IInputComponent, because i'm not letting other devs shoot their feet off by allowing for this to be an output.
    override val type = SaneResLoc(MOD_ID, "ore_dict")

    init {
        TODO("Stub class (for now).")
    }
}
