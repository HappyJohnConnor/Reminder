package com.example.author.reminder3v;

/**
 * Created by author on 2017/08/23.
 */

public class ReminderData {
    private String title;
    private String body;


    public ReminderData(String title, String body) {
        this.title = title;

        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
