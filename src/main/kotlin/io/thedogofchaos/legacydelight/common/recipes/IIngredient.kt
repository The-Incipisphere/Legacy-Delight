package io.thedogofchaos.legacydelight.common.recipes

import net.minecraft.item.ItemStack

/** TODO: Make implementations. */
interface IIngredient {
    fun matches(itemStack: ItemStack?): Boolean
}
