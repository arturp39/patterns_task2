package org.example.composite;

import org.example.exception.LeafOperationException;
import org.example.observer.Observable;
import org.example.observer.Observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements ProjectComponent, Observable {
    private final String name;
    private final String description;
    private Status status;
    private Date deadline;
    private List<Observer> observers = new ArrayList<>();

    public Task(String name, String description, Date deadline) {
        this.name = name;
        this.description = description;
        this.status = Status.TO_DO;
        this.deadline = deadline;
    }

    public Task(String name, String description, Status status, Date deadline) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    public void initObservers() {
        observers = new ArrayList<>();
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
        notifyObservers();
    }

    @Override
    public void add(ProjectComponent component) {
        throw new LeafOperationException("Cannot add to a task");
    }

    @Override
    public void remove(ProjectComponent component) {
        throw new LeafOperationException("Cannot remove from a task");
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
