package com.github.exformatgames.defender.components.audio_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.github.exformatgames.defender.Core;

public class SoundComponent implements Component, Pool.Poolable {
	public Sound sound = null;
	public long ID = 0;
	public float volume = 1;
	public boolean isLooping = false;
	public boolean play = true;
	public float pan = 0; //-1 -- 1
	public float mul = 1; //0-1

	public SoundComponent init(String name) {
		return init(Core.ASSETS.getSound(name), 1, true, 0);
	}

	public SoundComponent init(String name, float volume, boolean loop) {
        isLooping = loop;
		return init(Core.ASSETS.getSound(name), volume, true, 0);
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

    @Override
    public void reset() {
        this.sound = null;
        this.volume = 1;
        this.play = true;
        this.pan = 0;
        isLooping = false;
    }

    private final static ComponentMapper<SoundComponent> mapper = ComponentMapper.getFor(SoundComponent.class);

	public static SoundComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
