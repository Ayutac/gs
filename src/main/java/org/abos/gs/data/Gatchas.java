package org.abos.gs.data;

import org.abos.gs.core.Distribution;
import org.abos.gs.core.DistributionEntry;
import org.abos.gs.core.Gatcha;
import org.abos.gs.core.GatchaLike;
import org.abos.gs.core.StuffStack;

import java.util.List;

public interface Gatchas {

    GatchaLike FOREST_SCAVENGE = new Gatcha("forest_scavenge",
            List.of(),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.STICK), 10),
                    new DistributionEntry(new StuffStack(Items.SMALL_STONE), 10),
                    new DistributionEntry(new StuffStack(Items.BIG_STONE), 3),
                    new DistributionEntry(new StuffStack(Items.LONG_GRASS_BLADE, 2), 5)
            ), 10)));

}
