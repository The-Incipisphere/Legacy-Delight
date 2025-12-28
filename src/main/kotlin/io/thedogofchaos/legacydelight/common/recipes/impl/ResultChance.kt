package io.thedogofchaos.legacydelight.common.recipes.impl

import io.thedogofchaos.legacydelight.common.recipes.IResult
import net.minecraft.item.ItemStack
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.ranges.coerceIn

/**
 * Represents an ItemStack that has a CHANCE to be given as the result of a recipe.
 * @param chance The chance of the itemstack being returned by [ResultChance.getStack].
 * (Automatically clamped to the range 0.0f-1.0f)
 */
class ResultChance(
    val itemStack: ItemStack,
    chance: Float
) : IResult {
    val chance: Float = chance.coerceIn(0f, 1f)

    /**
     * Gives a copy of the ItemStack for this recipe result... or not. (Depends on the defined chance).
     * Do you feel lucky?
     *
     * @param rand The [Random] instance to use.
     * @return A [Optional]. Contains a copy of the stored [ItemStack]
     * if the chance roll succeeds, or [Optional.empty] otherwise.
     */
    override fun getStack(rand: Random): Optional<ItemStack> {
        return if (rand.nextFloat() <= chance) {
            Optional.of(itemStack.copy())
        } else {
            Optional.empty<ItemStack>()
        }
    }
}
