package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class AnchorComponent implements Component, Pool.Poolable {

    public Side side = Side.BOTTOM;

    private final static ComponentMapper<AnchorComponent> mapper = ComponentMapper.getFor(AnchorComponent.class);
    public static AnchorComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

    @Override
    public void reset() {
        side = Side.BOTTOM;
    }

    public enum Side {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM;
    }
}
