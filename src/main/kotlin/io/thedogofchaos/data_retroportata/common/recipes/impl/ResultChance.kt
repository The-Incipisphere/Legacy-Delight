package io.thedogofchaos.data_retroportata.common.recipes.impl

import io.thedogofchaos.data_retroportata.common.recipes.IResult
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.item.ItemStack
import java.util.Optional
import java.util.Random

/**
 * Represents an ItemStack that has a CHANCE to be given as the result of a recipe.
 * @param chance The chance of the itemstack being returned by [ResultChance.getStack].
 * (Automatically clamped to the range 0-100)
 */
// TODO: Overhaul this class to make it into a 'container' to put other components inside.
class ResultChance(
    private val itemStack: ItemStack,
    chance: Int
) : IResult {
    override val type = SaneResLoc("data_retroportata","chance_item")
    // clamp to 0-100
    val chance: Int = chance.coerceIn(0, 100)

    /**
     * Gives a copy of the ItemStack for this recipe result... or not. (Depends on the defined chance).
     * Do you feel lucky?
     *
     * @param rand The [java.util.Random] instance to use.
     * @return A [java.util.Optional]. Contains a copy of the stored [ItemStack]
     * if the chance roll succeeds, or [java.util.Optional.empty] otherwise.
     */
    override fun getStack(rand: Random): Optional<ItemStack> {
        return if (rand.nextInt(100) < chance) {
            Optional.of(itemStack.copy())
        } else {
            Optional.empty<ItemStack>()
        }
    }
}
