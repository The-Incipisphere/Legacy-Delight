package io.thedogofchaos.data_retroportata.common.recipes

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc

interface ISerializableRecipe<T : IRecipe<*>> {
    fun fromJson(id: SaneResLoc, json: JsonObject): T
    fun toJson(): JsonObject
}

