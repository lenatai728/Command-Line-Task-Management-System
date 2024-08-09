package hk.edu.polyu.comp.comp2021.tms.controller;

import hk.edu.polyu.comp.comp2021.tms.model.Model;
import hk.edu.polyu.comp.comp2021.tms.model.Result;

/**
 * The CommandController class represents a controller in the MVC pattern.
 * It controls the model and the view
 */
public class CommandController {
    private Model core = new Model();

    /**
     * [REQ1] 2 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result createSimpleTask(String option) {
        String[] data = option.split(" ", 5);
        if (data.length < 5) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.createSimpleTask(data[1], data[2], data[3], data[4]);
    }

    /**
     * [REQ2] 2 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result createCompositeTask(String option) {
        String[] data = option.split(" ", 4);
        if (data.length < 4) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.createCompositeTask(data[1], data[2], data[3]);
    }

    /**
     * [REQ3] 2 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result deleteTask(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.deleteTask(data[1]);
    }

    /**
     * [REQ4] 2 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result changeTask(String option) {
        String[] data = option.split(" ", 4);
        if (data.length < 4) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.changeTask(data[1], data[2], data[3]);
    }

    /**
     * [REQ5] 1 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result printTask(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.printTask(data[1]);
    }

    /**
     * [REQ6] 2 points
     * @return Result
     */
    public Result printAllTasks() {
        return core.printAllTasks();
    }

    /**
     * [REQ7] 4 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result reportDuration(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.reportDuration(data[1]);
    }

    /**
     * [REQ8] 4 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result reportEarliestFinishTime(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.reportEarliestFinishTime(data[1]);
    }

    /**
     * [REQ9] 2 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result defineBasicCriterion(String option) {
        String[] data = option.split(" ", 5);
        if (data.length < 5) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.defineBasicCriterion(data[1], data[2], data[3], data[4]);
    }

    /**
     * [REQ11] 3 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result defineNegatedCriterion(String option) {
        String[] data = option.split(" ", 3);
        if (data.length < 3) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.defineNegatedCriterion(data[1], data[2]);
    }

    /**
     * [REQ11] 3 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result defineBinaryCriterion(String option) {
        String[] data = option.split(" ", 5);
        if (data.length < 5) {
            return new Result(false, "Parameters error");
        }
        core.setLastCommand(data[0]);
        return core.defineBinaryCriterion(data[1], data[2], data[3], data[4]);
    }

    /**
     * [REQ12] 3 points
     * @return Result
     */
    public Result printAllCriterion() {
        return core.printAllCriterion();
    }

    /**
     * [REQ13] 5 points
     * @param option A string representing the option to be processed
     * @return Reult
     */
    public Result search(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.searchName(data[1]);
    }

    /**
     * [REQ14] 3 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result store(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.store(data[1]);
    }

    /**
     * [REQ15] 3 points
     * @param option A string representing the option to be processed
     * @return Result
     */
    public Result load(String option) {
        String[] data = option.split(" ", 2);
        if (data.length < 2) {
            return new Result(false, "Parameters error");
        }
        return core.load(data[1]);
    }

    /**
     * [BON2] 4 points
     * @return Result
     */
    public Result redo() {
        return core.redo();
    }
    /**
     * [BON2] 4 points
     * @return Result
     */
    public Result undo() {
        return core.undo();
    }


    private static String getHelp() {
        return "CreateSimpleTask [name] [description] [duration] [prerequisites]\n" +
                "CreateCompositeTask [name0] [description] [name1],[name2]...\n" +
                "DeleteTask name\n" +
                "ChangeTask name property newValue\n" +
                "PrintTask name\n" +
                "PrintAllTasks\n" +
                "ReportDuration name\n" +
                "ReportEarliestFinishTime name\n" +
                "DefineBasicCriterion name1 property op value\n" +
                "DefineNegatedCriterion name1 name2\n" +
                "DefineBinaryCriterion name1 name2 logicOp name3\n" +
                "PrintAllCriterion\n" +
                "Search name\n" +
                "Store path\n" +
                "Load path\n" +
                "Quit\n";

    }
}
