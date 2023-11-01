package com.github.exformatgames.defender.systems.audio_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.audio_components.SoundComponent;

public class SoundSystem extends IteratingSystem {

	public SoundSystem() {
		super(Family.all(SoundComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
		SoundComponent component = SoundComponent.getComponent(entity);

		if (component.play) {
			component.isPlaying = true;
			component.play = false;

			component.ID = component.sound.play(component.volume * Configurations.GLOBAL_SOUND_VOLUME);
			
			component.sound.setPan(component.ID, component.pan, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
			component.sound.setLooping(component.ID, component.isLooping);
		} else {
			component.sound.setVolume(component.ID, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
			component.sound.setPan(component.ID, component.pan, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
		}
	}
}
