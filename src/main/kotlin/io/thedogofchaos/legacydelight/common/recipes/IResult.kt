package io.thedogofchaos.legacydelight.common.recipes

import net.minecraft.item.ItemStack
import java.util.*

/**
 * Blueprint for any recipe results.
 */
interface IResult {
    /** Yea, just pass a [Random] in.
     * Don't forget to handle the [Optional] tho. */
    fun getStack(rand: Random): Optional<ItemStack>
}
