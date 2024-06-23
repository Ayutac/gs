package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the {@link RecipeLike} interface. This class is immutable.
 */
public final class Recipe implements RecipeLike {

    /**
     * The predicate for {@link TagLike} names.
     */
    public static final String PREDICATE = "recipe.";

    /**
     * The recipe registry.
     */
    private static final Map<String, RecipeLike> REGISTRY = new HashMap<>();

    private final String name;
    private final Set<StuffStack> input;
    private final Set<StuffStack> output;
    private final Set<StuffStack> catalysts;

    /**
     * Creates a new {@link Recipe}.
     * @param name name of the recipe
     * @param input input for the recipe
     * @param output output for the recipe
     * @param catalysts necessary catalysts for the recipe
     */
    public Recipe(@NotNull final String name,
                  @NotNull final Collection<StuffStack> input,
                  @NotNull final Collection<StuffStack> output,
                  @NotNull final Collection<StuffStack> catalysts) {
        if (name.startsWith(PREDICATE)) {
            this.name = name;
        }
        else {
            this.name = PREDICATE + name;
        }
        this.input = Set.copyOf(StuffStack.simplify(input));
        this.output = Set.copyOf(StuffStack.simplify(output));
        this.catalysts = Set.copyOf(StuffStack.simplify(catalysts));
    }

    @Override
    public @NotNull Set<StuffStack> getInput() {
        return input;
    }

    @Override
    public @NotNull Set<StuffStack> getOutput() {
        return output;
    }

    @Override
    public @NotNull Set<StuffStack> getCatalysts() {
        return catalysts;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     * Registers a {@link RecipeLike}.
     * @param recipe the recipe to register, not {@code null}
     */
    public static void register(@NotNull final RecipeLike recipe) throws IllegalStateException {
        if (REGISTRY.containsKey(recipe.getName())) {
            throw new IllegalStateException(recipe + " is already registered!");
        }
        REGISTRY.put(recipe.getName(), recipe);
    }

    /**
     * Looks up a {@link RecipeLike} from its name.
     * @param name the name to look for, not {@code null}
     * @return an {@link Optional} containing the recipe if it had been registered before, else empty
     */
    public static Optional<RecipeLike> lookup(@NotNull final String name) {
        final RecipeLike result = REGISTRY.get(Objects.requireNonNull(name));
        if (result == null) {
            return  Optional.empty();
        }
        return Optional.of(result);
    }
}
