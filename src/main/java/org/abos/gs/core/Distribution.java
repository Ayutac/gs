package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;
import java.util.Objects;

/**
 * A distribution for gatchas. This class is immutable.
 */
public final class Distribution {

    private final List<DistributionEntry> entries;
    private final int rolls;
    private final int weightSum;
    private final int[] culWeights;

    /**
     * Creates a new {@link Distribution}.
     * @param entries entries of this distribution, not {@code null}, not containing {@code null} and not empty
     * @param rolls how often this distribution is rolled for, positive
     * @throws ArithmeticException If an integer overflow occurs due to the weights of the entries being greater than {@link Integer#MAX_VALUE}.
     */
    public Distribution(@NotNull final List<DistributionEntry> entries, @Range(from = 1, to = Integer.MAX_VALUE) final int rolls) {
        if (rolls <= 0) {
            throw new IllegalArgumentException("Rolls must be positive!");
        }
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("At least one entry must be present!");
        }
        this.entries = List.copyOf(entries);
        this.rolls = rolls;
        // calculate the weight sum
        int sum = 0;
        for (final DistributionEntry de : this.entries) {
            sum = Math.addExact(sum, de.weight());
        }
        weightSum = sum;
        culWeights = new int[this.entries.size()+1];
        // culWeights[0] = 0;
        for (int i = 1; i <= this.entries.size(); i++) {
            culWeights[i] = culWeights[i-1] + this.entries.get(i-1).weight();
        }
    }

    public @NotNull StuffStack drawResult(final int randomInt) {
        int index = 0;
        if (randomInt >= weightSum) {
            index = entries.size()-1;
        }
        else if (randomInt >= 0) {
            int i = 1;
            do {
                if (culWeights[i-1] <= randomInt && randomInt < culWeights[i]) {
                    index = i-1;
                    break;
                }
                i++;
            } while (i <= culWeights.length);
        }
        return entries.get(index).stack();
    }

    /**
     * An immutable view of the entries of this distribution.
     * @return the entry list, not {@code null} or containing {@code null} and also not empty.
     */
    public @NotNull List<DistributionEntry> getEntries() {
        // immutable because see constructor
        return entries;
    }

    /**
     * Returns the number of rolls for this distribution.
     * @return the rolls, positive
     */
    public int getRolls() {
        return rolls;
    }

    /**
     * Returns the sum of the weights of this distribution.
     * @return the sum of the weights, positive
     */
    public int getWeightSum() {
        return weightSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distribution that)) return false;
        return rolls == that.rolls && Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, rolls);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(Distribution.class.getSimpleName());
        sb.append("{entries=").append(entries);
        sb.append(", rolls=").append(rolls);
        sb.append(", weightSum=").append(weightSum);
        sb.append('}');
        return sb.toString();
    }
}
