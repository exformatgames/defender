package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent implements Component, Pool.Poolable {
    public float x = 0;
    public float y = 0;

    public PositionComponent init(float x, float y){
        this.x = x;
        this.y = y;

        return this;
    }

    private final static ComponentMapper<PositionComponent> mapper = ComponentMapper.getFor(PositionComponent.class);

    public static PositionComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
