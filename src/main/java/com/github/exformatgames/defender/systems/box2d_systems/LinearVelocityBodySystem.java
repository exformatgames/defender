package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearVelocityBodyComponent;

public class LinearVelocityBodySystem extends IteratingSystem {

    public LinearVelocityBodySystem() {
        super(Family.all(LinearVelocityBodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);
        LinearVelocityBodyComponent velocityComponent = LinearVelocityBodyComponent.getComponent(entity);

        bodyComponent.body.setLinearVelocity(velocityComponent.velocity);

        entity.remove(LinearVelocityBodyComponent.class);
    }
}
