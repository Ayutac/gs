package org.abos.gs.data;

import org.abos.gs.core.Tag;
import org.abos.gs.core.TagLike;

import java.util.Set;

public interface Tags {
    // the alphabetical order here is merely for convenience
    TagLike BERRIES = new Tag("berries", Set.of(Items.BLACKBERRY, Items.CURRANT, Items.ELDERBERRY, Items.RASPBERRY, Items.ROWANBERRY, Items.STRAWBERRY));
    TagLike KNIFES = new Tag("knives", Set.of(Items.STONE_KNIFE));
    TagLike MUSHROOMS = new Tag("mushrooms", Set.of(Items.BOLET, Items.CHANTERELLE, Items.CHICKEN_OF_THE_WOODS, Items.CULTIVATED_MUSHROOM, Items.DRYADS_SADDLE, Items.LIONS_MANE, Items.MOREL));
    TagLike NUTS = new Tag("nuts", Set.of(Items.ACORN, Items.CHESTNUT, Items.HAZELNUT, Items.PINENUT, Items.WALNUT));
    TagLike STICKS = new Tag("sticks", Set.of(Items.STICK, Items.SHARP_STICK));

}
