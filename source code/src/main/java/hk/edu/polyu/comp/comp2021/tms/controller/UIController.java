package hk.edu.polyu.comp.comp2021.tms.controller;

import hk.edu.polyu.comp.comp2021.tms.model.Model;
import hk.edu.polyu.comp.comp2021.tms.model.Result;

/**
 * The UIController class manages user interactions for the UI.
 */
public class UIController {
    private Model core = new Model();

    /**
     * REQ1 can redo
     * @param name A string representation of the name of the task
     * @param description A string representation of the description of the task
     * @param duration A string representation of the duration of the task
     * @param prerequisites A string representation of the prerequisites of the task
     *
     * @return Result
     */
    public Result createSimpleTask(String name, String description, String duration, String prerequisites) {
        core.setLastCommand("CreateSimpleTask");
        return core.createSimpleTask(name, description, duration, prerequisites);
    }

    /**
     * REQ2  can redo
     * @param name A string representation of the name of the task
     * @param description A string representation of the description of the task
     * @param subTask A string representation of the subtask of the task
     *
     * @return Result
     */
    public Result createCompositeTask(String name, String description, String subTask) {
        core.setLastCommand("CreateCompositeTask");
        return core.createCompositeTask(name, description, subTask);
    }

    /**
     * REQ3 can redo
     * @param name A string representation of the name of the task to be deleted
     *
     * @return Result
     */
    public Result deleteTask(String name) {
        core.setLastCommand("DeleteTask");
        return core.deleteTask(name);
    }

    /**
     * REQ4 can redo
     * @param name A string representation of the new name of the task
     * @param newValue A string representation of the new value of the task
     * @param property A string representation of the new property of the task
     *
     * @return Result
     */
    public Result changeTask(String name, String property, String newValue) {
        core.setLastCommand("ChangeTask");
        return core.changeTask(name, property, newValue);
    }

    /**
     * REQ5
     * @param name A string representation of the name of the task that need to be printed
     *
     * @return Result
     */
    public Result printTask(String name) {
        return core.printTask(name);
    }

    /**
     * REQ6
     * @return Result
     */
    public Result printAllTasks() {
        return core.printAllTasks();
    }

    /**
     * REQ7
     * @param name A string representation of the name of the task that need to be reported
     *
     * @return Result
     */
    public Result reportDuration(String name) {
        return core.reportDuration(name);
    }

    /**
     * REQ8
     * @param name A string representation of the name of the task that need to be reported
     *
     * @return Result
     */
    public Result reportEarliestFinishTime(String name) {
        return core.reportEarliestFinishTime(name);
    }

    /**
     * REQ9 can redo
     * @param name A string representation of the name of the criterion
     * @param property A string representation of the property of the criterion
     * @param op A string representation of the operator of the criterion
     * @param value A string representation of the value of the criterion
     *
     * @return Result
     */
    public Result defineBasicCriterion(String name, String property, String op, String value) {
        core.setLastCommand("DefineBasicCriterion");
        return core.defineBasicCriterion(name, property, op, value);
    }

    /**
     * REQ11 can redo
     * @param name A string representation of the name of the exist criterion
     * @param negatedName A string representation of the name of the negatedCriterion
     *
     * @return Result
     */
    public Result defineNegatedCriterion(String name, String negatedName) {
        core.setLastCommand("DefineNegatedCriterion");
        return core.defineNegatedCriterion(name, negatedName);
    }

    /**
     * REQ11 can redo
     * @param name A string representation of the name of the binaryCriterion
     * @param name2 A string representation of the name of the existed criterion
     * @param logicOp A string representation of the operator of the binaryCriterion
     * @param name3 A string representation of the name of the existed criterion
     *
     * @return Result
     */
    public Result defineBinaryCriterion(String name, String name2, String logicOp, String name3) {
        core.setLastCommand("DefineBinaryCriterion");
        return core.defineBinaryCriterion(name, name2, logicOp, name3);
    }

    /**
     * REQ12
     * @return Result
     */
    public Result printAllCriterion() {
        return core.printAllCriterion();
    }

    /**
     * REQ13
     * @param name A string representation of the name of the criterion
     *
     * @return Result
     */
    public Result search(String name) {
        return core.searchName(name);
    }

    /**
     * REQ14
     * @param path A string representation of the path
     *
     * @return Result
     */
    public Result store(String path) {
        return core.store(path);
    }

    /**
     * REQ15
     * @param path A string representation of the path
     *
     * @return Result
     */
    public Result load(String path) {
        return core.load(path);
    }

    /**
     * Bonus
     * @return Result
     */
    public Result undo() {
        return core.undo();
    }
    /**
     * Bonus
     * @return Result
     */
    public Result redo() {
        return core.redo();
    }

}
