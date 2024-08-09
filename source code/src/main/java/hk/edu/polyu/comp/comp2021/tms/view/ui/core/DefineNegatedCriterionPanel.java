package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This panel allows the user to define a negated criterion.
 */
public class DefineNegatedCriterionPanel extends FormPanel {
    private JTextField name;
    private JTextField negatedName;

    /**
     * Constructs a DefineNegatedCriterionPanel with a given UIController.
     *
     * @param controller the UIController that controls the user interface.
     */
    public DefineNegatedCriterionPanel(UIController controller) {
        super(controller);
        name = addField("name");
        negatedName = addField("negated name");
    }

    @Override
    public Result submit() {
        Result result = controller.defineNegatedCriterion(name.getText(), negatedName.getText());
        if (result.isSuccess()) {
            return new Result(true, false, result.getMessage());
        } else {
            return new Result(false, false, result.getMessage());
        }
    }
}
