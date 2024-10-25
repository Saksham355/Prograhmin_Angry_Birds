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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Start extends Game implements Screen {
    private Game parent = null;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Texture enterTexture;
    private FitViewport viewport;
    private ImageButton button;

    private void setupButton() {
        Skin skin = new Skin();
        skin.add("buttonImage", enterTexture);
        button = new ImageButton(new TextureRegionDrawable(skin.getRegion("buttonImage")));
        button.setSize(200, 100);
        button.setPosition((Gdx.graphics.getWidth() / 2f - button.getWidth() / 2) - 500, 20f);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked! Going to homePage.");
                setScreen(new homePage(Start.this));
            }
        });
        stage.addActor(button);
        updateButtonPosition();
    }

    public Start(Game parent) {
        this.parent = parent;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("start.png");
        enterTexture = new Texture("play.png");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);
        setupButton();
    }

    @Override
    public void show() {
        if (viewport == null) {
            viewport = new FitViewport(800, 500);
        }
        if (stage == null) {
            stage = new Stage(viewport, spriteBatch);
            Gdx.input.setInputProcessor(stage);
            setupButton();
        }
    }

    private void updateButtonPosition() {
        button.setPosition(viewport.getWorldWidth() / 2f - button.getWidth() / 2, 20f);
    }

    @Override
    public void render(float v) {
    }

    @Override
    public void resize(int width, int height) {
        if (viewport == null) {
            System.out.println("Viewport is null! Initialization issue detected.");
        } else {
            viewport.update(width, height, true);
            updateButtonPosition();
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        super.render();
    }

    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();
        backgroundTexture.dispose();
        enterTexture.dispose();
    }
}
