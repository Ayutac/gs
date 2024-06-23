package org.abos.gs.data;

import org.abos.gs.core.Tag;
import org.abos.gs.core.TagLike;

import java.util.Set;

public interface Tags {

    TagLike KNIFES = new Tag("knives", Set.of(Items.STONE_KNIFE));
    TagLike STICKS = new Tag("sticks", Set.of(Items.STICK, Items.SHARP_STICK));

}
