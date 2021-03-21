package com.efimchick.ifmo.collections.countwords;


import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by EE on 2018-09-20.
 */
public class WordsExerciseTest {

    private static final Random rand = new Random();

    @Test
    public void testWarAndPeace() throws IOException {
        final String result = readPathToString(Paths.get("src/test/resources/WAPResult.txt"));

        final List<String> lines = Stream.concat(
                Files.lines(Paths.get("src", "test", "resources", "WAP12.txt"), Charset.forName("windows-1251")),
                Files.lines(Paths.get("src", "test", "resources", "WAP34.txt"), Charset.forName("windows-1251"))
        ).collect(Collectors.toList());

        assertEquals(result, new Words().countWords(lines));
    }

    @Test
    public void testNoLambdas() throws IOException {

        final Path sources = Paths.get("src/main/java");
        Files.walk(sources)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(this::assertSourceHasNoStreams);
    }


    @Test
    public void testRandomCases(){
        smallRandomTestCase();
        smallRandomTestCase();
        smallRandomTestCase();
        smallRandomTestCase();
        smallRandomTestCase();
        smallRandomTestCase();
    }


    public void smallRandomTestCase() {
        final List<String> testCase = new ArrayList<>();
        final int[] freqs = rand.ints()
                .map(i -> Math.abs(i) % 10 + 11)
                .distinct()
                .limit(4)
                .sorted()
                .toArray();

        final String[] lines = {"манул красив.", "котенок игрив.", "тигруля в полете.", "Пушкин - наше все"};

        for (int i = 0; i < 4; i++) {
            for (int times = 0; times < freqs[i]; times++) {
                testCase.add(lines[i]);
            }
        }

        Collections.shuffle(testCase);

        String result = String.join("\n",
                "наше - " + freqs[3],
                "пушкин - " + freqs[3],
                "полете - " + freqs[2],
                "тигруля - " + freqs[2],
                "игрив - " + freqs[1],
                "котенок - " + freqs[1],
                "красив - " + freqs[0],
                "манул - " + freqs[0]
        );

        assertEquals(result, new Words().countWords(testCase));
    }

    private void assertSourceHasNoStreams(final Path sourcePath) {
        final String source = readPathToString(sourcePath);

        assertFalse(source.contains("->"));
        assertFalse(source.contains("::"));
        assertFalse(source.contains("stream"));
    }

    private String readPathToString(final Path path) {
        try {
            return String.join("\n", readAllLines(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}