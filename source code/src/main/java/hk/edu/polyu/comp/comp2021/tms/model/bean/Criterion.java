package hk.edu.polyu.comp.comp2021.tms.model.bean;

import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Criterion class represents a search criterion that can be applied to a list of tasks.
 */
public class Criterion implements Serializable {
    private String name;
    private String property;
    private String op;
    private String value;
    private String value2;

    private boolean IsPrimitive;

    /**
     * Constructs a Criterion object for primitive types.
     *
     * @param isPrimitive A boolean indicating if the criterion is for primitive types.
     */
    public Criterion(boolean isPrimitive) {
        this.name = "IsPrimitive";
        IsPrimitive = isPrimitive;
    }

    /**
     * Constructs a Criterion object with a given operator and a single value.
     *
     * @param name      The name of the criterion.
     * @param property  The property to be checked.
     * @param op        The operator to be applied.
     * @param value     The value to be compared with.
     */
    public Criterion(String name, String property, String op, String value) {
        this.name = name;
        this.property = property;
        this.op = op;
        this.value = value;
    }
    /**
     * Constructs a Criterion object with a given operator and two values.
     *
     * @param name      The name of the criterion.
     * @param property  The property to be checked.
     * @param op        The operator to be applied.
     * @param value     The first value to be compared with.
     * @param value2    The second value to be compared with.
     */
    public Criterion(String name, String property, String op, String value, String value2) {
        this.name = name;
        this.property = property;
        this.op = op;
        this.value = value;
        this.value2 = value2;
    }

    /**
     * Gets the name of the criterion.
     *
     * @return A string representing the name of the criterion.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the criterion.
     *
     * @param name A string representing the new name of the criterion.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the property of the criterion.
     *
     * @return A string representing the property of the criterion.
     */
    public String getProperty() {
        return property;
    }

    /**
     * Sets the property of the criterion.
     *
     * @param property A string representing the new property of the criterion.
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Gets the operator of the criterion.
     *
     * @return A string representing the operator of the criterion.
     */
    public String getOp() {
        return op;
    }

    /**
     * Sets the operator of the criterion.
     *
     * @param op A string representing the new operator of the criterion.
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * Gets the value of the criterion.
     *
     * @return A string representing the value of the criterion.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the criterion.
     *
     * @param value A string representing the new value of the criterion.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the criterion.
     *
     * @return A string representing the value of the criterion.
     */
    public String getValue2() {
        return value2;
    }

    /**
     * Sets the value of the criterion.
     *
     * @param value2 A string representing the new value of the criterion.
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * @return boolean
     */
    public boolean isPrimitive() {
        return IsPrimitive;
    }

    /**
     * Sets the primitivity of the criterion.
     *
     * @param primitive A boolean representing the new primitivity of the criterion.
     */
    public void setPrimitive(boolean primitive) {
        IsPrimitive = primitive;
    }

    /**
     * Applies the criterion to a list of tasks based on the given criteria and tasks.
     *
     * @param criteria The list of criterion to be applied.
     * @param tasks    The list of tasks to be evaluated.
     * @return A list of tasks that satisfy the criteria.
     */
    public List<Task> searchByCriterion(List<Criterion> criteria, List<Task> tasks) {
        if (op.equals("!")) {
            List<Task> result = new ArrayList<>();
            for (Criterion criterion : criteria) {
                if (TextUtils.equals(value, criterion.getName())) {
                    if (criterion.getOp().equals("!") || criterion.getOp().equals("&&") || criterion.getOp().equals("||")) {
                        List<Task> resultTaskList = criterion.searchByCriterion(criteria, tasks);
                        result.addAll(tasks);
                        for (Task task : resultTaskList) {
                            result.remove(task);
                        }
                    } else {
                        result.addAll(criterion.search(tasks, true));
                    }
                }
            }
            return result;
        }
        if (op.equals("&&")) {
            List<Task> result = new ArrayList<>();
            List<Task> result2 = new ArrayList<>();
            for (Criterion criterion : criteria) {
                if (TextUtils.equals(value, criterion.getName())) {
                    result.addAll(criterion.search(tasks));
                }
                if (TextUtils.equals(value2, criterion.getName())) {
                    result2.addAll(criterion.search(result));
                }
            }
            return result2;
        }
        if (op.equals("||")) {
            List<Task> result = new ArrayList<>();
            for (Criterion criterion : criteria) {
                if (TextUtils.equals(value, criterion.getName())) {
                    result.addAll(criterion.search(tasks));
                }
                if (TextUtils.equals(value2, criterion.getName())) {
                    result.addAll(criterion.search(tasks));
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    /**
     * Searches tasks that satisfy this criterion.
     *
     * @param tasks The list of tasks to be evaluated.
     * @return A list of tasks that satisfy the criterion.
     */
    public List<Task> search(List<Task> tasks) {
        return search(tasks, false);
    }

    /**
     * Searches tasks that satisfy this criterion with a specified NOT operator.
     *
     * @param tasks The list of tasks to be evaluated.
     * @param not   A boolean indicating if the NOT operator is to be used.
     * @return A list of tasks that satisfy the criterion.
     */
    public List<Task> search(List<Task> tasks, boolean not) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (TextUtils.isEmpty(op) && IsPrimitive) {
                if (not) {
                    if (task instanceof CompositeTask) {
                        result.add(task);
                    }
                } else {
                    if (!(task instanceof CompositeTask)) {
                        result.add(task);
                    }
                }
            }
            if (TextUtils.equals(op, "contains")) {
                if (TextUtils.equals(property, "name")) {
                    if (not) {
                        if (!TextUtils.contains(task.getName(), value)) {
                            result.add(task);
                        }
                    } else {
                        if (TextUtils.contains(task.getName(), value)) {
                            result.add(task);
                        }
                    }
                }
                if (TextUtils.equals(property, "description")) {
                    if (not) {
                        if (!TextUtils.contains(task.getDescription(), value)) {
                            result.add(task);
                        }
                    } else {
                        if (TextUtils.contains(task.getDescription(), value)) {
                            result.add(task);
                        }
                    }
                }
                if (TextUtils.equals(property, "prerequisites")) {
                    if (not) {
                        if (!TextUtils.contains(task.getPrerequisites(), value)) {
                            result.add(task);
                        }
                    } else {
                        if (TextUtils.contains(task.getPrerequisites(), value)) {
                            result.add(task);
                        }
                    }
                }
            }
            if (TextUtils.equals(property, "duration")) {
                if (op.equals(">")) {
                    if (not) {
                        if (!(task.getDuration() > TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() > TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
                if (op.equals("<")) {
                    if (not) {
                        if (!(task.getDuration() < TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() < TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
                if (op.equals(">=")) {
                    if (not) {
                        if (!(task.getDuration() >= TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() >= TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
                if (op.equals("<=")) {
                    if (not) {
                        if (!(task.getDuration() <= TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() <= TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
                if (op.equals("==")) {
                    if (not) {
                        if (!(task.getDuration() == TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() == TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
                if (op.equals("!=")) {
                    if (not) {
                        if (!(task.getDuration() != TextUtils.toDouble(value))) {
                            result.add(task);
                        }
                    } else {
                        if (task.getDuration() != TextUtils.toDouble(value)) {
                            result.add(task);
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "name : " + name + '\n' +
                "property : " + property + '\n' +
                "op : " + op + '\n' +
                "value : " + value + '\n' +
                "IsPrimitive : " + IsPrimitive;
    }
}
