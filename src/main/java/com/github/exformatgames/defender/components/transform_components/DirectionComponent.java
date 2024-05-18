package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class DirectionComponent implements Component {

    private final Vector2 direction = new Vector2(1, 0);

    public Vector2 get() {
        return direction;
    }

    public void set(Vector2 direction) {
        this.direction.set(direction);
    }

    public void set(float x, float y) {
        this.direction.set(x, y);
    }

    private final static ComponentMapper<DirectionComponent> mapper = ComponentMapper.getFor(DirectionComponent.class);

    public static DirectionComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
