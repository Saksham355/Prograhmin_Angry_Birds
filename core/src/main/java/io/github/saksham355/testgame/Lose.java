package io.github.saksham355.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.Serializable;

public class Lose implements Screen{
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture winTexture;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;

    public Lose(Game parent) {
        this.parent = parent;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("blackbg.png");
        winTexture = new Texture("lose.png");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(new Texture("close.png")));
        backButton.setSize(125, 62);
        backButton.setPosition(700, 430);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new homePage(parent));
            }
        });

        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 800, 500);
        batch.draw(winTexture, 300, 150, 200, 200);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        winTexture.dispose();
        batch.dispose();
        stage.dispose();
    }
}
