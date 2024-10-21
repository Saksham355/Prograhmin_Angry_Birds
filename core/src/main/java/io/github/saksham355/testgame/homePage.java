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

public class homePage extends Game  implements Screen {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private FitViewport viewport;
    private Stage stage;
    private Texture level1;
    private Texture level2;
    private Texture level3;
    private Texture settings;
    private ImageButton level1Button;
    private ImageButton level2Button;
    private ImageButton level3Button;
    private ImageButton settingsButton;
    private Game parent;
    private Texture backTexture;
    public homePage(Game parent) {
        this.parent = parent;
    }


    private void updateButtonPosition(ImageButton button) {
        button.setPosition(
            viewport.getWorldWidth() / 2f - button.getWidth() / 2,20f
        );
    }
    public void resize(int width, int height,ImageButton button) {
        viewport.update(width, height, true);
        updateButtonPosition(button);
    }
    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("homePage.png");
        viewport = new FitViewport(800, 500);  // Initial viewport size
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        level1 = new Texture("l1.png");
        level2 = new Texture("l2.png");
        level3 = new Texture("l3.png");
        settings = new Texture("settings.png");

        Skin skin = new Skin();
        skin.add("level1", level1);
        skin.add("level2", level2);
        skin.add("level3", level3);
        skin.add("settings", settings);
        level1Button = new ImageButton(new TextureRegionDrawable(skin.getRegion("level1")));
        level2Button = new ImageButton(new TextureRegionDrawable(skin.getRegion("level2")));
        level3Button = new ImageButton(new TextureRegionDrawable(skin.getRegion("level3")));
        settingsButton = new ImageButton(new TextureRegionDrawable(skin.getRegion("settings")));
        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 1 Button clicked!");
                parent.setScreen(new level1(parent));
                // Add navigation logic here
            }
        });

        level2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 2 Button clicked!");
                parent.setScreen(new level2(parent));
                // Add navigation logic here
            }
        });

        level3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 3 Button clicked!");
                parent.setScreen(new level3(parent));
//                setScreen(new homePage(level.this));
                // Add navigation logic here
            }
        });
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Settings Button clicked!");
                parent.setScreen(new settings(parent));
            }
        });
        // Set button sizes
        level1Button.setSize(200, 100);
        level2Button.setSize(200, 100);
        level3Button.setSize(200, 100);
        settingsButton.setSize(125, 62);
        // Position buttons to form an arc at the top of the screen
        positionButtonsInArc();
        settingsButton.setPosition(viewport.getWorldWidth() - settingsButton.getWidth()+20, viewport.getWorldHeight() - settingsButton.getHeight() - 10);
        // Add buttons to the stage
        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);
        stage.addActor(settingsButton);
        backTexture = new Texture("back.png");  // Ensure you have a back.png file
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backTexture));

        // Set back button size and position (bottom-left corner)
        backButton.setSize(100, 50);  // Adjust the size as needed
        backButton.setPosition(10, 10);  // Position at bottom-left corner

        // Add click listener to the back button
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new Start());  // Return to the home page when clicked
            }
        });

        // Add back button to the stage
        stage.addActor(backButton);

    }

    private void positionButtonsInArc() {
        float screenWidth = viewport.getWorldWidth();
        float screenHeight = viewport.getWorldHeight();
        float marginTop = 20f; // Adjust the top margin
        float arcHeight = screenHeight - marginTop-270;

        // Position level1Button at the left
        level1Button.setPosition(screenWidth / 4f - level1Button.getWidth() / 2, arcHeight);

        // Position level2Button at the center
        level2Button.setPosition(screenWidth / 2f - level2Button.getWidth() / 2, arcHeight + 50);

        // Position level3Button at the right
        level3Button.setPosition(3 * screenWidth / 4f - level3Button.getWidth() / 2, arcHeight);
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        Gdx.input.setInputProcessor(stage);
        positionButtonsInArc();  // Update button positions when resizing
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        // Draw the background
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();

        // Draw the stage
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
    }
}
