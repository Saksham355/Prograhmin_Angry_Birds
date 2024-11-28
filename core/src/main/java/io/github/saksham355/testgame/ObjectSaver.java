package io.github.saksham355.testgame;

import java.io.*;

public class ObjectSaver {
    public static void saveObject(Object obj, String filename,boolean appendstatus) {
        try {
            boolean append = new File(filename).exists();
            FileOutputStream fos = new FileOutputStream(filename, append);
            ObjectOutputStream oos = append ? new AppendingObjectOutputStream(fos) : new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveObject(Object obj, String filename) {
        try {

            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);
            oos.close();
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

