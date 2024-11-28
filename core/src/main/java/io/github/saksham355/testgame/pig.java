package io.github.saksham355.testgame;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Serializable;

public class pig implements Serializable{

    String name;
    String img;
    int strength;
    String color;
    boolean onBlock;
    void decreaseStrength(int impact) {}
    boolean isDestroyed() {
        boolean isDestroyed = false;
        return isDestroyed;
    };
    public pig(String name, String img){
        this.name = name;
        this.img = img;
    }



}

