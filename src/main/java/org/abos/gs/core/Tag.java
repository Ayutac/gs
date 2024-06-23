package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the {@link TagLike} interface via a set. This class is immutable.
 */
public final class Tag implements TagLike {

    /**
     * The tag registry.
     */
    private static final Map<String, TagLike> REGISTRY = new HashMap<>();

    private final String name;
    private final Set<ItemLike> items;

    /**
     * Creates a new {@link Tag}
     * @param name the name of the tag
     * @param items which items belong to the tag
     */
    public Tag(@NotNull final String name, @NotNull final Set<ItemLike> items) {
        this.name = Objects.requireNonNull(name);
        this.items = Set.copyOf(items);
    }

    @Override
    public boolean test(ItemLike itemLike) {
        return items.contains(itemLike);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(name, tag.name) && Objects.equals(items, tag.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }

    @Override
    public @NotNull String toString() {
        final StringBuilder sb = new StringBuilder(Tag.class.getSimpleName());
        sb.append("{name='").append(name).append('\'');
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Registers a {@link TagLike}.
     * @param tag the tag to register, not {@code null}
     */
    public static void register(@NotNull final TagLike tag) throws IllegalStateException {
        if (REGISTRY.containsKey(tag.getName())) {
            throw new IllegalStateException(tag + " is already registered!");
        }
        REGISTRY.put(tag.getName(), tag);
    }

    /**
     * Looks up a {@link TagLike} from its name.
     * @param name the name to look for, not {@code null}
     * @return an {@link Optional} containing the tag if it had been registered before, else empty
     */
    public static Optional<TagLike> lookup(@NotNull final String name) {
        final TagLike result = REGISTRY.get(Objects.requireNonNull(name));
        if (result == null) {
            return  Optional.empty();
        }
        return Optional.of(result);
    }
}
