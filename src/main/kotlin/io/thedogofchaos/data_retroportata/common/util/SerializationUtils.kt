package io.thedogofchaos.data_retroportata.common.util

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack

object SerializationUtils {
    /**
     * @throws JsonParseException If the JSON is invalid (see individual messages for failure conditions)
     */
    // TODO: Evaluate on whether or not this is the best way to go about this shit
    fun parseItemStack(jsonObject: JsonObject): Pair<ItemStack, Boolean> {
        val item = run { // i put this in a run block for better clarity of what this shit does.
            val itemElement: JsonElement = jsonObject.get("item") ?: throw JsonParseException(
                "Item is REQUIRED (missing \"item\" element)"
            )
            if (itemElement.isJsonNull) throw JsonParseException(
                "Item MUST NOT be explicitly set to null (i.e. don't do \"item\": null)"
            )
            if (!itemElement.isJsonPrimitive || !itemElement.asJsonPrimitive.isString) throw JsonParseException("Item MUST be a string, got: $itemElement")
            SaneResLoc(itemElement.asString).toPair()
        }
        val count: Int = jsonObject.get("count")?.takeIf { !it.isJsonNull }?.asInt ?: 1
        val meta: Int = jsonObject.get("meta")?.takeIf { !it.isJsonNull }?.asInt ?: 0
        val metaWildcard = (meta == -1)

        return ItemStack(
            GameRegistry.findItem(item.first, item.second) ?: throw JsonParseException("Item not found in registry: ${item.first}:${item.second}"),
            count,
            meta
        ) to metaWildcard
    }
}
