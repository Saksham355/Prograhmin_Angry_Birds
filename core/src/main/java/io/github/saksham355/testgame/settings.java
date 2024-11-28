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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.Serializable;

public class settings implements Screen  {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;
    private Texture backTexture;
    private Texture volumeTexture;
    private Texture muteTexture;

    public settings(Game parent) {
        this.parent = parent;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("blackbg.png");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        backTexture = new Texture("back.png");
        volumeTexture = new Texture("buttons_enhanced.png");
        muteTexture = new Texture("Buttons.png");

        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backTexture));
        backButton.setSize(100, 50);
        backButton.setPosition(10, 10);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new homePage(parent));
            }
        });

        ImageButton volumeButton = new ImageButton(new TextureRegionDrawable(volumeTexture));
        volumeButton.setSize(150, 150);
        volumeButton.setPosition(200, 300);

        volumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Volume button clicked");
            }
        });

        ImageButton muteButton = new ImageButton(new TextureRegionDrawable(muteTexture));
        muteButton.setSize(150, 150);
        muteButton.setPosition(400, 300);

        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Mute button clicked");
            }
        });

        stage.addActor(backButton);
        stage.addActor(volumeButton);
        stage.addActor(muteButton);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();

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
