package com.efimchick.ifmo.io.filetree;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileTreeTest {

    @Test
    public void testEmptyCases() {
        assertTrue("null case failure", new FileTreeImpl().tree(null).isEmpty());
        assertTrue("nonexistent path case failure", new FileTreeImpl().tree(Paths.get("some", " non", "existent", "path")).isEmpty());
    }

    @Test
    public void testFileCases() {
        testCase("some.txt 0 bytes", tree("test1", "some.txt"));
        testCase("some1.txt 4 bytes", tree("test1", "some1.txt"));
        testCase("some2.txt 8 bytes", tree("test1", "some2.txt"));
    }

    @Test
    public void testDirCase1() throws IOException {
        testDirCase("test1");
    }

    @Test
    public void testDirCase2() throws IOException {
        testDirCase("test2");
    }

    @Test
    public void testDirCase3() throws IOException {
        testDirCase("test3");
    }

    @Test
    public void testDirCase4() throws IOException {
        testDirCase("test4");
    }

    @Test
    public void testDirCase5() throws IOException {
        testDirCase("test5");
    }

    @Test
    public void testDirCase6() throws IOException {
        testDirCase("test6");
    }

    @Test
    public void testDirCase7() throws IOException {
        testDirCase("test7");
    }

    @Test
    public void testDirCase8() throws IOException {
        testDirCase("test8");
    }

    private void testDirCase(final String caseName) throws IOException {
        testCase(expectedFile(caseName), tree(caseName));
    }

    private void testCase(final String expected, final String actual) {
        assertEquals(expected.trim(), actual.trim());
    }

    private String expectedFile(String caseName) throws IOException {
        return Files.lines(Paths.get("src/test/resources", caseName + ".txt"))
                .collect(Collectors.joining("\n"));

    }


    private String tree(final String... subPath) {
        return new FileTreeImpl().tree(Paths.get("src/test/resources", subPath)).orElse(null);
    }

}