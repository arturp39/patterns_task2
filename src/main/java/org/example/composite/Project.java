package org.example.composite;

import java.util.ArrayList;
import java.util.List;

public class Project implements ProjectComponent {
    private List<ProjectComponent> tasks = new ArrayList<>();
    private String name;
    private String description;
    private Status status;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.TO_DO;
    }

    @Override
    public void add(ProjectComponent component) {
        if (component instanceof Task) {
            tasks.add(component);
        } else {
            throw new UnsupportedOperationException("Projects can only contain tasks.");
        }
        updateStatus();
    }

    @Override
    public void remove(ProjectComponent component) {
        tasks.remove(component);
        updateStatus();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Status getStatus() {
        updateStatus();
        return status;
    }

    private void updateStatus() {
        boolean allToDo = true;
        boolean allDone = true;

        for (ProjectComponent task : tasks) {
            Status status = task.getStatus();
            if (status != Status.TO_DO) {
                allToDo = false;
            }
            if (status != Status.DONE) {
                allDone = false;
            }
        }
        if (allDone) {
            this.status = Status.DONE;
        } else if (allToDo) {
            this.status = Status.TO_DO;
        } else {
            this.status = Status.IN_PROGRESS;
        }
    }

    public List<ProjectComponent> getTasks() {
        return tasks;
    }
}
