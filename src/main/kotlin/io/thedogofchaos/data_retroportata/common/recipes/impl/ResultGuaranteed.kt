package io.thedogofchaos.data_retroportata.common.recipes.impl

import io.thedogofchaos.data_retroportata.common.recipes.IResult
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.item.ItemStack
import java.util.Optional
import java.util.Random

/**
 * Represents an ItemStack GUARANTEED to be given as the result of a recipe.
 */
@Deprecated("Superseded by ItemStackComponent. (my overall view of how this system will play out has changed drastically since i wrote this class)")
class ResultGuaranteed(private val itemStack: ItemStack) : IResult {

    /**
     * Always gives a copy of the ItemStack for this recipe result.
     *
     * @param rand NOOP here, but must not be null, given that [IResult] is meant to be iterated over.
     * @return An [java.util.Optional] containing a copy of the stored [ItemStack].
     */
    override fun getStack(rand: Random): Optional<ItemStack> = Optional.of(itemStack.copy())
    override val type = SaneResLoc("data_retroportata", TODO())
}
