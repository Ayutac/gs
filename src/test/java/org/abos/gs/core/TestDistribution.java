package org.abos.gs.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test class for {@link Distribution}.
 */
class TestDistribution {

    @Test
    void testDrawResult() {
        final ItemLike testItem = new Item("test", 0);
        final Distribution testDist = new Distribution(List.of(
                new DistributionEntry(new StuffStack(testItem, 1), 1),
                new DistributionEntry(new StuffStack(testItem, 2), 1),
                new DistributionEntry(new StuffStack(testItem, 3), 1)), 2);
        Assertions.assertEquals(new StuffStack(testItem, 1), testDist.drawResult(-1));
        Assertions.assertEquals(new StuffStack(testItem, 1), testDist.drawResult(0));
        Assertions.assertEquals(new StuffStack(testItem, 2), testDist.drawResult(1));
        Assertions.assertEquals(new StuffStack(testItem, 3), testDist.drawResult(2));
        Assertions.assertEquals(new StuffStack(testItem, 3), testDist.drawResult(3));
        Assertions.assertEquals(new StuffStack(testItem, 3), testDist.drawResult(4));
    }
}