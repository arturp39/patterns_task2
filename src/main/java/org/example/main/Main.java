package org.example.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.composite.Project;
import org.example.composite.ProjectComponent;
import org.example.composite.Status;
import org.example.composite.Task;
import org.example.observer.impl.Developer;
import org.example.reader.JsonReader;
import org.example.repository.ProjectRepository;
import org.example.repository.impl.ProjectRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        ProjectRepository repository = new ProjectRepositoryImpl();
        JsonReader jsonReader = new JsonReader();

        try {
            logger.info("Reading components from JSON file...");
            List<ProjectComponent> components = jsonReader.readComponentsFromFile("projects.json");
            for (ProjectComponent component : components) {
                repository.addComponent(component);
                logger.info("Added component: " + component.getName());
            }
        } catch (IOException e) {
            logger.error("Error reading from JSON file", e);
        }

        Developer dev1 = new Developer("Artur");
        Developer dev2 = new Developer("Roman");

        for (ProjectComponent component : repository.getComponents()) {
            if (component instanceof Project) {
                for (ProjectComponent task : ((Project) component).getTasks()) {
                    if (task instanceof Task) {
                        ((Task) task).addObserver(dev1);
                        ((Task) task).addObserver(dev2);
                        logger.info("Added observers to task: " + task.getName());
                    }
                }
            }
        }

        // Change the status of all tasks to DONE and notify observers
        for (ProjectComponent component : repository.getComponents()) {
            if (component instanceof Project) {
                for (ProjectComponent task : ((Project) component).getTasks()) {
                    if (task instanceof Task) {
                        ((Task) task).setStatus(Status.DONE);
                        logger.info("Changed status of task to DONE: " + task.getName());
                    }
                }
            }
        }

        // Display all projects and their tasks
        System.out.println("Displaying all projects and tasks:");
        displayProjects(repository);

        // Find a project by name
        String projectNameToFind = "Project Alpha";
        ProjectComponent foundProject = repository.findByName(projectNameToFind);
        if (foundProject != null) {
            logger.info("Found project by name '" + projectNameToFind + "': " + foundProject.getName());
        } else {
            logger.info("Project '" + projectNameToFind + "' not found.");
        }

        // Sort projects by name
        logger.info("Sorting projects by name:");
        repository.sortByName();
        displayProjects(repository);

        // Sort projects by status
        logger.info("Sorting projects by status:");
        repository.sortByStatus();
        displayProjects(repository);

        // Remove a project
        if (foundProject != null) {
            logger.info("Removing project: " + foundProject.getName());
            repository.removeComponent(foundProject);
            displayProjects(repository);
        }
    }

    private static void displayProjects(ProjectRepository repository) {
        for (ProjectComponent component : repository.getComponents()) {
            if (component instanceof Project project) {
                System.out.println("Project: " + project.getName() + ", Description: " + project.getDescription() + ", Status: " + project.getStatus());
                for (ProjectComponent taskComponent : project.getTasks()) {
                    if (taskComponent instanceof Task task) {
                        System.out.println("  Task: " + task.getName() + ", Description: " + task.getDescription() + ", Status: " + task.getStatus() + ", Deadline: " + task.getDeadline());
                    }
                }
            }
        }
    }
}
