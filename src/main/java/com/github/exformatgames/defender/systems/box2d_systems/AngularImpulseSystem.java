package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.AngularImpulseComponent;

public class AngularImpulseSystem extends IteratingSystem {

    public AngularImpulseSystem() {
        super(Family.one(AngularImpulseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        AngularImpulseComponent angularImpulseComponent = AngularImpulseComponent.getComponent(entity);
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        bodyComponent.body.applyAngularImpulse(angularImpulseComponent.impulse, true);

        entity.remove(AngularImpulseComponent.class);
    }
}
