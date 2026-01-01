package io.thedogofchaos.legacydelight.common.recipes

import io.thedogofchaos.data_retroportata.common.recipes.IResult
import net.minecraft.item.ItemStack
import java.util.*
import kotlin.ranges.coerceIn

/**
 * Represents an ItemStack that has a CHANCE to be given as the result of a recipe.
 * @param chance The chance of the itemstack being returned by [ResultChance.getStack].
 * (Automatically clamped to the range 0-100)
 */
class ResultChance(
    private val itemStack: ItemStack,
    chance: Int
) : IResult {
    // clamp to 0-100
    val chance: Int = chance.coerceIn(0, 100)

    /**
     * Gives a copy of the ItemStack for this recipe result... or not. (Depends on the defined chance).
     * Do you feel lucky?
     *
     * @param rand The [Random] instance to use.
     * @return A [Optional]. Contains a copy of the stored [ItemStack]
     * if the chance roll succeeds, or [Optional.empty] otherwise.
     */
    override fun getStack(rand: Random): Optional<ItemStack> {
        return if (rand.nextInt(100) < chance) {
            Optional.of(itemStack.copy())
        } else {
            Optional.empty<ItemStack>()
        }
    }
}
