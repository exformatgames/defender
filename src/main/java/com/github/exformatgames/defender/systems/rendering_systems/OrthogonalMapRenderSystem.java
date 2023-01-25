package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.exformatgames.defender.components.rendering_components.OrthoMapRenderComponent;

public class OrthogonalMapRenderSystem extends IteratingSystem {

    private final OrthographicCamera camera;

    public OrthogonalMapRenderSystem(OrthographicCamera camera) {
        super(Family.one(OrthoMapRenderComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        OrthoMapRenderComponent mapRenderComponent = OrthoMapRenderComponent.getComponent(entity);

        mapRenderComponent.mapRenderer.setView(camera);
        mapRenderComponent.mapRenderer.render();
    }
}
