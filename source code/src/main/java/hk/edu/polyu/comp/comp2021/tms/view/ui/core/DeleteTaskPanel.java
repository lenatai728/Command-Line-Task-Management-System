package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This panel allows the user to delete a task.
 */
public class DeleteTaskPanel extends FormPanel {
    private JTextField name;

    /**
     * Constructs a DeleteTaskPanel with a given UIController.
     *
     * @param controller the UIController that controls the user interface.
     */
    public DeleteTaskPanel(UIController controller) {
        super(controller);
        name = addField("name");
    }

    @Override
    public Result submit() {
        return controller.deleteTask(name.getText());
    }
}
