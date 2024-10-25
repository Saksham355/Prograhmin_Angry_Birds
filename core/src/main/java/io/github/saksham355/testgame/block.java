package io.github.saksham355.testgame;



public class block {
    String type;
    String img;
    int strength;
    boolean above;
    void decreaseStrength() {}
    void eliminateBlock() {}
    public block(String type, String img) {
        this.type = type;
        this.img = img;
    }
}
