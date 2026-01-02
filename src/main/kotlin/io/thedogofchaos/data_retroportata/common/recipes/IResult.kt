package io.thedogofchaos.data_retroportata.common.recipes

import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import java.util.Optional
import java.util.Random

/**
 * Common blueprint for any recipe results.
 */
interface IResult {
    /** Yea, just pass a [Random] in.
     * Don't forget to handle the [Optional] tho. */
    fun getStack(rand: Random): Optional<ItemStack>
    val type: SaneResLoc
}
