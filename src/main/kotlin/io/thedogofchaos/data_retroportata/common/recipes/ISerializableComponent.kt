package io.thedogofchaos.data_retroportata.common.recipes

import com.google.gson.JsonObject

interface ISerializableComponent<T> {
    fun fromJson(jsonObject: JsonObject): T
}
