package com.teamtreehouse.techdegrees.Model;

public class Todo {
    private int id;
    private String name;
    private boolean isCompleted;

    public Todo(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("Todo{id=%d, name='%s', isCompleted=%b}", id, name, isCompleted);
    }

}
