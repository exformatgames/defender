package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.utils.*;
import com.github.exformatgames.defender.components.box2d.WorldComponent;

public class UpdateWorldSystem extends IteratingSystem {

	public static float FRAME_TIME = 0;
	private long startTime = 0;

	public UpdateWorldSystem(){
		super(Family.all(WorldComponent.class).get());
	}

	@Override
	public void startProcessing() {
		startTime = TimeUtils.nanoTime();
	}

	@Override
	public void endProcessing() {
		FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
	}



	@Override
	protected void processEntity(Entity entity, float dt) {
		WorldComponent worldComponent = WorldComponent.getComponent(entity);

		worldComponent.world.step(dt * worldComponent.deltaTimeScalar, worldComponent.velocityIteration, worldComponent.positionIteration);
	}
}


