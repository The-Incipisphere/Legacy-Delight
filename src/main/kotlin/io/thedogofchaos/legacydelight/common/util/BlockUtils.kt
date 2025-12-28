package io.thedogofchaos.legacydelight.common.util

import net.minecraft.block.Block

object BlockUtils {
    /**
     * Because bounds aren't defined in 'Number of subpixels of a block' in 1.7.10 (compared to later versions),
     * they're defined in 'Fractions of a block' (i.e. 1/16 = 0.0625)
     */
    const val boundsMult: Float = 0.0625f

    /**
     * Sets the bounds for a block in a similar way to more modern versions of minecraft.
     * See the doc/comment on [BlockUtils.boundsMult] for a reason why.
     *
     * @param block The block to set the bounds of.
     */
    fun setSaneBounds(
        block: Block,
        minX: Int, minY: Int, minZ: Int,
        maxX: Int, maxY: Int, maxZ: Int
    ) {
        // the parameters here are cast to doubles by Block#setBlockBounds (for some reason), but i don't fucking care,
        // since the JVM type-casts numbers when basic arithmatic is involved
        block.setBlockBounds(
            minX * boundsMult,
            minY * boundsMult,
            minZ * boundsMult,
            maxX * boundsMult,
            maxY * boundsMult,
            maxZ * boundsMult
        )
    }
}
