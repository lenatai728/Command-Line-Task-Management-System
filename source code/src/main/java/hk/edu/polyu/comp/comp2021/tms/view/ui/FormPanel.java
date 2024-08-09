package hk.edu.polyu.comp.comp2021.tms.view.ui;

import hk.edu.polyu.comp.comp2021.tms.controller.UIController;
import hk.edu.polyu.comp.comp2021.tms.model.Result;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This abstract class provides a general form for user interfaces.
 */
public abstract class FormPanel extends JPanel {
    /**
     * The UIController object that used to interact with FormPanel class.
     */
    protected UIController controller;

    /**
     * The number of rows for GridLayout.
     */
    private static final int GRID_LAYOUT_ROW = 20;

    /**
     * The number of columns for GridLayout.
     */
    private static final int GRID_LAYOUT_COL = 1;

    /**
     * The width of the label component.
     */
    private static final int LABEL_WIDTH = 100;

    /**
     * The height of the label component.
     */
    private static final int LABEL_HEIGHT = 20;

    /**
     * The width of the field component.
     */
    private static final int FIELD_WIDTH = 200;

    /**
     * The height of the field component.
     */
    private static final int FIELD_HEIGHT = 20;

    /**
     * The width of the text area component.
     */
    private static final int TEXT_AREA_WIDTH = 300;

    /**
     * The height of the text area component.
     */
    private static final int TEXT_AREA_HEIGHT = 200;

    /**
     * The width of the scroll pane component.
     */
    private static final int SCROLL_PANE_WIDTH = 500;

    /**
     * The height of the scroll pane component.
     */
    private static final int SCROLL_PANE_HEIGHT = 450;

    /**
     * The width of the box component.
     */
    private static final int BOX_WIDTH = 500;

    /**
     * The height of the box component.
     */
    private static final int BOX_HEIGHT = 220;

    /**
     * Constructs a FormPanel with a given UIController.
     *
     * @param controller the UIController that controls the user interface.
     */
    public FormPanel(UIController controller) {
        this.controller = controller;
        GridLayout boxLayout = new GridLayout(GRID_LAYOUT_ROW, GRID_LAYOUT_COL);
        boxLayout.setVgap(4);
        setLayout(boxLayout);
    }

    /**
     * Adds a field with a label to the form.
     *
     * @param labelText the text of the label.
     * @return the added field.
     */
    public JTextField addField(String labelText) {
        JLabel label = new JLabel(labelText);
        JTextField field = new JTextField();
        add(label);
        add(field);
        return field;
    }

    /**
     * Adds a field with a label to a given container. The label and field are arranged
     * in a horizontal box, which is then added to the container.
     *
     * @param labelText the text of the label.
     * @param container the container to which the field and label are added.
     * @return the added field.
     */
    public JTextField addField(String labelText, Box container) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(labelText);
        JTextField field = new JTextField();
        label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        field.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        box.add(label);
        box.add(field);

        container.add(box);
        return field;
    }

    /**
     * Adds a single text area to the given container.
     *
     * @param container the container to which the text area is added.
     * @return the added text area.
     */

    public JTextArea addSingleText(Box container) {
        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT));
        container.add(textArea);
        return textArea;
    }

    /**
     * Adds a scrollable text area to the given container.
     *
     * @param container the container to which the scrollable text area is added.
     * @return the added scrollable text area.
     */
    public JTextArea addScrollText(Box container) {

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT));

        container.add(scrollPane);
        return textArea;
    }

    /**
     * Adds a scrollable text area to this form panel.
     *
     * @return the added scrollable text area.
     */
    public JTextArea addText() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT));

        add(scrollPane);
        return textArea;
    }

    /**
     * Creates a vertical box, adds it to this form panel and returns it.
     *
     * @return the created vertical box.
     */
    public Box getContainer() {
        Box box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        add(box);
        return box;
    }

    /**
     * Adds a button with a given text to this form panel.
     *
     * @param text the text of the button.
     * @return the added button.
     */
    public JButton addButton(String text) {
        JButton button = new JButton(text);
        add(button);
        return button;
    }

    /**
     * Submits the form. This method is abstract and should be implemented by subclasses.
     *
     * @return a Result object containing the result of the submission.
     */
    public abstract Result submit();


}
