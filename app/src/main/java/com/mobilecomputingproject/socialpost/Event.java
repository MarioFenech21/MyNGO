package com.mobilecomputingproject.socialpost;

public class Event {
    private String title;
    private String time;

    private String link;


    public Event(String title, String time, String link) {
        this.title = title;
        this.time = time;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getLink() {
        return link;
    }
}



