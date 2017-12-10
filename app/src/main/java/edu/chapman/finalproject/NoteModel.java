package edu.chapman.finalproject;

import org.joda.time.DateTime;

import java.util.UUID;

public class NoteModel {
    private String id;
    private String title;
    private String body;
    private DateTime date;

    NoteModel()
    {
        this.id = UUID.randomUUID().toString();
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    String getBody() {return body; }

    public void setTitle(String title)
    {
        this.title = title;
    }

    void setBody(String body)
    {
        this.body = body;
    }

    void setDate(DateTime date)
    {
        this.date = date;
    }

    public DateTime getDate()
    {
        return this.date;
    }
}
