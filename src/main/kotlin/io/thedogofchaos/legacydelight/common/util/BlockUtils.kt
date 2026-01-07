package io.thedogofchaos.legacydelight.common.util

import net.minecraft.block.Block

object BlockUtils {
    /**
     * Because block collision bounds aren't defined in 'Number of subpixels of a block' in 1.7.10 (compared to later versions),
     * they're defined in 'Fractions of a block' (i.e. 1/16 = 0.0625)
     */
    const val BOUNDS_MULT: Float = 0.0625f

    /**
     * Sets the bounds for a block in a similar way to more modern versions of minecraft.
     * See the KDoc on [BlockUtils.BOUNDS_MULT] for a reason why.
     *
     * @param block The block to set the bounds of.
     */
    fun setSaneBounds(
        block: Block,
        minX: Int, minY: Int, minZ: Int, // start corner
        maxX: Int, maxY: Int, maxZ: Int  // end corner
    ) {
        // the parameters here are cast to doubles by Block#setBlockBounds (for some reason), but i don't fucking care,
        // since the JVM type-casts numbers when basic arithmatic is involved
        block.setBlockBounds(
            minX * BOUNDS_MULT,
            minY * BOUNDS_MULT,
            minZ * BOUNDS_MULT,
            maxX * BOUNDS_MULT,
            maxY * BOUNDS_MULT,
            maxZ * BOUNDS_MULT
        )
    }
}
