package com.devcharles.piazzapanic.scene2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.devcharles.piazzapanic.GameScreen;
import com.devcharles.piazzapanic.PiazzaPanic;

public class Tutorial extends ApplicationAdapter implements Screen {

    OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Batch batch;
    private Sprite sprite;
    private String[] completeTutorial = {"bucket.png", "v2/GameScreen.jpg", "droplet.png"};
    private Integer newPageNumber;
    private Integer rightButtonOn = 1, leftButtonOn = 1;
    private TextButton leftRecipeButton, rightRecipeButton;




    public Tutorial(final Game game, final Integer currentPage) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal(completeTutorial[currentPage])));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.top();
        root.setFillParent(true);
        stage.addActor(root);


        TextButton exitButtonToGame = new TextButton("Exit the tutorial to go to the game", skin);
        root.add(exitButtonToGame).width(240).height(40).expandX().left();
        exitButtonToGame.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen((PiazzaPanic) game,2));
                dispose();
            }
        });

        root.row();

        //Begin layout
        if(currentPage == 0){
            leftButtonOn = 0;
        }
        else if(completeTutorial.length - 1 == currentPage){
            rightButtonOn = 0;
        }

        if(leftButtonOn == 1){
            TextButton leftRecipeButton = new TextButton("Page Left", skin);
            root.add(leftRecipeButton).width(140).height(60).expandX().left();
            //Checks if button is clicked
            leftRecipeButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    newPageNumber = currentPage - 1;
                    game.setScreen(new Tutorial(game, newPageNumber));
                    dispose();
                }
            });
        }
        if(rightButtonOn == 1){
            TextButton rightRecipeButton = new TextButton("Page Right", skin);
            root.add(rightRecipeButton).width(140).height(60).expandX().right();
            //Checks if button is clicked
            rightRecipeButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    newPageNumber = currentPage + 1;

                    game.setScreen(new Tutorial(game, newPageNumber));
                    dispose();
                }
            });
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose () {
        skin.dispose();
        stage.dispose();
    }
}

