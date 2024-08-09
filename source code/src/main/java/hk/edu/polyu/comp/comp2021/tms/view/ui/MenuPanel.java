package hk.edu.polyu.comp.comp2021.tms.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This is a Java Swing UI panel class.
 * It creates a panel with multiple buttons, each with a corresponding action.
 */
public class MenuPanel extends JPanel {
    private OnClickListener listener;
    /**
     * The number of rows for GridLayout.
     */
    private static final int GRID_LAYOUT_ROW = 18;
    /**
     * The number of columns for GridLayout.
     */
    private static final int GRID_LAYOUT_COL = 1;

    /**
     * Constructs a new MenuPanel.
     * Initializes the layout and adds buttons with their corresponding actions.
     */
    public MenuPanel() {
        GridLayout boxLayout = new GridLayout(GRID_LAYOUT_ROW, GRID_LAYOUT_COL);
        boxLayout.setVgap(4);
        setLayout(boxLayout);
        addButton("CreateSimpleTask(can redo)", e -> listener.onCreateSimpleTask());
        addButton("CreateCompositeTask(can redo)", e -> listener.onCreateCompositeTask());
        addButton("DeleteTask(can redo)", e -> listener.onDeleteTask());
        addButton("ChangeTask(can redo)", e -> listener.onChangeTask());
        addButton("PrintTask", e -> listener.onPrintTask());
        addButton("PrintAllTasks", e -> listener.onPrintAllTasks());
        addButton("ReportDuration", e -> listener.onReportDuration());
        addButton("ReportEarliestFinishTime", e -> listener.onReportEarliestFinishTime());
        addButton("DefineBasicCriterion(can redo)", e -> listener.onDefineBasicCriterion());
        addButton("DefineNegatedCriterion(can redo)", e -> listener.onDefineNegatedCriterion());
        addButton("DefineBinaryCriterion(can redo)", e -> listener.onDefineBinaryCriterion());
        addButton("PrintAllCriterion", e -> listener.onPrintAllCriterion());
        addButton("Search", e -> listener.onSearch());
        addButton("Store", e -> listener.onStore());
        addButton("Load", e -> listener.onLoad());
        addButton("Undo", e -> listener.onUndo());
        addButton("Redo", e -> listener.onRedo());
        addButton("Quit", e -> listener.onQuit());
    }

    /**
     * Sets the OnClickListener for this panel.
     *
     * @param listener the OnClickListener to be set
     */
    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Adds a button with a title and an ActionListener to the panel.
     *
     * @param title the title of the button
     * @param listener the ActionListener of the button
     */
    public void addButton(String title, ActionListener listener) {
        JButton button = new JButton();
        button.setText(title);
        button.addActionListener(listener);
        add(button);
    }

    /**
     * This interface defines the actions to be performed when buttons are clicked.
     */
    public interface OnClickListener {
        /**
         * Called when a simple task creation event occurs.
         */
        void onCreateSimpleTask();
        /**
         * Called when a composite task creation event occurs.
         */
        void onCreateCompositeTask();
        /**
         * Called when a delete task event occurs.
         */
        void onDeleteTask();
        /**
         * Called when a change task event occurs.
         */
        void onChangeTask();
        /**
         * Called when a print specific task event occurs.
         */
        void onPrintTask();
        /**
         * Called when a print all tasks event occurs.
         */
        void onPrintAllTasks();
        /**
         * Called when a report duration event occurs.
         */
        void onReportDuration();
        /**
         * Called when a report earliest finish time  event occurs.
         */
        void onReportEarliestFinishTime();
        /**
         * Called when a define basic criterion event occurs.
         */
        void onDefineBasicCriterion();
        /**
         * Called when a define negate criterion event occurs.
         */
        void onDefineNegatedCriterion();
        /**
         * Called when a define binary criterion event occurs.
         */
        void onDefineBinaryCriterion();
        /**
         * Called when a print all criterion event occurs.
         */
        void onPrintAllCriterion();
        /**
         * Called when a search event occurs.
         */
        void onSearch();
        /**
         * Called when a store event occurs.
         */
        void onStore();
        /**
         * Called when a load event occurs.
         */
        void onLoad();
        /**
         * Called when a undo event occurs.
         */
        void onUndo();
        /**
         * Called when a redo event occurs.
         */
        void onRedo();
        /**
         * Called when a quit event occurs.
         */
        void onQuit();
    }
}
