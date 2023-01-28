package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearImpulseComponent;

public class LinearImpulseSystem extends IteratingSystem {

    public LinearImpulseSystem() {
        super(Family.one(LinearImpulseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LinearImpulseComponent impulseComponent = LinearImpulseComponent.getComponent(entity);
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        bodyComponent.body.applyLinearImpulse(impulseComponent.impulseX, impulseComponent.impulseY, impulseComponent.pointX, impulseComponent.pointY, true);

        entity.remove(LinearImpulseComponent.class);
    }
}
