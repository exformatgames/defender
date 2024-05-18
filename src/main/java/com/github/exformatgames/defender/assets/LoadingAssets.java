package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingAssets {

	private final String DEFAULT_MUSIC_PATH = "audio/musics/";
	private final String DEFAULT_SOUNDS_PATH = "audio/sounds/";
	private final String DEFAULT_PARTICLES_PATH = "graphics/particles/";
	private final String DEFAULT_SHADERS_PATH = "graphics/shaders/";
	private final String DEFAULT_TEXTURES_PATH = "graphics/textures/";
	private final String DEFAULT_TEXTURE_ATLAS_PATH = "graphics/texture_atlas/";
	private final String DEFAULT_BITMAP_FONT_PATH = "graphics/bitmap_fonts/";

	private final Assets assets;

	private final AssetManager assetManager;
	private final BitmapFonts bitmapFonts;
	private final Musics musics;
	private final Sounds sounds;
	private TextureAtlas textureAtlas;

	private boolean loadingInProcess = false;
	private boolean loadingEnd = false;


	public LoadingAssets() {
		assetManager = new AssetManager();
		assets = new Assets();
		musics = new Musics();
		sounds = new Sounds();
		bitmapFonts = new BitmapFonts();
	}

	public void loadAll() {
		loadingInProcess = true;
		loadBitmapFonts();
		loadMaps();
		loadMusics();
		loadSounds();
		loadParticles();
		loadShaders();
		loadTextures();
		loadTextureAtlas();
	}

	public float update() {
		if (loadingInProcess) {
			assetManager.update();
			if (assetManager.isFinished() && ! loadingEnd) {
				initBitmapFonts();
				initTextureAtlas();
				texturesIntoAtlas();
				initSounds();
				initMusic();

				assets.setBitmapFonts(bitmapFonts);
				assets.setAssetManager(assetManager);
				assets.setMusics(musics);
				assets.setSounds(sounds);
				assets.setTextureAtlas(textureAtlas);

				loadingEnd = true;
				loadingInProcess = false;

				return 1;
			}

			return assetManager.getProgress();
		}

		if ( ! loadingInProcess && ! loadingEnd) {
			loadAll();
		}

		return 0;
	}


	private void initBitmapFonts() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_BITMAP_FONT_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				if (file.name().substring(file.name().indexOf('.'), file.name().length()).equals(".fnt")) {
					String path = file.path();
					String name = file.name().substring(0, file.name().indexOf('.'));
					bitmapFonts.add(assetManager.get(path, BitmapFont.class), name);
				}
			}
		}
	}

	private void texturesIntoAtlas() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_TEXTURES_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				String path = file.path();
				String name = file.name().substring(0, file.name().indexOf('.'));

				TextureRegion textureRegion = new TextureRegion(assetManager.get(path, Texture.class));

				textureAtlas.addRegion(name, textureRegion);
			}
		}
	}

	private void initTextureAtlas() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_TEXTURE_ATLAS_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				String path = file.path();
				String name = file.name();
				String type = name.substring(name.indexOf('.'), name.length());

				if (type.equals(".atlas")) {
					textureAtlas = assetManager.get(path, TextureAtlas.class);
				}
			}
		}

		if (textureAtlas == null) {
			textureAtlas = new TextureAtlas();
		}
	}

	private void initSounds(){
		FileHandle[] handle = Gdx.files.internal(DEFAULT_SOUNDS_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				String path = file.path();
                String name = file.name().substring(0, file.name().indexOf('.'));

                sounds.add(assetManager.get(path, Sound.class), name);
			}
		}
	}

	private void initMusic(){
		FileHandle[] handle = Gdx.files.internal(DEFAULT_MUSIC_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				String path = file.path();
				String name = file.name().substring(0, file.name().indexOf('.'));

				musics.add(assetManager.get(path, Music.class), name);
			}
		}
	}

	private void loadBitmapFonts() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_BITMAP_FONT_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				if (file.name().substring(file.name().indexOf('.'), file.name().length()).equals(".fnt")) {
					assetManager.load(file.path(), BitmapFont.class);
				}
			}
		}
	}

	private void loadTextures() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_TEXTURES_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				assetManager.load(file.path(), Texture.class);
			}
		}
	}

	public void loadMusics() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_MUSIC_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				assetManager.load(file.path(), Music.class);
			}
		}
	}

	public void loadSounds() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_SOUNDS_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
				assetManager.load(file.path(), Sound.class);
			}
		}
	}

	private void loadParticles() {
	}

	private void loadShaders() {
	}

	private void loadMaps() {
	}

	private void loadTextureAtlas() {
		FileHandle[] handle = Gdx.files.internal(DEFAULT_TEXTURE_ATLAS_PATH).list();

		for (FileHandle file: handle) {
			if (! file.isDirectory()) {
                if (file.name().substring(file.name().indexOf('.'), file.name().length()).equals(".atlas")) {
                    assetManager.load(file.path(), TextureAtlas.class);
                }
			}
		}
	}

	public boolean isLoadingEnd() {
		return loadingEnd;
	}

	public Assets getAssets() {
		return assets;
	}

	public Musics getMusics() {
		return musics;
	}

	public Sounds getSounds() {
		return sounds;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
}
