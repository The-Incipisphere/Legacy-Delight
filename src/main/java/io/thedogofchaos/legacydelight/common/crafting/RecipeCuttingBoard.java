package io.thedogofchaos.legacydelight.common.crafting;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;

//

/*
 * Hoo boy, time for pain.
 * this is the wild west of 1.7.10 and EVERYONE has their own implementations THERE IS NO STANDARD
 */
public class RecipeCuttingBoard {
    private final ResourceLocation id;
    private final IIngredient input;
    private final IIngredient tool;
    private final Collection<IResult> results;
    private final String soundName;

    /**
     * Construct a new Cutting Board recipe
     * (with custom sound)
     * @param id A unique identifier for the recipe.
     * @param input The item to be processed in the recipe.
     * @param tool The tool required to perform the recipe.
     * @param results All the results of the recipe.
     * @param soundName The name of the sound to play when the recipe is performed.
     */
    public RecipeCuttingBoard(ResourceLocation id, IIngredient input, IIngredient tool, Collection<IResult> results, String soundName) {
        this.id = id;
        this.input = input;
        this.tool = tool;
        this.results = results;
        this.soundName = soundName;
    }

    /**
     * Construct a new Cutting Board recipe
     * (with default sound)
     * @param id A unique identifier for the recipe.
     * @param input The item to be processed in the recipe.
     * @param tool The tool required to perform the recipe.
     * @param results All the results of the recipe.
     */
    public RecipeCuttingBoard(ResourceLocation id, IIngredient input, IIngredient tool, Collection<IResult> results) {
        this.id = id;
        this.input = input;
        this.tool = tool;
        this.results = results;
        this.soundName = "";
    }
}
