package hk.edu.polyu.comp.comp2021.tms.view.ui;

import hk.edu.polyu.comp.comp2021.tms.model.Result;
import hk.edu.polyu.comp.comp2021.tms.utils.TextUtils;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a content panel in the user interface, which includes a form panel for user input
 * and a confirm button to submit the form.
 */
public class ContentPanel extends JPanel {
    private FormPanel formPanel;

    /**
     * The width of the frame.
     */
    private static final int FRAME_WIDTH = 700;

    /**
     * The height of the frame.
     */
    private static final int FRAME_HEIGHT = 500;

    /**
     * Constructs a ContentPanel with a BorderLayout and a confirm button.
     */
    public ContentPanel() {
        BorderLayout borderLayout = new BorderLayout(10, 10);
        setLayout(borderLayout);

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        JButton button = new JButton();
        button.setText("Confirm");
        button.addActionListener(e -> {
            if (null == formPanel) {
                return;
            }
            Result result = formPanel.submit();
            if (result.isSuccess()) {
                if (!TextUtils.isEmpty(result.getMessage())) {
                    JOptionPane.showMessageDialog(null, result.getMessage());
                }
                if (result.isClose()) {
                    removeForm();
                }
            } else {
                if (!TextUtils.isEmpty(result.getMessage())) {
                    JOptionPane.showMessageDialog(null, result.getMessage());
                }
            }
        });
        add(button, BorderLayout.PAGE_END);

    }

    /**
     * Adds a form panel to this content panel.
     *
     * @param panel The FormPanel to be added.
     */
    public void addForm(FormPanel panel) {
        removeForm();
        this.formPanel = panel;
        add(panel, BorderLayout.PAGE_START);
        repaint();//刷新页面，重绘面板
        validate();//使重绘的面板确认生效
    }

    /**
     * Removes the current form panel from this content panel.
     */
    public void removeForm() {
        if (null != formPanel) {
            remove(formPanel);
            repaint();
            validate();
        }
    }
}
