package org.example.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.composite.ProjectComponent;
import org.example.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private static final Logger logger = LogManager.getLogger();
    private final List<ProjectComponent> components = new ArrayList<>();

    @Override
    public void addComponent(ProjectComponent component) {
        components.add(component);
        logger.info("Added component: " + component.getName());
    }

    @Override
    public void removeComponent(ProjectComponent component) {
        components.remove(component);
        logger.info("Removed component: " + component.getName());
    }

    @Override
    public List<ProjectComponent> getComponents() {
        return components;
    }

    @Override
    public ProjectComponent findByName(String name) {
        for (ProjectComponent component : components) {
            if (component.getName().equals(name)) {
                logger.info("Found component by name: " + name);
                return component;
            }
        }
        logger.info("Component not found by name: " + name);
        return null;
    }

    @Override
    public void sortByName() {
        components.sort(Comparator.comparing(ProjectComponent::getName));
        logger.info("Sorted components by name.");
    }

    @Override
    public void sortByStatus() {
        components.sort(Comparator.comparing(ProjectComponent::getStatus));
        logger.info("Sorted components by status.");
    }
}
