package com.github.exformatgames.defender.systems.game_systems.bullet;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.game_components.BulletComponent;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BulletRemoveSystem extends IteratingSystem {

    public BulletRemoveSystem() {
        super(Family.one(BulletComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CullingComponent cullingComponent = CullingComponent.getComponent(entity);

        if ( ! cullingComponent.inViewport) {
            EntityBuilder.addComponent(entity, RemoveEntityComponent.class);
        }
    }
}
