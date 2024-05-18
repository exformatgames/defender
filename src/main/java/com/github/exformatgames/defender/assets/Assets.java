package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Assets {

	private AssetManager assetManager;
	private BitmapFonts bitmapFonts;
	private Musics musics;
	private Sounds sounds;
	private TextureAtlas textureAtlas;

    private Skin skin;


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
    public TextureRegion getRegion(String name, int index) {
        return textureAtlas.findRegion(name, index);
    }
    public Array<TextureAtlas.AtlasRegion> getRegions(String name) {
        return textureAtlas.findRegions(name);
    }

    public Animation<TextureRegion> getAnimation(String name, float frameDuration) {
        return new Animation<TextureRegion>(frameDuration, getRegions(name));
    }

    public Animation<TextureRegion> getAnimation(String name, float frameDuration, Animation.PlayMode playMode) {
        return new Animation<TextureRegion>(frameDuration, getRegions(name), playMode);
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

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void dispose() {
        assetManager.dispose();
        bitmapFonts.dispose();
        musics.dispose();
        sounds.dispose();
        textureAtlas.dispose();
    }
}
