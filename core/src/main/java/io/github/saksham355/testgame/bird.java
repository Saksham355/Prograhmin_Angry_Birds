package io.github.saksham355.testgame;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Serializable;

public class bird {

        public String name;
        public String img;
        private int impact;
        public float speed;
        public void launch(float angle){};
        public boolean hasLaunched = false;
        public void decreaseImpact(){};
        public bird(String name, String img) {
            this.name = name;
            this.img = img;
        }

}



