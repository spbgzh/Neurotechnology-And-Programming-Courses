package com.epam.rd.autocode.observer.git;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Event {
    private Type type;
    private String branch;
    private List<Commit> commits;

    public Event(final Type type, final String branch, final List<Commit> commits) {
        this.type = type;
        this.branch = branch;
        this.commits = commits;
    }

    Type type() {
        return type;
    }

    String branch() {
        return branch;
    }

    List<Commit> commits() {
        return commits;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Event event = (Event) o;

        if (type != event.type) return false;
        if (!Objects.equals(branch, event.branch)) return false;
        return Objects.equals(commits, event.commits);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (commits != null ? commits.hashCode() : 0);
        return result;
    }

    enum Type {
        COMMIT,
        MERGE
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                .add(type.toString())
                .add(branch)
                .add(commits.toString())
                .toString();
    }
}
