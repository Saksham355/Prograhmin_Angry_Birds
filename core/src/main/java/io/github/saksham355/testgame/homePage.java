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

public class homePage extends Game implements Screen {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private FitViewport viewport;
    private Stage stage;
    private Texture level1;
    private Texture level2;
    private Texture level3;
    private Texture volumeTexture;
    private Texture muteTexture;
    private ImageButton level1Button;
    private ImageButton level2Button;
    private ImageButton level3Button;
    private ImageButton muteButton;
    private Game parent;
    private boolean isMuted = false;

    public homePage(Game parent) {
        this.parent = parent;
    }

    private void updateButtonPosition(ImageButton button) {
        button.setPosition(viewport.getWorldWidth() / 2f - button.getWidth() / 2, 20f);
    }

    public void resize(int width, int height, ImageButton button) {
        viewport.update(width, height, true);
        updateButtonPosition(button);
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("homePage.png");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        level1 = new Texture("l1.png");
        level2 = new Texture("l2.png");
        level3 = new Texture("l3.png");
        volumeTexture = new Texture("volume.png");
        muteTexture = new Texture("mute.png");

        level1Button = new ImageButton(new TextureRegionDrawable(level1));
        level2Button = new ImageButton(new TextureRegionDrawable(level2));
        level3Button = new ImageButton(new TextureRegionDrawable(level3));

        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 1 Button clicked!");
                parent.setScreen(new level1(parent));
            }
        });

        level2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 2 Button clicked!");
                parent.setScreen(new level2(parent));
            }
        });

        level3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 3 Button clicked!");
                parent.setScreen(new level3(parent));
            }
        });

        muteButton = new ImageButton(new TextureRegionDrawable(volumeTexture));
        muteButton.setSize(50, 50);
        muteButton.setPosition(700, 10);
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMuted = !isMuted;
                if (isMuted) {
                    muteButton.getStyle().up = new TextureRegionDrawable(muteTexture);
                    System.out.println("Muted");
                } else {
                    muteButton.getStyle().up = new TextureRegionDrawable(volumeTexture);
                    System.out.println("Unmuted");
                }
            }
        });

        level1Button.setSize(200, 100);
        level2Button.setSize(200, 100);
        level3Button.setSize(200, 100);

        positionButtonsInArc();

        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);
        stage.addActor(muteButton);

        ImageButton backButton = new ImageButton(new TextureRegionDrawable(new Texture("close.png")));
        backButton.setSize(125, 62);
        backButton.setPosition(700, 430);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        stage.addActor(backButton);
    }

    private void positionButtonsInArc() {
        float screenWidth = viewport.getWorldWidth();
        float screenHeight = viewport.getWorldHeight();
        float marginTop = 20f;
        float arcHeight = screenHeight - marginTop - 270;

        level1Button.setPosition(screenWidth / 4f - level1Button.getWidth() / 2, arcHeight);
        level2Button.setPosition(screenWidth / 2f - level2Button.getWidth() / 2, arcHeight + 50);
        level3Button.setPosition(3 * screenWidth / 4f - level3Button.getWidth() / 2, arcHeight);
    }

    @Override
    public void create() {}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        Gdx.input.setInputProcessor(stage);
        positionButtonsInArc();
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
        level1.dispose();
        level2.dispose();
        level3.dispose();
        volumeTexture.dispose();
        muteTexture.dispose();
    }
}
