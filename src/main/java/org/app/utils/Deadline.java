package org.app.utils;

import java.util.UUID;

public class Deadline {
    private UUID id = UUID.randomUUID();
    private String name;
    private String date;
    private String time;
    private String isFinished;

    public Deadline(String name, String date, String time, String isFinished) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.isFinished = isFinished;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }
}

