package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This panel allows the user to define a basic criterion.
 */
public class DefineBasicCriterionPanel extends FormPanel {
    private JTextField name;
    private JTextField property;
    private JTextField op;
    private JTextField value;

    /**
     * Constructs a DefineBasicCriterionPanel with a given UIController.
     *
     * @param controller the UIController that controls the user interface.
     */
    public DefineBasicCriterionPanel(UIController controller) {
        super(controller);
        name = addField("name");
        property = addField("property");
        op = addField("op");
        value = addField("value");
    }

    @Override
    public Result submit() {
        Result result = controller.defineBasicCriterion(name.getText(), property.getText(), op.getText(), value.getText());
        if (result.isSuccess()) {
            return new Result(true, false, result.getMessage());
        } else {
            return new Result(false, false, result.getMessage());
        }
    }
}
