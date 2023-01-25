package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.github.exformatgames.defender.components.transform_components.ScaleComponent;
import com.github.exformatgames.defender.components.transform_components.ScaleLoopComponent;

public class ScaleLoopSystem extends IteratingSystem {

    public ScaleLoopSystem() {
        super(Family.all(ScaleLoopComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScaleLoopComponent scaleLoopComponent = ScaleLoopComponent.getComponent(entity);
        ScaleComponent scaleComponent = ScaleComponent.getComponent(entity);

        scaleLoopComponent.timerX += deltaTime;
        scaleLoopComponent.timerY += deltaTime;

        float sinX = MathUtils.sin(scaleLoopComponent.timerX * scaleLoopComponent.frequencyX) + 3.14f / 2;
        float sinY = MathUtils.sin(scaleLoopComponent.timerY * scaleLoopComponent.frequencyY) + 3.14f / 2;

        if (scaleComponent == null) {
            scaleComponent = getEngine().createComponent(ScaleComponent.class);
            entity.add(scaleComponent);
        }

        scaleComponent.toX = MathUtils.map(0, 3.14f, scaleLoopComponent.minX, scaleLoopComponent.maxX, sinX);
        scaleComponent.toY = MathUtils.map(0, 3.14f, scaleLoopComponent.minY, scaleLoopComponent.maxY, sinY);
    }
}
