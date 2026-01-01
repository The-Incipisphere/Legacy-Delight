package io.thedogofchaos.data_retroportata.common.recipes

import net.minecraft.item.ItemStack

/** TODO: Make implementations. */
interface IIngredient {
    fun matches(itemStack: ItemStack?): Boolean
}
