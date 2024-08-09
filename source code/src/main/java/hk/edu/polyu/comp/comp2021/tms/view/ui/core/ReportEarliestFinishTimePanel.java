package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * The ReportEarliestFinishTimePanel class extends FormPanel,
 * providing the functionality to report the earliest finish time.
 */
public class ReportEarliestFinishTimePanel extends FormPanel {
    private JTextField name;
    private JTextArea area;
    private Box panel;

    /**
     * Constructs a new ReportEarliestFinishTimePanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public ReportEarliestFinishTimePanel(UIController controller) {
        super(controller);
        panel = getContainer();
        name = addField("name", panel);
        area = addSingleText(panel);
    }

    @Override
    public Result submit() {
        Result result = controller.reportEarliestFinishTime(name.getText());
        if (!TextUtils.isEmpty(result.getUiMessage())) {
            area.setText(result.getUiMessage());
        } else {
            area.setText(result.getMessage());
        }
        return new Result(true, false);
    }
}
