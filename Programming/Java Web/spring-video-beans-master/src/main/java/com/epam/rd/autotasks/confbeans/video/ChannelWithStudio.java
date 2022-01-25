package com.epam.rd.autotasks.confbeans.video;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class ChannelWithStudio extends Channel {

    private VideoStudioImpl videoStudio;

    public ChannelWithStudio(VideoStudio videoStudio) {
        this.videoStudio = (VideoStudioImpl) videoStudio;
    }

    public Video produce() {
        Video video = new Video(videoStudio.getFranchiseName(), videoStudio.produce().getPubTime());
        this.addVideo(video);
        return video;
    }

    @Override
    public Stream<Video> videos() {
        for (int i = 0; i < videoStudio.getFranchiseSize() ; i++) {
            this.produce();
        }
        return super.videos().skip(super.videos().count() - videoStudio.getFranchiseSize());
    }
}