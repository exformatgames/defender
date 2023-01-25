package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class AABBQueryComponent implements Component {

    public float x = 0;
    public float y = 0;
    public float x2 = 0;
    public float y2 = 0;

    public void init(float x, float y, float x2, float y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    private final static ComponentMapper<AABBQueryComponent> mapper = ComponentMapper.getFor(AABBQueryComponent.class);

    public static AABBQueryComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
