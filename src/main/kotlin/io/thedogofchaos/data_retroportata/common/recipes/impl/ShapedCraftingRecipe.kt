package io.thedogofchaos.data_retroportata.common.recipes.impl

import com.google.gson.JsonObject
import io.thedogofchaos.data_retroportata.MOD_ID
import io.thedogofchaos.data_retroportata.common.recipes.IInputComponent
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.ISerializableRecipe
import io.thedogofchaos.data_retroportata.Option
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.world.World

/**
 * Describes a shaped recipe for a **VANILLA (3x3)** crafting table.
 *
 * (NOTE: I don't know if I want to add in functionality to allow for
 * wider/taller crafting tables, like those from Avaritia or Extended Crafting.)
 */
data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
    val ingredients: ImmutableList<IInputComponent>,
    val result: ItemStackComponent,
) : IRecipe<ContainerWorkbench> {
    override val type = SaneResLoc(MOD_ID,"crafting_shaped")

    init {
        // Validate the ingredients.
        //require(ingredients.size)
        require(ingredients.size in 1..9) { "Recipes must have 1 to 9 ingredients, got '${ingredients.size}' ingredients." }
        ingredients.all { assertValidInput(it) }
    }

    override fun matches(
        container: ContainerWorkbench,
        world: World
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun validate(): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        fun assertValidInput(component: IInputComponent): Boolean {
            // Doing a compile-time check for this would be more than painful, so... Runtime is the best I'm gonna get.
            check((component is ItemStackComponent || component is OreDictComponent)) {
                "ShapedCraftingRecipe ingredients MUST be either an ItemStackComponent or an OreDictComponent, got $component."
            }
            return true
        }
    }

    // TODO: Consider whether this class should be moved elsewhere, for clarity.
    class Builder(result: ItemStackComponent, count: Int) {
        private var legend: MutableMap<Char, IInputComponent> = mutableMapOf()
        private var pattern: MutableList<String> = mutableListOf()

        init {
            val maxCount = result.itemStack.maxStackSize
            require(count in 1..maxCount) { "Stack count must be between 1 and {maxStackSize}, got $count" }
        }

        fun define(symbol: Char, comp: IInputComponent): Builder {
            check(!this.legend.containsKey(symbol)) {
                "Symbol $symbol is already defined!"
            }
            check(symbol == ' ') { "Symbol ' ' (U+0020: SPACE) is reserved for empty slots, and thus cannot be defined."}
            this.legend[symbol] = comp
            return this
        }

        fun pattern(proposedRow: String): Builder {
            check(!this.pattern.isEmpty() && proposedRow.length == this.pattern[0].length) {"Pattern must be the same width on every line!"}
            this.pattern.add(proposedRow)
            return this
        }

        companion object {
            fun shaped(result: ItemStackComponent, count: Int = 1): Builder {
                return Builder(result, count)
            }
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

        fun dissolvePattern(pattern: List<String>, legend: Map<Char, IInputComponent>) : ImmutableList<Option<IInputComponent>> {
            val inputList = mutableListOf<Option<IInputComponent>>()
            val usedSymbols = mutableSetOf<Char>()

            for (rowStr in pattern) {
                for (colChar in rowStr) {
                    if (colChar == ' ') {
                        inputList.add(Option.none())
                    } else {
                        legend[colChar]?.let {
                            inputList.add(Option.some(it))
                            usedSymbols.add(colChar)
                        } ?: error("Pattern references symbol '$colChar', but that symbol is not defined in the legend.")
                    }
                }
            }
            val unusedSymbols = (legend.keys - usedSymbols)
            check(unusedSymbols.isEmpty()) {"Legend defines symbols that aren't used anywhere in the pattern: $unusedSymbols "}

            return inputList.toImmutableList()
        }
    }
}


