package com.github.exformatgames.defender.systems.audio_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.audio_components.PointSoundComponent;
import com.github.exformatgames.defender.components.audio_components.SoundComponent;

public class SoundSystem extends IteratingSystem {

	public SoundSystem() {
		super(Family.all(SoundComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
		SoundComponent component = SoundComponent.getComponent(entity);
		PointSoundComponent pointSoundComponent = PointSoundComponent.getComponent(entity);

		if (component.play) {
			component.ID = component.sound.play(component.volume * Configurations.GLOBAL_SOUND_VOLUME);

			component.sound.setPan(component.ID, component.pan, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
			component.sound.setLooping(component.ID, component.isLooping);

			if ( ! component.isLooping) {
				entity.remove(SoundComponent.class);
				if (pointSoundComponent == null) {

				}
			}
            else {
                component.play = false;
            }
		} else {
			component.sound.setVolume(component.ID, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
			component.sound.setPan(component.ID, component.pan, component.volume * Configurations.GLOBAL_SOUND_VOLUME);
		}
	}
}
