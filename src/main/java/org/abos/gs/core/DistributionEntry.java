package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

/**
 * An entry for a {@link Distribution}.
 * @param weight the weight of the entry
 */
public record DistributionEntry(StuffStack stack, Integer weight) {

    /**
     * @see DistributionEntry
     */
    public DistributionEntry(@NotNull final StuffStack stack, @NotNull @Range(from = 1, to = Integer.MAX_VALUE) final Integer weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive!");
        }
        this.stack = Objects.requireNonNull(stack);
        this.weight = weight;
    }

}
