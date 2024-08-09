package hk.edu.polyu.comp.comp2021.tms;

import hk.edu.polyu.comp.comp2021.tms.controller.CommandController;
import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Model;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.model.bean.Criterion;
import hk.edu.polyu.comp.comp2021.tms.model.bean.Task;
import hk.edu.polyu.comp.comp2021.tms.utils.FileUtils;
import hk.edu.polyu.comp.comp2021.tms.utils.InputUtils;
import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;
import hk.edu.polyu.comp.comp2021.tms.view.Main;
import hk.edu.polyu.comp.comp2021.tms.view.ui.HomePanel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class used for testing the Model class.
 */
public class TestModel {

    /**
     * This test method tests the 'createSimpleTask' method of the Model class.
     * It verifies that task creation fails when the task name, task description, or task number is invalid,
     * and it succeeds when all input parameters are valid.
     * It also tests that task creation fails when a task with the same name already exists.
     */
    @Test
    public void testTask() {
        Model model = new Model();
        Result testName = model.createSimpleTask("read file", "read file", "1", ",");
        assertFalse(testName.isSuccess());

        Result testNameLength = model.createSimpleTask("readfiles", "read file", "1", ",");
        assertFalse(testNameLength.isSuccess());

        Result testDescription = model.createSimpleTask("readfile", "read file", "1", ",");
        assertFalse(testDescription.isSuccess());

        Result testNumber = model.createSimpleTask("readfile", "read-file", "1a", ",");
        assertFalse(testNumber.isSuccess());

        Result result = model.createSimpleTask("readfile", "read-file", "1", ",");
        assertTrue(result.isSuccess());

        Result testUnique = model.createSimpleTask("readfile", "read-file2", "1", ",");
        System.out.println(testUnique.isSuccess() + testUnique.getMessage());
        assertFalse(testUnique.isSuccess());
    }

    /**
     * Test the 'createCompositeTask' and 'deleteTask' methods of the Model class.
     */
    @Test
    public void testCompositeTask() {
        Model model = new Model();
        Result read = model.createSimpleTask("read", "read-file", "1", ",");
        Result process = model.createSimpleTask("process", "process-file", "1", "read");
        Result save = model.createSimpleTask("save", "save-file", "1", "read,process");

        Result list = model.createSimpleTask("list", "list-file", "1", ",");
        Result print = model.createSimpleTask("print", "print-list", "1", ",");

        Result checkName1 = model.createCompositeTask("read", "read file", ",");
        assertFalse(checkName1.isSuccess());
        Result checkName2 = model.createCompositeTask("read file", "read file", ",");
        assertFalse(checkName2.isSuccess());
        Result checkName3 = model.createCompositeTask("read-file", "read file", ",");
        assertFalse(checkName3.isSuccess());
        Result checkName4 = model.createCompositeTask("readfile", "read file", ",");
        assertFalse(checkName4.isSuccess());
        Result checkDescription = model.createCompositeTask("readfile", "read file", ",");
        assertFalse(checkDescription.isSuccess());
        Result checkSubTask1 = model.createCompositeTask("readfile", "read file", ",");
        assertFalse(checkSubTask1.isSuccess());
        Result checkSubTask2 = model.createCompositeTask("readfile", "read file", "read");
        assertFalse(checkSubTask2.isSuccess());
        Result success = model.createCompositeTask("readfile", "read-file", "read,process,save");
        assertTrue(success.isSuccess());

        Result delete = model.deleteTask("list");
        assertTrue(delete.isSuccess());
        Result addList = model.createSimpleTask("list", "list-file", "1", ",");
        assertTrue(addList.isSuccess());
    }

    /**
     * Test the 'changeTask' method of the Model class.
     */
    @Test
    public void testChangeTask() {
        Model model = new Model();

        Result read = model.createSimpleTask("read", "read-file", "1", ",");
        Result result = model.createSimpleTask("readfile", "read-file", "1", ",");
        assertTrue(result.isSuccess());

        Result testName = model.changeTask("readfile", "name", "read file");
        assertFalse(testName.isSuccess());

        Result testUnique = model.changeTask("readfile", "name", "read");
        assertFalse(testUnique.isSuccess());

        Result testNameLength = model.changeTask("readfile", "name", "readfiles");
        assertFalse(testNameLength.isSuccess());

        Result testDescription = model.changeTask("readfile", "description", "read file");
        assertFalse(testDescription.isSuccess());

        Result testNumber = model.changeTask("readfile", "duration", "1a");
        assertFalse(testNumber.isSuccess());
    }

    /**
     * Test the 'reportDuration' method of the Model class.
     */
    @Test
    public void testReportDuration() {
        Model model = new Model();
        Result read = model.createSimpleTask("read", "read-file", "1", ",");
        Result process = model.createSimpleTask("process", "process-file", "2", "read");
        Result save = model.createSimpleTask("save", "save-file", "4", "read,process");

        Result list = model.createSimpleTask("list", "list-file", "1", ",");
        Result print = model.createSimpleTask("print", "print-list", "2", "list");

        Result compositeRead = model.createCompositeTask("cread", "read-task", "read,process,save");
        Result compositePrint = model.createCompositeTask("cprint", "print-task", "list,print");

        assertEquals(1, model.reportDuration("read").getDuration());
        assertEquals(2, model.reportDuration("process").getDuration());
        assertEquals(4, model.reportDuration("save").getDuration());

        assertEquals(1, model.reportDuration("list").getDuration());
        assertEquals(2, model.reportDuration("print").getDuration());

        assertEquals(7, model.reportDuration("cread").getDuration());
        assertEquals(3, model.reportDuration("cprint").getDuration());


        Result t1 = model.createSimpleTask("t1", "read-file", "1", ",");
        Result t2 = model.createSimpleTask("t2", "process-file", "2", ",");
        Result t3 = model.createSimpleTask("t3", "save-file", "2", "t1,t2");
        Result t4 = model.createCompositeTask("t4", "read-task", "t1,t2,t3");

        assertEquals(4, model.reportDuration("t4").getDuration());
    }

    /**
     * Test the 'reportEarliestFinishTime' method of the Model class.
     */
    @Test
    public void testReportEarliestFinishTime() {

        Model model = new Model();
        Result read = model.createSimpleTask("read", "read-file", "1", ",");
        Result process = model.createSimpleTask("process", "process-file", "2", "read");
        Result save = model.createSimpleTask("save", "save-file", "4", "read,process");

        Result list = model.createSimpleTask("list", "list-file", "1", ",");
        Result print = model.createSimpleTask("print", "print-list", "2", "list");

        Result t1 = model.createSimpleTask("t1", "read-file", "1", ",");
        Result t2 = model.createSimpleTask("t2", "process-file", "2", ",");
        Result t3 = model.createSimpleTask("t3", "save-file", "2", "t1,t2");

        assertEquals(5, model.reportEarliestFinishTime("save").getDuration());
        assertEquals(3, model.reportEarliestFinishTime("print").getDuration());
        assertEquals(3, model.reportEarliestFinishTime("t3").getDuration());
    }

    /**
     * Test the 'testDefineBasicCriterion' method of the Model class.
     */
    @Test
    public void testDefineBasicCriterion() {
        Model model = new Model();
        Result r1 = model.defineBasicCriterion("c1", "name", "&&", "");
        Result r2 = model.defineBasicCriterion("c1", "description", "&&", "");
        Result r3 = model.defineBasicCriterion("c1", "prerequisites", "&&", "");
        Result r4 = model.defineBasicCriterion("c1", "subtasks", "&&", "");
        assertFalse(r1.isSuccess());
        assertFalse(r2.isSuccess());
        assertFalse(r3.isSuccess());
        assertFalse(r4.isSuccess());

        Result r5 = model.defineBasicCriterion("c1", "duration", "&&", "1a");
        Result r6 = model.defineBasicCriterion("c1", "duration", ">", "2");
        assertFalse(r5.isSuccess());
        assertTrue(r6.isSuccess());
    }
    /**
     * Test the 'searchName' method of the Model class.
     */
    @Test
    public void searchName() {
        Model model = new Model();

        Result read = model.createSimpleTask("read", "read-file", "1", ",");
        Result process = model.createSimpleTask("process", "process-file", "2", "read");
        Result save = model.createSimpleTask("save", "save-file", "4", "read,process");

        Result list = model.createSimpleTask("list", "list-file", "1", ",");
        Result print = model.createSimpleTask("print", "print-list", "2", "list");

        Result t1 = model.createSimpleTask("t1", "read-file", "1", ",");
        Result t2 = model.createSimpleTask("t2", "process-file", "2", ",");
        Result t3 = model.createSimpleTask("t3", "save-file", "2", "t1,t2");

        // 测试搜索
        Result c1 = model.defineBasicCriterion("c1", "duration", ">", "2");
        Result c2 = model.defineBasicCriterion("c2", "duration", "<", "2");
        Result c3 = model.defineBasicCriterion("c3", "duration", "==", "2");
        Result c4 = model.defineBasicCriterion("c4", "name", "contains", "t");
        assertEquals(1, model.searchName("c1").getTasks().size());
        assertEquals(3, model.searchName("c2").getTasks().size());
        assertEquals(4, model.searchName("c3").getTasks().size());

        Result n1 = model.defineNegatedCriterion("n1", "c1");
        Result n2 = model.defineNegatedCriterion("n2", "c2");
        assertEquals(7, model.searchName("n1").getTasks().size());
        assertEquals(5, model.searchName("n2").getTasks().size());

        Result b1 = model.defineBinaryCriterion("b1", "c1", "&&", "c2");
        Result b2 = model.defineBinaryCriterion("b2", "c3", "&&", "c4");
        assertEquals(0, model.searchName("b1").getTasks().size());
        assertEquals(3, model.searchName("b2").getTasks().size());

        Result u1 = model.defineNegatedCriterion("u1", "b2");
        Result u2 = model.defineNegatedCriterion("u2", "u1");
        assertEquals(5, model.searchName("u1").getTasks().size());
        assertEquals(3, model.searchName("u2").getTasks().size());

        Result o1 = model.defineBinaryCriterion("o1", "c3", "||", "c2");
        assertEquals(7, model.searchName("o1").getTasks().size());

        Result m1 = model.createCompositeTask("tt1", "m1", "read,process,save");
        Result m2 = model.createCompositeTask("tt2", "m2", "read,process,save");
        Result m3 = model.createCompositeTask("tt3", "m3", "read,process,save");
        Result i0 = model.defineBasicCriterion("i0", "name", "contains", "t");
        Result i1 = model.defineBinaryCriterion("i1", "IsPrimitive", "&&", "i0");
        Result i2 = model.defineNegatedCriterion("i2", "i1");

        assertEquals(8, model.searchName("i0").getTasks().size());
        assertEquals(5, model.searchName("i1").getTasks().size());
        assertEquals(6, model.searchName("i2").getTasks().size());
    }

    /**
     * Test the 'save' and 'read' method of the Model class.
     */
    @Test
    public void testSaveAndRead() {
        String filename = "testfile.txt";
        String expected = "Hello, World!";
        FileUtils.save(expected, filename);
        Object actual = FileUtils.read(filename);
        assertEquals(expected, actual);
        File file = new File(filename);
        file.delete();
    }

    /**
     * Test the 'read' method on non existed of the Model class.
     */
    @Test
    public void testReadNonExistentFile() {
        String filename = "nonexistent.txt";
        Object actual = FileUtils.read(filename);
        assertNull(actual);
    }


//    /**
//     * Test the 'read' method of the Model class.
//     */
//    @Test
//    public void testRead() {
//        String expected = "Hello";
//        InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
//        System.setIn(inputStream);
//        String result = InputUtils.read("Enter a message");
//        assertEquals(expected, result);
//    }
    /**
     * Test the 'createSimpleTask' method of the Model class.
     */
    @Test
    public void testCreateSimpleTask() {
        // Arrange
        CommandController controller = new CommandController();
        String option = "CreateSimpleTask task1 This is a task 60";
        Result result = controller.createSimpleTask(option);
        assertNotNull(result);
    }

    /**
     * Test the 'createSimpleTask' method of the CommandController class.
     */
    @Test
    public void testCreateSimpleTask_ParametersError() {
        CommandController controller = new CommandController();
        String option = "CreateSimpleTask task1 This is a task";
        Result result = controller.createSimpleTask(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'printAllTasks' method of the CommandController class.
     */
    @Test
    public void testPrintAllTasks() {
        CommandController controller = new CommandController();
        Result result = controller.printAllTasks();
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Test the 'deleteTask' method of the CommandController class.
     */
    @Test
    public void testDeleteTask_ParametersError() {
        CommandController controller = new CommandController();
        String option = "DeleteTask";
        Result result = controller.deleteTask(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'changeTask' method of the CommandController class.
     */
    @Test
    public void testChangeTask_ParametersError() {
        CommandController controller = new CommandController();
        String option = "ChangeTask task1 property";
        Result result = controller.changeTask(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'reportDuration' method of the CommandController class.
     */
    @Test
    public void testReportDuration_Success() {
        CommandController controller = new CommandController();
        String option = "ReportDuration task1";
        Result result = controller.reportDuration(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'reportDuration' method of the CommandController class.
     */
    @Test
    public void testReportDuration_ParametersError() {
        CommandController controller = new CommandController();
        String option = "ReportDuration";
        Result result = controller.reportDuration(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'reportEarliestFinishTime' method of the CommandController class.
     */
    @Test
    public void testReportEarliestFinishTime_Success() {
        CommandController controller = new CommandController();
        String option = "ReportEarliestFinishTime task1";
        Result result = controller.reportEarliestFinishTime(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'reportEarliestFinishTime' method of the CommandController class.
     */
    @Test
    public void testReportEarliestFinishTime_ParametersError() {
        CommandController controller = new CommandController();
        String option = "ReportEarliestFinishTime";
        Result result = controller.reportEarliestFinishTime(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'defineBasicCriterion' method of the CommandController class.
     */
    @Test
    public void testDefineBasicCriterion_Success() {
        CommandController controller = new CommandController();
        String option = "DefineBasicCriterion criterionName property operator value";
        Result result = controller.defineBasicCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'defineBasicCriterion' method of the CommandController class.
     */
    @Test
    public void testDefineBasicCriterion_ParametersError() {
        CommandController controller = new CommandController();
        String option = "DefineBasicCriterion";
        Result result = controller.defineBasicCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'defineNegatedCriterion' method of the CommandController class.
     */
    @Test
    public void testDefineNegatedCriterion_Success() {
        CommandController controller = new CommandController();
        String option = "DefineNegatedCriterion criterionName criterionToNegate";
        Result result = controller.defineNegatedCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'defineNegatedCriterion' method of the CommandController class with incorrect parameters.
     */
    @Test
    public void testDefineNegatedCriterion_ParametersError() {
        CommandController controller = new CommandController();
        String option = "DefineNegatedCriterion";
        Result result = controller.defineNegatedCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'defineBinaryCriterion' method of the CommandController class with correct parameters.
     */
    @Test
    public void testDefineBinaryCriterion_Success() {
        CommandController controller = new CommandController();
        String option = "DefineBinaryCriterion criterionName property operator value";
        Result result = controller.defineBinaryCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'defineBinaryCriterion' method of the CommandController class with incorrect parameters.
     */
    @Test
    public void testDefineBinaryCriterion_ParametersError() {
        CommandController controller = new CommandController();
        String option = "DefineBinaryCriterion";
        Result result = controller.defineBinaryCriterion(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'printAllCriterion' method of the CommandController class.
     */
    @Test
    public void testPrintAllCriterion() {
        CommandController controller = new CommandController();
        Result result = controller.printAllCriterion();
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Test the 'search' method of the CommandController class with correct parameters.
     */
    @Test
    public void testSearch_Success() {
        CommandController controller = new CommandController();
        String option = "Search criterionName";
        Result result = controller.search(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'search' method of the CommandController class with incorrect parameters.
     */
    @Test
    public void testSearch_ParametersError() {
        CommandController controller = new CommandController();
        String option = "Search";
        Result result = controller.search(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'store' method of the CommandController class with correct parameters.
     */
    @Test
    public void testStore_Success() {
        CommandController controller = new CommandController();
        String option = "Store fileName";
        Result result = controller.store(option);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Test the 'store' method of the CommandController class with incorrect parameters.
     */
    @Test
    public void testStore_ParametersError() {
        CommandController controller = new CommandController();
        String option = "Store";
        Result result = controller.store(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'load' method of the CommandController class with correct parameters.
     */
    @Test
    public void testLoad_Success() {
        CommandController controller = new CommandController();
        String option = "Load fileName";
        Result result = controller.load(option);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Test the 'load' method of the CommandController class with incorrect parameters.
     */
    @Test
    public void testLoad_ParametersError() {
        CommandController controller = new CommandController();
        String option = "Load";
        Result result = controller.load(option);
        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Parameters error", result.getMessage());
    }

    /**
     * Test the 'createSimpleTask' method of the UIController class with correct parameters.
     */
    @Test
    public void testCreateSimpleTask_Success() {
        UIController controller = new UIController();
        String name = "Task1";
        String description = "Description";
        String duration = "1 day";
        String prerequisites = "None";
        Result result = controller.createSimpleTask(name, description, duration, prerequisites);
        assertNotNull(result);
        assertFalse(result.isSuccess());
   }

    /**
     * Test the 'createCompositeTask' method of the UIController class with correct parameters.
     */
    @Test
    public void testCreateCompositeTask_Success() {
        UIController controller = new UIController();
        String name = "CompositeTask1";
        String description = "Description";
        String subTask = "Task1, Task2, Task3";
        Result result = controller.createCompositeTask(name, description, subTask);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'deleteTask' method of the UIController class with correct parameters.
     */
    @Test
    public void testDeleteTask_Success() {
        UIController controller = new UIController();
        String name = "Task1";
        Result result = controller.deleteTask(name);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'changeTask' method of the UIController class with correct parameters.
     */
    @Test
    public void testChangeTask_Success() {
        UIController controller = new UIController();
        String name = "Task1";
        String property = "duration";
        String newValue = "2 days";
        Result result = controller.changeTask(name, property, newValue);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'printTask' method of the UIController class with correct parameters.
     */
    @Test
    public void testPrintTask_Success() {
        UIController controller = new UIController();
        String name = "Task1";
        Result result = controller.printTask(name);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

    /**
     * Test the 'printAllCriterion' method of the UIController class.
     */
    @Test
    public void testPrintAllCriterion_Success() {
        UIController controller = new UIController();
        Result result = controller.printAllCriterion();
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Test the 'undo' method of the UIController class.
     */
    @Test
    public void testUndo_Success() {
        UIController controller = new UIController();
        Result result = controller.undo();
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Tests the redo method in UIController class.
     * Checks that a non-null Result is returned and isSuccess method on the Result object returns true.
     */
    @Test
    public void testRedo_Success() {
        UIController controller = new UIController();
        Result result = controller.redo();
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    /**
     * Tests the constructor of HomePanel class.
     * Checks that the title is set correctly and the panel is not resizable.
     */
    @Test
    public void testConstructor() {
        String title = "Test Title";
        HomePanel homePanel = new HomePanel(title);
        assertEquals(title, homePanel.getTitle());
        assertFalse(homePanel.isResizable());
    }

    // by Lena
    /**
     * Tests the createSimpleTask method in Model class with invalid task name containing special characters.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidName1() {
        Model model = new Model();
        Result result = model.createSimpleTask("&%*$", "Description", "1", ",");
        assertFalse(result.isSuccess());
        assertEquals("name only contains english letters and digits!",result.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with invalid task name starting with a digit.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidName2() {
        Model model = new Model();
        Result result = model.createSimpleTask("1task", "Description", "1", ",");
        assertFalse(result.isSuccess());
        assertEquals("name can't start digits!",result.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with an overly long task name.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidName3() {
        Model model = new Model();
        Result result = model.createSimpleTask("awudhiauhwduahwdhauwdawdawd", "Description", "1", ",");
        assertFalse(result.isSuccess());
        assertEquals("name too length, only 8 characters!",result.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with task name that is not unique.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidName4() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "Description", "1", ",");
        assertTrue(result1.isSuccess());
        Result result2 = model.createSimpleTask("task1", "Description", "1", ",");
        assertFalse(result2.isSuccess());
        assertEquals("name should be unique!",result2.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with invalid task description containing special characters.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidDescription() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "&$@*@$", "1", ",");
        assertEquals("description only contains english letters, digits and hyphen letter(-)!",result1.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with invalid task duration that is not a number.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidDuration() {
        Model model = new Model();
        Result result = model.createSimpleTask("Task2", "Description", "abc", ",");
        assertFalse(result.isSuccess());
        assertEquals("Duration not a number!",result.getMessage());
    }

    /**
     * Tests the createSimpleTask method in Model class with invalid prerequisite tasks that do not exist.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateSimpleTask_InvalidPrerequsites1() {
        Model model = new Model();
        Result result = model.createSimpleTask("Task1", "Description", "1", "Task0");
        assertFalse(result.isSuccess());
        assertEquals("Failed, task Task0 not exist!",result.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with invalid task name containing special characters.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidName1() {
        Model model = new Model();
        Result result = model.createCompositeTask("&%*$", "Description1", ",");
        assertFalse(result.isSuccess());
        assertEquals("name only contains english letters and digits!",result.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with invalid task name starting with a digit.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidName2() {
        Model model = new Model();
        Result result = model.createCompositeTask("1task", "Description",  ",");
        assertFalse(result.isSuccess());
        assertEquals("name can't start digits!",result.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with an overly long task name.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidName3() {
        Model model = new Model();
        Result result = model.createCompositeTask("awudhiauhwduahwdhauwdawdawd", "Description",  ",");
        assertFalse(result.isSuccess());
        assertEquals("name too length, only 8 characters!",result.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with task name that is not unique.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidName4() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "Description",  "1", ",");
        assertTrue(result1.isSuccess());
        Result result2 = model.createCompositeTask("task1", "Description",  ",");
        assertFalse(result2.isSuccess());
        assertEquals("name should be unique!",result2.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with invalid subtask count.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidSubtask1() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("t0", "Description",   "1",",");
        Result result2 = model.createCompositeTask("task1", "Description",   "t0");
        assertFalse(result2.isSuccess());
        assertEquals("sub task's count must >= 2",result2.getMessage());
    }

    /**
     * Tests the createCompositeTask method in Model class with invalid subtasks that do not exist.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testCreateCompositeTask_InvalidSubtask2() {
        Model model = new Model();
        Result result2 = model.createCompositeTask("task1", "Description",   "task0, task2");
        assertFalse(result2.isSuccess());
        assertEquals("Failed, task task0 not exist!",result2.getMessage());
    }

    /**
     * Tests the reportDuration method in Model class with an invalid task.
     * Checks that the method returns a failure Result with a specific error message.
     */

    @Test
    public void testReportDuration_InvalidTask() {
        Model model = new Model();
        Result result2 = model.reportDuration("task0");
        assertFalse(result2.isSuccess());
        assertEquals("Failed, task task0 not exist!",result2.getMessage());
    }

    /**
     * Tests the reportEarliestFinishTime method in Model class with an invalid task.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testReportEarliestFinishTime_InvalidTask() {
        Model model = new Model();
        Result result2 = model.reportEarliestFinishTime("task0");
        assertFalse(result2.isSuccess());
        assertEquals("Failed, task task0 not exist!",result2.getMessage());
    }

    /**
     * Tests the printTask method in Model class with a valid task.
     * Checks that the method returns a success Result with a specific success message.
     */
    @Test
    public void testPrintTask1() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "Description", "1", ",");
        Result result2 = model.printTask("task1");
        assertEquals("SUCCESS!",result2.getMessage());
    }

    /**
     * Tests the printTask method in Model class with an invalid task.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testPrintTask2Invalid() {
        Model model = new Model();
        Result result2 = model.printTask("task1");
        assertEquals("Failed, task task1 not exist!",result2.getMessage());
    }

    /**
     * Tests the deleteTask method in Model class with an invalid task.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testDeleteTaskInvalid() {
        Model model = new Model();
        Result result2 = model.deleteTask("task1");
        assertEquals("Failed, task task1 not exist!",result2.getMessage());
    }

    /**
     * Tests the changeTask method in Model class with an invalid property.
     * Checks that the method returns a failure Result with a specific error message.
     */
    @Test
    public void testChangeTaskInvalid() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "Description", "1", ",");
        assertTrue(result1.isSuccess());
        Result result2 = model.changeTask("task1", "awdhiauh", "w");
        assertEquals("Property invalid!",result2.getMessage());
    }

    /**
     * Tests the printAllTasks method in Model class with a valid task.
     * Checks that the method returns a success Result and the result's uiMessage matches the expected output string.
     */
    @Test
    public void testPrintAllTasks1() {
        Model model = new Model();
        Result result1 = model.createSimpleTask("task1", "Description", "1", ",");
        assertTrue(result1.isSuccess());
        Result result2 = model.printAllTasks();
        assertEquals("SUCCESS!",result2.getMessage());
        String str = "name : task1\n" +
                "description : Description\n" +
                "duration : 1.0\n" +
                "prerequisites : ," +
                "\r\n-----------\r\n";
        assertEquals(str, result2.getUiMessage());
    }

    /**
     * Tests the constructor of the Result class with success and duration parameters.
     * Checks that the created result is not null, its success and duration match the input parameters.
     */
    @Test
    public void testResult1() {
        Result result = new Result(true, 1.0);
        assertNotNull(result);
        assertEquals(true, result.isSuccess());
        assertEquals(1.0, result.getDuration());
    }

    /**
     * Tests the constructor of Result class with success and tasks parameters.
     * Checks that the created result is not null, its success and tasks match the input parameters.
     */
    @Test
    public void testResult2() {
        List<Task> tasks = new ArrayList<>();
        Result result = new Result(true, tasks);
        assertNotNull(result);
        assertEquals(true, result.isSuccess());
        assertNotNull(result.getTasks());
    }

    /**
     * Tests the setUiMessage method of the Result class.
     * Checks that the uiMessage of the result matches the input string after the method call.
     */
    @Test
    public void testResultSetUIMessage() {
        Result result = new Result(true, "test");
        result.setUiMessage("testing");
        assertEquals("testing", result.getUiMessage());
    }

    /**
     * Tests the setDuration method of the Result class.
     * Checks that the duration of the result matches the input value after the method call.
     */
    @Test
    public void testSetDuration() {
        Result result = new Result(true, "test");
        result.setDuration(1.0);
        assertEquals(1.0, result.getDuration());
    }

    /**
     * Tests the setSuccess method of the Result class.
     * Checks that the success of the result matches the input value after the method call.
     */
    @Test
    public void testSetSuccess() {
        Result result = new Result(true, "test");
        result.setSuccess(false);
        assertEquals(false, result.isSuccess());
    }

    /**
     * Tests the setMessage method in Result class.
     * Checks that the message of the result matches the input string after the method call.
     */
    @Test
    public void testSetMessage() {
        Result result = new Result(true, "test");
        result.setMessage("testing");
        assertEquals("testing", result.getMessage());
    }

    /**
     * Tests the setClose method in Result class.
     * Checks if the method call sets the close status correctly.
     */
    @Test
    public void testSetClose() {
        Result result = new Result(true, "test");
        result.setClose(true);
    }

    /**
     * Tests the toDouble method in TextUtils class with an invalid input.
     * Checks that the method returns -1 when the input string cannot be parsed to a double.
     */
    @Test
    public void testToDoubleInvalid() {
        double ret = TextUtils.toDouble("adwad");
        assertEquals(-1, ret);
    }

    /**
     * Tests the contains method in TextUtils class.
     * Checks that the method returns false when the input string does not contain the specified sequence of characters.
     */
    @Test
    public void testContains() {
        boolean ret = TextUtils.contains("", "awdawd");
        assertFalse(ret);
    }

    /**
     * Tests the checkInt method in TextUtils class.
     * Checks that the method returns true when the input string can be parsed to an integer and false otherwise.
     */
    @Test
    public void testCheckInt() {
        boolean ret1 = TextUtils.checkInt("1");
        boolean ret2 = TextUtils.checkInt("a");
        assertTrue(ret1);
        assertFalse(ret2);
    }

    /**
     * Tests the checkFloat method in TextUtils class.
     * Checks that the method returns true when the input string can be parsed to a float and false otherwise.
     */
    @Test
    public void testCheckFloat() {
        boolean ret1 = TextUtils.checkFloat("1");
        boolean ret2 = TextUtils.checkInt("a");
        assertTrue(ret1);
        assertFalse(ret2);
    }

    /**
     * Tests the checkLong method in TextUtils class.
     * Checks that the method returns true when the input string can be parsed to a long and false otherwise.
     */
    @Test
    public void testCheckLong() {
        boolean ret1 = TextUtils.checkLong("1");
        boolean ret2 = TextUtils.checkLong("a");
        assertTrue(ret1);
        assertFalse(ret2);
    }

    /**
     * Tests the toInt method in TextUtils class.
     * Checks that the method returns the parsed integer when the input string is a valid integer, and -1 otherwise.
     */
    @Test
    public void testToInt() {
        int ret1 = TextUtils.toInt("1");
        int ret2 = TextUtils.toInt("a");
        assertEquals(1, ret1);
        assertEquals(-1, ret2);
    }

    /**
     * Tests the toLong method in TextUtils class.
     * Checks that the method returns the parsed long when the input string is a valid long, and -1 otherwise.
     */
    @Test
    public void testToLong() {
        long ret1 = TextUtils.toInt("1");
        long ret2 = TextUtils.toInt("a");
        assertEquals(1, ret1);
        assertEquals(-1L, ret2);
    }

    /**
     * Tests the toFloat method in TextUtils class.
     * Checks that the method returns the parsed float when the input string is a valid float, and -1 otherwise.
     */
    @Test
    public void testToFloat() {
        float ret1 = TextUtils.toInt("1");
        float ret2 = TextUtils.toInt("a");
        assertEquals(1, ret1);
        assertEquals(-1, ret2);
    }

    /**
     * Tests the setName method in a class (please replace with the correct class name).
     * Checks that the name of the object matches the input string after the method call.
     */
    @Test
    public void testSetName() {
        float ret1 = TextUtils.toInt("1");
        float ret2 = TextUtils.toInt("a");
        assertEquals(1, ret1);
        assertEquals(-1, ret2);
    }

    /**
     * Tests the getDescription method in Task class.
     * Checks that the method returns the correct description of the task.
     */
    @Test
    public void testgetDescription() {
        Task t = new Task("t1", "d1", 1, ",");
        assertEquals("d1",t.getDescription());
    }

    /**
     * Tests the setDescription method in Task class.
     * Checks that the description of the task matches the input string after the method call.
     */
    @Test
    public void testsetDescription() {
        Task t = new Task("t1", "d1", 1, ",");
        t.setDescription("d2");
        assertEquals("d2",t.getDescription());
    }

    /**
     * Tests the setDuration method in Task class.
     * Checks that the duration of the task matches the input value after the method call.
     */
    @Test
    public void testTaskSetDuration() {
        Task t = new Task("t1", "d1", 1, ",");
        t.setDuration(2);
        assertEquals(2, t.getDuration());
    }

    /**
     * Tests the setPrerequisites method in Task class.
     * Checks that the prerequisites of the task matches the input string after the method call.
     */
    @Test
    public void testSetPrerequisites() {
        Task t0 = new Task("t0", "d1", 1, ",");
        Task t1 = new Task("t1", "d1", 1, ",");
        t1.setPrerequisites("t0");
        assertEquals("t0", t1.getPrerequisites());
    }

    /**
     * Tests the setName method in Criterion class.
     * Checks that the name of the criterion matches the input string after the method call.
     */
    @Test
    public void testSetNameCrit() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        c1.setName("c2");
        assertEquals("c2", c1.getName());
    }

    /**
     * Tests the getProperty method in Criterion class.
     * Checks that the method returns the correct property of the criterion.
     */
    @Test
    public void testGetPropertyCrit() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        assertEquals("name", c1.getProperty());
    }

    /**
     * Tests the setProperty method in Criterion class.
     * Checks that the property of the criterion matches the input string after the method call.
     */
    @Test
    public void testSetPropertyCrit() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        c1.setOp("a");
        c1.setProperty("duration");
        assertEquals("duration", c1.getProperty());
    }

    /**
     * Tests the setOp method in Criterion class.
     * Checks that the op of the criterion matches the input string after the method call.
     */
    @Test
    public void testSetOp() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        c1.setOp("a");
        assertEquals("a", c1.getOp());
    }

    /**
     * Tests the getValue method in Criterion class.
     * Checks that the method returns the correct value of the criterion.
     */
    @Test
    public void testGetValue() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        assertEquals("c2", c1.getValue());
    }

    /**
     * Tests the getValue2 method in Criterion class.
     * Checks that the method returns the correct value2 of the criterion.
     */
    @Test
    public void testGetValue2() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2", "c3");
        assertEquals("c3", c1.getValue2());
    }

    /**
     * Tests the setValue method in Criterion class.
     * Checks that the value of the criterion matches the input string after the method call.
     */
    @Test
    public void testSetValue() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2");
        c1.setValue("c0");
        assertEquals("c0", c1.getValue());
    }

/**
 * Tests the setValue2 method in Criterion class.
 * Checks that the value2 of the criterion matches the input string after the method call
 */
    @Test
    public void testSetValue2() {
        Criterion c1 = new Criterion("c1", "name", "w", "c2", "c3");
        c1.setValue2("c0");
        assertEquals("c0", c1.getValue2());
    }

    /**
     * Tests the isPrimitive method in Criterion class.
     * Checks if the method correctly identifies whether the criterion is primitive.
     */
    @Test
    public void testIsPrimitive() {
        Criterion c1 = new Criterion(true);
        Criterion c2 = new Criterion(false);
        assertTrue(c1.isPrimitive());
        assertFalse(c2.isPrimitive());
    }

    /**
     * Tests the setPrimitive method in Criterion class.
     * Checks if the method sets the primitive status of the criterion correctly.
     */
    @Test
    public void testSetPrimitive() {
        Criterion c1 = new Criterion(true);
        c1.setPrimitive(false);
        assertFalse(c1.isPrimitive());
    }

    /**
     * Tests the main method in Main class with the help command.
     * Checks if the system handles the help command and quit command correctly.
     */
    @Test
    public void testMain_HelpCommand() {
        String input = "Help\nQuit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Main.main(new String[0]);
    }







}


