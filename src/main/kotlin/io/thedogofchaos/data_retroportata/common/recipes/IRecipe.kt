package io.thedogofchaos.data_retroportata.common.recipes

import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.util.ResourceLocation

/**
 * Common blueprint for all SerDe&Reg-able recipes.
 *
 * All implementations of this interface should be immutable, please and thank you.
 */
interface IRecipe {
    /** Because all individual recipes should have a unique identifier each. */
    val id: SaneResLoc

    /**
     * Realistically, the return value of this method should be hard-coded per recipe class,
     * but if someone wants to backport KubeJS and do things dynamically, be my guest.
     */
    val type: SaneResLoc
}
