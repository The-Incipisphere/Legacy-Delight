package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.MOD_ID
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.ISerializableRecipe
import io.thedogofchaos.data_retroportata.common.recipes.Slot
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import java.util.Optional

/**
 * Describes a shaped recipe for a vanilla (3x3) crafting table.
 */
data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
    val legend: ImmutableMap<Char, IInputComponent>, // TODO: NUKE THIS BULLSHIT
    val pattern: ImmutableList<String>, // TODO: NUKE THIS BULLSHIT
    val result: ItemStackComponent,
) : IRecipe<ContainerWorkbench> {
    override val type = SaneResLoc(MOD_ID,"crafting_shaped")

    init { // TODO: NUKE THIS BULLSHIT
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

    companion object {
        fun isAcceptableInput(component: IInputComponent): Boolean = (component is ItemStackComponent || component is OreDictComponent)

        fun dissolvePattern(pattern: List<String>, legend: Map<Char, IInputComponent>) : ImmutableList<Slot> {
            val inputList = mutableListOf<Slot>()
            val usedSymbols = mutableSetOf<Char>()

            for (row in pattern) {
                for (col in row) {
                    if (col == ' ') {
                        inputList.add(Slot.Empty)
                    } else {
                        legend[col]?.let {
                            inputList.add(Slot.Filled(it))
                        } ?: error("Pattern references symbol '$col', but that symbol is not defined in the legend.")
                        usedSymbols.add(col)
                    }
                }
            }
            val unusedSymbols = (legend.keys - usedSymbols)
            check(unusedSymbols.isEmpty()) {"Legend defines symbols that aren't used anywhere in the pattern: $unusedSymbols "}

            // TODO: Somehow check if we still have unused symbols in the legend.
            return inputList.toImmutableList()
        }
    }

    object Serializer : ISerializableRecipe<ShapedCraftingRecipe> {
        override fun fromJson(
            id: SaneResLoc,
            json: JsonObject
        ): ShapedCraftingRecipe {
            TODO("Focusing on other parts of the mod currently.")
        }

        override fun toJson(): JsonObject {
            TODO("Focusing on other parts of the mod currently.")
        }
    }
}
