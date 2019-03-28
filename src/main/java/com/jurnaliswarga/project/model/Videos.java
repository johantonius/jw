package com.jurnaliswarga.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Videos{
    @Id
    @GeneratedValue
    private Long video_id;
    private String title;
    private String url;
    private String description;
    private String price;
    @Enumerated(value = EnumType.ORDINAL)
    private Region region;

    public Long getVideo_id() {
        return video_id;
    }
    public void setVideo_id(Long video_id) {
		this.video_id = video_id;
    }
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    @NotBlank(message = "isi ")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @NotBlank(message = "isi")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    @NotBlank(message = "password is required")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @NotBlank(message = "password is required")
    public String getDescription() {
        return description;
    }

    public void setDescripton(String description) {
        this.description = description;
    }
    @Lob
    private byte[] data;

//    public Videos() {
//
//    }
//
//    public Videos(String title,  byte[] data) {
//        this.title = title;
//        this.data = data;
//    }

}