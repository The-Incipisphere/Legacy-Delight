package io.thedogofchaos.data_retroportata.recipes

import net.minecraft.item.ItemStack
import java.util.Optional
import java.util.Random

/**
 * Common blueprint for any recipe results.
 */
interface IResult {
    /** Yea, just pass a [Random] in.
     * Don't forget to handle the [Optional] tho. */
    fun getStack(rand: Random): Optional<ItemStack>
}
