package io.thedogofchaos.legacydelight.common.recipes.impl;

import io.thedogofchaos.legacydelight.common.recipes.IResult;
import net.minecraft.item.ItemStack;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class ResultGuaranteed implements IResult {
    private final ItemStack itemStack;

    /**
     * Represents an ItemStack GUARANTEED to be given as the result of a recipe.
     * @throws NullPointerException If {@code itemStack} is null.
     */
    public ResultGuaranteed(ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "Result ItemStack must not be null.");
    }

    /**
     * Always gives a copy of the ItemStack for this recipe result.
     *
     * @param rand Does nothing here, but since {@link IResult} is meant to be iterated over,
     *             passing null will throw a NPE, in respect for inheritors like {@link ResultChance}.
     * @return An {@link Optional} containing a copy of the stored {@link net.minecraft.item.ItemStack}.
     * @throws NullPointerException If {@code rand} is null.
     */
    @Override
    public Optional<ItemStack> getStack(Random rand) {
        Objects.requireNonNull(rand, "Parameter 'rand' must not be null (in respect to other inheritors of IResult)");
        return Optional.of(itemStack.copy());
    }
}
