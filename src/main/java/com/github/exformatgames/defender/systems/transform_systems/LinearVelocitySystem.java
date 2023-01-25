package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.systems.*;
import com.badlogic.ashley.core.*;
import com.github.exformatgames.defender.components.transform_components.LinearVelocityComponent;
import com.github.exformatgames.defender.components.transform_components.TranslateComponent;

public class LinearVelocitySystem extends IteratingSystem {

    public LinearVelocitySystem() {
        super(Family.all(LinearVelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.getComponent(entity);
        TranslateComponent translateComponent = TranslateComponent.getComponent(entity);

        if (translateComponent == null) {
            translateComponent = getEngine().createComponent(TranslateComponent.class);
            translateComponent.x = linearVelocityComponent.value.x * dt;
            translateComponent.y = linearVelocityComponent.value.y * dt;

            entity.add(translateComponent);
        } else {
            translateComponent.x += linearVelocityComponent.value.x * dt;
            translateComponent.y += linearVelocityComponent.value.y * dt;
        }
    }
}
