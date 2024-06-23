package org.abos.gs.core;

import org.abos.common.Translatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * {@link Stuff} associated with a non-negative number.
 */
public record StuffStack(Stuff stuff, Integer count) implements Translatable {

    /**
     * @see StuffStack
     */
    public StuffStack(@NotNull final Stuff stuff, @NotNull @Range(from = 0, to = Integer.MAX_VALUE) final Integer count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative!");
        }
        this.stuff = Objects.requireNonNull(stuff);
        this.count = count;
    }

    @Override
    public @NotNull String getName() {
        return String.format("%s (%d)", stuff.getName(), count);
    }

    @Override
    public @NotNull String getTranslation(@NotNull ResourceBundle rb) {
        return String.format("%s (%d)", rb.getString(stuff.getName()), count);
    }

    @Override
    public @NotNull String toString() {
        return getName();
    }

    /**
     * Counts the appearance of the given stuff in the stack collection.
     * @param name the name of the stuff to count; tags will only match themselves
     * @param stacks a collection of {@link StuffStack}, not {@code null} and not containing {@code null} but can be empty
     * @throws ArithmeticException If an integer overflow occurs.
     */
    public static int count(@NotNull final String name, @NotNull final Collection<StuffStack> stacks) {
        Objects.requireNonNull(name);
        int count = 0;
        for (final StuffStack stack : stacks) {
            if (stack.stuff().getName().equals(name)) {
                count = Math.addExact(count, stack.count());
            }
        }
        return count;
    }

    /**
     * Takes the given collection and simplifies it so that each {@link Stuff} only appears at most once in the resulting
     * collection but their {@link #count} stays the same.
     * @return A mutable collection that doesn't guarantee any order on its elements. Not {@code null} and not containing {@code null}.
     * Can be empty even if the supplied collection wasn't because Stuff with total count of 0 gets removed.
     * @throws ArithmeticException If an integer overflow occurs.
     */
    public static @NotNull Collection<StuffStack> simplify(@NotNull final Collection<StuffStack> stacks) {
        final List<StuffStack> result = new ArrayList<>(stacks.size());
        final Set<String> checked = new HashSet<>();
        for (final StuffStack stack : stacks) {
            if (checked.add(stack.stuff().getName())) {
                final int count = count(stack.stuff().getName(), stacks);
                if (count > 0) {
                    result.add(new StuffStack(stack.stuff(), count));
                }
            }
        }
        return result;
    }
}
