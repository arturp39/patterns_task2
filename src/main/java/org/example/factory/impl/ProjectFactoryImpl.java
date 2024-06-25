package org.example.factory.impl;

import org.example.composite.Project;
import org.example.composite.ProjectComponent;
import org.example.composite.Task;
import org.example.composite.Status;
import org.example.factory.ProjectFactory;

import java.util.Date;

public class ProjectFactoryImpl implements ProjectFactory {
    @Override
    public ProjectComponent createProject(String name, String description) {
        return new Project(name, description);
    }

    @Override
    public ProjectComponent createTask(String name, String description, Date deadline) {
        return new Task(name, description, deadline);
    }

    @Override
    public ProjectComponent createTask(String name, String description, Status status, Date deadline) {
        return new Task(name, description, status, deadline);
    }
}
