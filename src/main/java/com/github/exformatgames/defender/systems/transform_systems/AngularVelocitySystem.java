package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.transform_components.AngularVelocityComponent;
import com.github.exformatgames.defender.components.transform_components.RotateComponent;

public class AngularVelocitySystem extends IteratingSystem {

    public AngularVelocitySystem() {
        super(Family.all(AngularVelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        AngularVelocityComponent angularVelocityComponent = AngularVelocityComponent.getComponent(entity);
        RotateComponent rotateComponent = RotateComponent.getComponent(entity);

        if (rotateComponent == null) {
            rotateComponent = getEngine().createComponent(RotateComponent.class);
            rotateComponent.degres = angularVelocityComponent.value * dt;
            entity.add(rotateComponent);
        } else {
            rotateComponent.degres += angularVelocityComponent.value * dt;
        }
    }
}
