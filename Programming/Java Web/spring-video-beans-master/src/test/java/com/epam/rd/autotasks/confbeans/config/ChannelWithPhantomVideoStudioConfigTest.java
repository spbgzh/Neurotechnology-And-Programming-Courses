package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(ChannelWithPhantomVideoStudioConfig.class)
class ChannelWithPhantomVideoStudioConfigTest {

    @Autowired(required = false)
    Map<String, VideoStudio> beanStudioBeans;

    @Autowired
    Channel channel;

    @Autowired
    Video nextRelease;

    @Autowired
    Video nextNextRelease;

    @Test
    void testBeans() {
        assertTrue(beanStudioBeans == null);
    }

    @Test
    void testChannelNames() {
        assertEquals(
                Set.of(
                        "Cat & Curious 1",
                        "Cat & Curious 2",
                        "Cat & Curious 3",
                        "Cat & Curious 4",
                        "Cat & Curious 5",
                        "Cat & Curious 6",
                        "Cat & Curious 7",
                        "Cat & Curious 8"),
                channel.videos()
                        .map(Video::getName)
                        .collect(toSet())
        );
    }

    @Test
    void testChannelPubTimes() {
        assertEquals(
                Set.of(

                        "2001-10-18T10:00",
                        "2003-10-18T10:00",
                        "2005-10-18T10:00",
                        "2007-10-18T10:00",
                        "2009-10-18T10:00",
                        "2011-10-18T10:00",
                        "2013-10-18T10:00",
                        "2015-10-18T10:00"),
                channel.videos()
                        .map(Video::getPubTime)
                        .map(LocalDateTime::toString)
                        .collect(toSet())
        );
    }

    @Test
    void testNextReleases() {
        final Set<Video> expectedNextReleases = new HashSet<>(Set.of(
                new Video("Cat & Curious 9", LocalDateTime.of(2017, 10, 18, 10, 0)),
                new Video("Cat & Curious 10", LocalDateTime.of(2019, 10, 18, 10, 0)),
                new Video("Cat & Curious 11", LocalDateTime.of(2021, 10, 18, 10, 0)),
                new Video("Cat & Curious 12", LocalDateTime.of(2023, 10, 18, 10, 0)),
                new Video("Cat & Curious 13", LocalDateTime.of(2025, 10, 18, 10, 0)),
                new Video("Cat & Curious 14", LocalDateTime.of(2027, 10, 18, 10, 0))
        ));
        expectedNextReleases.retainAll(Set.of(nextRelease, nextNextRelease));

        assertEquals(expectedNextReleases, Set.of(nextRelease, nextNextRelease));
    }

}