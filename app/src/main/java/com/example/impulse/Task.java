package com.example.impulse;

import java.util.ArrayList;

public class Task {
    private String title;

    public Task(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    private static int lastTaskID = 0;

    public static ArrayList<Task> createContactsList(int numContacts) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        for (int i = 1; i <= numContacts; i++) {
            tasks.add(new Task("task"));
        }

        return tasks;
    }


}
