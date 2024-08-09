package hk.edu.polyu.comp.comp2021.tms.view.ui;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.view.ui.core.*;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main panel of the application, providing an interface for user interaction.
 * Implements the MenuPanel.OnClickListener interface to handle menu actions.
 */
public class HomePanel extends JFrame implements MenuPanel.OnClickListener {
    // ... Class member declarations ...
    private Container container;
    /**
     * The UIController object that used to interact with HomePanel class.
     */
    protected UIController controller = new UIController();
    private MenuPanel menuPanel = new MenuPanel();
    private ContentPanel contentPanel = new ContentPanel();

    private final CreateSimpleTaskPanel createSimpleTaskPanel = new CreateSimpleTaskPanel(controller);
    private final ChangeTaskPanel changeTaskPanel = new ChangeTaskPanel(controller);
    private final CreateCompositeTaskPanel createCompositeTaskPanel = new CreateCompositeTaskPanel(controller);
    private final DefineBasicCriterionPanel defineBasicCriterionPanel = new DefineBasicCriterionPanel(controller);
    private final DefineBinaryCriterionPanel defineBinaryCriterionPanel = new DefineBinaryCriterionPanel(controller);
    private final DefineNegatedCriterionPanel defineNegatedCriterionPanel = new DefineNegatedCriterionPanel(controller);
    private final DeleteTaskPanel deleteTaskPanel = new DeleteTaskPanel(controller);
    private final LoadPanel loadPanel = new LoadPanel(controller);
    private final PrintAllCriterionPanel printAllCriterionPanel = new PrintAllCriterionPanel(controller);
    private final PrintAllTasksPanel printAllTasksPanel = new PrintAllTasksPanel(controller);
    private final PrintTaskPanel printTaskPanel = new PrintTaskPanel(controller);
    private final UndoPanel undoPanel = new UndoPanel(controller);
    private final RedoPanel redoPanel = new RedoPanel(controller, this);
    private final ReportDurationPanel reportDurationPanel = new ReportDurationPanel(controller);
    private final ReportEarliestFinishTimePanel reportEarliestFinishTimePanel = new ReportEarliestFinishTimePanel(controller);
    private final SearchPanel searchPanel = new SearchPanel(controller);
    private final StorePanel storePanel = new StorePanel(controller);

    /**
            * Constructs a HomePanel with a given title.
     *
             * @param title the title of the HomePanel.
            * @throws HeadlessException if the GraphicsEnvironment.isHeadless() returns true.
            */
    public HomePanel(String title) throws HeadlessException {
        super(title);
        setResizable(false);
        init();
    }

    /**
     * Initializes the HomePanel by setting up its layout and components.
     */
    public void init() {
        container = getContentPane();

        BorderLayout borderLayout = new BorderLayout(10, 10);
        setLayout(borderLayout);

        menuPanel.setListener(this);
        container.add(menuPanel, BorderLayout.LINE_START);
        container.add(contentPanel, BorderLayout.LINE_END);
    }

    @Override
    public void onCreateSimpleTask() {
        contentPanel.addForm(createSimpleTaskPanel);
    }

    @Override
    public void onCreateCompositeTask() {
        contentPanel.addForm(createCompositeTaskPanel);
    }

    @Override
    public void onDeleteTask() {
        contentPanel.addForm(deleteTaskPanel);
    }

    @Override
    public void onChangeTask() {
        contentPanel.addForm(changeTaskPanel);
    }

    @Override
    public void onPrintTask() {
        contentPanel.addForm(printTaskPanel);
    }

    @Override
    public void onPrintAllTasks() {
        contentPanel.addForm(printAllTasksPanel);
    }

    @Override
    public void onReportDuration() {
        contentPanel.addForm(reportDurationPanel);
    }

    @Override
    public void onReportEarliestFinishTime() {
        contentPanel.addForm(reportEarliestFinishTimePanel);
    }

    @Override
    public void onDefineBasicCriterion() {
        contentPanel.addForm(defineBasicCriterionPanel);
    }

    @Override
    public void onDefineNegatedCriterion() {
        contentPanel.addForm(defineNegatedCriterionPanel);
    }

    @Override
    public void onDefineBinaryCriterion() {
        contentPanel.addForm(defineBinaryCriterionPanel);
    }

    @Override
    public void onPrintAllCriterion() {
        contentPanel.addForm(printAllCriterionPanel);
    }

    @Override
    public void onSearch() {
        contentPanel.addForm(searchPanel);
    }

    @Override
    public void onStore() {
        contentPanel.addForm(storePanel);
    }

    @Override
    public void onLoad() {
        contentPanel.addForm(loadPanel);
    }
    @Override
    public void onUndo() {
        contentPanel.addForm(undoPanel);
    }

    @Override
    public void onRedo() {
        contentPanel.addForm(redoPanel);
    }

    @Override
    public void onQuit() {
        System.exit(0);
    }
}
