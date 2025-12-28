package io.thedogofchaos.legacydelight.common.recipes

import net.minecraft.util.ResourceLocation

interface IRecipe {
    /** Because all individual recipes should have a unique identifier each. */
    val id: ResourceLocation

    /**
     * Realistically, the return value of this method should be hard-coded per recipe class,
     * but if someone wants to backport KubeJS and do things dynamically, be my guest.
     */
    val type: ResourceLocation
}
