package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.MOD_ID
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.ISerializableRecipe
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Describes a shaped recipe for a vanilla crafting table.
 */
data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
    val legend: ImmutableMap<String, IInputComponent>,
    val pattern: ImmutableList<String>,
    val result: ItemStackComponent,
) : IRecipe<ContainerWorkbench> {
    override val type = SaneResLoc(MOD_ID,"crafting_shaped")

    init {
        // Validate the legend...
        require(legend.isNotEmpty()) {"ShapedCraftingRecipe legends must not be empty."}
        require(legend.size <= 9) { "ShapedCraftingRecipe legends cannot be more than 9 entries long, got ${legend.size} entries." }
        legend.entries.forEach { (symbol, component) ->
            require(symbol.isNotBlank() && symbol.length == 1) {"ShapedCraftingRecipe legend symbols MUST be exactly 1 non-whitespace character long, got $symbol"}
            // doing a compile-time check for this would be more than painful, so.. runtime is the best i'm gonna get.
            require(component is ItemStackComponent || component is OreDictComponent) {"ShapedCraftingRecipe legend components MUST be an ItemStackComponent or OreDictComponent, got $component"}
        }
    }

    override fun matches(
        container: ContainerWorkbench,
        world: World
    ): Boolean {
        TODO("Not yet implemented")
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
