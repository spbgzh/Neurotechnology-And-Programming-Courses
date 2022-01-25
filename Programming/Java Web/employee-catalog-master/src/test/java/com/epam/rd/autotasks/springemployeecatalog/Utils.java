package com.epam.rd.autotasks.springemployeecatalog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

class Utils {

    private static Path testResPath = Paths.get("src", "test", "resources");

    static String readTestResourceFile(final Path path) {
        try {
            return Files.lines(testResPath.resolve(path), StandardCharsets.UTF_8)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    interface TestCaseRoutine {
        void doCase(Path testCase) throws Exception;
    }

    static void testCaseRoutine(Path testCase, TestCaseRoutine routine) throws Exception {
        try {
            routine.doCase(testCase);
        } catch (AssertionError error) {
            throw new MvcTestsError("Failed on case: " + testCase, error);
        }
    }

    private static void writeTestResourceFile(final Path path, String content) {
        try {
            Files.write(testResPath.resolve(path), Collections.singletonList(content), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
