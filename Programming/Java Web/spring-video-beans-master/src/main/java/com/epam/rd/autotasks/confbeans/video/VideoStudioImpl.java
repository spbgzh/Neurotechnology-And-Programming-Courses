package com.epam.rd.autotasks.confbeans.video;

import java.time.LocalDateTime;
import java.time.Period;

public class VideoStudioImpl implements VideoStudio {

    private final String franchiseName;
    private final int franchiseSize;
    private int lastPart;
    private final LocalDateTime firstPubTime;
    private final Period period;

    public VideoStudioImpl(String franchiseName, int franchiseSize, LocalDateTime firstPubTime, Period period) {
        this.franchiseName = franchiseName;
        this.franchiseSize = franchiseSize;
        this.lastPart = 1;
        this.firstPubTime = firstPubTime;
        this.period = period;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public int getFranchiseSize() {
        return franchiseSize;
    }

    public int getLastPart() {
        return lastPart;
    }

    @Override
    public Video produce() {
        Video video = new Video(franchiseName + " " + lastPart, firstPubTime.plus(period.multipliedBy(lastPart - 1)));
        lastPart++;
        return video;
    }
}