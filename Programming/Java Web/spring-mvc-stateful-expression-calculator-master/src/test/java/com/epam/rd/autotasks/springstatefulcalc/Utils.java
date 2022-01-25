package com.epam.rd.autotasks.springstatefulcalc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class Utils {

    private static Stream<String> uncheckedLines(final Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean findInSource(final String s) throws IOException {
        return Files.walk(Paths.get("src", "main", "java"))
                .filter(p -> p.getFileName().toString().endsWith(".java"))
                .flatMap(Utils::uncheckedLines)
                .anyMatch(line -> line.contains(s));
    }
}
