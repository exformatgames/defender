package com.github.exformatgames.defender.systems.util_system.actions;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.util_components.actions.StartActionComponent;
import com.github.exformatgames.defender.components.util_components.actions.TimeActionComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class TimeActionSystem extends IteratingSystem {

    public TimeActionSystem() {
        super(Family.all(TimeActionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TimeActionComponent actionComponent = TimeActionComponent.getComponent(entity);
        actionComponent.timer -= deltaTime;

        if (actionComponent.timer < 0) {
            EntityBuilder.addComponent(entity, StartActionComponent.class);
            entity.remove(TimeActionComponent.class);
        }
    }
}
