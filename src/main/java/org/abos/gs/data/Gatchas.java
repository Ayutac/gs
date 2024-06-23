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
                    new DistributionEntry(new StuffStack(Items.BIG_STONE), 3),
                    new DistributionEntry(new StuffStack(Items.BRANCH), 5),
                    new DistributionEntry(new StuffStack(Items.LONG_GRASS_BLADE, 2), 5),
                    new DistributionEntry(new StuffStack(Items.SMALL_STONE), 10),
                    new DistributionEntry(new StuffStack(Items.STICK), 10)
            ), 10)));

    GatchaLike FOREST_FORAGE = new Gatcha("forest_forage",
            List.of(new StuffStack(Items.WOODEN_TOKEN)),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.ACORN, 5), 4),
                    new DistributionEntry(new StuffStack(Items.BLACKBERRY, 3), 5),
                    new DistributionEntry(new StuffStack(Items.BOLET, 3), 3),
                    new DistributionEntry(new StuffStack(Items.CHANTERELLE, 3), 3),
                    new DistributionEntry(new StuffStack(Items.CHESTNUT, 3), 4),
                    new DistributionEntry(new StuffStack(Items.CHICKEN_OF_THE_WOODS, 2), 3),
                    new DistributionEntry(new StuffStack(Items.CULTIVATED_MUSHROOM, 3), 3),
                    new DistributionEntry(new StuffStack(Items.CURRANT, 6), 5),
                    new DistributionEntry(new StuffStack(Items.DRYADS_SADDLE, 2), 3),
                    new DistributionEntry(new StuffStack(Items.ELDERBERRY, 6), 5),
                    new DistributionEntry(new StuffStack(Items.HAZELNUT, 4), 4),
                    new DistributionEntry(new StuffStack(Items.LIONS_MANE, 2), 3),
                    new DistributionEntry(new StuffStack(Items.MOREL, 3), 3),
                    new DistributionEntry(new StuffStack(Items.PINENUT, 8), 4),
                    new DistributionEntry(new StuffStack(Items.RASPBERRY, 3), 5),
                    new DistributionEntry(new StuffStack(Items.ROWANBERRY, 6), 5),
                    new DistributionEntry(new StuffStack(Items.STRAWBERRY, 2), 5),
                    new DistributionEntry(new StuffStack(Items.WALNUT, 3), 4)
            ), 5)));

}
