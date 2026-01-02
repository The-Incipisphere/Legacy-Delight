package io.thedogofchaos.data_retroportata.common.recipes

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.common.recipes.impl.ItemIngredient

interface IIngredientSerializer {

    fun fromJson(jsonObject: JsonObject): ItemIngredient {
        TODO("Not yet implemented")
    }
}
