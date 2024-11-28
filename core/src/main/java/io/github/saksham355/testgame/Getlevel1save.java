package io.github.saksham355.testgame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Getlevel1save {
    public static Level1Save getlevel1State(String filename) {
        Level1Save getData = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            getData = (Level1Save) in.readObject();

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException while loading save data: " + e.getMessage());
        }
        return getData;
    }
}
