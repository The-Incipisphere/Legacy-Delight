package io.thedogofchaos.legacydelight.common.recipes;

import net.minecraft.item.ItemStack;

import java.util.Optional;
import java.util.Random;

/**
 * Interface for any recipe results.
 */
public interface IResult {
    /// Yea, just pass a {@link java.util.Random} in.
    /// Don't forget to handle the {@link java.util.Optional} tho.
    Optional<ItemStack> getStack(Random rand);
}
