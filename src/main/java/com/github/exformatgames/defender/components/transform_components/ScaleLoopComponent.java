package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class ScaleLoopComponent implements Component {

    public float minX = 0;
    public float maxX = 2;
    public float minY = 0;
    public float maxY = 2;

    public float frequencyX = 10;
    public float frequencyY = 10;

    public float timerX = 0;
    public float timerY = 0;



    public void init(float minX, float maxX, float minY, float maxY, float frequencyX, float frequencyY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.frequencyX = frequencyX;
        this.frequencyY = frequencyY;
    }

    private final static ComponentMapper<ScaleLoopComponent> mapper = ComponentMapper.getFor(ScaleLoopComponent.class);

    public static ScaleLoopComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
