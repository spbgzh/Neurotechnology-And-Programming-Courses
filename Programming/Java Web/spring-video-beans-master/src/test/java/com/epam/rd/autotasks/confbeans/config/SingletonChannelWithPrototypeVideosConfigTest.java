package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(SingletonChannelWithPrototypeVideosConfig.class)
class SingletonChannelWithPrototypeVideosConfigTest {

    @Autowired
    Map<String, Video> videoBeans;

    @Autowired
    @Qualifier("video1")
    Video video1;

    @Autowired
    Channel channel;

    @Autowired
    Channel channelShadow;

    @Test
    void testVideoNames() {
        assertEquals(
                Set.of(
                        "How to boil water",
                        "How to build a house",
                        "How to escape solitude"),
                videoBeans.values().stream()
                        .map(Video::getName)
                        .collect(toSet())
        );
    }

    @Test
    void testVideoPubTimes() {
        assertEquals(
                Set.of(

                        "2020-10-10T10:10",
                        "2020-10-10T10:11",
                        "2020-10-10T10:12"),
                videoBeans.values().stream()
                        .map(Video::getPubTime)
                        .map(LocalDateTime::toString)
                        .collect(toSet())
        );
    }

    @Test
    void testVideo1() {
        assertEquals(
                new Video(
                        "How to boil water",
                        LocalDateTime.of(2020, 10, 10, 10, 10)),
                video1);

        assertNotSame(video1, videoBeans.get("video1"));
    }

    @Test
    void testChannel() {
        assertEquals(
                new ArrayList<>(videoBeans.values()),
                channel.videos().collect(toList()));
        assertTrue(channel.videos().noneMatch(v -> video1 == v));

        assertSame(channel, channelShadow);
    }

}