package io.github.saksham355.testgame;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Serializable;

public class block{
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
