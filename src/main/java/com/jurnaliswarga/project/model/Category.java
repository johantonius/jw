package com.jurnaliswarga.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Category{
    @Id
    @GeneratedValue
    private String category_id;
    private String name;


    public String getName() {
        return name;
    }


    public String getCategory_id() {
        return category_id;
    }


    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }


    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    private Set<Videos> videos;

    public Set<Videos> getVideos() {
        return videos;
    }

    public void setVideos(Set<Videos> videos) {
        this.videos = videos;
    }
}