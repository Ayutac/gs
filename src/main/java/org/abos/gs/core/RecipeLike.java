package org.abos.gs.core;

import org.abos.common.Named;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

/**
 * A recipe to transform {@link Stuff} into other Stuff. Implementations of this interface should be immutable.
 */
public interface RecipeLike extends Named {

    /**
     * Returns the input for this recipe.
     * @return an immutable, simplified view of the inputs, not {@code null}
     */
    @NotNull Set<StuffStack> getInput();

    /**
     * Returns the output for this recipe.
     * @return an immutable, simplified view of the outputs, not {@code null}
     */
    // TODO don't allow tags in here
    @NotNull Set<StuffStack> getOutput();

    /**
     * Returns the catalysts for this recipe.
     * @return an immutable, simplified view of the catalysts, might be empty but not {@code null}
     */
    @NotNull Set<StuffStack> getCatalysts();

    /**
     * Returns the duration for this recipe.
     * @return the duration in seconds, non-negative
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int getDuration();

    /**
     * Returns a collection of item stacks (not tags) that match the input of the recipe.
     * If any tag or item couldn't be matched (including the catalysts), returns nothing.
     * @return {@link Optional#empty()} if the recipe couldn't be matched against the inventory,
     * otherwise the optional contains a collection of item stacks (not tags) that need to be removed from the inventory
     * for the recipe to work.
     */
    // TODO test method for this
    default @NotNull Optional<Collection<StuffStack>> matchWithInventory(@NotNull final Inventory inventory) {
        final Inventory testInv = inventory.clone();
        // start with the catalysts
        for (final StuffStack stack : getCatalysts()) {
            // match concrete item
            if (stack.stuff() instanceof ItemLike) {
                if (!testInv.subtract(stack)) {
                    return Optional.empty();
                }
            }
            // match tag
            else {
                // we need to match each entry by itself
                for (int i = 0; i < stack.count(); i++) {
                    // we have no choice but to check the complete inventory for a match each time
                    final Optional<ItemLike> item = testInv.matchTag((TagLike)stack.stuff());
                    if (item.isEmpty()) {
                        return Optional.empty();
                    }
                    if (!testInv.subtract(new StuffStack(item.get()))) {
                        // impossible to reach, matchTag only finds stuff that is there at least once
                        throw new AssertionError("Item was *just* there!");
                    }
                }
            }
        } // -> for (each catalyst)
        // continue with the input
        Collection<StuffStack> result = new LinkedList<>();
        for (final StuffStack stack : getInput()) {
            // match concrete item
            if (stack.stuff() instanceof ItemLike) {
                if (!testInv.subtract(stack)) {
                    return Optional.empty();
                }
                else {
                    result.add(stack);
                }
            }
            // match tag
            else {
                // we need to match each entry by itself
                for (int i = 0; i < stack.count(); i++) {
                    // we have no choice but to check the complete inventory for a match each time
                    final Optional<ItemLike> item = testInv.matchTag((TagLike)stack.stuff());
                    if (item.isEmpty()) {
                        return Optional.empty();
                    }
                    if (!testInv.subtract(new StuffStack(item.get()))) {
                        // impossible to reach, matchTag only finds stuff that is there at least once
                        throw new AssertionError("Item was *just* there!");
                    }
                    else {
                        result.add(new StuffStack(item.get()));
                    }
                }
            }
        } // -> for (each input)
        return Optional.of(result);
    }

}
