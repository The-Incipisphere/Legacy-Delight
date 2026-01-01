package io.thedogofchaos.data_retroportata.recipes

import net.minecraft.item.ItemStack

/** TODO: Make implementations. */
interface IIngredient {
    fun matches(itemStack: ItemStack?): Boolean
}
