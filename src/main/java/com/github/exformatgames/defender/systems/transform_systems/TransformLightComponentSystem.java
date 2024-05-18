package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.transform_components.TransformLightComponent;

public class TransformLightComponentSystem extends IteratingSystem {

    public TransformLightComponentSystem() {
        super(Family.all(LightComponent.class, TransformLightComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        LightComponent lightComponent = LightComponent.getComponent(entity);
        TransformLightComponent transform = TransformLightComponent.getComponent(entity);

        lightComponent.position.set(transform.position);
        lightComponent.dir = transform.dir;

        lightComponent.light.setPosition(transform.position.x, transform.position.y);
        lightComponent.light.setDirection(transform.dir);

        entity.remove(TransformLightComponent.class);
    }
}
