package org.abos.gs.core;

import org.abos.gs.data.Gatchas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Random;

/**
 * Test class for {@link Gatcha}.
 */
class TestGatcha {

    @Test
    public void testRoll() {
        Collection<StuffStack> rollResult = Gatchas.FOREST_FORAGE.roll(new Random());
        Assertions.assertTrue(rollResult.size() <= 5);
        rollResult = Gatchas.FOREST_TIMBER.roll(new Random());
        Assertions.assertTrue(rollResult.size() >= 3);
        System.out.println(rollResult);
    }

}