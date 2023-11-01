package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
	
	private AssetManager assetManager;
	private BitmapFonts bitmapFonts;
	private Musics musics;
	private Sounds sounds;
	private TextureAtlas textureAtlas;

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setBitmapFonts(BitmapFonts bitmapFonts) {
		this.bitmapFonts = bitmapFonts;
	}

	public BitmapFonts getBitmapFonts() {
		return bitmapFonts;
	}
	
	public void setMusics(Musics musics) {
		this.musics = musics;
	}

	public Musics getMusics() {
		return musics;
	}

	public void setSounds(Sounds sounds) {
		this.sounds = sounds;
	}

	public Sounds getSounds() {
		return sounds;
	}

	public void setTextureAtlas(TextureAtlas textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	//====================
	public TextureRegion getRegion(String name) {
		return textureAtlas.findRegion(name);
	}
	public Music getMusic(String name) {
		return musics.get(name);
	}
	public Sound getSound(String name) {
		return sounds.get(name);
	}
	public BitmapFont getBitmapFont(String name) {
		return bitmapFonts.get(name);
	}
}
