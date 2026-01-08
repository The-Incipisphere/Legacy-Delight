package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.common.recipes.IComponent
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.ISerializableRecipe
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

/**
 * Describes a shaped recipe for a vanilla crafting table.
 */
data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
    // FOOTGUN ALERT: If I were to make any Results implement IComponent,
    // 'key' would be able to have results as *inputs*...
    // which is a nightmare to figure out what to do with.
    val key: ImmutableMap<String, IInputComponent>,
    val pattern : ImmutableList<String>,
) : IRecipe {
    override val type = SaneResLoc("data_retroportata","crafting_shaped")


    init {
        require(key.size <= 9) { "Shaped recipe key cannot have more than 9 entries." }
    }

    companion object Serializer : ISerializableRecipe<ShapedCraftingRecipe> {
        override fun fromJson(
            id: SaneResLoc,
            json: JsonObject
        ): ShapedCraftingRecipe {
            TODO("Focusing on other parts of the mod currently.")
        }
    }
}
