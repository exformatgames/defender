package com.github.exformatgames.defender.systems.audio_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
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

			component.ID = component.sound.play(component.volume);
			
			component.sound.setPan(component.ID, component.pan, component.volume);
			component.sound.setLooping(component.ID, component.isLooping);
		} else {
			component.sound.setVolume(component.ID, component.volume);
			component.sound.setPan(component.ID, component.pan, component.volume);
		}
	}
}
