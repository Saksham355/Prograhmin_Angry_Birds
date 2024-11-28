package io.github.saksham355.testgame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Getlevel2save {
    public static Level2Save getlevel2State(String filename) {
        Level2Save getData = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            getData = (Level2Save) in.readObject();

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException while loading save data: " + e.getMessage());
        }
        return getData;
    }
}
