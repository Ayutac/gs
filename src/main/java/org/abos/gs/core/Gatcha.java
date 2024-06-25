package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class Gatcha implements GatchaLike {

    /**
     * The predicate for {@link GatchaLike} names.
     */
    public static final String PREDICATE = "gatcha.";

    /**
     * The gatcha registry.
     */
    private static final Map<String, GatchaLike> REGISTRY = new HashMap<>();

    private final String name;
    private final Set<StuffStack> offerings;
    private final Collection<Distribution> distributions;

    /**
     * Creates a new {@link Gatcha} instance.
     * @param name the translatable name of this gatcha
     * @param offerings what must be offered to pull from this gatcha, may be empty, but not {@code null} or contain {@code null}
     * @param distributions the distributions that are pulled from, may not be empty, {@code null} or contain {@code null}
     */
    public Gatcha(@NotNull final String name,
                  @NotNull final Collection<StuffStack> offerings,
                  @NotNull final Collection<Distribution> distributions) {
        if (distributions.isEmpty()) {
            throw new IllegalArgumentException("At least one distribution must be specified!");
        }
        if (name.startsWith(PREDICATE)) {
            this.name = name;
        }
        else {
            this.name = PREDICATE + name;
        }
        this.offerings = Set.copyOf(StuffStack.simplify(offerings));
        this.distributions = List.copyOf(distributions);
    }

    /**
     * @see #Gatcha(String, Collection, Collection)
     */
    public Gatcha(@NotNull final String name,
                  @NotNull final StuffStack offerings,
                  @NotNull final Collection<Distribution> distributions) {
        this(name, List.of(offerings), distributions);
    }

    @Override
    public @NotNull Set<StuffStack> getOfferings() {
        return offerings;
    }

    @Override
    public @NotNull Collection<Distribution> getDistributions() {
        return distributions;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     * Registers a {@link GatchaLike}.
     * @param gatcha the gatcha to register, not {@code null}
     */
    public static void register(@NotNull final GatchaLike gatcha) throws IllegalStateException {
        if (REGISTRY.containsKey(gatcha.getName())) {
            throw new IllegalStateException(gatcha + " is already registered!");
        }
        REGISTRY.put(gatcha.getName(), gatcha);
    }

    /**
     * Looks up a {@link GatchaLike} from its name.
     * @param name the name to look for, not {@code null}
     * @return an {@link Optional} containing the gatcha if it had been registered before, else empty
     */
    public static @NotNull Optional<GatchaLike> lookup(@NotNull final String name) {
        final GatchaLike result = REGISTRY.get(Objects.requireNonNull(name));
        if (result == null) {
            return  Optional.empty();
        }
        return Optional.of(result);
    }

    /**
     * Returns all available gatchas.
     * @return a mutable, unsorted list of gatchas not backed by the registry, not {@code null} or containing {@code null}
     */
    public static @NotNull List<GatchaLike> getAll() {
        return new ArrayList<>(REGISTRY.values());
    }
}
