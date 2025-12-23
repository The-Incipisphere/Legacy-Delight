package io.thedogofchaos.legacydelight.common.recipes;

import net.minecraft.item.ItemStack;

/// TODO: Make implementations.
public interface IIngredient {
    boolean matches(ItemStack itemStack);
}
