package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.box2d.AngularImpulseComponent;
import com.github.exformatgames.defender.components.box2d.BodyComponent;

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
