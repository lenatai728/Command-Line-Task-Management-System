package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.bean.Task;

import java.util.List;

/**
 * The Result class is a utility class used to store the result of an operation along with
 * any associated message. It is typically used for return values and for printing to UI.
 */
public class Result {
    private boolean success;
    private String message;
    private double duration;

    private List<Task> tasks;

    private boolean close = true;

    private String uiMessage;

    /**
     * Constructs a new Result with the given success status and message.
     *
     * @param success the success status
     * @param message the associated message
     */
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Constructs a new Result with the given success status, message, and UI message.
     *
     * @param success the success status
     * @param message the associated message
     * @param uiMessage the associated UI message
     */
    public Result(boolean success, String message, String uiMessage) {
        this.success = success;
        this.message = message;
        this.uiMessage = uiMessage;
    }

    /**
     * Constructs a new Result with the given success status and message.
     *
     * @param success the success status
     * @param duration the task duration
     */
    public Result(boolean success, double duration) {
        this.success = success;
        this.duration = duration;
    }

    /**
     * Constructs a new Result with the given success status, message, and UI message.
     *
     * @param success the success status
     * @param duration the task duration
     * @param uiMessage the associated UI message
     */
    public Result(boolean success, double duration, String uiMessage) {
        this.success = success;
        this.duration = duration;
        this.uiMessage = uiMessage;
    }

    /**
     * Constructs a new Result with the given success status and message.
     *
     * @param success the success status
     * @param tasks List of tasks
     */
    public Result(boolean success, List<Task> tasks) {
        this.success = success;
        this.tasks = tasks;
    }

    /**
     * Constructs a new Result with the given success status, message, and UI message.
     *
     * @param success the success status
     * @param tasks List of tasks
     * @param uiMessage the associated UI message
     */
    public Result(boolean success, List<Task> tasks, String uiMessage) {
        this.success = success;
        this.tasks = tasks;
        this.uiMessage = uiMessage;
    }

    /**
     * Constructs a new Result with the given success status and close flag.
     *
     * @param success the success status
     * @param close the close flag
     */
    public Result(boolean success, boolean close) {
        this.success = success;
        this.close = close;
    }

    /**
     * Constructs a new Result with the given success status, close flag, and message.
     *
     * @param success the success status
     * @param close the close flag
     * @param message the associated message
     */
    public Result(boolean success, boolean close, String message) {
        this.success = success;
        this.close = close;
        this.message = message;
    }

    /**
     * Returns the success status of the operation.
     *
     * @return the success status
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success status of the operation.
     *
     * @param success the success status to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the associated message of the operation.
     *
     * @return the associated message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the associated message of the operation.
     *
     * @param message the associated message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the duration of the task
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Returns the tasks associated with the operation.
     *
     * @return the associated tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the close flag of the operation.
     *
     * @return the close flag
     */
    public boolean isClose() {
        return close;
    }

    /**
     * Sets the close flag of the operation.
     *
     * @param close the close flag to set
     */
    public void setClose(boolean close) {
        this.close = close;
    }

    /**
     * Returns the UI message associated with the operation.
     *
     * @return the UI message
     */
    public String getUiMessage() {
        return uiMessage;
    }

    /**
     * Sets the UI message associated with the operation.
     *
     * @param uiMessage the UI message to set
     */
    public void setUiMessage(String uiMessage) {
        this.uiMessage = uiMessage;
    }
}
