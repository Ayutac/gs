package org.abos.gs.core;

import org.abos.common.DescribableTranslatable;
import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

/**
 * Describes an item. Implementations of this interface should be immutable.
 */
public interface ItemLike extends Stuff, DescribableTranslatable {

    /**
     * Returns the rarity of an item.
     *
     * @return the rarity of an item, not negative and not {@code null}
     */
    int getRarity();

    @Override
    default @NotNull String getTranslation(@NotNull ResourceBundle rb) {
        return rb.getString(String.format("item.%s", getName()));
    }
}
