package io.thedogofchaos.legacydelight.common.crafting.impl;

import io.thedogofchaos.legacydelight.common.crafting.IResult;
import net.minecraft.item.ItemStack;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class ChanceResult implements IResult {
    private final ItemStack itemStack;
    private final float chance;

    /**
     * Represents an ItemStack that has a CHANCE to be given as the result of a recipe.
     * @param chance The chance of the itemstack being returned by {@link ChanceResult#getStack(Random)}.
     *               (Automatically clamped to the range 0.0f-1.0f)
     * @throws NullPointerException If {@code rand} is null.
     */
    public ChanceResult(ItemStack itemStack, float chance) {
        this.itemStack = Objects.requireNonNull(itemStack, "Result ItemStack must not be null.");
        this.chance = Math.max(0, Math.min(1, chance));
    }

    /**
     * Gives a copy of the ItemStack for this recipe result... or not. (Depends on the defined chance).
     * Do you feel lucky?
     *
     * @param rand The {@link Random} instance to use.
     * @return A {@link Optional}. Contains a copy of the stored {@link net.minecraft.item.ItemStack}
     *         if the chance roll succeeds, or {@link Optional#empty()} otherwise.
     */
    @Override
    public Optional<ItemStack> getStack(Random rand) {
        if(rand.nextFloat() <= chance) {
            return Optional.of(itemStack.copy());
        } else {
            return Optional.empty();
        }
    }

    public float getChance() {
        return chance;
    }
}
