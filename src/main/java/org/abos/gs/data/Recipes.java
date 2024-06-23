package org.abos.gs.data;

import org.abos.gs.core.Recipe;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.core.StuffStack;

import java.util.List;

public interface Recipes {

    RecipeLike BIG_SHARP_STONE = new Recipe("big_sharp_stone",
            List.of(new StuffStack(Items.BIG_STONE), new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.BIG_SHARP_STONE)),
            List.of());
    RecipeLike SMALL_SHARP_STONE = new Recipe("small_sharp_stone",
            List.of(new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.SMALL_SHARP_STONE)),
            List.of());
    RecipeLike SMALL_STONE = new Recipe("big_stone",
            List.of(new StuffStack(Items.BIG_STONE)),
            List.of(new StuffStack(Items.SMALL_STONE, 5)),
            List.of());
    RecipeLike SHARP_STICK = new Recipe("sharp_stick",
            List.of(new StuffStack(Items.STICK)),
            List.of(new StuffStack(Items.SHARP_STICK)),
            List.of(new StuffStack(Tags.KNIFES)));
    RecipeLike STONE_KNIFE = new Recipe("stone_knife",
            List.of(new StuffStack(Items.SMALL_SHARP_STONE), new StuffStack(Items.STICK), new StuffStack(Items.LONG_GRASS_BLADE, 2)),
            List.of(new StuffStack(Items.STONE_KNIFE)),
            List.of());

}
