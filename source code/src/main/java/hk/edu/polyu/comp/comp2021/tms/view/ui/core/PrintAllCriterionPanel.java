package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * The PrintAllCriterionPanel class extends FormPanel,
 * providing the functionality to print all criteria.
 */
public class PrintAllCriterionPanel extends FormPanel {

    private JTextArea area;

    /**
     * Constructs a new PrintAllCriterionPanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public PrintAllCriterionPanel(UIController controller) {
        super(controller);
        area = addText();
    }

    @Override
    public Result submit() {
        Result result = controller.printAllCriterion();
        area.setText(result.getUiMessage());
        return new Result(true, false);
    }
}
