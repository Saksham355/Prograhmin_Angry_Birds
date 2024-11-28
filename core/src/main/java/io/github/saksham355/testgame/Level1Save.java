package io.github.saksham355.testgame;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Level1Save implements Serializable {

    Vector2 pigPosition;
    Vector2 w1Position;
    Vector2 w2Position;
    Vector2 w3Position;
    Vector2 w4Position;
    Vector2 redBirdPosition;
    Vector2 yellowBirdPosition;
    boolean redBird;
    boolean yellowBird;
    boolean blackBird;
    boolean pig;
    boolean w1;
    boolean w2;
    boolean w3;
    boolean w4;
    boolean redBirdStatic;
    boolean yellowBirdStatic;

    public Level1Save(Vector2 pigPosition, Vector2 w1Position, Vector2 w2Position, Vector2 w3Position, Vector2 w4Position,
                      Vector2 redBirdPosition, Vector2 yellowBirdPosition,
                      boolean redBird, boolean yellowBird, boolean blackBird, boolean pig,
                      boolean w1, boolean w2, boolean w3, boolean w4,
                      boolean redBirdStatic, boolean yellowBirdStatic) {
        this.pigPosition = pigPosition;
        this.w1Position = w1Position;
        this.w2Position = w2Position;
        this.w3Position = w3Position;
        this.w4Position = w4Position;
        this.redBirdPosition = redBirdPosition;
        this.yellowBirdPosition = yellowBirdPosition;
        this.redBird = redBird;
        this.yellowBird = yellowBird;
        this.blackBird = blackBird;
        this.pig = pig;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
        this.redBirdStatic = redBirdStatic;
        this.yellowBirdStatic = yellowBirdStatic;
    }

    public Vector2 getPigPosition() {
        return pigPosition;
    }

    public Vector2 getW1Position() {
        return w1Position;
    }

    public Vector2 getW2Position() {
        return w2Position;
    }

    public Vector2 getW3Position() {
        return w3Position;
    }

    public Vector2 getW4Position() {
        return w4Position;
    }

    public Vector2 getRedBirdPosition() { // Added
        return redBirdPosition;
    }

    public Vector2 getYellowBirdPosition() { // Added
        return yellowBirdPosition;
    }

    public boolean isRedBird() {
        return redBird;
    }

    public boolean isYellowBird() {
        return yellowBird;
    }

    public boolean isBlackBird() {
        return blackBird;
    }

    public boolean isPig() {
        return pig;
    }

    public boolean isW1() {
        return w1;
    }

    public boolean isW2() {
        return w2;
    }

    public boolean isW3() {
        return w3;
    }

    public boolean isW4() {
        return w4;
    }

    public boolean isRedBirdStatic() { return redBirdStatic; }

    public boolean isYellowBirdStatic() { // Added
        return yellowBirdStatic;
    }
}
