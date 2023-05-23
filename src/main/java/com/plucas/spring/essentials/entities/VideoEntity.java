package com.plucas.spring.essentials.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class VideoEntity {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String name;


    private String description;

    public VideoEntity() {
    }

    public VideoEntity(String username, String name, String description) {
        this.username = username;
        this.description = description;
        this.name = name;
    }

    public VideoEntity(String name, String description) {
        this.id = null;
        this.description = description;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
