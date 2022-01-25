package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(ChannelWithVideoStudioConfig.class)
class ChannelWithVideoStudioConfigTest {

    @Autowired
    Map<String, Object> beans;

    @Autowired
    Channel channel;

    @Autowired
    VideoStudio studio;

    @Test
    void testBeans() {
        System.out.println(beans);
        assertTrue(beans.values().stream().noneMatch(Video.class::isInstance));
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
    void testStudio() {
        assertEquals(
                new Video("Cat & Curious 9", LocalDateTime.of(2017, 10, 18, 10, 0)),
                studio.produce()
        );
        assertEquals(
                new Video("Cat & Curious 10", LocalDateTime.of(2019, 10, 18, 10, 0)),
                studio.produce()
        );
    }

}