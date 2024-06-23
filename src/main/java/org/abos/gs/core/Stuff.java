package org.abos.gs.core;

import org.abos.common.Translatable;

/**
 * Something that is either an {@link ItemLike} or a {@link TagLike} or something else that could be used as an ingredient
 * and result of crafting. Implementations of this interface should be immutable.
 */
public interface Stuff extends Translatable {
    // marker interface for now
}
