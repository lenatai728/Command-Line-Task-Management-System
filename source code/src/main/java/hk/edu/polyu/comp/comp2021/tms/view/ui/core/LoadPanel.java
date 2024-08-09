package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

import javax.swing.*;

/**
 * This class represents a GUI panel for loading data from a specified path.
 * It extends the FormPanel class and is used within the context of a larger GUI.
 */
public class LoadPanel extends FormPanel {
    private JTextField path;

    /**
     * Constructs a LoadPanel with a specified UIController.
     *
     * @param controller the UIController to handle interactions with this panel
     */
    public LoadPanel(UIController controller) {
        super(controller);
        path = addField("path");
    }

    @Override
    public Result submit() {
        return controller.load(path.getText());
    }
}
