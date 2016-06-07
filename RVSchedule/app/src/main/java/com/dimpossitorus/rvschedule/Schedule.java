package com.dimpossitorus.rvschedule;

/**
 * Created by Dimpos Sitorus on 07/06/2016.
 */
public class Schedule {
    private String title;
    private String description;

    public Schedule(String _title, String _description) {
        title = _title;
        description = _description;
    }

    public Schedule () {
        title = "Title";
        description = "Description";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle (String _title) {
        title = _title;
    }

    public void setDescription (String _description) {
        description = _description;
    }

    public String toString() {
        return "{"+title+", "+description+"}";
    }

}
