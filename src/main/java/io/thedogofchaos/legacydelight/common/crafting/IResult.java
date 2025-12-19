package io.thedogofchaos.legacydelight.common.crafting;

import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * Interface for any recipe results.
 */
public interface IResult {
    ItemStack getStack(Random rand);
}
