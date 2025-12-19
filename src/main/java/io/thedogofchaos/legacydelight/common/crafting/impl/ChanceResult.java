package io.thedogofchaos.legacydelight.common.crafting.impl;

import io.thedogofchaos.legacydelight.common.crafting.IResult;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Random;

public class ChanceResult implements IResult {
    private final ItemStack itemStack;
    private final float chance;

    /**
     * Represents an ItemStack that has a CHANCE to be given as the result of a recipe.
     */
    public ChanceResult(ItemStack itemStack, float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    @Override
    @Nullable
    public ItemStack getStack(Random rand) {
        if(chance <= rand.nextFloat()) {
            return itemStack;
        } else {
            return null;
        }
    }
}
