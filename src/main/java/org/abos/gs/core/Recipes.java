package org.abos.gs.core;

import java.util.List;

public interface Recipes {

    RecipeLike SHARP_STONE = new Recipe("sharp_stone",
            List.of(new StuffStack(Items.SMALL_STONE, 2)),
            List.of(new StuffStack(Items.SMALL_SHARP_STONE)),
            List.of());

}
