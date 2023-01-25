package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;

public class CullingSystem extends IteratingSystem {

    private final OrthographicCamera camera;

    private final Rectangle cameraBounds;

    public CullingSystem(OrthographicCamera camera) {
        super(Family.all(CullingComponent.class).get());
        this.camera = camera;

        cameraBounds = new Rectangle();
        cameraBounds.x = camera.position.x;
        cameraBounds.y = camera.position.y;
        cameraBounds.width = camera.viewportWidth;
        cameraBounds.height = camera.viewportHeight;
    }

    @Override
    public void update(float deltaTime) {
        float viewportHalfWidth = camera.viewportWidth / 2;
        float viewportHalfHeight = camera.viewportHeight / 2;

        cameraBounds.x = camera.position.x - viewportHalfWidth;
        cameraBounds.y = camera.position.y - viewportHalfHeight;
        cameraBounds.width = camera.viewportWidth;
        cameraBounds.height = camera.viewportHeight;

        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        CullingComponent cullingComponent = CullingComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);

        cullingComponent.inViewport = cameraBounds.overlaps(spriteComponent.getBoundingRectangle());
    }
}
