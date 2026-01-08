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
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

/**
 * Describes a shaped recipe for a vanilla crafting table.
 */
data class ShapedCraftingRecipe(
    override val id: SaneResLoc,
    val key: ImmutableMap<String, IInputComponent>,
    val pattern: ImmutableList<String>,
    val result: ItemStackComponent,
) : IRecipe<ContainerWorkbench> {
    override val type = SaneResLoc(MOD_ID,"crafting_shaped")

    init {
        // doing a compile-time check for this would be more than painful
        require(key.values.all { it is ItemStackComponent || it is OreDictComponent }) { "(TODO: WRITE COOL SHIT) "}
        require(key.size <= 9) { "Shaped recipe key cannot have more than 9 entries." }
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
