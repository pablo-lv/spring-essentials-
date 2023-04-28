package com.plucas.spring.essentials.controllers;

import com.plucas.spring.essentials.entities.VideoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {


    private final VideoService videoService;

    public HomeController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", videoService.getVideos());
        return "index";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute Video newVideo) {
        videoService.create(newVideo);
        return  "redirect:/";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute VideoSearch search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);

        model.addAttribute("videos", searchResults);

        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(
            @ModelAttribute UniversalSearch search, Model model) {
        List<VideoEntity> searchResults =
                videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

}
