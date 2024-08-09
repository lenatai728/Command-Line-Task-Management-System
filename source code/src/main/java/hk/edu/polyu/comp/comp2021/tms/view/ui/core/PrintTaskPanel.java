package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The PrintTaskPanel class extends FormPanel, providing the functionality to print a specified task.
 */
public class PrintTaskPanel extends FormPanel {
    private JTextField name;
    private JTextArea area;
    private Box panel;

    /**
     * Constructs a new PrintTaskPanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public PrintTaskPanel(UIController controller) {
        super(controller);
        panel = getContainer();
        name = addField("name", panel);
        area = addSingleText(panel);
    }

    @Override
    public Result submit() {
        Result result = controller.printTask(name.getText());
        if (!TextUtils.isEmpty(result.getUiMessage())) {
            area.setText(result.getUiMessage());
        } else {
            area.setText(result.getMessage());
        }
        return new Result(true, false);
    }
}
