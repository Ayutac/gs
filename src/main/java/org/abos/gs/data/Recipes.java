package org.abos.gs.data;

import org.abos.gs.core.Recipe;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.core.StuffStack;

import java.util.List;

public interface Recipes {
    // the alphabetical order here is merely for convenience
    RecipeLike BIG_SHARP_STONE = new Recipe("big_sharp_stone",
            List.of(new StuffStack(Items.BIG_STONE), new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.BIG_SHARP_STONE)),
            List.of(),
            3);
    RecipeLike BOW_DRILL = new Recipe("bow_drill",
            List.of(new StuffStack(Items.PLANT_ROPE), new StuffStack(Items.STICK, 2)),
            List.of(new StuffStack(Items.BOW_DRILL)),
            List.of(),
            2);
    RecipeLike COOKED_VENISON = new Recipe("cooked_venison",
            List.of(new StuffStack(Items.RAW_VENISON), new StuffStack(Items.FIRE)),
            List.of(new StuffStack(Items.COOKED_VENISON)),
            List.of(new StuffStack(Items.BRANCH)),
            8);
    RecipeLike DEER_BUTCHER = new Recipe("deer_butcher",
            List.of(new StuffStack(Items.DEER_CARCASS)),
            List.of(new StuffStack(Items.RAW_VENISON, 3), new StuffStack(Items.DEER_HIDE), new StuffStack(Items.BONE, 5), new StuffStack(Items.DEER_SKULL), new StuffStack(Items.SINEW, 8), new StuffStack(Items.HOOF, 4)),
            List.of(new StuffStack(Tags.KNIFES), new StuffStack(Tags.AXES)),
            15);
    RecipeLike DEER_SNARE = new Recipe("deer_snare",
            List.of(new StuffStack(Tags.ROPES), new StuffStack(Items.STICK, 2)),
            List.of(new StuffStack(Items.DEER_SNARE)),
            List.of(new StuffStack(Tags.KNIFES)),
            1);
    RecipeLike DRIED_LEAF = new Recipe("dried_leaf",
            List.of(new StuffStack(Items.LEAF)),
            List.of(new StuffStack(Items.DRIED_LEAF)),
            List.of(new StuffStack(Tags.BOARDS)),
            4);
    RecipeLike DRIED_MOSS = new Recipe("dried_moss",
            List.of(new StuffStack(Items.MOSS)),
            List.of(new StuffStack(Items.DRIED_MOSS)),
            List.of(new StuffStack(Tags.BOARDS)),
            8);
    RecipeLike DRIED_SINEW = new Recipe("dried_sinew",
            List.of(new StuffStack(Items.SINEW)),
            List.of(new StuffStack(Items.DRIED_SINEW)),
            List.of(new StuffStack(Tags.BOARDS)),
            5);
    RecipeLike FIRE_FROM_MEDIUM_CAMPFIRE = new Recipe("fire_from_medium_campfire",
            List.of(new StuffStack(Items.MEDIUM_CAMPFIRE), new StuffStack(Tags.TINDER, 4)),
            List.of(new StuffStack(Items.FIRE, 3), new StuffStack(Items.BIG_STONE, 8), new StuffStack(Items.ASH, 5)),
            List.of(new StuffStack(Items.BOW_DRILL)),
            8);
    RecipeLike FIRE_FROM_SMALL_CAMPFIRE = new Recipe("fire_from_small_campfire",
            List.of(new StuffStack(Items.SMALL_CAMPFIRE), new StuffStack(Tags.TINDER, 2)),
            List.of(new StuffStack(Items.FIRE), new StuffStack(Items.SMALL_STONE, 8), new StuffStack(Items.ASH, 2)),
            List.of(new StuffStack(Items.BOW_DRILL)),
            4);
    RecipeLike MEDIUM_CAMPFIRE = new Recipe("medium_campfire",
            List.of(new StuffStack(Items.BIG_STONE, 8), new StuffStack(Tags.STICKS, 12), new StuffStack(Items.BRANCH, 6), new StuffStack(Tags.LOGS, 3)),
            List.of(new StuffStack(Items.MEDIUM_CAMPFIRE)),
            List.of(),
            4);
    RecipeLike PLANT_ROPE = new Recipe("plant_rope",
            List.of(new StuffStack(Items.LONG_GRASS_BLADE, 5)),
            List.of(new StuffStack(Items.PLANT_ROPE)),
            List.of(),
            3);
    RecipeLike SMALL_CAMPFIRE = new Recipe("small_campfire",
            List.of(new StuffStack(Items.SMALL_STONE, 8), new StuffStack(Tags.STICKS, 6), new StuffStack(Items.BRANCH, 2)),
            List.of(new StuffStack(Items.SMALL_CAMPFIRE)),
            List.of(),
            3);
    RecipeLike SMALL_SHARP_STONE = new Recipe("small_sharp_stone",
            List.of(new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.SMALL_SHARP_STONE)),
            List.of(),
            2);
    RecipeLike SMALL_STONE = new Recipe("big_stone",
            List.of(new StuffStack(Items.BIG_STONE)),
            List.of(new StuffStack(Items.SMALL_STONE, 5)),
            List.of(),
            2);
    RecipeLike SHARP_STICK = new Recipe("sharp_stick",
            List.of(new StuffStack(Items.STICK)),
            List.of(new StuffStack(Items.SHARP_STICK)),
            List.of(new StuffStack(Tags.KNIFES)),
            1);
    RecipeLike STONE_AXE = new Recipe("stone_axe",
            List.of(new StuffStack(Items.BIG_SHARP_STONE), new StuffStack(Items.WOODEN_ROD), new StuffStack(Items.LONG_GRASS_BLADE, 4)),
            List.of(new StuffStack(Items.STONE_AXE)),
            List.of(),
            5);
    RecipeLike STONE_KNIFE = new Recipe("stone_knife",
            List.of(new StuffStack(Items.SMALL_SHARP_STONE), new StuffStack(Items.STICK), new StuffStack(Items.LONG_GRASS_BLADE, 2)),
            List.of(new StuffStack(Items.STONE_KNIFE)),
            List.of(),
            3);
    RecipeLike STONE_PICKAXE = new Recipe("stone_pickaxe",
            List.of(new StuffStack(Items.BIG_STONE), new StuffStack(Items.SMALL_SHARP_STONE, 2), new StuffStack(Items.SHARP_STICK), new StuffStack(Items.WOODEN_ROD), new StuffStack(Items.LONG_GRASS_BLADE, 4)),
            List.of(new StuffStack(Items.STONE_PICKAXE)),
            List.of(),
            15);
    RecipeLike STRIPPED_OLD_LOG = new Recipe("stripped_old_log",
            List.of(new StuffStack(Items.OLD_LOG)),
            List.of(new StuffStack(Items.STRIPPED_OLD_LOG), new StuffStack(Items.BARK, 4)),
            List.of(new StuffStack(Tags.AXES)),
            4);
    RecipeLike STRIPPED_YOUNG_LOG = new Recipe("stripped_young_log",
            List.of(new StuffStack(Items.YOUNG_LOG)),
            List.of(new StuffStack(Items.STRIPPED_YOUNG_LOG), new StuffStack(Items.BARK, 4)),
            List.of(new StuffStack(Tags.AXES)),
            4);
    RecipeLike WOODEN_BOARD = new Recipe("wooden_board",
            List.of(new StuffStack(Tags.STRIPPED_LOGS)),
            List.of(new StuffStack(Items.WOODEN_BOARD), new StuffStack(Items.WOODEN_ROD, 2)),
            List.of(new StuffStack(Tags.KNIFES), new StuffStack(Tags.AXES)),
            10);
    RecipeLike WOODEN_BOWL = new Recipe("wooden_bowl",
            List.of(new StuffStack(Tags.STRIPPED_LOGS)),
            List.of(new StuffStack(Items.WOODEN_BOWL, 2)),
            List.of(new StuffStack(Tags.KNIFES), new StuffStack(Tags.AXES)),
            10);
    RecipeLike WOODEN_ROD = new Recipe("wooden_rod",
            List.of(new StuffStack(Items.BRANCH)),
            List.of(new StuffStack(Items.WOODEN_ROD)),
            List.of(new StuffStack(Tags.KNIFES)),
            3);
    RecipeLike WOODEN_TOKEN = new Recipe("wooden_token",
            List.of(new StuffStack(Items.STICK, 2), new StuffStack(Items.SMALL_STONE), new StuffStack(Items.LONG_GRASS_BLADE, 4)),
            List.of(new StuffStack(Items.WOODEN_TOKEN)),
            List.of(),
            1);

}
