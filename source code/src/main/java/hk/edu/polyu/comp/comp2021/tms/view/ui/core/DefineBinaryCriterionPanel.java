package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This panel allows the user to define a binary criterion.
 */
public class DefineBinaryCriterionPanel extends FormPanel {
    private JTextField name;
    private JTextField name2;
    private JTextField logicOp;
    private JTextField name3;

    /**
     * Constructs a DefineBinaryCriterionPanel with a given UIController.
     *
     * @param controller the UIController that controls the user interface.
     */
    public DefineBinaryCriterionPanel(UIController controller) {
        super(controller);
        name = addField("name");
        name2 = addField("name2");
        logicOp = addField("logic op");
        name3 = addField("name3");
    }

    @Override
    public Result submit() {
        Result result = controller.defineBinaryCriterion(name.getText(), name2.getText(), logicOp.getText(), name3.getText());
        if (result.isSuccess()) {
            return new Result(true, false, result.getMessage());
        } else {
            return new Result(false, false, result.getMessage());
        }
    }
}
