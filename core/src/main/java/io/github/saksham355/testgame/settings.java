package io.github.saksham355.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class settings implements Screen {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;
    private Texture backTexture;

    public settings(Game parent) {
        this.parent = parent;  // Store reference to parent game
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("blackbg.png");  // Background texture
        viewport = new FitViewport(800, 500);  // Initial viewport size
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Back button texture
        backTexture = new Texture("back.png");  // Ensure you have a back.png file
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backTexture));

        // Set back button size and position (bottom-left corner)
        backButton.setSize(100, 50);  // Adjust the size as needed
        backButton.setPosition(10, 10);  // Position at bottom-left corner

        // Add click listener to the back button
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new homePage(parent));  // Return to the home page when clicked
            }
        });

        // Add back button to the stage
        stage.addActor(backButton);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);  // Update viewport on resize
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);  // Clear the screen with black

        // Draw the background
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();

        // Draw the stage (includes the back button)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();
        backgroundTexture.dispose();
        backTexture.dispose();
    }
}
