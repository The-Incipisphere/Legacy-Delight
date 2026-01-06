package io.thedogofchaos.legacydelight.common.recipes

import com.google.gson.JsonObject
import io.thedogofchaos.legacydelight.LegacyDelight.resLoc
import io.thedogofchaos.data_retroportata.common.recipes.IIngredient
import io.thedogofchaos.data_retroportata.common.recipes.IRecipe
import io.thedogofchaos.data_retroportata.common.recipes.IResult
import io.thedogofchaos.data_retroportata.common.recipes.RecipeSerializer
import io.thedogofchaos.data_retroportata.common.util.SaneResLoc
import io.thedogofchaos.legacydelight.LegacyDelight
import kotlinx.collections.immutable.ImmutableCollection
import net.minecraft.util.ResourceLocation
import java.util.*

/**
 * Represents a recipe for the cutting board.
 * (Can have a custom completion sound!)
 *
 * @param id A unique identifier for the recipe.
 * @param input The item to be processed in the recipe.
 * @param tool The tool required to perform the recipe.
 * @param results All the results of the recipe.
 * @param soundName The name of the sound to play when the recipe is performed.
 *
 * @throws NullPointerException If any params except `soundName` are null.
 */
data class RecipeCuttingBoard(
    override val id: SaneResLoc,
    val input: IIngredient,
    val tool: IIngredient,
    val results: ImmutableCollection<IResult>,
    private val soundName: String? = null
) : IRecipe {
    override val type = SaneResLoc(LegacyDelight.MODID,"cutting_board")

    init {
        // Yes Kotlin, I know what the fuck I'm doing.
        // These exist purely for Java Interop.
        @Suppress("SENSELESS_COMPARISON")
        when {
            id == null -> throw IllegalArgumentException("Parameter 'id' must not be null. (this is literally the thing that identifies your recipe, why tf are you setting it to null)")
            input == null -> throw IllegalArgumentException("Parameter 'input' must not be null. (ah yes, let me just hit my bare chopping board with my knife and produce chopped onions out of thin air)")
            tool == null -> throw IllegalArgumentException("Parameter 'tool' must not be null. (what are you gonna hit those apples with? your fists?)")
            results == null -> throw IllegalArgumentException("Parameter 'results' must not be null. (mfw my loaf of bread suddenly ceases to exist the moment i cut it)")
        }
    }

    /**
     * @return [Optional.empty] if [RecipeCuttingBoard.soundName] is null.
     * Otherwise, returns an [Optional] containing the string of the sound to be played when a recipe is successfully completed.
     */
    fun getSoundName(): Optional<String> {
        return Optional.ofNullable<String>(soundName)
    }
//    companion object {
//        fun fromJson(id: ResourceLocation, Resourcejson: JsonObject): IRecipe {
//            val input = IIngredient.fromJson(json.getAsJsonObject("input"))
//            val tool = IIngredient.fromJson(json.getAsJsonObject("tool"))
//            val results = json.getAsJsonArray("results")
//                .map<IResult> { IResult.fromJson(it.asJsonObject) }
//            val soundName = json.getAsJsonObject("sound_name")?.get("name")?.asString
//            RecipeCuttingBoard(id, input, tool, results, soundName)
//        }
//    }
    companion object Serializer : RecipeSerializer<RecipeCuttingBoard> {
        override fun fromJson(id: ResourceLocation, json: JsonObject): RecipeCuttingBoard {
            TODO("Need to figure out how the fuck i avoid pigeonholing data_retroportata:data/item_stack and data_retroportata:data/ore_dict")
        }
    }
}
