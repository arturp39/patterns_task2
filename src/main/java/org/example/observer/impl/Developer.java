package org.example.observer.impl;

import org.example.composite.Task;
import org.example.observer.Observer;

public class Developer implements Observer {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    @Override
    public void update(Task task) {
        System.out.println("Developer " + name + " notified. Task " + task.getName() + " status: " + task.getStatus());
    }
}
