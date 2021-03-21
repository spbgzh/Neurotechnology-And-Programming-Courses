package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.RussianRoulette;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class RiskyShotTest {

    @Test
    public void successfulShots() {
        RussianRoulette[] roulettes = new RussianRoulette[]{
                new RussianRoulette(0),
                new RussianRoulette(1),
                new RussianRoulette(6),
                new RussianRoulette(19)
        };
        for (int i = 0; i < roulettes.length; i++) {
            RussianRoulette roulette = roulettes[i];
            new RiskyShot(5 - i % 2, roulette).handleShot();
        }
    }

    @Test
    public void plainRouletteTest() {
        shotTest(0, 0, this::assertFNF, this::fail);
        shotTest(0, 1, this::assertIO, this::fail);
        shotTest(0, 2, this::fail, this::pass);
        shotTest(0, 3, this::fail, this::pass);
        shotTest(0, 4, this::assertUE, this::fail);
        shotTest(0, 5, this::fail, this::pass);
    }

    @Test
    public void plainPlus3RouletteTest() {
        shotTest(3, 0, this::fail, this::pass);
        shotTest(3, 1, this::assertUE, this::fail);
        shotTest(3, 2, this::fail, this::pass);
        shotTest(3, 3, this::assertFNF, this::fail);
        shotTest(3, 4, this::assertIO, this::fail);
        shotTest(3, 5, this::fail, this::pass);
    }

    @Test
    public void plainPlus15RouletteTest() {
        shotTest(15, 0, this::fail, this::pass);
        shotTest(15, 1, this::assertUE, this::fail);
        shotTest(15, 2, this::fail, this::pass);
        shotTest(15, 3, this::assertFNF, this::fail);
        shotTest(15, 4, this::assertIO, this::fail);
        shotTest(15, 5, this::fail, this::pass);
    }

    @Test
    public void countShots() {
        {
            final CountingRussianRoulette roulette = new CountingRussianRoulette();
            new RiskyShot(2, roulette).handleShot();
            assertEquals("Bad handling of Arithmetic or Number Format Exceptions", 3, roulette.hit);
        }
        {
            final CountingRussianRoulette roulette = new CountingRussianRoulette();
            new RiskyShot(3, roulette).handleShot();
            assertEquals("Bad handling of Arithmetic or Number Format Exceptions", 2, roulette.hit);
        }
    }


    private void assertFNF(final Exception e) {
        assertEquals(IllegalArgumentException.class, e.getClass());
        assertEquals(e.getMessage(), "File is missing");
        assertEquals(FileNotFoundException.class, e.getCause().getClass());
        assertEquals(e.getCause().getMessage(), "1");
    }

    private void assertIO(final Exception e) {
        assertEquals(IllegalArgumentException.class, e.getClass());
        assertEquals("File error", e.getMessage());
        assertEquals(IOException.class, e.getCause().getClass());
        assertEquals("2", e.getCause().getMessage());
    }

    private void assertUE(final Exception e) {
        assertEquals(UnsupportedOperationException.class, e.getClass());
        assertEquals("5", e.getMessage());
    }

    private void fail(int i) {
        Assert.fail();
    }

    private void pass(int i) {
    }

    private void fail(Exception e) {
        Assert.fail();
    }

    private static void shotTest(int shift,
                                 int input,
                                 Consumer<Exception> exceptionAssertion,
                                 Consumer<Integer> noExceptionAssertion) {
        try {
            final RussianRoulette roulette = new RussianRoulette(shift);
            new RiskyShot(input, roulette).handleShot();
        } catch (Exception e) {
            exceptionAssertion.accept(e);
            return;
        }
        noExceptionAssertion.accept(0);
    }

}