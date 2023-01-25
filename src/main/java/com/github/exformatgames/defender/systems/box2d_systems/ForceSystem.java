package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.ForceComponent;

public class ForceSystem extends IteratingSystem {

    public ForceSystem() {
        super(Family.all(ForceComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        ForceComponent forceComponent = ForceComponent.getComponent(entity);
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        if (forceComponent.center) {
            bodyComponent.body.applyForceToCenter(forceComponent.force, true);
        } else {
            bodyComponent.body.applyForce(forceComponent.force, forceComponent.point, true);
        }

        entity.remove(ForceComponent.class);
    }
}
