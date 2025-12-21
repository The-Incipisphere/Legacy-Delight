package io.thedogofchaos.legacydelight.common.crafting;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/*
 * Hoo boy, time for pain.
 * this is the wild west of 1.7.10 and EVERYONE has their own implementations
 * THERE IS NO STANDARD
 */
public class RecipeCuttingBoard {
    private final ResourceLocation id;
    private final IIngredient input;
    private final IIngredient tool;
    private final Collection<IResult> results;
    @Nullable private final String soundName;

    /**
     * Construct a new Cutting Board recipe
     * (with a custom completion sound)
     *
     * @param id A unique identifier for the recipe.
     * @param input The item to be processed in the recipe.
     * @param tool The tool required to perform the recipe.
     * @param results All the results of the recipe.
     * @param soundName The name of the sound to play when the recipe is performed.
     *
     * @throws NullPointerException If any params except {@code soundName} are null.
     */
    public RecipeCuttingBoard(ResourceLocation id, IIngredient input, IIngredient tool, Collection<IResult> results, String soundName) {
        this.id = Objects.requireNonNull(id, "Parameter 'id' must not be null. (this is literally the thing that identifies your recipe, why tf are you setting it to null)");
        this.input = Objects.requireNonNull(input, "Parameter 'input' must not be null. (ah yes, let me just hit my bare chopping board with my knife and produce chopped onions out of thin air)");
        this.tool = Objects.requireNonNull(tool, "Parameter 'tool' must not be null. (what are you gonna hit those apples with? your fists?)");
        this.results = Objects.requireNonNull(results, "Parameter 'results' must not be null. (mfw my loaf of bread suddenly ceases to exist the moment i cut it)");
        this.soundName = soundName;
    }

    /**
     * Construct a new Cutting Board recipe
     * (with the default completion sound)
     *
     * @param id A unique identifier for the recipe.
     * @param input The item to be processed in the recipe.
     * @param tool The tool required to perform the recipe.
     * @param results All the results of the recipe.
     *
     * @throws NullPointerException If any params are null.
     */
    public RecipeCuttingBoard(ResourceLocation id, IIngredient input, IIngredient tool, Collection<IResult> results) {
        this(id, input, tool, results, null);
    }

    public ResourceLocation getId() {
        return id;
    }

    public IIngredient getInput() {
        return input;
    }

    public IIngredient getTool() {
        return tool;
    }

    public Collection<IResult> getResults() {
        return results;
    }

    /**
     * @return {@link Optional#empty()} if {@link RecipeCuttingBoard#soundName} is null.
     * Otherwise, returns an
     */
    public Optional<String> getSoundName() {
        return Optional.ofNullable(soundName);
    }
}
