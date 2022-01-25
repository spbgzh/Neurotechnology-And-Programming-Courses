package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.ChannelWithStudio;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.Period;

@Configuration
public class ChannelWithInjectedPrototypeVideoConfig {

    VideoStudioImpl videoStudio = new VideoStudioImpl("Cat Failure Compilation", 7, LocalDateTime.of(2001, 10, 18, 10, 0), Period.ofDays(1));

    @Bean
    @Scope("prototype")
    public Video video() {
        return channel().produce();
    }

    @Bean
    @Scope("singleton")
    public ChannelWithStudio channel() {
        return new ChannelWithStudio(videoStudio);
    }
}
