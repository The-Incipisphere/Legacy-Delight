package io.thedogofchaos.data_retroportata.common.recipes

import com.google.gson.JsonObject

/**
 * Represents a component that is serializable.
 * What else did you expect?
 */
interface ISerializableComponent<T : IComponent> {
    fun fromJson(jsonObject: JsonObject): T
    fun toJson(): JsonObject
}
