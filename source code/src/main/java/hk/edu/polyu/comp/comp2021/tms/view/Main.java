package hk.edu.polyu.comp.comp2021.tms.view;

import hk.edu.polyu.comp.comp2021.tms.controller.CommandController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.utils.InputUtils;

/**
 * The main class for the command line application.
 * It creates an instance of CommandController and performs a series of command line operations.
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     * It performs a loop of command line operations based on user input.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CommandController controller = new CommandController();
        boolean help = false;
        boolean redo = false;
        String redoCommand = "";
        while (true) {
            String option;
            if (redo) {
                option = InputUtils.read("Redo " + redoCommand + "! Input Message");
                redo = false;
                redoCommand = "";
            } else {
                if (help) {
                    option = InputUtils.read("Input Message");
                    help = false;
                } else {
                    option = InputUtils.read(getMenu());
                }
            }
            Result result = null;
            if (option.equals("Help")) {
                System.out.println(getHelp());
                help = true;
            } else if (option.startsWith("CreateSimpleTask")) {// [REQ1]
                result = controller.createSimpleTask(option);
            } else if (option.startsWith("CreateCompositeTask")) {// [REQ2]
                result = controller.createCompositeTask(option);
            } else if (option.startsWith("DeleteTask")) {// [REQ3]
                result = controller.deleteTask(option);
            } else if (option.startsWith("ChangeTask")) {// [REQ4]
                result = controller.changeTask(option);
            } else if (option.startsWith("PrintTask")) {// [REQ5]
                result = controller.printTask(option);
            } else if (option.startsWith("PrintAllTasks")) {// [REQ6]
                result = controller.printAllTasks();
            } else if (option.startsWith("ReportDuration")) {// [REQ7]
                result = controller.reportDuration(option);
            } else if (option.startsWith("ReportEarliestFinishTime")) {// [REQ8]
                result = controller.reportEarliestFinishTime(option);
            } else if (option.startsWith("DefineBasicCriterion")) {// [REQ9]
                result = controller.defineBasicCriterion(option);
            } else if (option.startsWith("DefineNegatedCriterion")) {// [REQ11]
                result = controller.defineNegatedCriterion(option);
            } else if (option.startsWith("DefineBinaryCriterion")) {// [REQ11]
                result = controller.defineBinaryCriterion(option);
            } else if (option.startsWith("PrintAllCriterion")) {// [REQ12]
                result = controller.printAllCriterion();
            } else if (option.startsWith("Search")) {// [REQ13]
                result = controller.search(option);
            } else if (option.startsWith("Store")) {// [REQ14]
                result = controller.store(option);
            } else if (option.startsWith("Load")) {// [REQ15]
                result = controller.store(option);
            } else if (option.startsWith("Undo")) {// [REQ15]
                result = controller.undo();
            } else if (option.startsWith("Redo")) {// [REQ15]
                result = controller.redo();
                redo = true;
                redoCommand = result.getMessage();
                System.out.println("Redo start!");
                continue;
            } else if (option.startsWith("Quit")) { // [REQ16] 1 points
                return;
            }
            if (null != result) {
                System.out.println(result.getMessage());
            }
        }
    }

    /**
     * Returns the menu for the command line interface.
     *
     * @return a string representing the menu
     */
    private static String getMenu() {
        return "| ----------< MENU >-------\n" +
                "| CreateSimpleTask(can redo)\n" +
                "| CreateCompositeTask(can redo)\n" +
                "| DeleteTask(can redo)\n" +
                "| ChangeTask(can redo)\n" +
                "| PrintTask\n" +
                "| PrintAllTasks\n" +
                "| ReportDuration\n" +
                "| ReportEarliestFinishTime\n" +
                "| DefineBasicCriterion(can redo)\n" +
                "| DefineNegatedCriterion(can redo)\n" +
                "| DefineBinaryCriterion(can redo)\n" +
                "| PrintAllCriterion\n" +
                "| Search\n" +
                "| Store\n" +
                "| Load\n" +
                "| Help\n" +
                "| Undo\n" +
                "| Redo\n" +
                "| Quit\n" +
                "| -------------------------\n";
    }

    /**
     * Returns the help message for the command line interface.
     *
     * @return a string representing the help message
     */
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
