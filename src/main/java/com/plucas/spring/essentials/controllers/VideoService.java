package com.plucas.spring.essentials.controllers;

import com.plucas.spring.essentials.entities.VideoEntity;
import com.plucas.spring.essentials.repositories.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

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

    public Video createVideo(Video video) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(video);
        this.videos = List.copyOf(extend);
        return video;
    }

    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.name())
            && StringUtils.hasText(videoSearch.description())) {
            return videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase(videoSearch.name(), videoSearch.description());
        }

        if (StringUtils.hasText(videoSearch.name())) {
            return videoRepository.findByNameContainsIgnoreCase(videoSearch.name());
        }

        if (StringUtils.hasText(videoSearch.description())) {
            return videoRepository.findByDescriptionContainsIgnoreCase(videoSearch.description());
        }

//        Sort.TypedSort<Video> video = Sort.sort(Video.class);
//        Sort sort = Sort.by(Video::name).ascending().and(Sort.by(Video::).descending());

        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe, //
                ExampleMatcher.matchingAny() //
                        .withIgnoreCase() //
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return videoRepository.findAll(example);
    }

//    @PostConstruct
//    void initDatabase() {
//        videoRepository.save(new VideoEntity("Need HELP with your SPRING BOOT 3 App?",
//                "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."));
//        videoRepository.save(new VideoEntity("Don't do THIS to your own CODE!",
//                "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"));
//        videoRepository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",
//                "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
//    }
}
