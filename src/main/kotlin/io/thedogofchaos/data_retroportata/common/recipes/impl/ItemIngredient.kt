package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import cpw.mods.fml.common.registry.GameRegistry
import io.thedogofchaos.data_retroportata.common.recipes.IIngredient
import io.thedogofchaos.data_retroportata.common.recipes.IIngredientSerializer
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import io.thedogofchaos.data_retroportata.common.util.SerializationUtils
import net.minecraft.item.ItemStack

/**
 * Describes a basic ingredient item.
 */
// TODO: Convert this bullshit into a holder.
data class ItemIngredient(val itemStack: ItemStack, val metaWildcard: Boolean) : IIngredient {
    override val type: SaneResLoc = SaneResLoc("data_retroportata","item")

    companion object Serializer: IIngredientSerializer {
        override fun fromJson(jsonObject: JsonObject): ItemIngredient {
            val (itemStack, metaWildcard) = SerializationUtils.parseItemStack(jsonObject)

            return ItemIngredient(
                itemStack,
                // FIXME: always false, since net.minecraft.item.ItemStack#ItemStack(net.minecraft.item.Item, int, int)
                //  always sets itemDamage to 0 if it's less than 0
                metaWildcard
            )
        }
    }
}
