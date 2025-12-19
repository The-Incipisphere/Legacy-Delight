package io.thedogofchaos.legacydelight.common.util;

import net.minecraft.block.Block;

public class BlockUtils {

    /**
     * Because bounds aren't defined in 'Number of subpixels of a block' in 1.7.10 (compared to later versions),
     * they're defined in 'Fractions of a block' (i.e. 1/16 = 0.0625)
     */
    public static final float boundsMult = 0.0625F;

    /**
     * Sets the bounds for a block in a similar way to more modern versions of minecraft.
     * See the doc/comment on {@link BlockUtils#boundsMult} for a reason why.
     *
     * @param block The block to set the bounds of.
     */
    public static void setSaneBounds(Block block, float minX, float minY, float minZ, float maxX, float maxY,
        float maxZ) {
        // the parameters here are cast to doubles by Block#setBlockBounds (for some reason), but i don't fucking care,
        // since Java casts floats to doubles automatically when arithmetic is involved.
        block.setBlockBounds(
            minX * boundsMult,
            minY * boundsMult,
            minZ * boundsMult,
            maxX * boundsMult,
            maxY * boundsMult,
            maxZ * boundsMult);
    }
}
