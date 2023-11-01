package com.github.exformatgames.defender.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.*;
import com.github.exformatgames.defender.Core;

public class MusicComponent implements Component {

	public Music music = null;

	public boolean remove = false;
	public boolean pause = false;
	public boolean stop = false;
	public boolean play = false;

	public float volume = 0;

	public MusicComponent init(String name, boolean play, float volume) {
		return init(Core.ASSETS.getMusic(name), play, volume);
	}

	public MusicComponent init(String name, float volume) {
		return init(Core.ASSETS.getMusic(name), true, volume);
	}

	public MusicComponent init(String name) {
		return init(Core.ASSETS.getMusic(name), true, 1);
	}

	public MusicComponent init(Music music) {
		return init(music, true, 1);
	}

	public MusicComponent init(Music music, float volume) {
		return init(music, true, volume);
	}

	public MusicComponent init(Music music, boolean play, float volume) {
		this.music = music;
		this.play = play;
		this.volume = volume;

		return this;
	}

	private static final ComponentMapper<MusicComponent> mapper = ComponentMapper.getFor(MusicComponent.class);

	public static MusicComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
