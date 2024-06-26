package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the {@link ItemLike} interface, immutable.
 */
public final class Item implements ItemLike {

    /**
     * The predicate for {@link ItemLike} names.
     */
    public static final String PREDICATE = "item.";

    /**
     * The item registry.
     */
    private static final Map<String, ItemLike> REGISTRY = new HashMap<>();

    private final String name;
    private final int rarity;

    /**
     * Creates a new {@link Item}.
     * @param name the name of the item; if it doesn't start with {@link #PREDICATE}, that will be prepended
     * @param rarity the rarity of the item, not negative
     */
    public Item(@NotNull final String name, @Range(from = 0, to = Integer.MAX_VALUE) final int rarity) {
        if (rarity < 0) {
            throw new IllegalArgumentException("Rarity cannot be negative!");
        }
        if (name.startsWith(PREDICATE)) {
            this.name = name;
        }
        else {
            this.name = PREDICATE + name;
        }
        this.rarity = rarity;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public int getRarity() {
        return rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return rarity == item.rarity && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rarity);
    }

    @Override
    public @NotNull String toString() {
        final StringBuilder sb = new StringBuilder(Item.class.getSimpleName());
        sb.append("{name='").append(name).append('\'');
        sb.append(", rarity=").append(rarity);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Registers an {@link ItemLike}.
     * @param item the item to register, not {@code null}
     */
    public static void register(@NotNull final ItemLike item) throws IllegalStateException {
        if (REGISTRY.containsKey(item.getName())) {
            throw new IllegalStateException(item + " is already registered!");
        }
        REGISTRY.put(item.getName(), item);
    }

    /**
     * Looks up an {@link ItemLike} from its name.
     * @param name the name to look for, not {@code null}
     * @return an {@link Optional} containing the item if it had been registered before, else empty
     */
    public static Optional<ItemLike> lookup(@NotNull final String name) {
        final ItemLike result = REGISTRY.get(Objects.requireNonNull(name));
        if (result == null) {
            return  Optional.empty();
        }
        return Optional.of(result);
    }
}
