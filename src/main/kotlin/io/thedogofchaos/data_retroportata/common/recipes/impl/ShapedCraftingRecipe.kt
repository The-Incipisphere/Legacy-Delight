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
    val legend: ImmutableMap<Char, IInputComponent>,
    val pattern: ImmutableList<String>,
    val result: ItemStackComponent,
) : IRecipe<ContainerWorkbench> {
    override val type = SaneResLoc(MOD_ID,"crafting_shaped")

    init {
        // Validate the legend...
        require(legend.size in 1..9) { "ShapedCraftingRecipe legends must be between 1 and 9 entries long, got '${legend.size}' entries." }
        legend.entries.forEach { (symbol, component) ->
            require(!symbol.isWhitespace()) {"ShapedCraftingRecipe legend symbols MUST be non-whitespace, got $symbol"}
            // doing a compile-time check for this would be more than painful, so.. runtime is the best i'm gonna get.
            require(component is ItemStackComponent || component is OreDictComponent) {"ShapedCraftingRecipe legend components MUST be an ItemStackComponent or OreDictComponent, got $component"}
        }
        // ...and then validate that the pattern conforms to the legend.
        require(pattern.size in 1..3) {"ShapedCraftingRecipe pattern must be between 1 and 3 rows long, got '${pattern.size}' rows."}
        pattern.forEachIndexed { rowIndex, row ->
            require(row.length in 1..3) { "Row $rowIndex of pattern must have 1–3 columns, got '${row.length}' columns" }
            val unknownChars: String = row.filter { it !in legend.keys && it != ' '}
            require(unknownChars.isEmpty()) { "Pattern row $rowIndex contains unknown symbols: $unknownChars" }
        }
    }

    fun symbolAt(row: Int, col: Int): Char = pattern[row][col]

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
