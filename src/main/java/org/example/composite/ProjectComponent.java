package org.example.composite;

public interface ProjectComponent {
    void add(ProjectComponent component);
    void remove(ProjectComponent component);
    String getName();
    String getDescription();
    Status getStatus();
}
