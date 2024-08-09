package hk.edu.polyu.comp.comp2021.tms.model.bean;

/**
 * This class represents a composite task, which includes a list of subtasks.
 */
public class CompositeTask extends Task {
    private String subtasks;

    /**
     * Constructs a CompositeTask with the given name, description, and subtasks.
     *
     * @param name The name of the composite task.
     * @param description The description of the composite task.
     * @param subtasks The subtasks of the composite task, represented as a string.
     */
    public CompositeTask(String name, String description, String subtasks) {
        this.name = name;
        this.description = description;
        this.subtasks = subtasks;
    }

    /**
     * Gets the subtasks of the composite task.
     *
     * @return A string representing the subtasks of the composite task.
     */
    public String getSubtasks() {
        return subtasks;
    }

    /**
     * Sets the subtasks of the composite task.
     *
     * @param subtasks A string representing the new subtasks of the composite task.
     */
    public void setSubtasks(String subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "name : " + name + '\n' +
                "description : " + description + '\n' +
                "subtasks : " + subtasks;
    }
}
