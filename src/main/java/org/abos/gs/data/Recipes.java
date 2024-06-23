package org.abos.gs.data;

import org.abos.gs.core.Recipe;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.core.StuffStack;

import java.util.List;

public interface Recipes {

    RecipeLike SMALL_SHARP_STONE = new Recipe("small_sharp_stone",
            List.of(new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.SMALL_SHARP_STONE)),
            List.of());

}
