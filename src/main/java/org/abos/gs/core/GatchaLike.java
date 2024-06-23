package org.abos.gs.core;

import org.abos.common.DescribableTranslatable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Describes a form of a gatcha machine: certain items go in, randomized items plop out.
 */
public interface GatchaLike extends DescribableTranslatable {

    /**
     * Returns what is needed to offer to use this gatcha.
     * @return an immutable view of the offering, might be empty but not {@code null}
     */
    @NotNull Collection<StuffStack> getOffering();

    /**
     * Returns the gatcha {@link Distribution Distributions}.
     * @return an immutable view of the distributions, not {@code null}
     */
    @NotNull Collection<Distribution> getDistributions();

    /**
     * Rolls this gatcha with the given RNG.
     * @return a mutable, simplified collection of roll results
     */
    default @NotNull Collection<StuffStack> roll(@NotNull final Random random) {
        final List<StuffStack> rollResults = new LinkedList<>();
        for (final Distribution dist : getDistributions()) {
            for (int roll = 0; roll < dist.getRolls(); roll++) {
                rollResults.add(dist.drawResult(random.nextInt(dist.getWeightSum())));
            }
        }
        return StuffStack.simplify(rollResults);
    }

}
