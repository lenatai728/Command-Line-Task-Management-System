package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * The PrintAllTasksPanel class extends FormPanel,
 * providing the functionality to print all tasks.
 */
public class PrintAllTasksPanel extends FormPanel {
    private JTextArea area;

    /**
     * Constructs a new PrintAllTasksPanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public PrintAllTasksPanel(UIController controller) {
        super(controller);
        area = addText();
    }

    @Override
    public Result submit() {
        Result result = controller.printAllTasks();
        area.setText(result.getUiMessage());
        return new Result(true, false);
    }
}
