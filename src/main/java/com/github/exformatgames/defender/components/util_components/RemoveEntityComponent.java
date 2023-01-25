package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class RemoveEntityComponent implements Component {

    public float timer = -1;

    private final static ComponentMapper<RemoveEntityComponent> mapper = ComponentMapper.getFor(RemoveEntityComponent.class);

    public static RemoveEntityComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
