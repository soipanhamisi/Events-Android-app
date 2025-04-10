package com.example.myeventsapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class events {
    private String eventName;
    private String eventDescription;
    private String venue;
    private String date;
    private String time;

    public events() {
    }

    public events(String time, String date, String venue, String eventDescription, String eventName) {
        this.time = time;
        this.date = date;
        this.venue = venue;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
