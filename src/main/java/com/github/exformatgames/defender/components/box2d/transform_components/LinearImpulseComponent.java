package com.github.exformatgames.defender.components.box2d.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class LinearImpulseComponent implements Component {

    public float impulseX = 0;
    public float impulseY = 0;

    public float pointX = 0;
    public float pointY = 0;

    public void init(float impulseX, float impulseY, float pointX, float pointY) {
        this.impulseX = impulseX;
        this.impulseY = impulseY;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public void init(float impulseX, float impulseY) {
        this.impulseX = impulseX;
        this.impulseY = impulseY;
    }

    private final static ComponentMapper<LinearImpulseComponent> mapper = ComponentMapper.getFor(LinearImpulseComponent.class);

    public static LinearImpulseComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
