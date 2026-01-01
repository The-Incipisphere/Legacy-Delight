package io.thedogofchaos.legacydelight.common.recipes

import io.thedogofchaos.data_retroportata.common.recipes.IResult
import net.minecraft.item.ItemStack
import java.util.*

/**
 * Represents an ItemStack GUARANTEED to be given as the result of a recipe.
 */
class ResultGuaranteed(private val itemStack: ItemStack) : IResult {

    /**
     * Always gives a copy of the ItemStack for this recipe result.
     *
     * @param rand NOOP here, but must not be null, given that [IResult] is meant to be iterated over.
     * @return An [Optional] containing a copy of the stored [ItemStack].
     */
    override fun getStack(rand: Random): Optional<ItemStack> = Optional.of(itemStack.copy())
}
