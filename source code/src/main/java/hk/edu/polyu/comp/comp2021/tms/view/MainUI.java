package hk.edu.polyu.comp.comp2021.tms.view;

import hk.edu.polyu.comp.comp2021.tms.view.ui.HomePanel;

import javax.swing.*;

/**
 * This is the main class for a Java Swing application.
 * It creates a HomePanel and sets its properties.
 */
public class MainUI {
    /**
     * The width of the frame.
     */
    private static final int FRAME_WIDTH = 768;
    /**
     * The height of the frame.
     */
    private static final int FRAME_HEIGHT = 1280;
    /**
     * The x coordinate of the frame.
     */
    private static final int FRAME_X = 300;
    /**
     * The y coordinate of the frame.
     */
    private static final int FRAME_Y = 100;

    /**
     * The main method is the entry point of the application.
     * It creates a HomePanel, sets its properties, and makes it visible.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HomePanel frame = new HomePanel("Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.pack();
        frame.setVisible(true);
    }

}
