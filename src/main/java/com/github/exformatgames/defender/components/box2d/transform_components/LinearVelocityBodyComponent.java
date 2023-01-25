package com.github.exformatgames.defender.components.box2d.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class LinearVelocityBodyComponent implements Component {

    public float x = 0;
    public float y = 0;
    public Vector2 velocity = new Vector2();

    public void init(float x, float y ) {
        this.x = x;
        this.y = y;
        velocity.set(x, y);
    }

    private final static ComponentMapper<LinearVelocityBodyComponent> mapper = ComponentMapper.getFor(LinearVelocityBodyComponent.class);

    public static LinearVelocityBodyComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
