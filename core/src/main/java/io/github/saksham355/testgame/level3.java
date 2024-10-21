package io.github.saksham355.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class level3 implements Screen {
    private SpriteBatch batch;
    private Texture texture;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;  // Reference to the main game
    private Texture catapult;
    private Texture red;
    private Texture black;
    private Texture yellow;

    public level3(Game parent) {
        this.parent = parent;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        texture = new Texture("l3bg.jpg");  // Background texture
        viewport = new FitViewport(800, 500);  // Initial viewport size
        stage = new Stage(viewport, batch);
        catapult = new Texture("catapult.png");  // Catapult texture
        red = new Texture("red.png");
        black = new Texture("black.png");
        yellow = new Texture("yellow.png");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);  // Clear screen with black color

        batch.begin();

        // Draw the background
        batch.draw(texture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Draw the catapult at the bottom-left corner
        batch.draw(catapult, 1, 70, 150, 100);  // Adjust width and height as needed
        batch.draw(red, 1, 70, 60, 60);
        batch.draw(black, 160, 70, 60, 60);
        batch.draw(yellow, 100, 70, 60, 60);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);  // Update the viewport on resize
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        catapult.dispose();
        stage.dispose();
    }
}
