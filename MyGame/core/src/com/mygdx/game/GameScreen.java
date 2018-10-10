package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

	final MyGame game;
	OrthographicCamera camera;
	Texture dropImage;
	Texture bucketImage;
	Vector3 position;
	Texture sunImage;
	Texture planetImage;
	Texture planet2Image;
	Texture moonImage;
	Texture moon2Image;
	Body body;
	Sun sun;
	Planets planet;
	Planets planet2;
	Moon moon;
	Moon moon2;

	private Stage stage;
	private String title;
	private boolean rotation;

	public GameScreen (final MyGame gam) {
		this.game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		sunImage = new Texture("sun.png");
		planetImage = new Texture("planet1.png");
		planet2Image = new Texture("planet2.png");
		moonImage = new Texture("moon.png");
		moon2Image = new Texture("moon2.png");

		position = new Vector3();
		position.x = 800 / 2 - 64 / 2;
		position.y = 480 / 2 - 64 / 2;
        rotation = true;

		sun = new Sun(sunImage, position);
		body = new Body(position);
		planet = new Planets(body, planetImage,200, 5);
		planet2 = new Planets(body, planet2Image,120, 3);
		moon = new Moon(planet, moonImage,65, 10);
		moon2 = new Moon(planet2, moon2Image,60, 13);

		createButton();
	}

	public void createButton() {

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("images.pack"));
		skin.addRegions(buttonAtlas);
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = game.font;
		textButtonStyle.up = skin.getDrawable("button-up");
		textButtonStyle.down = skin.getDrawable("button-down");
		textButtonStyle.checked = skin.getDrawable("button-up");

		title = "Stop";

		final TextButton play = new TextButton(title, textButtonStyle);
		play.setPosition(0, 0);
		play.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				play.setChecked(!play.isChecked());
				if (title == "Stop")
					title = "Play";
				else
					title = "Stop";
				play.setText(title);

				rotation = !rotation;

				return true;
			};
		});

		stage.addActor(play);
	}

	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(sun.bImage, sun.bPos.x, sun.bPos.y);
		game.batch.draw(planet.bImage, planet.bPos.x, planet.bPos.y);
		game.batch.draw(planet2.bImage, planet2.bPos.x, planet2.bPos.y);
		game.batch.draw(moon.bImage, moon.bPos.x, moon.bPos.y);
		game.batch.draw(moon2.bImage, moon2.bPos.x, moon2.bPos.y);
		game.batch.end();

		if (rotation) {
            planet.updatePos();
            planet2.updatePos();
			moon.updatePos();
			moon2.updatePos();
        }
	}

	@Override
	public void resize(int width, int height) {

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

	@Override
	public void dispose () {
		stage.dispose();
		dropImage.dispose();
		bucketImage.dispose();
		body.dispose();
		sun.dispose();
		planet.dispose();
		planet2.dispose();
		moon.dispose();
		moon2.dispose();
	}
}
