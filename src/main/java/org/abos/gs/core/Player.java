package org.abos.gs.core;

import org.abos.common.Named;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Player implements Named, Serializable {

    @Serial
    private static final long serialVersionUID = 9834521352L;

    protected final String name;

    protected final Inventory inventory = new Inventory();

    protected final Inventory craftingInv = new Inventory();

    public Player(@NotNull final String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public @NotNull Inventory getCraftingInv() {
        return craftingInv;
    }

    /**
     * Returns all recipes that can be crafted given the current crafting inventory.
     * @return a mutable, unsorted list of legit recipes, not {@code null} or containing {@code null}
     */
    public @NotNull List<RecipeLike> matchingRecipes() {
        final List<RecipeLike> matches = new LinkedList<>();
        for (final RecipeLike recipe : Recipe.getAll()) {
            if (craftingInv.matchesRecipe(recipe)) {
                matches.add(recipe);
            }
        }
        return matches;
    }

}
