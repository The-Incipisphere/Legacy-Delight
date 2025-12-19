package io.thedogofchaos.legacydelight.common.crafting.impl;

import io.thedogofchaos.legacydelight.common.crafting.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/// TODO: Implement.
public class OreDictIngredient implements IIngredient {
    public OreDictIngredient(String ore) {

    }

    @Override
    public boolean matches(ItemStack itemStack) {
        return false;
    }
}
