package org.abos.gs.core;

import org.abos.common.Named;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A recipe to transform {@link Stuff} into other Stuff. Implementations of this interface should be immutable.
 */
public interface RecipeLike extends Named {

    /**
     * Returns the input for this recipe.
     * @return an immutable view of the inputs, not {@code null}
     */
    @NotNull Collection<StuffStack> getInput();

    /**
     * Returns the output for this recipe.
     * @return an immutable view of the outputs, not {@code null}
     */
    @NotNull Collection<StuffStack> getOutput();

    /**
     * Returns the catalysts for this recipe.
     * @return an immutable view of the catalysts, might be empty but not {@code null}
     */
    @NotNull Collection<StuffStack> getCatalysts();

}
