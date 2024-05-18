package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.util_components.CreateEntityComponent;

public class CreateEntitySystem extends IteratingSystem {

    public CreateEntitySystem() {
        super(Family.all(CreateEntityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CreateEntityComponent component = CreateEntityComponent.getComponent(entity);

        component.timer += deltaTime;

        if (component.count > 0 || component.infinityCount) {
            if (component.timer > component.interval) {

                component.timer = 0;
                component.count--;

                if ( ! component.position.isZero()) {
                    component.entityBuilder.create(component.position);
                }
                else {
                    component.entityBuilder.create();
                }
            }
        } else {
            getEngine().removeEntity(entity);
        }
    }
}
