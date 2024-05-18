package com.github.exformatgames.defender.systems.game_systems.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearImpulseComponent;
import com.github.exformatgames.defender.components.game_components.state.JumpComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class JumpSystem extends IteratingSystem {

    public JumpSystem() {
        super(Family.all(JumpComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        JumpComponent jumpComponent = JumpComponent.getComponent(entity);

        EntityBuilder.addComponent(entity, LinearImpulseComponent.class).init(jumpComponent.xImpulse, jumpComponent.yImpulse);

        entity.remove(JumpComponent.class);
    }
}
