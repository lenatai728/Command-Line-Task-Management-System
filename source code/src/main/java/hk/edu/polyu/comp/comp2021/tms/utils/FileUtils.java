package hk.edu.polyu.comp.comp2021.tms.utils;

import java.io.*;

/**
 * This class provides utility methods for file operations such as save and read.
 */
public final class FileUtils {
    private FileUtils() {
    }

    /**
     * Saves an object to a file with a given filename.
     *
     * @param o        the object to be saved.
     * @param filename the name of the file where the object is to be saved.
     */
    public static void save(Object o, String filename) {
        try (FileOutputStream outStr = new FileOutputStream(filename);
             ObjectOutputStream outObj = new ObjectOutputStream(outStr)) {
            outObj.writeObject(o);
            outObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an object from a file with a given filename.
     *
     * @param filename the name of the file from which the object is to be read.
     * @return the object read from the file, or null if the file does not exist.
     */
    public static Object read(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        Object o = null;
        try (FileInputStream inStr = new FileInputStream(filename);
             ObjectInputStream inObj = new ObjectInputStream(inStr)){
            o = inObj.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return o;
    }

}
