package com.github.exformatgames.defender;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.assets.Assets;
import com.github.exformatgames.defender.assets.LoadingAssets;

public class LoadingScreen implements Screen {

	protected Game game;
	private Screen secondScreen;

	private Viewport viewport;
	protected SpriteBatch spriteBatch;
	protected ShapeRenderer shapeRenderer;

	private Texture backgroundTexture;
	
	private LoadingAssets loadingAssets;

	protected float progressLoading = 0;

	public LoadingScreen(Game game, Screen secondScreen) {
		this.game = game;
		this.secondScreen = secondScreen;
	}
	
	public LoadingScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport.apply(true);

		backgroundTexture = new Texture("loading_background.png");

		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		loadingAssets = new LoadingAssets();
	}


	@Override
	public void render(float dt) {
		ScreenUtils.clear(0,0,0,0);

		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

		spriteBatch.begin();
		renderBackgroundTexture(dt);
		renderProgressBar(dt, progressLoading);
		spriteBatch.end();

		progressLoading = loadingAssets.update();
		if (loadingAssets.isLoadingEnd()) {
			endLoading();
		}
	}

	protected void renderBackgroundTexture(float dt) {
		float wh = Math.max(viewport.getWorldWidth(), viewport.getWorldHeight());

		float x = viewport.getWorldWidth() / 2 - wh / 2;
		float y = viewport.getWorldHeight() / 2 - wh / 2;

		spriteBatch.draw(backgroundTexture, x, y, wh, wh);
	}

	protected void renderProgressBar(float dt, float progress) {
		shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
		shapeRenderer.setColor(Color.GOLDENROD);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(0, viewport.getWorldHeight() / 100, viewport.getWorldWidth() * progress, viewport.getWorldHeight() / 100);
		shapeRenderer.end();
	}

	protected void endLoading() {
		if (secondScreen != null)
			game.setScreen(secondScreen);
	}


	@Override
	public void resize(int w, int h) {
		viewport.update(w, h, true);
		viewport.apply(true);
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
	public void dispose() {
	}

	public Assets getAssets() {
		return loadingAssets.getAssets();
	}
}
