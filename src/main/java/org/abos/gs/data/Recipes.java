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
