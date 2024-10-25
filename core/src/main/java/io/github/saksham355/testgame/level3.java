package io.github.saksham355.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class level3 implements Screen {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;
    private Texture catapult;
    private Texture red, black, yellow;
    private Texture pig1, pig2, pig3;
    private Texture wood, glass, rocks;
    private Texture pauseTexture;
    private ImageButton pauseButton;
    private bird r = new bird("red", "red.png");
    private bird y = new bird("yellow", "yellow.png");
    private bird b = new bird("black", "black.png");
    private pig p1 = new pig("pig1", "pig1.png");
    private pig p2 = new pig("pig2", "pig2.png");
    private pig p3 = new pig("pig3", "pig3.png");
    private block w = new block("wood", "wood.jpg");
    private block g = new block("glass", "glass.jpg");
    private block rock = new block("rock", "rock.jpg");

    public level3(Game parent) {
        this.parent = parent;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("l3bg.jpg");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, batch);
        catapult = new Texture("catapult.png");

        red = new Texture(r.img);
        black = new Texture(b.img);
        yellow = new Texture(y.img);
        pig1 = new Texture(p1.img);
        pig2 = new Texture(p2.img);
        pig3 = new Texture(p3.img);
        wood = new Texture(w.img);
        glass = new Texture(g.img);
        rocks = new Texture(rock.img);
        Gdx.input.setInputProcessor(stage);

        pauseTexture = new Texture("pause.png");
        pauseButton = new ImageButton(new TextureRegionDrawable(pauseTexture));
        pauseButton.setSize(50, 50);
        pauseButton.setPosition(viewport.getWorldWidth() - pauseButton.getWidth() - 10, 430);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new PauseScreen(parent, level3.this));
            }
        });
        stage.addActor(pauseButton);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            parent.setScreen(new Win(parent));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            parent.setScreen(new Lose(parent));
        }
        batch.begin();

        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(catapult, 1, 70, 150, 100);
        batch.draw(red, 1, 70, 60, 60);
        batch.draw(black, 160, 70, 60, 60);
        batch.draw(yellow, 100, 70, 60, 60);

        float baseX = 450;
        float baseY = 72;

        batch.draw(wood, baseX, baseY, 100, 20);
        batch.draw(wood, baseX, baseY + 20, 20, 80);
        batch.draw(wood, baseX + 80, baseY + 20, 20, 80);
        batch.draw(wood, baseX, baseY + 100, 100, 20);
        batch.draw(pig1, baseX + 20, baseY + 20, 60, 60);

        batch.draw(glass, baseX + 100, baseY, 120, 20);
        batch.draw(glass, baseX + 100, baseY + 20, 20, 80);
        batch.draw(glass, baseX + 200, baseY + 20, 20, 80);
        batch.draw(glass, baseX + 100, baseY + 100, 120, 20);
        batch.draw(pig2, baseX + 125, baseY + 20, 60, 60);

        batch.draw(rocks, baseX + 30, baseY + 120, 140, 20);
        batch.draw(rocks, baseX + 30, baseY + 140, 20, 80);
        batch.draw(rocks, baseX + 147, baseY + 140, 20, 80);
        batch.draw(rocks, baseX + 30, baseY + 220, 140, 20);
        batch.draw(pig3, baseX + 65, baseY + 140, 60, 60);

        batch.end();

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
        batch.dispose();
        backgroundTexture.dispose();
        catapult.dispose();
        red.dispose();
        black.dispose();
        yellow.dispose();
        pig1.dispose();
        pig2.dispose();
        pig3.dispose();
        wood.dispose();
        glass.dispose();
        rocks.dispose();
        stage.dispose();
        pauseTexture.dispose();
    }
}
