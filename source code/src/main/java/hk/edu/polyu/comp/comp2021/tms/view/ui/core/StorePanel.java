package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * The StorePanel class extends FormPanel,
 * providing the functionality to store.
 */
public class StorePanel extends FormPanel {
    private JTextField path;

    /**
     * Constructs a new StorePanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public StorePanel(UIController controller) {
        super(controller);
        path = addField("path");
    }

    @Override
    public Result submit() {
        return controller.store(path.getText());
    }
}
