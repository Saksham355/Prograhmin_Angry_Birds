package io.github.saksham355.testgame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.physics.box2d.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class level2 implements Screen{
    private World worldDef = new World(new Vector2(0, -30), true);
    private Body redBird, floor,left,right,up,catapultBase,yellowBird,blackBird;
    private Body pigBody1,pigBody2,pigBody3;
    private ArrayList<Body> birdBodies = new ArrayList<>();
    private ArrayList<Body> pigBodies = new ArrayList<>();
    private ArrayList<Body> blockBodies = new ArrayList<>();
    private List<Body> collisionBodies = new ArrayList<>();
    private List<Body> boundry = new ArrayList<>();
    private List<Texture> collisionTexture = new ArrayList<>();
    private int currentBirdIndex = 0;
    private Body w1,w2,w3,w4;

    private Body g1,g2,g3,g4;

    private Body r1,r2,r3,r4;
    private Map<Body,Integer> powerMap = new HashMap<>();
    private SpriteBatch batch;
    private Texture backgroundTexture, catapult;
    private FitViewport viewport;
    private Stage stage;
    private Game parent;
    private bird r = new bird("red", "red.png");
    private bird y = new bird("yellow", "yellow.png");
    private bird b = new bird("black", "black.png");
    private pig p1 = new pig("pig1", "pig1.png");
    private pig p2 = new pig("pig2", "pig2.png");
    private pig p3 = new pig("pig3", "pig3.png");
    private block wood1 = new block("wood", "wood.jpg");
    private block glass1 = new block("glass", "glass.jpg");
//    private block rock1 = new block("rock", "rock.jpg");

    private block wood2 = new block("wood", "wood.jpg");
    private block glass2 = new block("glass", "glass.jpg");
//    private block rock2 = new block("rock", "rock.jpg");

    private block wood3 = new block("wood", "wood.jpg");
    private block glass3 = new block("glass", "glass.jpg");

    private block wood4 = new block("wood", "wood.jpg");
    private block glass4 = new block("glass", "glass.jpg");
    private Texture red, black, yellow;
    private Texture pig1, pig3;
    private Texture woodT1, woodT2, woodT3,woodT4;
    private Texture glassT1, glassT2, glassT3,glassT4;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private final float scale = 2.0f;
    private Vector2 dragStart = null;
    private Vector2 dragEnd = null;
    float baseX = 450;
    float baseY = 110;
    float d;
    public level2(Game parent) {
        this.parent = parent;
    }
    public Body createBox(int x, int y, int width, int height, boolean isStatic) {

        Body b;
        BodyDef bodyDef = new BodyDef();
        if (isStatic) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        } else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        bodyDef.position.set(x  , y );
        bodyDef.fixedRotation = false;
        b = worldDef.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width , height );
        b.createFixture(shape, 1f);
        shape.dispose();
        return b;
    }
    public Body createCircular(int x, int y,int radius, boolean isStatic) {
        Body b;
        BodyDef bodyDef = new BodyDef();
        if (isStatic) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        } else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        bodyDef.position.set(x  , y );
        bodyDef.fixedRotation = false;
        b = worldDef.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        b.createFixture(shape, 1f);
        shape.dispose();
        return b;
    }
    private void launchProjectile(Body body, Vector2 dragStart, Vector2 dragEnd) {
        float dragDistance = dragEnd.dst(dragStart);
        float angle = (float) Math.atan2(dragEnd.y - dragStart.y, dragEnd.x - dragStart.x);
        float forceMultiplier = 10000000000f; // Adjust for desired power
        float forceX = -(float) Math.cos(angle) * dragDistance * forceMultiplier;
        float forceY = (float) Math.sin(angle) * dragDistance * forceMultiplier;
        body.applyLinearImpulse(new Vector2(forceX, forceY), body.getWorldCenter(), true);
    }
    private void inputUpdate() {
        if (currentBirdIndex >= birdBodies.size()) {
            return;
        }
        Body currentBird = birdBodies.get(currentBirdIndex);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (dragStart == null) {
                dragStart = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            }
            dragEnd = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        } else if (dragStart != null && dragEnd != null) {
            launchProjectile(currentBird, dragStart, dragEnd);
            dragStart = null;
            dragEnd = null;
        }
    }


    private void setupInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            private boolean dragging = false;
            private Body selectedBird = null;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                float x = worldCoordinates.x;
                float y = worldCoordinates.y;

                for (Body bird : birdBodies) {
                    if (bird.getType() == BodyDef.BodyType.StaticBody) {
                        if (Math.abs(bird.getPosition().x - x) < 1.0f && Math.abs(bird.getPosition().y - y) < 1.0f) {
                            selectedBird = bird;
                            dragging = true;
                            break;
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (dragging && selectedBird != null) {
                    Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                    float x = worldCoordinates.x;
                    float y = worldCoordinates.y;
                    selectedBird.setTransform(x, y, 0);
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (dragging && selectedBird != null) {
                    dragging = false;
                    selectedBird = null;
                }
                return true;
            }
        });
    }


    private void setupContactListener(float delta) {
        worldDef.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();

                boolean isBirdA = birdBodies.contains(bodyA);
                boolean isBirdB = birdBodies.contains(bodyB);
                boolean isCollisionBodyA = collisionBodies.contains(bodyA);
                boolean isCollisionBodyB = collisionBodies.contains(bodyB);

                if (isBirdA && isCollisionBodyB) {
                    Body bird = bodyA;
                    Body collider = bodyB;
                    if(powerMap.get(collider)-powerMap.get(bird) <=0) {
                        Gdx.app.postRunnable(() ->
                        {
                            int p = powerMap.get(collider);
                            int birdpower = powerMap.get(bird);
                            worldDef.destroyBody(collider);
                            collisionBodies.remove(collider);
                            if(pigBodies.contains(collider)){
                                pigBodies.remove(collider);
                            }
                            birdpower = birdpower - p;
                            p = 0;
                            powerMap.put(collider, p);
                            powerMap.put(bird, birdpower);
                            if (birdpower == 0)
                                removeBird(bird);
                        });
                    }
                    else{
                        Gdx.app.postRunnable(() -> {
                            int p = powerMap.get(collider);
                            int birdpower = powerMap.get(bird);
                            removeBird(bird);

                            birdpower = 0;
                            p = p - powerMap.get(bird);
                            powerMap.put(collider, p);
                            powerMap.put(bird, birdpower);
                        });
                    }
                }

                else if(isBirdB && isCollisionBodyA){
                    Body bird = bodyB;
                    Body collider = bodyA;
                    if(powerMap.get(collider)-powerMap.get(bird) <=0) {
                        Gdx.app.postRunnable(() ->
                        {
                            int p = powerMap.get(collider);
                            int birdpower = powerMap.get(bird);
                            worldDef.destroyBody(collider);
                            collisionBodies.remove(collider);
                            if(pigBodies.contains(collider)){
                                pigBodies.remove(collider);
                            }
                            birdpower = birdpower - p;
                            p = 0;
                            powerMap.put(collider, p);
                            powerMap.put(bird, birdpower);
                            if (birdpower == 0)
                                removeBird(bird);
                        });
                    }
                    else{
                        Gdx.app.postRunnable(() -> {
                            int p = powerMap.get(collider);
                            int birdpower = powerMap.get(bird);
                            removeBird(bird);

                            birdpower = 0;
                            p = p - powerMap.get(bird);
                            powerMap.put(collider, p);
                            powerMap.put(bird, birdpower);
                        });

                    }


                }
            }


            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}

        });
    }







    private void removeBird(Body bird) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.postRunnable(() -> {
                    if (birdBodies.contains(bird)) {
                        birdBodies.remove(bird);
                    }
                    if (worldDef != null && bird != null) {
                        worldDef.destroyBody(bird);
                    }
                    setupNextBird();
                });
            }
        }, 1);
    }
    private void setupNextBird() {
        if (currentBirdIndex < birdBodies.size()) {
            Body nextBird = birdBodies.get(currentBirdIndex);
            nextBird.setTransform(125, 200, 0);
            nextBird.setType(BodyDef.BodyType.DynamicBody);
        }
    }






    private void drawRotatedTexture(Texture texture, Body body, float width, float height) {
        batch.draw(texture,
            body.getPosition().x - width / 2, body.getPosition().y - height / 2,
            width / 2, height / 2,
            width, height,
            1, 1,
            (float) Math.toDegrees(body.getAngle()), 0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false);
    }



    @Override
    public void show() {
        setupContactListener(d);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 500);
        floor = createBox(400, 100, 800, 10, true);
        left = createBox(0,140,1,500, true);
        right = createBox(800,140,1,500, true);
        up = createBox(400,500,800,1,true);
        catapultBase = createBox(110, 140, 20, 30, true);
        batch = new SpriteBatch();
        backgroundTexture = new Texture("l2bg.jpg");
        viewport = new FitViewport(800, 500);
        stage = new Stage(viewport, batch);
        catapult = new Texture("catapult.png");
        debugRenderer = new Box2DDebugRenderer();
        red = new Texture(r.img);
        yellow = new Texture(y.img);
        black = new Texture(b.img);
        woodT1 = new Texture(wood1.img);
        glassT1 = new Texture(glass1.img);
        woodT2 = new Texture(wood1.img);
        glassT2 = new Texture(glass1.img);
        woodT3 = new Texture(wood1.img);
        glassT3 = new Texture(glass1.img);
        woodT4 = new Texture(wood1.img);
        glassT4 = new Texture(glass1.img);
        pig1 = new Texture(p1.img);
        pig3 = new Texture(p3.img);

        Gdx.input.setInputProcessor(stage);
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(new Texture("pause.png")));
        pauseButton.setSize(50, 50);
        pauseButton.setPosition(viewport.getWorldWidth() - pauseButton.getWidth() - 10, 430);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new PauseScreen(parent, new level2(parent)));
                Level2Save adddata = new Level2Save(
                    pigBody1 != null ? pigBody1.getPosition() : null,
                    w1 != null ? w1.getPosition() : null,
                    w2 != null ? w2.getPosition() : null,
                    w3 != null ? w3.getPosition() : null,
                    w4 != null ? w4.getPosition() : null,
                    redBird != null ? redBird.getPosition() : null,
                    yellowBird != null ? yellowBird.getPosition() : null,
                    g1 != null ? g1.getPosition():null,
                    g2 != null ? g2.getPosition():null,
                    g3 != null ? g3.getPosition():null,
                    g4 != null ? g4.getPosition():null,
                    pigBody3 !=null? pigBody3.getPosition():null,
                    birdBodies != null && birdBodies.contains(redBird),
                    birdBodies != null && birdBodies.contains(yellowBird),
                    birdBodies != null && birdBodies.contains(blackBird),
                    pigBodies != null && pigBodies.contains(pigBody1),
                    collisionBodies != null && collisionBodies.contains(w1),
                    collisionBodies != null && collisionBodies.contains(w2),
                    collisionBodies != null && collisionBodies.contains(w3),
                    collisionBodies != null && collisionBodies.contains(w4),
                    collisionBodies != null && collisionBodies.contains(g1),
                    collisionBodies != null && collisionBodies.contains(g2),
                    collisionBodies != null && collisionBodies.contains(g3),
                    collisionBodies != null && collisionBodies.contains(g4),
                    pigBodies != null && pigBodies.contains(pigBody3),
                    redBird != null && redBird.getType() == BodyDef.BodyType.StaticBody,
                    yellowBird != null && yellowBird.getType() == BodyDef.BodyType.StaticBody);
                ObjectSaver.saveObject(adddata,"gamesave2.ser");
                Level2Save datamap = Getlevel2save.getlevel2State("gamesave2.ser");
                System.out.println(datamap.isW1());
                System.out.println(collisionBodies.contains(w1));
            }
        });
        stage.addActor(pauseButton);
        File datafile = new File("gamesave2.ser");
        if (!datafile.exists()) {
            blackBird = createCircular(115, 180, 20, false);
            yellowBird = createCircular(70, 176, 20, true);
            redBird = createCircular(30, 176, 20, true);
            pigBody1 = createCircular((int) baseX + 50, (int) baseY + 47, 25, false);
            pigBody3 = createCircular((int) baseX + 155, (int) baseY + 47, 25, false);
            w1 = createBox((int) baseX + 50, (int) baseY + 10, 100 / 2, 20 / 2, false);
            w2 = createBox((int) baseX + 10, (int) baseY + 60, 20 / 2, 80 / 2, false);
            w3 = createBox((int) baseX + 90, (int) baseY + 60, 20 / 2, 80 / 2, false);
            w4 = createBox((int) baseX + 50, (int) baseY + 110, 100 / 2, 20 / 2, false);
            g1 = createBox((int) baseX + 160, (int) baseY + 10, 120 / 2, 20 / 2, false);
            g2 = createBox((int) baseX + 110, (int) baseY + 60, 20 / 2, 80 / 2, false);
            g3 = createBox((int) baseX + 210, (int) baseY + 60, 20 / 2, 80 / 2, false);
            g4 = createBox((int) baseX + 160, (int) baseY + 110, 120 / 2, 20 / 2, false);
            pigBodies.add(pigBody1);
            pigBodies.add(pigBody3);
            birdBodies.add(blackBird);
            birdBodies.add(yellowBird);
            birdBodies.add(redBird);
            blockBodies.add(w1);
            blockBodies.add(w2);
            blockBodies.add(w3);
            blockBodies.add(w4);
            blockBodies.add(g1);
            blockBodies.add(g2);
            blockBodies.add(g3);
            blockBodies.add(g4);
            collisionTexture.add(woodT1);
            collisionTexture.add(woodT2);
            collisionTexture.add(woodT3);
            collisionTexture.add(woodT4);
            collisionTexture.add(glassT1);
            collisionTexture.add(glassT2);
            collisionTexture.add(glassT3);
            collisionTexture.add(glassT4);
            collisionTexture.add(pig1);
            collisionTexture.add(pig3);
            boundry.add(floor);
            boundry.add(up);
            boundry.add(right);
            boundry.add(left);
            collisionBodies.add(w1);
            collisionBodies.add(w2);
            collisionBodies.add(w3);
            collisionBodies.add(w4);
            collisionBodies.add(r1);
            collisionBodies.add(r2);
            collisionBodies.add(r3);
            collisionBodies.add(r4);
            collisionBodies.add(g1);
            collisionBodies.add(g2);
            collisionBodies.add(g3);
            collisionBodies.add(g4);
            collisionBodies.add(pigBody1);
            collisionBodies.add(pigBody3);
            powerMap.put(redBird, 1);
            powerMap.put(yellowBird, 2);
            powerMap.put(blackBird, 3);
            powerMap.put(w1, 1);
            powerMap.put(w2, 1);
            powerMap.put(w3, 1);
            powerMap.put(w4, 1);
            powerMap.put(g1, 2);
            powerMap.put(g2, 2);
            powerMap.put(g3, 2);
            powerMap.put(g4, 2);
            powerMap.put(pigBody1, 1);
            powerMap.put(pigBody2, 1);
            powerMap.put(pigBody3, 1);
        }
        else{
            Level2Save data2 = Getlevel2save.getlevel2State("gamesave2.ser");
            if(data2.isPig()){
                pigBody1 = createCircular((int)data2.getPigPosition().x,(int)data2.getPigPosition().y, 25, false);
                pigBodies.add(pigBody1);
                collisionBodies.add(pigBody1);
                powerMap.put(pigBody1, 1);
            }
            if(data2.isPig2()){
                pigBody3 = createCircular((int)data2.getPig2Position().x,(int)data2.getPig2Position().y, 25, false);
                pigBodies.add(pigBody3);
                collisionBodies.add(pigBody3);
                powerMap.put(pigBody3, 1);
            }
            if(data2.isBlackBird()){
                blackBird = createCircular(115, 180, 20,  false);
                birdBodies.add(blackBird);
                powerMap.put(blackBird, 3);
            }
            if(data2.isYellowBird()){
                yellowBird = createCircular((int)data2.getYellowBirdPosition().x, (int)data2.getYellowBirdPosition().y, 20, data2.isYellowBirdStatic()); // Yellow bird (initially static)
                birdBodies.add(yellowBird);
                powerMap.put(yellowBird, 2);
            }
            if(data2.isRedBird()){
                redBird = createCircular((int)data2.getRedBirdPosition().x, (int)data2.getRedBirdPosition().y, 20, data2.isRedBirdStatic());
                birdBodies.add(redBird);
                powerMap.put(redBird, 1);
            }
            if(data2.isW1()){
                System.out.println("Here");
                w1 = createBox((int) data2.getW1Position().x, (int)data2.getW1Position().y, 100 / 2, 20 / 2, false);
                blockBodies.add(w1);
                collisionBodies.add(w1);
                powerMap.put(w1, 1);
            }
            if(data2.isW2()){
                w2 = createBox((int) data2.getW2Position().x, (int) data2.getW2Position().y, 20 / 2, 80 / 2, false);
                blockBodies.add(w2);
                collisionBodies.add(w2);
                powerMap.put(w2, 1);
            }
            if(data2.isW3()){
                w3 = createBox((int)data2.getW3Position().x, (int) (int)data2.getW3Position().y, 20 / 2, 80 / 2, false);
                blockBodies.add(w3);
                collisionBodies.add(w3);
                powerMap.put(w3, 1);

            }
            if(data2.isW4()){
                w4 = createBox((int) data2.getW4Position().x, (int) data2.getW4Position().y, 100 / 2, 20 / 2, false);
                blockBodies.add(w4);
                collisionBodies.add(w4);
                powerMap.put(w4, 1);
            }
            if(data2.isG1()){
                g1 = createBox((int) data2.getG1Position ().x,(int)  data2.getG1Position ().y, 120 / 2, 20 / 2, false);
                blockBodies.add(g1);
                collisionBodies.add(g1);
                powerMap.put(g1, 2);
            }
            if(data2.isG2()){
                g2 = createBox((int)data2.getG2Position().x,(int) data2.getG2Position().y, 20 / 2, 80 / 2, false);
                blockBodies.add(g2);
                collisionBodies.add(g2);
                powerMap.put(g2, 2);
            }
            if(data2.isG3()){
                g3 = createBox((int)data2.getG3Position ().x,(int) data2.getG3Position().y, 20 / 2, 80 / 2, false);
                blockBodies.add(g3);
                collisionBodies.add(g3);
                powerMap.put(g3, 2);
            }
            if(data2.isG4()){
                g4 = createBox((int)data2.getG4Position ().x,(int) data2.getG4Position().y, 120 / 2, 20 / 2, false);
                blockBodies.add(g4);
                collisionBodies.add(g4);
                powerMap.put(g4, 2);
            }
        }

    }


    public void render(float delta) {
        if((pigBodies.size()==0)){
            parent.setScreen(new Win((parent)));
            File dataFile = new File("gamesave2.ser");
            dataFile.delete();
        }
        if((birdBodies.size()==0)&&(pigBodies.size()!=0)) {
            parent.setScreen(new Lose(parent));
            File dataFile = new File("gamesave2.ser");
            dataFile.delete();
        }
        inputUpdate();
        d = delta;
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(catapult, catapultBase.getPosition().x - (150 / 2), catapultBase.getPosition().y - (100 / 2), 150, 100);

        // Drawing birds dynamically from the list
        if (birdBodies.contains(blackBird)) {
            drawRotatedTexture(black, blackBird, 60, 60);
        }
        if (birdBodies.contains(yellowBird)) {
            drawRotatedTexture(yellow, yellowBird, 70, 70);
        }
        if (birdBodies.contains(redBird)) {
            drawRotatedTexture(red, redBird, 60, 60);
        }

        // Drawing blocks

        if (collisionBodies.contains(w1) && w1!=null) {drawRotatedTexture(woodT1, w1, 100, 20);}
        if (collisionBodies.contains(w2) && w2!=null) {drawRotatedTexture(woodT2, w2, 20, 80);}
        if (collisionBodies.contains(w3) && w3!=null ) {drawRotatedTexture(woodT3, w3, 20, 80);}
        if (collisionBodies.contains(w4)&& w4!=null) {drawRotatedTexture(woodT4, w4, 100, 20);}

        if(collisionBodies.contains(g1)) {drawRotatedTexture(glassT1, g1, 120, 20);}
        if(collisionBodies.contains(g2)) {drawRotatedTexture(glassT2, g2, 20, 80);}
        if(collisionBodies.contains(g3)) {drawRotatedTexture(glassT3, g3, 20, 80);}
        if(collisionBodies.contains(g4)) {drawRotatedTexture(glassT4, g4, 120, 20);}
        if(collisionBodies.contains(pigBody1)){drawRotatedTexture(pig1, pigBody1, 60, 60);}
        if(collisionBodies.contains(pigBody3)){drawRotatedTexture(pig3, pigBody3, 60, 60);}

        batch.end();
//        debugRenderer.render(worldDef, camera.combined);
        stage.act(delta);
        stage.draw();
    }

    private void destroyAllBodies() {

        Array<Body> bodies = new Array<Body>();
        worldDef.getBodies(bodies);
        for(Body b:bodies){
            worldDef.destroyBody(b);
        }

    }

    public void update(float delta){
        worldDef.step(1 / 60f, 6, 12);

        cameraUpdate(delta);
        batch.setProjectionMatrix(camera.combined);

    }

    private void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = 400;
        position.y = 250;
        camera.position.set(position);
        camera.update();
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
        worldDef.dispose();
        backgroundTexture.dispose();
        catapult.dispose();
        red.dispose();
        stage.dispose();
    }
}

