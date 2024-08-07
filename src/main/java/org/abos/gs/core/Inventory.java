package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Inventory implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 4982375432L;

    protected final Map<String, Integer> items = new HashMap<>();

    public Inventory() {
        // nothing to do
    }

    /**
     * Returns an unsorted view of this inventory.
     * @return a mutable, unsorted, simplified view not backed by this inventory
     */
    public @NotNull List<StuffStack> viewContent() {
        final List<StuffStack> result = new ArrayList<>(items.size());
        for (final var entry : items.entrySet()) {
            result.add(new StuffStack(Item.lookup(entry.getKey()).get(), entry.getValue()));
        }
        return result;
    }

    /**
     * Returns the amount of the given item in this inventory.
     * @param item the item to check, not {@code null}
     * @return the amount, non-negative
     */
    public @Range(from = 0, to = Integer.MAX_VALUE) int count(final @NotNull ItemLike item) {
        return items.getOrDefault(item.getName(), 0);
    }

    /**
     * Adds the specified stack to this inventory.
     * @param stack the stack to add, not {@code null}
     */
    public void add(@NotNull final StuffStack stack) {
        items.merge(stack.stuff().getName(), stack.count(), Integer::sum);
    }

    /**
     * Adds all specified stacks to this inventory.
     * @param stacks the stacks to add, not {@code null} and not containing {@code null}
     */
    public void addAll(@NotNull final Iterable<StuffStack> stacks) {
        for (final StuffStack stack : stacks) {
            add(stack);
        }
    }

    private Optional<Map<String, Integer>> canSubtract(@NotNull final Iterable<StuffStack> stacks) {
        final Map<String, Integer> diffMap = new HashMap<>();
        // we need the diffMap because in the iterable there could be several stacks of the same stuff
        for (final StuffStack stack : stacks) {
            final Integer oldValue = diffMap.getOrDefault(stack.stuff().getName(), items.get(stack.stuff().getName()));
            if (oldValue == null) {
                return Optional.empty();
            }
            final int newValue = Math.subtractExact(oldValue, stack.count());
            if (newValue < 0) {
                return Optional.empty();
            }
            diffMap.put(stack.stuff().getName(), newValue);
        }
        return Optional.of(diffMap);
    }

    public boolean subtractAll(@NotNull final Iterable<StuffStack> stacks) {
        final var canSubtract = canSubtract(stacks);
        if (canSubtract.isEmpty()) {
            return false;
        }
        final Map<String, Integer> diffMap = canSubtract.get();
        for (final var entry : diffMap.entrySet()) {
            if (entry.getValue() == 0) {
                items.remove(entry.getKey());
            }
            else if (entry.getValue() < 0) {
                // impossible to reach
                throw new AssertionError("Value cannot be negative at this moment.");
            }
            else {
                items.put(entry.getKey(), entry.getValue());
            }
        }
        return true;
    }

    public boolean subtract(@NotNull final StuffStack stack) {
        return subtractAll(List.of(stack));
    }

    public @NotNull Optional<ItemLike> matchTag(@NotNull final TagLike tag) {
        for (final String itemName : items.keySet()) {
            final ItemLike item = Item.lookup(itemName).get();
            if (tag.test(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public boolean matchesRecipe(@NotNull final RecipeLike recipe) {
        return recipe.matchWithInventory(this).isPresent();
    }

    public boolean subtractRecipe(@NotNull final RecipeLike recipe) {
        final var input = recipe.matchWithInventory(this);
        if (input.isEmpty()) {
            return false;
        }
        if (!subtractAll(input.get())) {
            // impossible
            throw new AssertionError("We just checked this!");
        }
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Inventory clone() {
        final Inventory clone = new Inventory();
        clone.items.putAll(this.items);
        return clone;
    }
}
