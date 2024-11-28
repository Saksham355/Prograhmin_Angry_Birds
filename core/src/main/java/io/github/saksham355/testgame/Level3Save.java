package io.github.saksham355.testgame;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Level3Save implements Serializable {
    private static final long serialVersionUID = -5166046959631454126L;
    Vector2 pig1Position;
    Vector2 pig2Position;
    Vector2 pig3Position;
    Vector2 redBirdPosition;
    Vector2 yellowBirdPosition;
    Vector2 w1Position;
    Vector2 w2Position;
    Vector2 w3Position;
    Vector2 w4Position;
    Vector2 g1Position;
    Vector2 g2Position;
    Vector2 g3Position;
    Vector2 g4Position;
    Vector2 r1Position;
    Vector2 r2Position;
    Vector2 r3Position;
    Vector2 r4Position;
    boolean redBird;
    boolean yellowBird;
    boolean blackBird;
    boolean pig1;
    boolean pig2;
    boolean pig3;
    boolean w1;
    boolean w2;
    boolean w3;
    boolean w4;
    boolean g1;
    boolean g2;
    boolean g3;
    boolean g4;
    boolean r1;
    boolean r2;
    boolean r3;
    boolean r4;
    boolean redBirdStatic;
    boolean yellowBirdStatic;

    public Level3Save(boolean yellowBirdStatic,
                      boolean redBirdStatic, boolean r4,
                      boolean r3, boolean r2, boolean r1,
                      boolean g4, boolean g3, boolean g2,
                      boolean g1, boolean w4, boolean w3,
                      boolean w2, boolean w1, boolean pig3,
                      boolean pig2, boolean pig1, boolean blackBird,
                      boolean yellowBird, boolean redBird, Vector2 r4Position,
                      Vector2 r3Position, Vector2 r2Position, Vector2 r1Position,
                      Vector2 g4Position, Vector2 g3Position, Vector2 g2Position,
                      Vector2 g1Position, Vector2 w4Position, Vector2 w3Position,
                      Vector2 w2Position, Vector2 w1Position, Vector2 yellowBirdPosition,
                      Vector2 redBirdPosition,Vector2 pig3Position, Vector2 pig2Position, Vector2 pig1Position) {
        this.yellowBirdStatic = yellowBirdStatic;
        this.redBirdStatic = redBirdStatic;
        this.r4 = r4;
        this.r3 = r3;
        this.r2 = r2;
        this.r1 = r1;
        this.g4 = g4;
        this.g3 = g3;
        this.g2 = g2;
        this.g1 = g1;
        this.w4 = w4;
        this.w3 = w3;
        this.w2 = w2;
        this.w1 = w1;
        this.pig3 = pig3;
        this.pig2 = pig2;
        this.pig1 = pig1;
        this.blackBird = blackBird;
        this.yellowBird = yellowBird;
        this.redBird = redBird;
        this.r4Position = r4Position;
        this.r3Position = r3Position;
        this.r2Position = r2Position;
        this.r1Position = r1Position;
        this.g4Position = g4Position;
        this.g3Position = g3Position;
        this.g2Position = g2Position;
        this.g1Position = g1Position;
        this.w4Position = w4Position;
        this.w3Position = w3Position;
        this.w2Position = w2Position;
        this.w1Position = w1Position;
        this.yellowBirdPosition = yellowBirdPosition;
        this.redBirdPosition = redBirdPosition;
        this.pig3Position = pig3Position;
        this.pig2Position = pig2Position;
        this.pig1Position = pig1Position;
    }

    public Vector2 getPig1Position() {
        return pig1Position;
    }

    public Vector2 getPig2Position() {
        return pig2Position;
    }

    public Vector2 getPig3Position() {
        return pig3Position;
    }

    public Vector2 getRedBirdPosition() {
        return redBirdPosition;
    }

    public Vector2 getYellowBirdPosition() {
        return yellowBirdPosition;
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

    public Vector2 getR1Position() {
        return r1Position;
    }

    public Vector2 getR2Position() {
        return r2Position;
    }

    public Vector2 getR3Position() {
        return r3Position;
    }

    public Vector2 getR4Position() {
        return r4Position;
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

    public boolean isPig1() {
        return pig1;
    }

    public boolean isPig2() {
        return pig2;
    }

    public boolean isPig3() {
        return pig3;
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

    public boolean isR1() {
        return r1;
    }

    public boolean isR2() {
        return r2;
    }

    public boolean isR3() {
        return r3;
    }

    public boolean isR4() {
        return r4;
    }

    public boolean isRedBirdStatic() {
        return redBirdStatic;
    }

    public boolean isYellowBirdStatic() {
        return yellowBirdStatic;
    }
}

