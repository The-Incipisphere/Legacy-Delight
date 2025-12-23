package io.thedogofchaos.legacydelight.common.recipes.impl;

import io.thedogofchaos.legacydelight.common.recipes.IIngredient;
import net.minecraft.item.ItemStack;

/// TODO: Implement.
public class IngredientOreDict implements IIngredient {
    public IngredientOreDict(String ore) {

    }

    @Override
    public boolean matches(ItemStack itemStack) {
        return false;
    }
}
