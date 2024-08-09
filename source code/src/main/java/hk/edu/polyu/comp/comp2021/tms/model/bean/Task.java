package hk.edu.polyu.comp.comp2021.tms.model.bean;

import java.io.Serializable;

/**
 * Represents a Task in the Task Management System.
 * A Task is marked by its name, description, duration, and prerequisites.
 * Implements Serializable for object storage and retrieval.
 */
public class Task implements Serializable {
    /**
     * only english letters and digits
     * cannot start with digits
     * may contain at most eight characters
     */
    protected String name;
    /**
     * contain english letters, digits, and the hyphen letter(-)
     */
    protected String description;
    /**
     * a positive real number,
     * and it gives the minimum amount of time in hours required to complete the task
     */
    protected double duration;
    /**
     * is a comma-separated list of task names
     * each task name in the list should be defined already
     * and a task can only start when all its prerequisite tasks have finished
     * An empty list is denoted using a single comma(,)
     */
    protected String prerequisites;

    /**
     * Default constructor that constructs a new Task with no name, no description,
     * zero duration and no prerequisites.
     */
    public Task() {
    }

    /**
     * Constructs a new Task with the specified name, description,
     * duration, and prerequisites.
     *
     * @param name the name of the task
     * @param description the description of the task
     * @param duration the duration of the task
     * @param prerequisites the prerequisites of the task
     */
    public Task(String name, String description, double duration, String prerequisites) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.prerequisites = prerequisites;
    }

    /**
     * Returns the name of this task.
     *
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this task.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of this task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this task.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the duration of this task.
     *
     * @return the duration of the task
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the duration of this task.
     *
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Returns the prerequisites of this task.
     *
     * @return the prerequisites of the task
     */
    public String getPrerequisites() {
        return prerequisites;
    }

    /**
     * Sets the prerequisites of this task.
     *
     * @param prerequisites the prerequisites to set
     */
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return "name : " + name + '\n' +
                "description : " + description + '\n' +
                "duration : " + duration + '\n' +
                "prerequisites : " + prerequisites;
    }
}
