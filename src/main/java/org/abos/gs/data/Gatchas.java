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
                    new DistributionEntry(new StuffStack(Items.BARK), 4),
                    new DistributionEntry(new StuffStack(Items.BIG_STONE), 6),
                    new DistributionEntry(new StuffStack(Items.BRANCH), 10),
                    new DistributionEntry(new StuffStack(Items.DRIED_LEAF), 1),
                    new DistributionEntry(new StuffStack(Items.LEAF, 3), 2),
                    new DistributionEntry(new StuffStack(Items.MOSS), 2),
                    new DistributionEntry(new StuffStack(Items.LONG_GRASS_BLADE, 2), 10),
                    new DistributionEntry(new StuffStack(Items.SMALL_STONE), 20),
                    new DistributionEntry(new StuffStack(Items.STICK), 20)
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

    GatchaLike FOREST_TIMBER = new Gatcha("forest_timber",
            List.of(new StuffStack(Items.STONE_AXE)),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.YOUNG_LOG, 5), 9),
                    new DistributionEntry(new StuffStack(Items.OLD_LOG, 5), 1)
                ), 1), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.BRANCH, 2), 1),
                    new DistributionEntry(new StuffStack(Items.STICK, 6), 1)
                ), 5), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.LEAF, 3), 1),
                    new DistributionEntry(new StuffStack(Items.LEAF, 6), 1)
                ), 3), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.STONE_AXE), 7),
                    new DistributionEntry(new StuffStack(Items.BIG_SHARP_STONE), 2),
                    new DistributionEntry(new StuffStack(Items.STONE_AXE, 0), 1)
                ), 1)));

    GatchaLike FOREST_DEER = new Gatcha("forest_deer",
            List.of(new StuffStack(Items.DEER_SNARE)),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.DEER_CARCASS), 6),
                    new DistributionEntry(new StuffStack(Items.DEER_CARCASS, 0), 4)
            ), 1), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.DEER_SNARE), 6),
                    new DistributionEntry(new StuffStack(Items.STICK), 4)
            ), 1)));

    GatchaLike FOREST_DIG = new Gatcha("forest_dig",
            List.of(new StuffStack(Items.STONE_SHOVEL)),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.DIRT), 6),
                    new DistributionEntry(new StuffStack(Items.CLAY), 4)
            ), 5), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.BIG_STONE), 40),
                    new DistributionEntry(new StuffStack(Items.SANDSTONE), 20),
                    new DistributionEntry(new StuffStack(Items.DIRT), 40)
            ), 3), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.POTATO), 10),
                    new DistributionEntry(new StuffStack(Items.CARROT), 10),
                    new DistributionEntry(new StuffStack(Items.EARTHWORM), 20),
                    new DistributionEntry(new StuffStack(Items.DANDELION_ROOT), 10),
                    new DistributionEntry(new StuffStack(Items.SMALL_FOSSIL), 1),
                    new DistributionEntry(new StuffStack(Items.DIRT), 49)
            ), 2), new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.STONE_SHOVEL), 7),
                    new DistributionEntry(new StuffStack(Items.BIG_STONE), 2),
                    new DistributionEntry(new StuffStack(Items.STONE_SHOVEL, 0), 1)
            ), 1)));

    GatchaLike FOREST_GNOME = new Gatcha("forest_gnome",
            List.of(new StuffStack(Items.GNOME_HUT), new StuffStack(Items.COOKED_VENISON, 5)),
            List.of(new Distribution(List.of(
                    new DistributionEntry(new StuffStack(Items.GNOME), 9),
                    new DistributionEntry(new StuffStack(Items.GNOME_HUT), 1)
            ), 1)));
}
