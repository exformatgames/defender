package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class EnemyComponent implements Component {

    public String name = "";
    public float contactDamage = 1;

    private final static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);

    public static EnemyComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
