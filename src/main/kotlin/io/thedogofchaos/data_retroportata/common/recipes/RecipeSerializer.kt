package io.thedogofchaos.data_retroportata.common.recipes

import com.google.gson.JsonObject
import net.minecraft.util.ResourceLocation

interface RecipeSerializer<T : IRecipe> {
    fun fromJson(id: ResourceLocation, json: JsonObject): T
}

