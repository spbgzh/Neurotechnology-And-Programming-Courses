package com.epam.rd.autocode;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NoTouchyTouchyDomainClassesTests {

    @Test
    public void testNoChangesToReferenceClasses() throws Exception {
        final ImmutableMap<String, String> src2Ref = ImmutableMap.<String, String>builder()
                .put("src/main/java/com/epam/rd/autocode/domain/Department.java", "src/test/resources/ref/Department.java")
                .put("src/main/java/com/epam/rd/autocode/domain/Employee.java", "src/test/resources/ref/Employee.java")
                .put("src/main/java/com/epam/rd/autocode/domain/FullName.java", "src/test/resources/ref/FullName.java")
                .put("src/main/java/com/epam/rd/autocode/domain/Position.java", "src/test/resources/ref/Position.java")
                .put("src/main/java/com/epam/rd/autocode/ConnectionSource.java", "src/test/resources/ref/ConnectionSource.java")
                .put("src/main/resources/init-ddl.sql", "src/test/resources/ref/init-ddl.sql")
                .put("src/main/resources/init-dml.sql", "src/test/resources/ref/init-dml.sql")
                .build();

        src2Ref.forEach((src, ref) -> assertSourceEqualsReference(Paths.get(src), Paths.get(ref)));
    }

    private void assertSourceEqualsReference(final Path src, final Path ref) {
        try {
            final List<String> refLines = Files.readAllLines(src);
            final List<String> srcLines = Files.readAllLines(ref);
            assertEquals(src + " was modified", refLines, srcLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
