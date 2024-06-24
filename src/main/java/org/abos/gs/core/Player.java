package org.abos.gs.core;

import org.abos.common.Named;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

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

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory getCraftingInv() {
        return craftingInv;
    }
}
