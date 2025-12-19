package io.thedogofchaos.legacydelight.common.crafting.impl;

import io.thedogofchaos.legacydelight.common.crafting.IResult;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class GuaranteedResult implements IResult {
    private final ItemStack items;

    /**
     * Represents an ItemStack GUARANTEED to be given as the result of a recipe.
     */
    public GuaranteedResult(ItemStack items) {
        this.items = items;
    }

    @Override
    public ItemStack getStack(Random rand) {
        return items;
    }
}
