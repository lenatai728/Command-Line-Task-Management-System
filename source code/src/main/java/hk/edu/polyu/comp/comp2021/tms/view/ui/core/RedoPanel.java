package hk.edu.polyu.comp.comp2021.tms.view.ui.core;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.view.ui.FormPanel;
import hk.edu.polyu.comp.comp2021.tms.view.ui.MenuPanel;

/**
 * The RedoPanel class extends FormPanel,
 * providing the functionality to redo a previously undone command.
 */
public class RedoPanel extends FormPanel {

    private MenuPanel.OnClickListener listener;

    /**
     * Constructs a new RedoPanel.
     *
     * @param controller the UIController that manages user interactions for the UI
     * @param listener an OnClickListener object for handling click events
     */
    public RedoPanel(UIController controller, MenuPanel.OnClickListener listener) {
        super(controller);
        this.listener = listener;
    }

    @Override
    public Result submit() {
        Result result = controller.redo();
        String command = result.getMessage();

        switch (command) {
            case "CreateSimpleTask":
                listener.onCreateSimpleTask();
                break;
            case "CreateCompositeTask":
                listener.onCreateCompositeTask();
                break;
            case "DeleteTask":
                listener.onDeleteTask();
                break;
            case "ChangeTask":
                listener.onChangeTask();
                break;
            case "DefineBasicCriterion":
                listener.onDefineBasicCriterion();
                break;
            case "DefineNegatedCriterion":
                listener.onDefineNegatedCriterion();
                break;
            case "DefineBinaryCriterion":
                listener.onDefineBinaryCriterion();
                break;
        }
        return new Result(true, false, "Redo Start!");
    }
}
