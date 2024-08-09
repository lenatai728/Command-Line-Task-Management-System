package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This panel allows the user to change the properties of a task.
 */
public class ChangeTaskPanel extends FormPanel {
    private JTextField name;
    private JTextField property;
    private JTextField newValue;

    /**
     * Constructs a ChangeTaskPanel.
     * @param controller The UIController to be used for the change task operations.
     */
    public ChangeTaskPanel(UIController controller) {
        super(controller);
        name = addField("name");
        property = addField("property");
        newValue = addField("new value");
    }

    @Override
    public Result submit() {
        return controller.changeTask(name.getText(), property.getText(), newValue.getText());
    }
}
