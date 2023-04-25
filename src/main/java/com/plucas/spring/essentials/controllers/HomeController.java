package com.plucas.spring.essentials.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    record Video(String name) {}

    List<Video> videos = List.of(
            new Video("Need Help with your spring boot 3"),
            new Video("Don't do this to your own code!"),
            new Video("Secrets to fix broken code")
    );

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", videos);
        return "index";
    }
}
