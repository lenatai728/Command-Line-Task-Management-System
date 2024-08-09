package hk.edu.polyu.comp.comp2021.tms.utils;

/**
 * A utility class that provides static methods for working with text.
 */
public final class TextUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private TextUtils() {}

    /**
     * Checks whether two strings are equal.
     *
     * @param a the first string
     * @param b the second string
     * @return true if the strings are equal, false otherwise
     */
    public static boolean equals(String a, String b) {
        if (a != null) {
            return a.equals(b);
        } else {
            return b == null;
        }
    }

    /**
     * Checks whether a string is null or empty.
     *
     * @param a the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String a) {
        return null == a || "".equals(a);
    }

    /**
     * Checks if a string contains a certain substring.
     *
     * @param origin the original string to search within
     * @param keywords the substring to search for
     * @return true if the original string contains the substring, false otherwise
     */
    public static boolean contains(String origin, String keywords) {
        if (isEmpty(origin)) {
            return false;
        } else {
            return origin.contains(keywords);
        }
    }

    /**
     * Checks whether the given string can be parsed to an integer.
     *
     * @param string the string to check
     * @return true if the string can be parsed to an integer, false otherwise
     */
    public static boolean checkInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string can be parsed to a long.
     *
     * @param string the string to check
     * @return true if the string can be parsed to a long, false otherwise
     */
    public static boolean checkLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string can be parsed to a float.
     *
     * @param string the string to check
     * @return true if the string can be parsed to a float, false otherwise
     */
    public static boolean checkFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string can be parsed to a double.
     *
     * @param string the string to check
     * @return true if the string can be parsed to a double, false otherwise
     */
    public static boolean checkDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses a string to an integer.
     *
     * @param string the string to parse
     * @return the parsed integer, or -1 if the string cannot be parsed
     */
    public static int toInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Parses a string to a long.
     *
     * @param string the string to parse
     * @return the parsed long, or -1 if the string cannot be parsed
     */
    public static long toLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    /**
     * Parses a string to a float.
     *
     * @param string the string to parse
     * @return the parsed float, or -1 if the string cannot be parsed
     */
    public static float toFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Parses a string to a double.
     *
     * @param string the string to parse
     * @return the parsed double, or -1 if the string cannot be parsed
     */
    public static double toDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks whether a string matches the name pattern.
     * The pattern is: only English letters and digits.
     *
     * @param name the string to check
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean checkName(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }

    /**
     * Checks whether a string matches the description pattern.
     * The pattern is: English letters, digits, and the hyphen letter(-).
     *
     * @param name the string to check
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean checkDescription(String name) {
        return name.matches("[a-zA-Z0-9-]+");
    }

    /**
     * Checks whether the first character of a string is a digit.
     *
     * @param name the string to check
     * @return true if the first character is a digit, false otherwise
     */
    public static boolean firstDigits(String name) {
        return Character.isDigit(name.charAt(0));
    }

    /**
     * Checks whether a string contains at most eight characters.
     *
     * @param name the string to check
     * @return true if the string contains at most eightcharacters, false otherwise
     */
    public static boolean checkNameLength(String name) {
        return name.length() <= 8;
    }
}
