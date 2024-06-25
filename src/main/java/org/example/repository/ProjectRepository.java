package org.example.repository;

import org.example.composite.ProjectComponent;
import java.util.List;

public interface ProjectRepository {
    void addComponent(ProjectComponent component);
    void removeComponent(ProjectComponent component);
    List<ProjectComponent> getComponents();
    ProjectComponent findByName(String name);
    void sortByName();
    void sortByStatus();
}
