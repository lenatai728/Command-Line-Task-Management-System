package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This class represents a panel for creating a composite task.
 */
public class CreateCompositeTaskPanel extends FormPanel {
    private JTextField name;
    private JTextField description;
    private JTextField subTask;

    /**
     * Constructs a CreateCompositeTaskPanel.
     *
     * @param controller The UIController to be used for the task creation operations.
     */
    public CreateCompositeTaskPanel(UIController controller) {
        super(controller);
        name = addField("name");
        description = addField("description");
        subTask = addField("sub task");
    }

    @Override
    public Result submit() {
        Result result = controller.createCompositeTask(name.getText(), description.getText(), subTask.getText());
        if (result.isSuccess()) {
            return new Result(true, false, result.getMessage());
        } else {
            return new Result(false, false, result.getMessage());
        }
    }
}
