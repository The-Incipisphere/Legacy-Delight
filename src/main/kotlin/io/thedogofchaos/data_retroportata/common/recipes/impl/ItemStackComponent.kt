package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import cpw.mods.fml.common.registry.GameRegistry
import io.thedogofchaos.data_retroportata.MOD_ID
import io.thedogofchaos.data_retroportata.common.recipes.IComponent
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.recipes.IOutputComponent
import io.thedogofchaos.data_retroportata.common.recipes.ISerializableComponent
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import net.minecraft.item.ItemStack

/**
 * Describes a bog-standard [ItemStack] in a serializable manner, for use with serializable recipes.
 */
data class ItemStackComponent(val itemStack: ItemStack, val metaWildcard: Boolean) : IInputComponent, IOutputComponent {
    override val type: SaneResLoc = SaneResLoc(MOD_ID,"item_stack")

    companion object Serializer: ISerializableComponent<ItemStackComponent> {
        override fun fromJson(jsonObject: JsonObject): ItemStackComponent {
            val item = run { // i put this in a run block for better clarity of what this shit does.
                val itemElement: JsonElement = jsonObject.get("item") ?: throw JsonParseException(
                    "Item is REQUIRED (missing \"item\" element)"
                )
                if (itemElement.isJsonNull) throw JsonParseException(
                    "Item MUST NOT be explicitly set to null (i.e. don't do \"item\": null)"
                )
                if (!itemElement.isJsonPrimitive || !itemElement.asJsonPrimitive.isString) throw JsonParseException(
                    "Item MUST be a string, got: $itemElement"
                )
                SaneResLoc(itemElement.asString).toPair()
            }
            val count: Int = jsonObject.get("count")?.takeIf { !it.isJsonNull }?.asInt ?: 1
            val meta: Int = jsonObject.get("meta")?.takeIf { !it.isJsonNull }?.asInt ?: 0
            val metaWildcard = (meta == -1)

            return ItemStackComponent(
                ItemStack(
                    GameRegistry.findItem(item.first, item.second) ?: throw JsonParseException("Item not found in registry: ${item.first}:${item.second}"),
                    count,
                    meta // don't worry, the ItemStack constructor resets itemDamage/meta to 0 if the value is less than 0
                ),
                metaWildcard
            )
        }
    }
}
