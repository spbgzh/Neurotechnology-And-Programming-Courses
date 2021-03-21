package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertEquals;

public class CollectingTest {

    @Test
    public void testSum() {
        assertEquals(15, new Collecting().sum(IntStream.iterate(1, i -> i + 1).limit(5)));
        assertEquals(10, new Collecting().sum(IntStream.iterate(0, i -> i + 1).limit(5)));
        assertEquals(1, new Collecting().sum(IntStream.iterate(1, i -> -i).limit(5)));
        assertEquals(-2, new Collecting().sum(IntStream.iterate(0, i -> -(i + 1)).limit(5)));
        assertEquals(-14, new Collecting().sum(IntStream.iterate(0, i -> -2).limit(8)));

    }

    @Test
    public void testProd() {
        assertEquals(120, new Collecting().production(IntStream.iterate(1, i -> i + 1).limit(5)));
        assertEquals(0, new Collecting().production(IntStream.iterate(0, i -> i + 1).limit(5)));
        assertEquals(1, new Collecting().production(IntStream.iterate(1, i -> -i).limit(5)));
        assertEquals(-120, new Collecting().production(IntStream.iterate(-1, i -> -(abs(i) + 1)).limit(5)));
        assertEquals(-128, new Collecting().production(IntStream.iterate(1, i -> -2).limit(8)));
    }

    @Test
    public void testOddSum() {
        assertEquals(9, new Collecting().oddSum(IntStream.iterate(1, i -> i + 1).limit(5)));
        assertEquals(4, new Collecting().oddSum(IntStream.iterate(0, i -> i + 1).limit(5)));
        assertEquals(1, new Collecting().oddSum(IntStream.iterate(1, i -> -i).limit(5)));
        assertEquals(-9, new Collecting().oddSum(IntStream.iterate(-1, i -> -(abs(i) + 1)).limit(5)));
        assertEquals(-7, new Collecting().oddSum(IntStream.iterate(1, i -> -(abs(i) + 1)).limit(5)));
        assertEquals(0, new Collecting().oddSum(IntStream.iterate(0, i -> -2).limit(8)));
    }

    @Test
    public void testSumByRemainder() {

        assertEquals(
                Map.of(0, 15),
                new Collecting().sumByRemainder(1, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(0, 6,
                        1, 9),
                new Collecting().sumByRemainder(2, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(0, 3,
                        1, 5,
                        2, 7),
                new Collecting().sumByRemainder(3, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(0, 4,
                        1, 6,
                        2, 2,
                        3, 3),
                new Collecting().sumByRemainder(4, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(0, 5,
                        1, 1,
                        2, 2,
                        3, 3,
                        4, 4),
                new Collecting().sumByRemainder(5, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(5, 5,
                        1, 1,
                        2, 2,
                        3, 3,
                        4, 4),
                new Collecting().sumByRemainder(6, IntStream.iterate(1, i -> i + 1).limit(5))
        );
        assertEquals(
                Map.of(1, 1,
                        3, 1748,
                        4, 92,
                        5, 157,
                        6, 48),
                new Collecting().sumByRemainder(7, IntStream.iterate(1, i -> i + (i + 19) / 2).limit(10))
        );
        assertEquals(
                Map.of(0, 21,
                        1, 15,
                        2, 21),
                new Collecting().sumByRemainder(3, IntStream.iterate(0, i -> (i + 1) % 7).limit(20))
        );

    }

    @Test
    public void testTotalScores() {
        assertEquals(
                Map.of(new Person("Ragnar", "Silverhand", 26), 77.0,
                        new Person("Umberto", "Paige", 23), 73.66666666666667,
                        new Person("Johnny", "Lodbrok", 20), 89.66666666666667),
                new Collecting().totalScores(programmingResults(new Random(42))));
        assertEquals(
                Map.of(new Person("Ragnar", "Paige", 26), 82.33333333333333,
                        new Person("Betty", "Silverhand", 23), 74.66666666666667,
                        new Person("Umberto", "Lodbrok", 23), 72.66666666666667),
                new Collecting().totalScores(programmingResults(new Random(45682))));
        assertEquals(
                Map.of(new Person("Johnny", "Lodbrok", 26), 62.25,
                        new Person("Ragnar", "Silverhand", 30), 58.75,
                        new Person("Betty", "Eco", 33), 56.75),
                new Collecting().totalScores(historyResults(new Random(85364))));
        assertEquals(
                Map.of(new Person("Betty", "Eco", 22), 60.0,
                        new Person("Ragnar", "Silverhand", 36), 62.5,
                        new Person("Johnny", "Lodbrok", 26), 49.0),
                new Collecting().totalScores(historyResults(new Random(986513))));
    }

    @Test
    public void testAverageTotalScores() {
        assertEquals(80.66666666666667, new Collecting().averageTotalScore(programmingResults(new Random(8624))), 0.001);
        assertEquals(76.66666666666667, new Collecting().averageTotalScore(programmingResults(new Random(9513))), 0.001);
        assertEquals(54.666666666666664, new Collecting().averageTotalScore(historyResults(new Random(8456))), 0.001);
        assertEquals(52.166666666666664, new Collecting().averageTotalScore(historyResults(new Random(9821))), 0.001);
    }

    public void assertMapEquals(Map<String, Double> expected, Map<String, Double> actual) {
        assertEquals("Map sizes don't match!", expected.size(), actual.size());
        for (String key: expected.keySet()) {
            assertEquals("problem with task " + key, expected.get(key), actual.get(key), 0.0001);
        }
    }

    @Test
    public void testAverageScoresPerTask() {
        Map<String, Double> expected1 = Map.of("Lab 2. War and Peace", 80.66666666666667,
                "Lab 1. Figures", 77.66666666666667,
                "Lab 3. File Tree", 81.0
        );
        Map<String, Double> received1 = new Collecting().averageScoresPerTask(programmingResults(new Random(969)));
        assertMapEquals(expected1, received1);
        Map<String, Double> expected2 = Map.of("Lab 2. War and Peace", 67.0,
                "Lab 1. Figures", 85.66666666666667,
                "Lab 3. File Tree", 68.33333333333333
        );
        Map<String, Double> received2 = new Collecting().averageScoresPerTask(programmingResults(new Random(654)));
        assertMapEquals(expected2, received2);
        Map<String, Double> expected3 = Map.of("Phalanxing", 51.666666666666664,
                "Tercioing", 37.666666666666664,
                "Wedging", 40.333333333333336,
                "Shieldwalling", 75.66666666666667
        );
        Map<String, Double> received3 = new Collecting().averageScoresPerTask(historyResults(new Random(853)));
        assertMapEquals(expected3, received3);
        Map<String, Double> expected4 = Map.of("Phalanxing", 44.0,
                "Tercioing", 47.333333333333336,
                "Wedging", 33.333333333333336,
                "Shieldwalling", 83.0
        );
        Map<String, Double> received4 = new Collecting().averageScoresPerTask(historyResults(new Random(753)));
        assertMapEquals(expected4, received4);
    }

    @Test
    public void testDefineMarks() {
        assertEquals(
                Map.of(new Person("Umberto", "Silverhand", 29), "A",
                        new Person("Ragnar", "Eco", 21), "B",
                        new Person("Johnny", "Paige", 30), "B"
                ),
                new Collecting().defineMarks(programmingResults(new Random(4))));

        assertEquals(
                Map.of(new Person("Umberto", "Paige", 32), "C",
                        new Person("Johnny", "Lodbrok", 27), "B",
                        new Person("Ragnar", "Silverhand", 25), "B"
                ),
                new Collecting().defineMarks(programmingResults(new Random(1))));
        assertEquals(
                Map.of(new Person("Johnny", "Paige", 35), "C",
                        new Person("Betty", "Lodbrok", 27), "D",
                        new Person("Ragnar", "Eco", 34), "D"
                ),
                new Collecting().defineMarks(programmingResults(new Random(7848))));
        assertEquals(
                Map.of(new Person("Umberto", "Eco", 20), "C",
                        new Person("Ragnar", "Lodbrok", 26), "E",
                        new Person("Betty", "Paige", 37), "E"
                ),
                new Collecting().defineMarks(programmingResults(new Random(52145))));
        assertEquals(
                Map.of(new Person("Johnny", "Lodbrok", 29), "F",
                        new Person("Umberto", "Paige", 24), "F",
                        new Person("Betty", "Eco", 28), "F"
                ),
                new Collecting().defineMarks(historyResults(new Random(56985))));
        assertEquals(
                Map.of(new Person("Ragnar", "Eco", 31), "F",
                        new Person("Johnny", "Paige", 31), "F",
                        new Person("Umberto", "Silverhand", 19), "E"
                ),
                new Collecting().defineMarks(historyResults(new Random(753))));
    }

    @Test
    public void testEasiestTask() {
        assertEquals("Lab 3. File Tree",
                new Collecting().easiestTask(programmingResults(new Random(969))));
        assertEquals("Lab 1. Figures",
                new Collecting().easiestTask(programmingResults(new Random(654))));
        assertEquals("Shieldwalling",
                new Collecting().easiestTask(historyResults(new Random(853))));
        assertEquals("Shieldwalling",
                new Collecting().easiestTask(historyResults(new Random(7))));
    }

    @Test
    public void testToPrintableStringCollector() {
        assertEquals(
                "Student         | Lab 1. Figures | Lab 2. War and Peace | Lab 3. File Tree | Total | Mark |\n" +
                        "Eco Johnny      |             56 |                   69 |               90 | 71.67 |    D |\n" +
                        "Lodbrok Umberto |             70 |                   95 |               59 | 74.67 |    D |\n" +
                        "Paige Ragnar    |             51 |                   68 |               57 | 58.67 |    F |\n" +
                        "Average         |          59.00 |                77.33 |            68.67 | 68.33 |    D |",
                programmingResults(new Random(3578964))
                        .collect(new Collecting().printableStringCollector()));
        assertEquals(
                "Student           | Lab 1. Figures | Lab 2. War and Peace | Lab 3. File Tree | Total | Mark |\n" +
                        "Eco Betty         |             86 |                   68 |               85 | 79.67 |    C |\n" +
                        "Paige Umberto     |             51 |                   63 |               84 | 66.00 |    E |\n" +
                        "Silverhand Ragnar |             61 |                   84 |               99 | 81.33 |    C |\n" +
                        "Average           |          66.00 |                71.67 |            89.33 | 75.67 |    C |",
                programmingResults(new Random(84516))
                        .collect(new Collecting().printableStringCollector()));
        assertEquals(
                "Student           | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |\n" +
                        "Lodbrok Johnny    |          0 |            62 |        76 |      89 | 56.75 |    F |\n" +
                        "Paige Umberto     |         56 |            82 |        73 |       0 | 52.75 |    F |\n" +
                        "Silverhand Ragnar |         73 |            54 |         0 |      53 | 45.00 |    F |\n" +
                        "Average           |      43.00 |         66.00 |     49.67 |   47.33 | 51.50 |    F |",
                historyResults(new Random(684537))
                        .collect(new Collecting().printableStringCollector()));

        assertEquals(
                "Student        | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |\n" +
                        "Eco Betty      |          0 |            83 |        89 |      59 | 57.75 |    F |\n" +
                        "Lodbrok Johnny |         61 |            92 |        67 |       0 | 55.00 |    F |\n" +
                        "Paige Umberto  |         75 |            94 |         0 |      52 | 55.25 |    F |\n" +
                        "Average        |      45.33 |         89.67 |     52.00 |   37.00 | 56.00 |    F |",
                historyResults(new Random(9568745))
                        .collect(new Collecting().printableStringCollector()));
    }


    private final String[] names = {"Johnny", "Betty", "Ragnar", "Umberto"};
    private final String[] lastNames = {"Silverhand", "Paige", "Lodbrok", "Eco"};
    private final String[] programTasks = {"Lab 1. Figures", "Lab 2. War and Peace", "Lab 3. File Tree"};
    private final String[] practicalHistoryTasks = {"Shieldwalling", "Phalanxing", "Wedging", "Tercioing"};

    private Stream<CourseResult> programmingResults(final Random random) {
        int n = random.nextInt(names.length);
        int l = random.nextInt(lastNames.length);

        return IntStream.iterate(0, i -> i + 1)
                .limit(3)
                .mapToObj(i -> new Person(
                        names[(n + i) % names.length],
                        lastNames[(l + i) % lastNames.length],
                        18 + random.nextInt(20)))
                .map(p -> new CourseResult(p, Arrays.stream(programTasks).collect(toMap(
                        task -> task,
                        task -> random.nextInt(51) + 50))));
    }

    private Stream<CourseResult> historyResults(final Random random) {
        int n = random.nextInt(names.length);
        int l = random.nextInt(lastNames.length);
        AtomicInteger t = new AtomicInteger(practicalHistoryTasks.length);

        return IntStream.iterate(0, i -> i + 1)
                .limit(3)
                .mapToObj(i -> new Person(
                        names[(n + i) % names.length],
                        lastNames[(l + i) % lastNames.length],
                        18 + random.nextInt(20)))
                .map(p -> new CourseResult(p,
                        IntStream.iterate(t.getAndIncrement(), i -> t.getAndIncrement())
                                .map(i -> i % practicalHistoryTasks.length)
                                .mapToObj(i -> practicalHistoryTasks[i])
                                .limit(3)
                                .collect(toMap(
                                        task -> task,
                                        task -> random.nextInt(51) + 50))));
    }


}