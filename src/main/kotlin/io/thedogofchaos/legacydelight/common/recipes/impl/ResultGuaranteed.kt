package io.thedogofchaos.legacydelight.common.recipes.impl

import io.thedogofchaos.legacydelight.common.recipes.IResult
import net.minecraft.item.ItemStack
import java.util.*

/**
 * Represents an ItemStack GUARANTEED to be given as the result of a recipe.
 * @throws NullPointerException If `itemStack` is null.
 */
class ResultGuaranteed(val itemStack: ItemStack) : IResult {

    /**
     * Always gives a copy of the ItemStack for this recipe result.
     *
     * @param rand Does nothing here, but since [IResult] is meant to be iterated over,
     * passing null will throw a NPE, in respect for inheritors like [ResultChance].
     * @return An [Optional] containing a copy of the stored [ItemStack].
     * @throws IllegalArgumentException If `rand` is null.
     */
    override fun getStack(rand: Random): Optional<ItemStack> {
        @Suppress("SENSELESS_COMPARISON")
        if (rand == null) throw IllegalArgumentException("Parameter 'rand' must not be null (in respect to other inheritors of IResult)")
        return Optional.of(itemStack.copy())
    }
}
