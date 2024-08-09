package hk.edu.polyu.comp.comp2021.tms.utils;

import java.util.Scanner;

/**
 * Utility class for reading various types of input from the console.
 * This class cannot be instantiated or extended.
 */
public final class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    private InputUtils() {

    }

    /**
     * Reads a line of text from the console.
     *
     * @param message the message to display to the user
     * @return the line of text entered by the user
     */
    public static String read(String message) {
        System.out.println(message + ":");
        while (true) {
            String result = scanner.nextLine();
            if (TextUtils.isEmpty(result)) {
                System.out.println("Input invalid!");
            } else {
                return result;
            }
        }
    }

    /**
     * Reads a line of text from the console.
     *
     * @param message the message to display to the user
     * @param canEmpty check whether the message can be empty or not
     * @return the line of text entered by the user
     */
    public static String read(String message, boolean canEmpty) {
        System.out.println(message + ":");
        while (true) {
            String result = scanner.nextLine();
            if (canEmpty) {
                return result;
            }
            if (TextUtils.isEmpty(result)) {
                System.out.println("Input invalid!");
            } else {
                return result;
            }
        }
    }

    /**
     * Reads a line of integer from the console.
     *
     * @param message the message to display to the user
     * @return the line of text entered by the user
     */
    public static String readInt(String message) {
        System.out.println(message + ":");
        while (true) {
            String result = scanner.nextLine();
            if (TextUtils.isEmpty(result) || !TextUtils.checkInt(result)) {
                System.out.println("Input invalid!");
            } else {
                return result;
            }
        }
    }

    /**
     * Reads a line of numbers from the console.
     *
     * @param message the message to display to the user
     * @return the line of text entered by the user
     */
    public static String readLong(String message) {
        System.out.println(message + ":");
        while (true) {
            String result = scanner.nextLine();
            if (TextUtils.isEmpty(result) || !TextUtils.checkLong(message)) {
                System.out.println("Input invalid!");
            } else {
                return result;
            }
        }
    }

    /**
     * Reads a line of numbers from the console.
     *
     * @param message the message to display to the user
     * @return the line of text entered by the user
     */
    public static String readFloat(String message) {
        System.out.println(message + ":");
        while (true) {
            String result = scanner.nextLine();
            if (TextUtils.isEmpty(result) || !TextUtils.checkFloat(message)) {
                System.out.println("Input invalid!");
            } else {
                return result;
            }
        }
    }

}
