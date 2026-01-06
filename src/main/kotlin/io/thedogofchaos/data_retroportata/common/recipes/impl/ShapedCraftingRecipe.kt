package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.RecipeSerializer
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.util.ResourceLocation

data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
) : IRecipe {
    override val type = SaneResLoc("data_retroportata","shaped_crafting")

    companion object Serializer : RecipeSerializer<ShapedCraftingRecipe> {
        override fun fromJson(
            id: ResourceLocation,
            json: JsonObject
        ): ShapedCraftingRecipe {
            TODO("Focusing on other parts of the mod currently.")
        }
    }
}
