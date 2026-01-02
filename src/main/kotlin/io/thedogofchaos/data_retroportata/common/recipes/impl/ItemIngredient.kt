package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import cpw.mods.fml.common.registry.GameRegistry
import io.thedogofchaos.data_retroportata.common.recipes.IIngredient
import io.thedogofchaos.data_retroportata.common.recipes.IIngredientSerializer
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.item.ItemStack

/**
 * Describes a basic ingredient item.
 */
data class ItemIngredient(val itemStack: ItemStack, val matchAllMeta: Boolean) : IIngredient {
    override val type: SaneResLoc = SaneResLoc("data_retroportata","item")

    companion object Serializer: IIngredientSerializer {
        override fun fromJson(jsonObject: JsonObject): ItemIngredient {
            val item = run { // i put this in a run block for better clarity of what this shit does.
                val itemElement: JsonElement = jsonObject.get("item") ?: throw JsonParseException(
                    "Item is REQUIRED (missing \"item\" element)"
                )
                if (itemElement.isJsonNull) throw JsonParseException(
                    "Item MUST NOT be explicitly set to null (i.e. \"item\": null)"
                )
                if (!itemElement.isJsonPrimitive || !itemElement.asJsonPrimitive.isString) throw JsonParseException("Item MUST be a string, got: $itemElement")
                SaneResLoc(itemElement.asString).toPair()
            }
            val count: Int = jsonObject.get("count")?.takeIf { !it.isJsonNull }?.asInt ?: 1
            val meta: Int = jsonObject.get("meta")?.takeIf { !it.isJsonNull }?.asInt ?: 0

            return ItemIngredient(
                ItemStack(
                    GameRegistry.findItem(item.first, item.second),
                    count,
                    meta
                ),
                (meta == -1),
            )
        }
    }
}
