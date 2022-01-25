package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Period;

@Configuration
public class ChannelWithVideoStudioConfig {

    @Bean
    public VideoStudio videoStudio() {
        return new VideoStudioImpl("Cat & Curious", 8, LocalDateTime.of(2001, 10, 18, 10, 0), Period.ofYears(2));
    }

    @Bean
    public Channel channel(VideoStudio studio) {
        VideoStudioImpl videoStudio = (VideoStudioImpl) studio;
        Channel channel = new Channel();
        while (videoStudio.getLastPart() <= videoStudio.getFranchiseSize()) {
            channel.addVideo(videoStudio.produce());
        }
        return channel;
    }
}