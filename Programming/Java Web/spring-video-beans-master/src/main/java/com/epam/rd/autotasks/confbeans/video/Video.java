package com.epam.rd.autotasks.confbeans.video;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Video {
    private final String name;
    private final LocalDateTime pubTime;

    public Video(final String name, final LocalDateTime pubTime) {
        checkNotNull(name);
        checkNotNull(pubTime);
        this.name = name;
        this.pubTime = pubTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getPubTime() {
        return pubTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Video video = (Video) o;

        if (!name.equals(video.name)) return false;
        return pubTime.equals(video.pubTime);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pubTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Video.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("pubTime=" + pubTime)
                .toString();
    }
}
