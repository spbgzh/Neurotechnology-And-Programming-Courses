package com.epam.rd.autocode.hashtableopen816;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HashtableOpen8to16Test {

    private static final String ASSERT_FAILED_MESSAGE_PREFIX = "Failed at ";

    @ParameterizedTest
    @MethodSource("simpleFillCases")
    void testSimpleFilling(String testCaseName, int[] elements, String expectedKeys) {
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        for (int element : elements) {
            hashtable.insert(element, element);
        }

        assertEquals(expectedKeys, Arrays.toString(hashtable.keys()), () -> ASSERT_FAILED_MESSAGE_PREFIX + testCaseName);
    }

    @ParameterizedTest
    @MethodSource("fillAndRemoveCases")
    void testFillingAndThenGradualRemove(String testCaseName, int[] elements, List<String> expectedKeysList) {
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        for (int element : elements) {
            hashtable.insert(element, element);
        }

        final List<String> actualKeysList = new ArrayList<>();
        actualKeysList.add(Arrays.toString(hashtable.keys()));

        for (final int element : elements) {
            hashtable.remove(element);
            actualKeysList.add(Arrays.toString(hashtable.keys()));
        }

        assertEquals(
                String.join("\n", expectedKeysList),
                String.join("\n", actualKeysList),
                () -> ASSERT_FAILED_MESSAGE_PREFIX + testCaseName);
    }

    @ParameterizedTest
    @MethodSource("fillAndRefillCases")
    void testRandomFillAndRefill(String testCaseName, int[] elements, List<String> expectedKeysList) {
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        Random random = new Random(Integer.parseInt(testCaseName) + elements[0]);

        List<Integer> elementsInRandomOrder = Arrays.stream(elements).boxed().collect(Collectors.toList());
        final List<String> actualKeysList = new ArrayList<>();

        Collections.shuffle(elementsInRandomOrder, random);
        for (int element : elementsInRandomOrder) {
            hashtable.insert(element, element);
            actualKeysList.add(Arrays.toString(hashtable.keys()));
        }

        Collections.shuffle(elementsInRandomOrder, random);
        for (final int element : elementsInRandomOrder) {
            hashtable.remove(element);
            actualKeysList.add(Arrays.toString(hashtable.keys()));
        }

        Collections.shuffle(elementsInRandomOrder, random);
        for (final int element : elementsInRandomOrder) {
            hashtable.insert(element, element);
            actualKeysList.add(Arrays.toString(hashtable.keys()));
        }

        assertEquals(
                String.join("\n", expectedKeysList),
                String.join("\n", actualKeysList),
                () -> ASSERT_FAILED_MESSAGE_PREFIX + testCaseName);
    }

    @Test
    public void testSearch(){
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        hashtable.insert(10,10);
        hashtable.insert(18,18);
        hashtable.insert(34,34);

        assertEquals(10, hashtable.search(10));
        assertEquals(18, hashtable.search(18));
        assertEquals(34, hashtable.search(34));

        hashtable.remove(18);

        assertEquals(10, hashtable.search(10));
        assertEquals(null, hashtable.search(18));
        assertEquals(34, hashtable.search(34));

        hashtable.remove(10);

        assertEquals(null, hashtable.search(10));
        assertEquals(null, hashtable.search(18));
        assertEquals(34, hashtable.search(34));

        hashtable.insert(10,10);
        hashtable.insert(18,18);
        hashtable.insert(34,34);
        hashtable.insert(42,42);
        hashtable.insert(50,50);
        hashtable.insert(58,58);
        hashtable.insert(66,66);
        hashtable.insert(74,74);

        hashtable.remove(2);
        assertEquals(10, hashtable.search(10));

        hashtable.remove(10);

        assertEquals(null, hashtable.search(10));
        assertEquals(18, hashtable.search(18));
        assertEquals(34, hashtable.search(34));
        assertEquals(42, hashtable.search(42));
        assertEquals(50, hashtable.search(50));
        assertEquals(58, hashtable.search(58));
        assertEquals(66, hashtable.search(66));
        assertEquals(74, hashtable.search(74));

        hashtable.remove(50);

        assertEquals(null, hashtable.search(10));
        assertEquals(18, hashtable.search(18));
        assertEquals(34, hashtable.search(34));
        assertEquals(42, hashtable.search(42));
        assertEquals(null, hashtable.search(50));
        assertEquals(58, hashtable.search(58));
        assertEquals(66, hashtable.search(66));
        assertEquals(74, hashtable.search(74));
    }


    @Test
    public void testOverflow(){
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        for (int i = 0; i < 32; i+=2) {
            hashtable.insert(i, i);
        }

        assertThrows(IllegalStateException.class, () -> hashtable.insert(42, 42));

        hashtable.insert(16, 32);
    }



    static Stream<Arguments> simpleFillCases() throws IOException {
        return readTestCases("simple-fill", HashtableOpen8to16Test::readKeys);
    }

    static Stream<Arguments> fillAndRemoveCases() throws IOException {
        return readTestCases("fill-and-remove", HashtableOpen8to16Test::readKeysSeries);
    }

    static Stream<Arguments> fillAndRefillCases() throws IOException {
        return readTestCases("fill-and-refill", HashtableOpen8to16Test::readKeysSeries);
    }

    private static Stream<Arguments> readTestCases(final String s, final Function<Path, Object> keysExtractor) throws IOException {
        final Path testCaseRoot = Paths.get("src", "test", "resources", s);

        return Files.walk(testCaseRoot, 1)
                .filter(Files::isDirectory)
                .filter(path -> !testCaseRoot.equals(path))
                .map(testCase -> arguments(
                        testCase.getFileName().toString(),
                        readElements(testCase),
                        keysExtractor.apply(testCase)
                ));
    }

    private static int[] readElements(final Path testCase) {
        try (Stream<String> lines = Files.lines(testCase.resolve("fill.txt"))) {
            return lines.mapToInt(Integer::valueOf).toArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String readKeys(final Path testCase) {
        try {
            return Files.readString(testCase.resolve("keys.txt"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static List<String> readKeysSeries(final Path testCase) {
        try {
            return Files.readAllLines(testCase.resolve("keys.txt"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}