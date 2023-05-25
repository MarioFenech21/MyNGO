package com.mobilecomputingproject.socialpost;


import java.util.Map;

public class Post {
    private String imageUrl;
    private String description;
    private String link;



    public Post(String imageUrl, String description, String link) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }




}

