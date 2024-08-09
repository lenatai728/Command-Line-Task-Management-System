package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.bean.CompositeTask;
import hk.edu.polyu.comp.comp2021.tms.model.bean.Criterion;
import hk.edu.polyu.comp.comp2021.tms.model.bean.Task;
import hk.edu.polyu.comp.comp2021.tms.utils.FileUtils;
import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Model class represents a model in the MVC pattern. It contains tasks and criteria,
 * and methods to manipulate and query them. It also keeps a backup of the last state of tasks and criteria.
 */
public class Model {
    /**
     * The constant for separating strings.
     */
    public static final String SEPARATOR_COMMA = ",";
    private String lastCommand = "";
    private List<Task> allTask = new ArrayList<>();
    private List<Criterion> criteriaList = new ArrayList<>();

    private List<Task> lastTask = new ArrayList<>();
    private List<Criterion> lastCriteriaList = new ArrayList<>();

    /**
     * Constructs a new model and initializes it.
     */
    public Model() {
        initPrimitive();
    }

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
        backup();
        Result check = checkTaskProperty(name, description, duration, prerequisites);
        if (check.isSuccess()) {
            allTask.add(new Task(name, description, TextUtils.toDouble(duration), prerequisites));
            return new Result(true, "SUCCESS");
        } else {
            return check;
        }
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
        backup();
        Result check = checkCompositeTaskProperty(name, description, subTask);
        if (check.isSuccess()) {
            allTask.add(new CompositeTask(name, description, subTask));
            return new Result(true, "SUCCESS");
        } else {
            return check;
        }
    }

    /**
     * REQ3 can redo
     * @param name A string representation of the name of the task to be deleted
     *
     * @return Result
     */
    public Result deleteTask(String name) {
        backup();
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                for (Task t : allTask) {
                    if (TextUtils.equals(t.getName(), name)) {
                        continue;
                    }
                    String[] names = task.getPrerequisites().split(SEPARATOR_COMMA);
                    for (String n : names) {
                        if (TextUtils.equals(n, name)) {
                            return new Result(false, "Failed, task " + name + " is " + t.getName() + "'s prerequisite");
                        }
                    }
                }
                allTask.remove(task);
                return new Result(true, "SUCCESS");
            }
        }
        return new Result(false, "Failed, task " + name + " not exist!");
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
        backup();
        Task target = null;
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                target = task;
                break;
            }
        }
        if (null == target) {
            return new Result(false, "Failed, task " + name + " not exist!");
        }
        // 首先判断是Task还是CompositeTask，然后根据不同属性名来判断更改哪些属性
        if (target instanceof CompositeTask) {
            if (TextUtils.equals("name", property)) {
                Result checkName = checkName(newValue);
                if (checkName.isSuccess()) {
                    target.setName(newValue);
                } else {
                    return checkName;
                }
            } else if (TextUtils.equals("description", property)) {
                Result checkDescription = checkDescription(newValue);
                if (checkDescription.isSuccess()) {
                    target.setDescription(newValue);
                } else {
                    return checkDescription;
                }
            } else if (TextUtils.equals("subtasks", property)) {
                Result checkSubtasks = checkSubTask(newValue);
                if (checkSubtasks.isSuccess()) {
                    ((CompositeTask) target).setSubtasks(newValue);
                } else {
                    return checkSubtasks;
                }
            } else {
                return new Result(false, "Property invalid!");
            }
        } else {
            if (TextUtils.equals("name", property)) {
                Result checkName = checkName(newValue);
                if (checkName.isSuccess()) {
                    target.setName(newValue);
                } else {
                    return checkName;
                }
            } else if (TextUtils.equals("description", property)) {
                Result checkDescription = checkDescription(newValue);
                if (checkDescription.isSuccess()) {
                    target.setDescription(newValue);
                } else {
                    return checkDescription;
                }
            } else if (TextUtils.equals("duration", property)) {
                Result checkDuration = checkDuration(newValue);
                if (checkDuration.isSuccess()) {
                    target.setDuration(TextUtils.toDouble(newValue));
                } else {
                    return checkDuration;
                }
            } else if (TextUtils.equals("prerequisites", property)) {
                Result checkPrerequisites = checkPrerequisites(newValue);
                if (checkPrerequisites.isSuccess()) {
                    target.setPrerequisites(newValue);
                } else {
                    return checkPrerequisites;
                }
            } else {
                return new Result(false, "Property invalid!");
            }
        }
        return new Result(true, "SUCCESS");
    }

    /**
     * REQ5
     * @param name A string representation of the name of the task that need to be printed
     *
     * @return Result
     */
    public Result printTask(String name) {
        Task target = null;
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                target = task;
                break;
            }
        }
        if (null == target) {
            return new Result(false, "Failed, task " + name + " not exist!");
        }
        System.out.println(target);
        return new Result(true, "SUCCESS!", target.toString());
    }

    /**
     * REQ6
     * @return Result
     */
    public Result printAllTasks() {
        StringBuilder builder = new StringBuilder();
        for (Task task : allTask) {
            builder.append(task);
            builder.append("\r\n-----------\r\n");
            System.out.println(task);
            System.out.println("--------");
        }
        return new Result(true, "SUCCESS!", builder.toString());
    }

    /**
     * REQ7
     * @param name A string representation of the name of the task that need to be reported
     *
     * @return Result
     */
    public Result reportDuration(String name) {
        Task target = null;
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                target = task;
                break;
            }
        }
        if (null == target) {
            return new Result(false, "Failed, task " + name + " not exist!");
        }
        double duration;
        String message;
        if (target instanceof CompositeTask) {
            duration = findCompositeTaskMinimum((CompositeTask) target);
            message = "The duration of task " + target.getName() + " is : " + duration;
            System.out.println(message);
        } else {
            duration = target.getDuration();
            message = "The duration of task " + target.getName() + " is : " + duration;
            System.out.println(message);
        }
        return new Result(true, duration, message);
    }

    /**
     * REQ8
     * @param name A string representation of the name of the task that need to be reported
     *
     * @return Result
     */
    public Result reportEarliestFinishTime(String name) {
        Task target = null;
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                target = task;
                break;
            }
        }
        if (null == target) {
            return new Result(false, "Failed, task " + name + " not exist!");
        }
        double duration = 0;
        String message;
        if (target instanceof CompositeTask) {
            message = "Please dont Select CompositeTask";
            System.out.println(message);
        } else {
            duration = getMinPrerequisiteTime(target);
            message = "The duration of task " + target.getName() + " is : " + duration;
            System.out.println(message);
        }
        return new Result(true, duration, message);
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
        backup();
        Result checkName = checkName(name);
        if (!checkName.isSuccess()) {
            return checkName;
        }
        if (TextUtils.equals(property, "name") || TextUtils.equals(property, "description") || TextUtils.equals(property, "prerequisites") || TextUtils.equals(property, "subtasks")) {
            if (!TextUtils.equals("contains", op)) {
                return new Result(false, "op must be \"contains\"");
            }
        }
        if (TextUtils.equals(property, "duration")) {
            if (!TextUtils.equals(">", op)
                    && !TextUtils.equals("<", op)
                    && !TextUtils.equals(">=", op)
                    && !TextUtils.equals("<=", op)
                    && !TextUtils.equals("==", op)
                    && !TextUtils.equals("!=", op)) {
                return new Result(false, "op must be >、<、>=、<=、== or !=");
            }
            if (!TextUtils.checkDouble(value)) {
                return new Result(false, "value must be number");
            }
        }
        if (TextUtils.equals(property, "subtasks")) {
            if (!TextUtils.contains(value, ",")) {
                return new Result(false, "value must be a list of comma-separated task names");
            }
        }
        criteriaList.add(new Criterion(name, property, op, value));
        return new Result(true, "SUCCESS");
    }

    /**
     * REQ10
     * @return Result
     */
    public Result initPrimitive() {
        criteriaList.add(new Criterion(true));
        return new Result(true, "SUCCESS");
    }

    /**
     * REQ11 can redo
     * @param name A string representation of the name of the exist criterion
     * @param negatedName A string representation of the name of the negatedCriterion
     *
     * @return Result
     */
    public Result defineNegatedCriterion(String name, String negatedName) {
        backup();
        Result checkName = checkCriterionName(name);
        if (checkName.isSuccess()) {
            criteriaList.add(new Criterion(name, "name", "!", negatedName));
            return new Result(true, "SUCCESS");
        } else {
            return checkName;
        }
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
        backup();
        Result checkName = checkCriterionName(name);
        if (checkName.isSuccess()) {
            criteriaList.add(new Criterion(name, "name", logicOp, name2, name3));
            return new Result(true, "SUCCESS");
        } else {
            return checkName;
        }
    }

    /**
     * REQ12
     * @return Result
     */
    public Result printAllCriterion() {
        StringBuilder builder = new StringBuilder();
        for (Criterion criterion : criteriaList) {
            builder.append(criterion);
            builder.append("\r\n-----------\r\n");
            System.out.println(criterion);
            System.out.println("-------");
        }
        return new Result(true, "SUCCESS", builder.toString());
    }


    /**
     * REQ13
     * @param name A string representation of the name of the criterion
     *
     * @return Result
     */
    public Result searchName(String name) {
        Criterion target = null;
        for (Criterion criterion : criteriaList) {
            if (TextUtils.equals(name, criterion.getName())) {
                target = criterion;
                break;
            }
        }
        if (null == target) {
            return new Result(false, "Criterion not exist");
        }
        List<Task> tasks = new ArrayList<>();
        if (target.getOp().equals("!") || target.getOp().equals("&&") || target.getOp().equals("||")) {
            tasks.addAll(target.searchByCriterion(criteriaList, allTask));
        } else {
            tasks.addAll(target.search(allTask));
        }
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append(task);
            builder.append("\r\n-------------\r\n");
            System.out.println(task);
            System.out.println("-------");
        }
        return new Result(true, tasks, builder.toString());
    }

    /**
     * REQ14
     * @param path A string representation of the path
     *
     * @return Result
     */
    public Result store(String path) {
        List<Object> data = new ArrayList<>();
        data.addAll(allTask);
        data.addAll(criteriaList);
        FileUtils.save(data, path + "/task.data");
        return new Result(true, "SUCCESS");
    }

    /**
     * REQ15
     * @param path A string representation of the path
     *
     * @return Result
     */
    public Result load(String path) {
        List<Object> data = (List<Object>) FileUtils.read(path + "/task.data");
        if (null != data && !data.isEmpty()) {
            allTask.clear();
            criteriaList.clear();
            for (Object o : data) {
                if (o instanceof Task) {
                    allTask.add((Task) o);
                }
                if (o instanceof Criterion) {
                    criteriaList.add((Criterion) o);
                }
            }
        }
        return new Result(true, "SUCCESS");
    }

    /**
     * Bonus
     * @return Result
     */
    public Result undo() {
        allTask.clear();
        criteriaList.clear();
        allTask.addAll(lastTask);
        criteriaList.addAll(lastCriteriaList);
        return new Result(true, "SUCCESS");
    }

    /**
     * Bonus
     * @return Result
     */
    public Result redo() {
        allTask.clear();
        criteriaList.clear();
        allTask.addAll(lastTask);
        criteriaList.addAll(lastCriteriaList);
        String command = lastCommand;
        lastCommand = "";
        return new Result(true, command);
    }

    /**
     * Sets the last executed command.
     *
     * @param lastCommand the last executed command
     */
    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * Recursively finds the maximum duration time for a task with its prerequisites.
     *
     * @param task the task to calculate max time for
     * @return the maximum time
     */
    private double findTaskMaxTime(Task task) {
        String[] names = task.getPrerequisites().split(SEPARATOR_COMMA);
        double maxTime = 0;
        for (String name : names) {
            Task t = getTask(name);
            if (null != t) {
                maxTime = Math.max(maxTime, findTaskMaxTime(t));
            }
        }
        return maxTime + task.getDuration();
    }

    /**
     * Finds the maximum duration time for a CompositeTask with its subtasks.
     *
     * @param task the CompositeTask to calculate max time for
     * @return the maximum time
     */
    private double findCompositeTaskMinimum(CompositeTask task) {
        String subtasks = task.getSubtasks();
        String[] names = subtasks.split(SEPARATOR_COMMA);
        double maxTime = 0;
        for (String subName : names) {
            Task subTask = getTask(subName);
            if (subTask == null) {
                continue;
            }
            maxTime = Math.max(maxTime, findTaskMaxTime(subTask));
        }
        return maxTime;
    }

    /**
     * Recursively finds the minimum duration time for a task with its prerequisites.
     *
     * @param task the task to calculate min time for
     * @return the minimum time
     */
    private double getMinPrerequisiteTime(Task task) {
        String[] names = task.getPrerequisites().split(SEPARATOR_COMMA);
        double minTime = 0;
        for (String name : names) {
            Task t = getTask(name);
            if (null != t) {
                if (minTime == 0) {
                    minTime = getMinPrerequisiteTime(t);
                } else {
                    minTime = Math.min(minTime, getMinPrerequisiteTime(t));
                }
            }
        }
        return minTime + task.getDuration();
    }

    /**
     * Retrieves a task by its name.
     *
     * @param name the name of the task
     * @return the task if found, null otherwise
     */
    private Task getTask(String name) {
        for (Task task : allTask) {
            if (TextUtils.equals(name, task.getName())) {
                return task;
            }
        }
        return null;
    }

    /**
     * Checks the validity of a task's name.
     *
     * @param name the name of the task
     * @return a Result object indicating success or failure
     */
    private Result checkName(String name) {
        if (!TextUtils.checkName(name)) {
            return new Result(false, "name only contains english letters and digits!");
        }
        if (TextUtils.firstDigits(name)) {
            return new Result(false, "name can't start digits!");
        }
        if (!TextUtils.checkNameLength(name)) {
            return new Result(false, "name too length, only 8 characters!");
        }
        for (Task task : allTask) {
            if (TextUtils.equals(task.getName(), name)) {
                return new Result(false, "name should be unique!");
            }
        }

        return new Result(true, "check success");
    }

    /**
     * Checks the validity of a criterion's name.
     *
     * @param name the name of the criterion
     * @return a Result object indicating success or failure
     */
    private Result checkCriterionName(String name) {
        if (!TextUtils.checkName(name)) {
            return new Result(false, "name only contains english letters and digits!");
        }
        if (TextUtils.firstDigits(name)) {
            return new Result(false, "name can't start digits!");
        }
        if (!TextUtils.checkNameLength(name)) {
            return new Result(false, "name too length, only 8 characters!");
        }
        for (Criterion criterion : criteriaList) {
            if (TextUtils.equals(criterion.getName(), name)) {
                return new Result(false, "name should be unique!");
            }
        }

        return new Result(true, "check success");
    }

    /**
     * Checks the validity of a description.
     *
     * @param description the description to check
     * @return a Result object indicating success or failure
     */
    private Result checkDescription(String description) {

        if (!TextUtils.checkDescription(description)) {
            return new Result(false, "description only contains english letters, digits and hyphen letter(-)!");
        }
        return new Result(true, "check success");
    }

    /**
     * Checks the validity of a subtask.
     *
     * @param subTask the subtask to check
     * @return a Result object indicating success or failure
     */
    private Result checkSubTask(String subTask) {
        String[] names = subTask.split(SEPARATOR_COMMA);
        if (names.length < 2) {
            return new Result(false, "sub task's count must >= 2");
        }
        for (String n : names) {
            boolean found = false;
            for (Task task : allTask) {
                if (TextUtils.equals(n, task.getName())) {
                    if (task instanceof CompositeTask) {
                        return new Result(false, "Failed, CompositeTask not allow in subtask");
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                return new Result(false, "Failed, task " + n + " not exist!");
            }
        }
        return new Result(true, "check success");
    }

    /**
     * Checks the properties of a CompositeTask.
     *
     * @param name the name of the CompositeTask
     * @param description the description of the CompositeTask
     * @param subTask the subtask of the CompositeTask
     * @return a Result object indicating success or failure
     */
    private Result checkCompositeTaskProperty(String name, String description, String subTask) {
        Result checkName = checkName(name);
        if (!checkName.isSuccess()) {
            return checkName;
        }
        Result checkDescription = checkDescription(description);
        if (!checkDescription.isSuccess()) {
            return checkDescription;
        }
        Result checkSubtask = checkSubTask(subTask);
        if (!checkSubtask.isSuccess()) {
            return checkSubtask;
        }
        return new Result(true, "check success");
    }

    /**
     * Checks the validity of a duration.
     *
     * @param duration the duration to check
     * @return a Result object indicating success or failure
     */
    private Result checkDuration(String duration) {
        if (!TextUtils.checkDouble(duration)) {
            return new Result(false, "Duration not a number!");
        }
        return new Result(true, "check success");
    }

    /**
     * Checks the validity of prerequisites.
     *
     * @param prerequisites the prerequisites to check
     * @return a Result object indicating success or failure
     */
    private Result checkPrerequisites(String prerequisites) {
        String[] names = prerequisites.split(SEPARATOR_COMMA);
        for (String n : names) {
            boolean found = false;
            for (Task task : allTask) {
                if (TextUtils.equals(n, task.getName())) {
                    if (task instanceof CompositeTask) {
                        return new Result(false, "Failed, CompositeTask not allow in prerequisites");
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                return new Result(false, "Failed, task " + n + " not exist!");
            }
        }
        return new Result(true, "check success");
    }

    /**
     * Checks the properties of a Task.
     *
     * @param name the name of the Task
     * @param description the description of the Task
     * @param duration the duration of the Task
     * @param prerequisites the prerequisites of the Task
     * @return a Result object indicating success or failure
     */
    private Result checkTaskProperty(String name, String description, String duration, String prerequisites) {
        Result checkName = checkName(name);
        if (!checkName.isSuccess()) {
            return checkName;
        }
        Result checkDuration = checkDuration(duration);
        if (!checkDuration.isSuccess()) {
            return checkDuration;
        }
        Result checkDescription = checkDescription(description);
        if (!checkDescription.isSuccess()) {
            return checkDescription;
        }
        Result checkPrerequisites = checkPrerequisites(prerequisites);
        if (!checkPrerequisites.isSuccess()) {
            return checkPrerequisites;
        }
        return new Result(true, "check success");
    }

    /**
     * Backups the current state of tasks and criteria.
     */
    private void backup() {
        lastTask.clear();
        lastCriteriaList.clear();
        lastTask.addAll(allTask);
        lastCriteriaList.addAll(criteriaList);
    }

}
