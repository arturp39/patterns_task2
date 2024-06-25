package org.example.factory;

import org.example.composite.ProjectComponent;
import org.example.composite.Status;

import java.util.Date;

public interface ProjectFactory {
    ProjectComponent createProject(String name, String description);
    ProjectComponent createTask(String name, String description, Date deadline);
    ProjectComponent createTask(String name, String description, Status status, Date deadline);
}
