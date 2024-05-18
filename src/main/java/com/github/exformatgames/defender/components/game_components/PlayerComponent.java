package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class PlayerComponent implements Component {

    public float lastStepTime = 0;

    private final static ComponentMapper<PlayerComponent> mapper = ComponentMapper.getFor(PlayerComponent.class);

    public static PlayerComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
