package org.example.observer;

import org.example.composite.Task;

public interface Observer {
    void update(Task task);
}
