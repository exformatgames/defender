package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

/**
 * 2023
 * @author exformat
 */

public class ScaleLoopComponent implements Component, Pool.Poolable {

    public float minX = 0;
    public float maxX = 2;
    public float minY = 0;
    public float maxY = 2;

    public float frequencyX = 10;
    public float frequencyY = 10;

    public float timerX = 0;
    public float timerY = 0;

    @Override
    public void reset() {
        minX = 0;
        maxX = 2;
        minY = 0;
        maxY = 2;
        frequencyX = 10;
        frequencyY = 10;
        timerX = 0;
        timerY = 0;
    }

    /**
     *
     * scalars: 0 - max Float value
     * frequencies: 0 - max Float value
     * @param minXScale minimal scalar for sprite width. SpriteComponent.width * minXScale
     * @param maxXScale maximal scalar for sprite width. SpriteComponent.width * maxXScale
     * @param minYScale minimal scalar for sprite height. SpriteComponent.height * minYScale
     * @param maxYScale maximal scalar for sprite height. SpriteComponent.height * maxYScale
     * @param frequencyX sprite width transformation frequency per second.
     * @param frequencyY sprite height transformation frequency per second.
     */
    public void init(float minXScale, float maxXScale, float minYScale, float maxYScale, float frequencyX, float frequencyY) {
        this.minX = minXScale;
        this.maxX = maxXScale;
        this.minY = minYScale;
        this.maxY = maxYScale;
        this.frequencyX = frequencyX;
        this.frequencyY = frequencyY;
    }

    private final static ComponentMapper<ScaleLoopComponent> mapper = ComponentMapper.getFor(ScaleLoopComponent.class);

    public static ScaleLoopComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
