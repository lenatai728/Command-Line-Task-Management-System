package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This class represents a panel for creating a simple task.
 */
public class CreateSimpleTaskPanel extends FormPanel {
    private JTextField name;
    private JTextField description;
    private JTextField duration;
    private JTextField prerequisites;

    /**
     * Constructs a CreateSimpleTaskPanel.
     *
     * @param controller The UIController to be used for the task creation operations.
     */
    public CreateSimpleTaskPanel(UIController controller) {
        super(controller);
        name = addField("name");
        description = addField("description");
        duration = addField("duration");
        prerequisites = addField("prerequisites");
    }

    @Override
    public Result submit() {
        Result result = controller.createSimpleTask(name.getText(), description.getText(), duration.getText(), prerequisites.getText());
        if (result.isSuccess()) {
            return new Result(true, false, result.getMessage());
        } else {
            return new Result(false, false, result.getMessage());
        }
    }
}
