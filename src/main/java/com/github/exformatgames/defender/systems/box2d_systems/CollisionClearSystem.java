package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.EndContactComponent;

public class CollisionClearSystem extends IteratingSystem {

    public CollisionClearSystem() {
        super(Family.one(BeginContactComponent.class, EndContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        BeginContactComponent beginContactComponent = BeginContactComponent.getComponent(entity);
        EndContactComponent endContactComponent = EndContactComponent.getComponent(entity);

        if (beginContactComponent != null) {
            entity.remove(BeginContactComponent.class);
        }
        if (endContactComponent != null) {
            entity.remove(EndContactComponent.class);
        }
    }
}
