package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.WorldComponent;

public class UpdateWorldSystem extends IteratingSystem {

    private float timer = 0;

	public UpdateWorldSystem(){
		super(Family.all(WorldComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
        timer += dt;

        if (timer >= Configurations.BOX2D_TIME_STEP) {
            timer = 0;
            WorldComponent worldComponent = WorldComponent.getComponent(entity);
            worldComponent.world.step(Configurations.BOX2D_TIME_STEP, worldComponent.velocityIteration, worldComponent.positionIteration);
        }
	}
}


