package com.plucas.spring.essentials.controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    List<Video> videos = List.of(
            new Video("Need Help with your spring boot 3"),
            new Video("Don't do this to your own code!"),
            new Video("Secrets to fix broken code")
    );

    public List<Video> getVideos() {
        return videos;
    }

    public List<Video> create(Video video) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(video);
        this.videos = List.copyOf(extend);
        return videos;
    }
}
