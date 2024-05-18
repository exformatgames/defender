package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class FireComponent implements Component {


    private final static ComponentMapper<FireComponent> mapper = ComponentMapper.getFor(FireComponent.class);
    public static FireComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
