package org.abos.gs.core;

import org.abos.common.DescribableTranslatable;

/**
 * Describes an item. Implementations of this interface should be immutable.
 */
public interface ItemLike extends Stuff, DescribableTranslatable {

    /**
     * Returns the rarity of an item.
     * @return the rarity of an item, not negative and not {@code null}
     */
    Integer getRarity();

}
