package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;

/**
 * The UndoPanel class extends FormPanel,
 * providing the functionality to undo.
 */
public class UndoPanel extends FormPanel {

    /**
     * Constructs a new UndoPanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     */
    public UndoPanel(UIController controller) {
        super(controller);
    }

    @Override
    public Result submit() {
        return controller.undo();
    }
}
