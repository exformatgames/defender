package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.TransformBodyComponent;

public class TransformBodySystem extends IteratingSystem {

    public TransformBodySystem() {
        super(Family.all(TransformBodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        TransformBodyComponent transform = TransformBodyComponent.getComponent(entity);
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        bodyComponent.body.setTransform(transform.position, transform.angle);
        bodyComponent.body.setAngularVelocity(transform.angularVelocity);
        bodyComponent.body.setLinearVelocity(transform.linearVelocity);
        bodyComponent.body.setAwake(transform.awake);

        entity.remove(TransformBodyComponent.class);
    }
}
