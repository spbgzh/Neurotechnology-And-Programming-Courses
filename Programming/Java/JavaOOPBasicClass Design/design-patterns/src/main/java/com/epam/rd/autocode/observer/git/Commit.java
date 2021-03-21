package com.epam.rd.autocode.observer.git;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class Commit{
    private String author;
    private String[] changes;

    public Commit(final String author, final String[] changes) {
        this.author = author;
        this.changes = changes;
    }

    String author(){
        return author;
    }

    String[] changes(){
        return changes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Commit commit = (Commit) o;

        if (!Objects.equals(author, commit.author)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(changes, commit.changes);
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(changes);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Commit.class.getSimpleName() + "[", "]")
                .add(author)
                .add(Arrays.toString(changes))
                .toString();
    }
}
