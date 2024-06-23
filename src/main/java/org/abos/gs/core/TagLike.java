package org.abos.gs.core;

import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Represent a collection of items bundled together. Implementations of this interface should be immutable.
 */
public interface TagLike extends Stuff, Predicate<ItemLike> {

    @Override
    default @NotNull String getTranslation(@NotNull ResourceBundle rb) {
        return rb.getString(String.format("tag.%s", getName()));
    }
}
