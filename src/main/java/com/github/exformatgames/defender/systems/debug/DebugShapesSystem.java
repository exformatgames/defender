package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.exformatgames.defender.components.math_components.CircleComponent;

public class DebugShapesSystem extends IteratingSystem {

    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;

    public DebugShapesSystem(OrthographicCamera camera, ShapeRenderer shapeRenderer) {
        super(Family.one(CircleComponent.class).get());

        this.camera = camera;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void update(float deltaTime) {

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);

        super.update(deltaTime);

        shapeRenderer.end();

    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        CircleComponent circleComponent = CircleComponent.getComponent(entity);
        if (circleComponent != null) {
            shapeRenderer.circle(circleComponent.circle.x, circleComponent.circle.y, circleComponent.circle.radius, 25);
        }
    }
}
