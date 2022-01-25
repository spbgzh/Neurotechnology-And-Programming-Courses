package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(ChannelWithInjectedPrototypeVideoConfig.class)
class ChannelWithInjectedPrototypeVideoConfigTest {

    @Autowired(required = false)
    Map<String, VideoStudio> beanStudioBeans;

    @Autowired
    Channel channel;

    @Autowired
    Video video;

    @Autowired
    Video otherVideo;

    @Autowired
    Video anotherOtherVideo;

    @Autowired
    ApplicationContext context;

    String expectedVideoName = "Cat Failure Compilation";

    @Test
    void testBeans() {
        assertTrue(beanStudioBeans == null);
    }

    @Test
    void testVideos() {
        assertEquals(expectedVideoName, video.getName());
        assertEquals(expectedVideoName, otherVideo.getName());
        assertEquals(expectedVideoName, anotherOtherVideo.getName());
        assertNotEquals(video, otherVideo);
        assertNotEquals(video, anotherOtherVideo);

        final List<Video> weeklyVideos = channel.videos().limit(7)
                .collect(Collectors.toList());

        for (Video v : weeklyVideos) {
            assertEquals(expectedVideoName, v.getName());
        }

        assertEquals(
                Period.ofDays(6),
                Period.between(
                        weeklyVideos.get(0).getPubTime().toLocalDate(),
                        weeklyVideos.get(6).getPubTime().toLocalDate()
                )
        );

        final List<Video> anotherWeeklyVideos = channel.videos().limit(7)
                .collect(Collectors.toList());

        for (Video v : anotherWeeklyVideos) {
            assertEquals(expectedVideoName, v.getName());
        }

        assertEquals(
                Period.ofDays(6),
                Period.between(
                        anotherWeeklyVideos.get(0).getPubTime().toLocalDate(),
                        anotherWeeklyVideos.get(6).getPubTime().toLocalDate()
                )
        );

        assertEquals(
                Period.ofDays(13),
                Period.between(
                        weeklyVideos.get(0).getPubTime().toLocalDate(),
                        anotherWeeklyVideos.get(6).getPubTime().toLocalDate()
                )
        );

        final Video videoBeanAfterwards = context.getBean(Video.class);
        assertEquals(expectedVideoName, videoBeanAfterwards.getName());
        assertNotEquals(video, videoBeanAfterwards);

        assertEquals(
                Period.ofDays(14),
                Period.between(
                        weeklyVideos.get(0).getPubTime().toLocalDate(),
                        videoBeanAfterwards.getPubTime().toLocalDate()
                )
        );

        assertEquals(
                Period.ofDays(1),
                Period.between(
                        anotherWeeklyVideos.get(6).getPubTime().toLocalDate(),
                        videoBeanAfterwards.getPubTime().toLocalDate()
                )
        );
    }
}