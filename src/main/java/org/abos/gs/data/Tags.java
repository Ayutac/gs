package org.abos.gs.data;

import org.abos.gs.core.Tag;
import org.abos.gs.core.TagLike;

import java.util.Set;

public interface Tags {
    // the alphabetical order here is merely for convenience
    TagLike AXES = new Tag("axes", Set.of(Items.STONE_AXE));
    TagLike BERRIES = new Tag("berries", Set.of(Items.BLACKBERRY, Items.CURRANT, Items.ELDERBERRY, Items.RASPBERRY, Items.ROWANBERRY, Items.STRAWBERRY));
    TagLike BOARDS = new Tag("boards", Set.of(Items.WOODEN_BOARD));
    TagLike KNIFES = new Tag("knives", Set.of(Items.STONE_KNIFE));
    TagLike LOGS = new Tag("knives", Set.of(Items.OLD_LOG, Items.STRIPPED_OLD_LOG, Items.STRIPPED_YOUNG_LOG, Items.YOUNG_LOG));
    TagLike MUSHROOMS = new Tag("mushrooms", Set.of(Items.BOLET, Items.CHANTERELLE, Items.CHICKEN_OF_THE_WOODS, Items.CULTIVATED_MUSHROOM, Items.DRYADS_SADDLE, Items.LIONS_MANE, Items.MOREL));
    TagLike NUTS = new Tag("nuts", Set.of(Items.ACORN, Items.CHESTNUT, Items.HAZELNUT, Items.PINENUT, Items.WALNUT));
    TagLike ROPES = new Tag("ropes", Set.of(Items.PLANT_ROPE));
    TagLike STICKS = new Tag("sticks", Set.of(Items.STICK, Items.SHARP_STICK));
    TagLike STRIPPED_LOGS = new Tag("stripped_logs", Set.of(Items.STRIPPED_OLD_LOG, Items.STRIPPED_YOUNG_LOG));
    TagLike TINDER = new Tag("tinder", Set.of(Items.BARK, Items.DRIED_LEAF, Items.DRIED_MOSS));

}
