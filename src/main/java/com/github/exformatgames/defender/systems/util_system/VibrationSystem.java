package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.github.exformatgames.defender.components.platform_components.VibrationComponent;

public class VibrationSystem extends IteratingSystem {

	public VibrationSystem(){
		super(Family.all(VibrationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		VibrationComponent vibrationComponent = VibrationComponent.getComponent(entity);
		Gdx.input.vibrate(vibrationComponent.millis);
		entity.remove(VibrationComponent.class);
	}
}
