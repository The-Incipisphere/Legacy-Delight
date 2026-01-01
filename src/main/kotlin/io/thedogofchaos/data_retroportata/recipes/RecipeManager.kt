package io.thedogofchaos.data_retroportata.recipes

import net.minecraft.util.ResourceLocation
import java.util.Collections
import java.util.Optional
import kotlin.collections.get

object RecipeManager {
    /**
     * Mutable map, for use with setters and internal mutation.
     *
     * If you touch this via ASM/Mixins/Reflection, and shit ends up breaking, don't come crying to me.
     */
    private var recipesInternal: MutableMap<ResourceLocation, IRecipe> = mutableMapOf()

    /**
     * A ***LIVE BUT UNMODIFIABLE*** view of [recipesInternal], for use with getters. **THIS IS NOT A COPY.**
     *
     * (I'm not letting people shoot themselves in the feet THAT easily.)
     */
    val recipes: Map<ResourceLocation, IRecipe> get() = Collections.unmodifiableMap(recipesInternal)

    fun register(recipe: IRecipe) {
        if (recipesInternal.containsKey(recipe.id)) {
            TODO("Gotta figure out how to handle recipe overriding without this turning into the wild west.")
        }
        recipesInternal[recipe.id] = recipe
    }

    /**
     * Returns [Optional]
     */
    fun getRecipe(id: ResourceLocation?): Optional<IRecipe> = Optional.ofNullable(recipes[id]);

    // TODO: THIS WILL BE MOVED SOMEWHERE ELSE ONCE RECIPEMANAGER IS PART OF DATA_RETROPORTATA
    fun init() {}
}
