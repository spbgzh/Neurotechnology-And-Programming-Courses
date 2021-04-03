package com.efimchick.ifmo.io.filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.IntStream;

public class Util {


    public void gen() {
        Random rand = new Random(78954);
        final Path dir = Paths.get("src/test/resources", "test8");
        final int dirs = 3 + rand.nextInt(5);
        genSubDirs(dir, dirs, 0, 5, rand);
    }

    private void genSubDirs(Path dir, int dirsToGen, int level, int maxLevel, Random rand) {

        IntStream.range(0, dirsToGen)
                .mapToObj(i -> dir.resolve(randomString(rand, 3 + i)))
                .forEach(
                        subd -> {
                            try {
                                Files.createDirectories(subd);
                                if (rand.nextBoolean() && level != maxLevel) {
                                    genSubDirs(
                                            subd,
                                            3 + rand.nextInt(5),
                                            level + 1,
                                            maxLevel,
                                            rand
                                    );
                                }

                                if (rand.nextBoolean()) {
                                    genFiles(subd,
                                            1 + rand.nextInt(5),
                                            rand);
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
    }

    private void genFiles(final Path dir, final int files, final Random rand) {
        IntStream.range(0, files)
                .mapToObj(i -> dir.resolve(randomString(rand, 3 + i) + ".txt"))
                .forEach(file -> {
                    try {
                        Files.createFile(file);
                        Files.writeString(file, randomString(rand, 8 * (1 + rand.nextInt(10))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private String randomString(Random rand, int len) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        return rand.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
