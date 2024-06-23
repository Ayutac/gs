package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;

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

    public Gatcha(@NotNull final String name,
                  @NotNull final Collection<StuffStack> offerings,
                  @NotNull final Collection<Distribution> distributions) {
        if (name.startsWith(PREDICATE)) {
            this.name = name;
        }
        else {
            this.name = PREDICATE + name;
        }
        this.offerings = Set.copyOf(StuffStack.simplify(offerings));
        this.distributions = List.copyOf(distributions);
    }

    @Override
    public @NotNull Set<StuffStack> getOffering() {
        return offerings
    }

    @Override
    public @NotNull Collection<Distribution> getDistributions() {
        return distributions
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
    public static Optional<GatchaLike> lookup(@NotNull final String name) {
        final GatchaLike result = REGISTRY.get(Objects.requireNonNull(name));
        if (result == null) {
            return  Optional.empty();
        }
        return Optional.of(result);
    }
}
