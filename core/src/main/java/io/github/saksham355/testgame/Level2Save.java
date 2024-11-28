package io.github.saksham355.testgame;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Level2Save implements Serializable {

    Vector2 pigPosition;
    Vector2 w1Position;
    Vector2 w2Position;
    Vector2 w3Position;
    Vector2 w4Position;
    Vector2 redBirdPosition;
    Vector2 yellowBirdPosition;
    Vector2 g1Position;
    Vector2 g2Position;
    Vector2 g3Position;
    Vector2 g4Position;
    Vector2 pig2Position;
    boolean redBird;
    boolean yellowBird;
    boolean blackBird;
    boolean pig;
    boolean w1;
    boolean w2;
    boolean w3;
    boolean w4;
    boolean g1;
    boolean g2;
    boolean g3;
    boolean g4;
    boolean pig2;
    boolean redBirdStatic;
    boolean yellowBirdStatic;

    public Level2Save(Vector2 pigPosition, Vector2 w1Position, Vector2 w2Position, Vector2 w3Position, Vector2 w4Position,
                              Vector2 redBirdPosition, Vector2 yellowBirdPosition,
                              Vector2 g1Position, Vector2 g2Position, Vector2 g3Position, Vector2 g4Position, Vector2 pig2Position,
                              boolean redBird, boolean yellowBird, boolean blackBird, boolean pig,
                              boolean w1, boolean w2, boolean w3, boolean w4,
                              boolean g1, boolean g2, boolean g3, boolean g4, boolean pig2,
                              boolean redBirdStatic, boolean yellowBirdStatic) {

        this.pigPosition = pigPosition;
        this.w1Position = w1Position;
        this.w2Position = w2Position;
        this.w3Position = w3Position;
        this.w4Position = w4Position;
        this.redBirdPosition = redBirdPosition;
        this.yellowBirdPosition = yellowBirdPosition;
        this.g1Position = g1Position;
        this.g2Position = g2Position;
        this.g3Position = g3Position;
        this.g4Position = g4Position;
        this.pig2Position = pig2Position;
        this.redBird = redBird;
        this.yellowBird = yellowBird;
        this.blackBird = blackBird;
        this.pig = pig;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
        this.g1 = g1;
        this.g2 = g2;
        this.g3 = g3;
        this.g4 = g4;
        this.pig2 = pig2;
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

    public Vector2 getRedBirdPosition() {
        return redBirdPosition;
    }

    public Vector2 getYellowBirdPosition() {
        return yellowBirdPosition;
    }

    public Vector2 getG1Position() {
        return g1Position;
    }

    public Vector2 getG2Position() {
        return g2Position;
    }

    public Vector2 getG3Position() {
        return g3Position;
    }

    public Vector2 getG4Position() {
        return g4Position;
    }

    public Vector2 getPig2Position() {
        return pig2Position;
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

    public boolean isG1() {
        return g1;
    }

    public boolean isG2() {
        return g2;
    }

    public boolean isG3() {
        return g3;
    }

    public boolean isG4() {
        return g4;
    }

    public boolean isPig2() {
        return pig2;
    }

    public boolean isRedBirdStatic() {
        return redBirdStatic;
    }

    public boolean isYellowBirdStatic() {
        return yellowBirdStatic;
    }
}
