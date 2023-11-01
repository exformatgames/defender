package com.github.exformatgames.defender.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.*;
import com.github.exformatgames.defender.Core;

public class SoundComponent implements Component {
	public Sound sound = null;
	public long ID = 0;
	public float volume = 1;
	public boolean isPlaying = false;
	public boolean isLooping = false;
	public boolean play = true;
	public float pan = 0; //-1 -- 1
	public float mul = 1; //0-1

	public SoundComponent init(String name) {
		return init(Core.ASSETS.getSound(name), 1, true, 0);
	}

	public SoundComponent init(String name, float volume) {
		return init(Core.ASSETS.getSound(name), volume, true, 0);
	}

	public SoundComponent init(String name, float volume, boolean play, float pan) {
		return init(Core.ASSETS.getSound(name), volume, play, pan);
	}

	public SoundComponent init(Sound sound, float volume, boolean play, float pan) {
		this.sound = sound;
		this.volume = volume;
		this.play = play;
		this.pan = pan;

		return this;
	}

	private final static ComponentMapper<SoundComponent> mapper = ComponentMapper.getFor(SoundComponent.class);

	public static SoundComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
