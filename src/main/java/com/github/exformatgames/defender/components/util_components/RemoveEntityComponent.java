package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class RemoveEntityComponent implements Component, Pool.Poolable {

    public float timer = -1;

    private final static ComponentMapper<RemoveEntityComponent> mapper = ComponentMapper.getFor(RemoveEntityComponent.class);

    public static RemoveEntityComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

    @Override
    public void reset() {
        timer = -1;
    }
}
