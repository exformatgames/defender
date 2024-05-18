package com.github.exformatgames.defender.systems.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.game_components.DamageComponent;
import com.github.exformatgames.defender.components.game_components.HPComponent;

public class DamageSystem extends IteratingSystem {

    public DamageSystem() {
        super(Family.all(DamageComponent.class, HPComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent hpComponent = HPComponent.getComponent(entity);
        DamageComponent damageComponent = DamageComponent.getComponent(entity);

        hpComponent.HP -= damageComponent.damage;
        entity.remove(DamageComponent.class);
    }
}
