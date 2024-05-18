package com.github.exformatgames.defender.systems.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.game_components.DeathComponent;
import com.github.exformatgames.defender.components.game_components.HPComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class DeathSystem extends IteratingSystem {

    public DeathSystem() {
        super(Family.all(HPComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent hpComponent = HPComponent.getComponent(entity);

        if (hpComponent.HP < 0) {
            EntityBuilder.addComponent(entity, DeathComponent.class);
        }
    }
}
