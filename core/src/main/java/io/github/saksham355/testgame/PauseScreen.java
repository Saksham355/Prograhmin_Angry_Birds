package io.github.saksham355.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.File;
import java.io.Serializable;

public class PauseScreen implements Screen {
    private Game parent;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private FitViewport viewport;
    private Texture resumeTexture;
    private Texture retryTexture;
    private Texture closeTexture;
    private ImageButton resumeButton;
    private ImageButton retryButton;
    private ImageButton closeButton;
    private Screen previousScreen;

    public PauseScreen(Game parent, Screen previousScreen) {
        this.parent = parent;
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);


        resumeTexture = new Texture("playgame.png");
        resumeButton = new ImageButton(new TextureRegionDrawable(resumeTexture));
        resumeButton.setSize(150, 50);
        resumeButton.setPosition(viewport.getWorldWidth() / 2 - resumeButton.getWidth() / 2, viewport.getWorldHeight() / 2 + 20);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Resume Button clicked! Returning to the game.");

                parent.setScreen(previousScreen);
            }
        });
        stage.addActor(resumeButton);

        // Retry button setup
        retryTexture = new Texture("replay.png");
        retryButton = new ImageButton(new TextureRegionDrawable(retryTexture));
        retryButton.setSize(150, 50);
        retryButton.setPosition(viewport.getWorldWidth() / 2 - retryButton.getWidth() / 2, viewport.getWorldHeight() / 2 - 80);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Retry Button clicked! Restarting the level.");
//                previousScreen.dispose();
                File datafile = null;
                if(previousScreen instanceof level1) {
                    datafile = new File("gamesave1.ser");
                }
                else if(previousScreen instanceof level2) {
                    datafile = new File("gamesave2.ser");
                } else if (previousScreen instanceof level3) {
                    datafile = new File("gamesave3.ser");
                }
                System.out.println(datafile.delete());
                System.out.println();
                ScreenUtils.clear(0,0,0,1);
                parent.setScreen(previousScreen);
            }
        });
        stage.addActor(retryButton);

        closeTexture = new Texture("close.png");
        closeButton = new ImageButton(new TextureRegionDrawable(closeTexture));
        closeButton.setSize(50, 50);
        closeButton.setPosition(viewport.getWorldWidth() / 2 - closeButton.getWidth() / 2, viewport.getWorldHeight() / 2 - 150);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Close Button clicked! Returning to home page.");
                parent.setScreen(new homePage(parent));

            }
        });
        stage.addActor(closeButton);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        shapeRenderer.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        batch.dispose();
        shapeRenderer.dispose();
        resumeTexture.dispose();
        retryTexture.dispose();
        closeTexture.dispose();
    }



}
